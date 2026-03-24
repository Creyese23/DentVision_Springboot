package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Insumo;
import com.sena.creyese.dentvision_spring.service.InsumoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
public class InsumoController {

    @Autowired
    private final InsumoService insumoService;

    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
    }

    @GetMapping
    public List<Insumo> listarTodos() {
        return insumoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insumo> obtenerPorId(@PathVariable Long id) {
        return insumoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Insumo> crear(@Valid @RequestBody Insumo insumo) {
        Insumo nuevo = insumoService.guardar(insumo);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insumo> actualizar(@PathVariable Long id, @Valid @RequestBody Insumo insumo) {
        return insumoService.obtenerPorId(id)
                .map(existente -> {
                    existente.setNombre(insumo.getNombre());
                    existente.setDescripcion(insumo.getDescripcion());
                    existente.setStock(insumo.getStock());
                    existente.setStockMinimo(insumo.getStockMinimo());
                    Insumo actualizado = insumoService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (insumoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        insumoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
