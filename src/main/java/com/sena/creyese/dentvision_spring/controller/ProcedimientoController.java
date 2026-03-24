package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Procedimiento;
import com.sena.creyese.dentvision_spring.service.ProcedimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedimientos")
public class ProcedimientoController {

    @Autowired
    private final ProcedimientoService procedimientoService;

    public ProcedimientoController(ProcedimientoService procedimientoService) {
        this.procedimientoService = procedimientoService;
    }

    @GetMapping
    public List<Procedimiento> listarTodos() {
        return procedimientoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Procedimiento> obtenerPorId(@PathVariable Long id) {
        return procedimientoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Procedimiento> crear(@Valid @RequestBody Procedimiento procedimiento) {
        Procedimiento nuevo = procedimientoService.guardar(procedimiento);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Procedimiento> actualizar(@PathVariable Long id, @Valid @RequestBody Procedimiento procedimiento) {
        return procedimientoService.obtenerPorId(id)
                .map(existente -> {
                    existente.setDescripcion(procedimiento.getDescripcion());
                    existente.setEstado(procedimiento.isEstado());
                    existente.setFecha(procedimiento.getFecha());
                    existente.setCita(procedimiento.getCita());
                    Procedimiento actualizado = procedimientoService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (procedimientoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        procedimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
