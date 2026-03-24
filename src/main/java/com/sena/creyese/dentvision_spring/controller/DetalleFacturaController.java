package com.sena.creyese.dentvision_spring.controller;


import com.sena.creyese.dentvision_spring.modelo.DetalleFactura;
import com.sena.creyese.dentvision_spring.service.DetalleFacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle_factura")
public class DetalleFacturaController {

    @Autowired
    private final DetalleFacturaService detalleFacturaService;

    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping
    public List<DetalleFactura> listarTodos() {
        return detalleFacturaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> obtenerPorId(@PathVariable Long id) {
        return detalleFacturaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleFactura> crear(@Valid @RequestBody DetalleFactura detalleFactura) {
        DetalleFactura nuevo = detalleFacturaService.guardar(detalleFactura);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> actualizar(@PathVariable Long id, @Valid @RequestBody DetalleFactura detalleFactura) {
        return detalleFacturaService.obtenerPorId(id)
                .map(existente -> {
                    existente.setCantidad(detalleFactura.getCantidad());
                    existente.setPrecio(detalleFactura.getPrecio());
                    existente.setFactura(detalleFactura.getFactura());
                    existente.setServicios(detalleFactura.getServicios());
                    DetalleFactura actualizado = detalleFacturaService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (detalleFacturaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        detalleFacturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
