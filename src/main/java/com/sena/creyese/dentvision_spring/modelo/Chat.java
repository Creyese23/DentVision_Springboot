package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * Entidad que representa un mensaje de chat en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "chats" en la base de datos y contiene la información
 * de los mensajes intercambiados entre usuarios del sistema. Cada mensaje incluye
 * timestamp, contenido, remitente, destinatario y estado de lectura.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Sistema de mensajería interna
 * - Control de estado de lectura
 * - Relaciones bidireccionales con usuarios
 * - Timestamps precisos con LocalDateTime
 * - Validaciones de contenido
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "chats")
public class Chat {
    
    /** Identificador único del mensaje de chat (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChat;

    /** Fecha y hora exacta del mensaje */
    @Column(nullable = false)
    private LocalDateTime fechaChat;

    /** Contenido del mensaje */
    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max = 1000, message = "Máximo 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String mensaje;

    /** Usuario que envía el mensaje */
    @ManyToOne
    @JoinColumn(name = "idRemitente")
    private Usuario remitente;

    /** Usuario que recibe el mensaje */
    @ManyToOne
    @JoinColumn(name = "idDestinatario")
    private Usuario destinatario;

    /** Estado de lectura del mensaje (true=leído, false=no leído) */
    @Column(nullable = false)
    private boolean leido;

    /**
     * Obtiene el identificador único del mensaje.
     * 
     * @return ID del mensaje
     */
    public Long getIdChat() {
        return idChat;
    }

    /**
     * Establece el identificador único del mensaje.
     * 
     * @param idChat ID del mensaje a establecer
     */
    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    /**
     * Obtiene la fecha y hora del mensaje.
     * 
     * @return Fecha y hora del mensaje
     */
    public LocalDateTime getFechaChat() {
        return fechaChat;
    }

    /**
     * Establece la fecha y hora del mensaje.
     * 
     * @param fechaChat Fecha y hora a establecer
     */
    public void setFechaChat(LocalDateTime fechaChat) {
        this.fechaChat = fechaChat;
    }

    /**
     * Obtiene el contenido del mensaje.
     * 
     * @return Mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el contenido del mensaje.
     * 
     * @param mensaje Mensaje a establecer
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el usuario remitente del mensaje.
     * 
     * @return Usuario remitente
     */
    public Usuario getRemitente() {
        return remitente;
    }

    /**
     * Establece el usuario remitente del mensaje.
     * 
     * @param remitente Usuario remitente a establecer
     */
    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
    }

    /**
     * Obtiene el usuario destinatario del mensaje.
     * 
     * @return Usuario destinatario
     */
    public Usuario getDestinatario() {
        return destinatario;
    }

    /**
     * Establece el usuario destinatario del mensaje.
     * 
     * @param destinatario Usuario destinatario a establecer
     */
    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * Obtiene el estado de lectura del mensaje.
     * 
     * @return true si está leído, false si no está leído
     */
    public boolean isLeido() {
        return leido;
    }

    /**
     * Establece el estado de lectura del mensaje.
     * 
     * @param leido Estado a establecer (true=leído, false=no leído)
     */
    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}
