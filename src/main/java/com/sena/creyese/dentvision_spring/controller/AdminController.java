package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Admin;
import com.sena.creyese.dentvision_spring.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> listarTodos() {
        return adminService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> obtenerPorId(@PathVariable Long id) {
        return adminService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Admin> crear(@Valid @RequestBody Admin admin) {
        Admin nuevo = adminService.guardar(admin);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> actualizar(@PathVariable Long id, @Valid @RequestBody Admin admin) {
        return adminService.obtenerPorId(id)
                .map(existente -> {
                    existente.setTipoDocumento(admin.getTipoDocumento());
                    existente.setDocumento(admin.getDocumento());
                    existente.setTelefono(admin.getTelefono());
                    existente.setDireccion(admin.getDireccion());
                    existente.setFecha_nacimiento(admin.getFecha_nacimiento());
                    existente.setUsuario(admin.getUsuario());
                    Admin actualizado = adminService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (adminService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        adminService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
