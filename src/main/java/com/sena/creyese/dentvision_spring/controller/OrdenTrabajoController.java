package com.sena.creyese.dentvision_spring.controller;


import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;
import com.sena.creyese.dentvision_spring.service.OrdenTrabajoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-trabajo")
public class OrdenTrabajoController {

    @Autowired
    private final OrdenTrabajoService ordenTrabajoService;

    public OrdenTrabajoController(OrdenTrabajoService ordenTrabajoService) {
        this.ordenTrabajoService = ordenTrabajoService;
    }

    @GetMapping
    public List<OrdenTrabajo> listarTodos() {
        return ordenTrabajoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> obtenerPorId(@PathVariable Long id) {
        return ordenTrabajoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrdenTrabajo> crear(@Valid @RequestBody OrdenTrabajo ordenTrabajo) {
        OrdenTrabajo nueva = ordenTrabajoService.guardar(ordenTrabajo);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> actualizar(@PathVariable Long id, @Valid @RequestBody OrdenTrabajo ordenTrabajo) {
        return ordenTrabajoService.obtenerPorId(id)
                .map(existente -> {
                    existente.setFecha(ordenTrabajo.getFecha());
                    existente.setEstado(ordenTrabajo.getEstado());
                    existente.setObservaciones(ordenTrabajo.getObservaciones());
                    existente.setPaciente(ordenTrabajo.getPaciente());
                    existente.setOdontologo(ordenTrabajo.getOdontologo());
                    existente.setTecnico(ordenTrabajo.getTecnico());
                    OrdenTrabajo actualizado = ordenTrabajoService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (ordenTrabajoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ordenTrabajoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
