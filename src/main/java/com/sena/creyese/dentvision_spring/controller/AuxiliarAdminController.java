package com.sena.creyese.dentvision_spring.controller;


import com.sena.creyese.dentvision_spring.modelo.AuxiliarAdmin;
import com.sena.creyese.dentvision_spring.service.AuxiliarAdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auxiliares-admin")
public class AuxiliarAdminController {

    private final AuxiliarAdminService auxiliarAdminService;

    public AuxiliarAdminController(AuxiliarAdminService auxiliarAdminService) {
        this.auxiliarAdminService = auxiliarAdminService;
    }

    @GetMapping
    public List<AuxiliarAdmin> listarTodos() {
        return auxiliarAdminService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuxiliarAdmin> obtenerPorId(@PathVariable Long id) {
        return auxiliarAdminService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuxiliarAdmin> crear(@Valid @RequestBody AuxiliarAdmin auxiliarAdmin) {
        AuxiliarAdmin nuevo = auxiliarAdminService.guardar(auxiliarAdmin);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuxiliarAdmin> actualizar(@PathVariable Long id, @Valid @RequestBody AuxiliarAdmin auxiliarAdmin) {
        return auxiliarAdminService.obtenerPorId(id)
                .map(existente -> {
                    existente.setTipoDocumento(auxiliarAdmin.getTipoDocumento());
                    existente.setDocumento(auxiliarAdmin.getDocumento());
                    existente.setTelefono(auxiliarAdmin.getTelefono());
                    existente.setDireccion(auxiliarAdmin.getDireccion());
                    existente.setGenero(auxiliarAdmin.getGenero());
                    existente.setFechaNacimiento(auxiliarAdmin.getFechaNacimiento());
                    existente.setUsuario(auxiliarAdmin.getUsuario());
                    AuxiliarAdmin actualizado = auxiliarAdminService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (auxiliarAdminService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        auxiliarAdminService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
