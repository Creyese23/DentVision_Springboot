package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Notificaciones;
import com.sena.creyese.dentvision_spring.service.NotificacionesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionesController {

    @Autowired
    private final NotificacionesService notificacionesService;

    public NotificacionesController(NotificacionesService notificacionesService) {
        this.notificacionesService = notificacionesService;
    }

    @GetMapping
    public List<Notificaciones> listarTodos() {
        return notificacionesService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificaciones> obtenerPorId(@PathVariable Long id) {
        return notificacionesService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Notificaciones> crear(@Valid @RequestBody Notificaciones notificaciones) {
        Notificaciones nueva = notificacionesService.guardar(notificaciones);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificaciones> actualizar(@PathVariable Long id, @Valid @RequestBody Notificaciones notificaciones) {
        return notificacionesService.obtenerPorId(id)
                .map(existente -> {
                    existente.setMensaje(notificaciones.getMensaje());
                    existente.setLeido(notificaciones.isLeido());
                    existente.setFechaNotificacion(notificaciones.getFechaNotificacion());
                    existente.setUsuario(notificaciones.getUsuario());
                    Notificaciones actualizada = notificacionesService.guardar(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (notificacionesService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        notificacionesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
