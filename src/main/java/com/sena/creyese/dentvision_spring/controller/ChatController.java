package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Chat;
import com.sena.creyese.dentvision_spring.modelo.Usuario;
import com.sena.creyese.dentvision_spring.service.ChatService;
import com.sena.creyese.dentvision_spring.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;
    private final UsuarioService usuarioService;

    public ChatController(ChatService chatService, UsuarioService usuarioService) {
        this.chatService = chatService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Chat> listarTodos() {
        return chatService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> obtenerPorId(@PathVariable Long id) {
        return chatService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Chat> crear(@Valid @RequestBody Chat chat) {
        Chat nuevo = chatService.guardar(chat);
        return ResponseEntity.ok(nuevo);
    }

    @PostMapping("/enviar")
    public ResponseEntity<Chat> enviarMensaje(@RequestBody Map<String, Object> payload) {
        Long idRemitente = Long.valueOf(payload.get("idRemitente").toString());
        Long idDestinatario = Long.valueOf(payload.get("idDestinatario").toString());
        String mensaje = payload.get("mensaje").toString();

        Usuario remitente = usuarioService.obtenerPorId(idRemitente)
                .orElseThrow(() -> new RuntimeException("Remitente no encontrado"));
        Usuario destinatario = usuarioService.obtenerPorId(idDestinatario)
                .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));

        Chat nuevoMensaje = chatService.enviarMensaje(remitente, destinatario, mensaje);
        return ResponseEntity.ok(nuevoMensaje);
    }

    @GetMapping("/usuario/{idUsuario}/enviados")
    public ResponseEntity<List<Chat>> obtenerMensajesEnviados(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.obtenerPorId(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Chat> mensajes = chatService.obtenerMensajesEnviadosPorUsuario(usuario);
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/usuario/{idUsuario}/recibidos")
    public ResponseEntity<List<Chat>> obtenerMensajesRecibidos(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.obtenerPorId(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Chat> mensajes = chatService.obtenerMensajesRecibidosPorUsuario(usuario);
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/usuario/{idUsuario}/no-leidos")
    public ResponseEntity<List<Chat>> obtenerMensajesNoLeidos(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.obtenerPorId(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Chat> mensajes = chatService.obtenerMensajesNoLeidos(usuario);
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/conversacion/{idUsuario1}/{idUsuario2}")
    public ResponseEntity<List<Chat>> obtenerConversacion(@PathVariable Long idUsuario1, @PathVariable Long idUsuario2) {
        Usuario usuario1 = usuarioService.obtenerPorId(idUsuario1)
                .orElseThrow(() -> new RuntimeException("Usuario 1 no encontrado"));
        Usuario usuario2 = usuarioService.obtenerPorId(idUsuario2)
                .orElseThrow(() -> new RuntimeException("Usuario 2 no encontrado"));
        List<Chat> conversacion = chatService.obtenerConversacionEntreUsuarios(usuario1, usuario2);
        return ResponseEntity.ok(conversacion);
    }

    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Chat>> obtenerMensajesPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<Chat> mensajes = chatService.obtenerMensajesPorRangoDeFechas(inicio, fin);
        return ResponseEntity.ok(mensajes);
    }

    @PutMapping("/{id}/marcar-leido")
    public ResponseEntity<Void> marcarComoLeido(@PathVariable Long id) {
        chatService.marcarMensajeComoLeido(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chat> actualizar(@PathVariable Long id, @Valid @RequestBody Chat chat) {
        return chatService.obtenerPorId(id)
                .map(existente -> {
                    existente.setMensaje(chat.getMensaje());
                    existente.setLeido(chat.isLeido());
                    Chat actualizado = chatService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (chatService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        chatService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
