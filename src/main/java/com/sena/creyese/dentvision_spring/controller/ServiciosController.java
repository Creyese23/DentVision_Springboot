package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Servicios;
import com.sena.creyese.dentvision_spring.service.ServiciosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServiciosController {

    @Autowired
    private final ServiciosService serviciosService;

    public ServiciosController(ServiciosService serviciosService) {
        this.serviciosService = serviciosService;
    }

    @GetMapping
    public List<Servicios> listarTodos() {
        return serviciosService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicios> obtenerPorId(@PathVariable Long id) {
        return serviciosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Servicios> crear(@Valid @RequestBody Servicios servicios) {
        Servicios nuevo = serviciosService.guardar(servicios);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicios> actualizar(@PathVariable Long id, @Valid @RequestBody Servicios servicios) {
        return serviciosService.obtenerPorId(id)
                .map(existente -> {
                    existente.setNombreServicios(servicios.getNombreServicios());
                    existente.setDescripcionServicios(servicios.getDescripcionServicios());
                    existente.setPrecioServicios(servicios.getPrecioServicios());
                    Servicios actualizado = serviciosService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (serviciosService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        serviciosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
