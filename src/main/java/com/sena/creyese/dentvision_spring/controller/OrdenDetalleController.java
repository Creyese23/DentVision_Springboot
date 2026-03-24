package com.sena.creyese.dentvision_spring.controller;


import com.sena.creyese.dentvision_spring.modelo.OrdenDetalle;
import com.sena.creyese.dentvision_spring.service.OrdenDetalleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden-detalles")
public class OrdenDetalleController {

    @Autowired
    private final OrdenDetalleService ordenDetalleService;

    public OrdenDetalleController(OrdenDetalleService ordenDetalleService) {
        this.ordenDetalleService = ordenDetalleService;
    }

    @GetMapping
    public List<OrdenDetalle> listarTodos() {
        return ordenDetalleService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDetalle> obtenerPorId(@PathVariable Long id) {
        return ordenDetalleService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrdenDetalle> crear(@Valid @RequestBody OrdenDetalle ordenDetalle) {
        OrdenDetalle nuevo = ordenDetalleService.guardar(ordenDetalle);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenDetalle> actualizar(@PathVariable Long id, @Valid @RequestBody OrdenDetalle ordenDetalle) {
        return ordenDetalleService.obtenerPorId(id)
                .map(existente -> {
                    existente.setDescripcion(ordenDetalle.getDescripcion());
                    existente.setCantidad(ordenDetalle.getCantidad());
                    existente.setOrden(ordenDetalle.getOrden());
                    OrdenDetalle actualizado = ordenDetalleService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (ordenDetalleService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ordenDetalleService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
