package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Roles;
import com.sena.creyese.dentvision_spring.service.RolService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public List<Roles> listarTodos() {
        return rolService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> obtenerPorId(@PathVariable Long id) {
        return rolService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Roles> crear(@Valid @RequestBody Roles rol) {
        Roles nuevoRol = rolService.guardar(rol);
        return ResponseEntity.ok(nuevoRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Roles> actualizar(@PathVariable Long id, @Valid @RequestBody Roles rol) {
        return rolService.obtenerPorId(id)
                .map(rolExistente -> {
                    rolExistente.setNombreRol(rol.getNombreRol());
                    Roles actualizado = rolService.guardar(rolExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (rolService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        rolService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
