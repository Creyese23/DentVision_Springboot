package com.sena.creyese.dentvision_spring.controller;


import com.sena.creyese.dentvision_spring.modelo.Entrega;
import com.sena.creyese.dentvision_spring.service.EntregaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    @Autowired
    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    public List<Entrega> listarTodos() {
        return entregaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> obtenerPorId(@PathVariable Long id) {
        return entregaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Entrega> crear(@Valid @RequestBody Entrega entrega) {
        Entrega nueva = entregaService.guardar(entrega);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> actualizar(@PathVariable Long id, @Valid @RequestBody Entrega entrega) {
        return entregaService.obtenerPorId(id)
                .map(existente -> {
                    existente.setFecha(entrega.getFecha());
                    existente.setEstado(entrega.isEstado());
                    existente.setObservaciones(entrega.getObservaciones());
                    existente.setOrden(entrega.getOrden());
                    Entrega actualizada = entregaService.guardar(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (entregaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        entregaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
