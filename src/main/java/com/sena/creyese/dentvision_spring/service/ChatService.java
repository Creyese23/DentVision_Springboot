package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Chat;
import com.sena.creyese.dentvision_spring.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Chat> listarTodos() {
        return chatRepository.findAll();
    }

    public Optional<Chat> obtenerPorId(Long id) {
        return chatRepository.findById(id);
    }

    public Chat guardar(Chat chat) {
        return chatRepository.save(chat);
    }

    public void eliminar(Long id) {
        chatRepository.deleteById(id);
    }
}
