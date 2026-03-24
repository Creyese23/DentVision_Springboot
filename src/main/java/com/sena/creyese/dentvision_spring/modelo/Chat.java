package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChat;


    @Column(nullable = false)
    private LocalDateTime fechaChat;

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public LocalDateTime getFechaChat() {
        return fechaChat;
    }

    public void setFechaChat(LocalDateTime fechaChat) {
        this.fechaChat = fechaChat;
    }
}
