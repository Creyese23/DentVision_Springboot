package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Chat;
import com.sena.creyese.dentvision_spring.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
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

    @PutMapping("/{id}")
    public ResponseEntity<Chat> actualizar(@PathVariable Long id, @Valid @RequestBody Chat chat) {
        return chatService.obtenerPorId(id)
                .map(existente -> {
                    existente.setFechaChat(chat.getFechaChat());
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
