package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Mensajes;
import com.sena.creyese.dentvision_spring.service.MensajesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
public class MensajesController {

    @Autowired
    private final MensajesService mensajesService;

    public MensajesController(MensajesService mensajesService) {
        this.mensajesService = mensajesService;
    }

    @GetMapping
    public List<Mensajes> listarTodos() {
        return mensajesService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensajes> obtenerPorId(@PathVariable Long id) {
        return mensajesService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mensajes> crear(@Valid @RequestBody Mensajes mensajes) {
        Mensajes nuevo = mensajesService.guardar(mensajes);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensajes> actualizar(@PathVariable Long id, @Valid @RequestBody Mensajes mensajes) {
        return mensajesService.obtenerPorId(id)
                .map(existente -> {
                    existente.setMensaje(mensajes.getMensaje());
                    existente.setFechaEnvio(mensajes.getFechaEnvio());
                    existente.setChat(mensajes.getChat());
                    existente.setUsuario(mensajes.getUsuario());
                    Mensajes actualizado = mensajesService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (mensajesService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mensajesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
