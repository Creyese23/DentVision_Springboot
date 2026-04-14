package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Entidad que representa un mensaje en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "mensajes" en la base de datos y contiene la información
 * sobre mensajes individuales enviados dentro de conversaciones, incluyendo el contenido
 * del mensaje, fecha y hora de envío, y relaciones con la conversación y el usuario
 * emisor. Facilita la comunicación interna y seguimiento de interacciones.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Gestión de mensajes individuales
 * - Timestamps precisos con LocalDateTime
 * - Relaciones con conversaciones y usuarios
 * - Historial completo de comunicaciones
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "mensajes")
public class Mensajes {
    
    /** Identificador único del mensaje (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;

    /** Contenido del mensaje */
    @Column(nullable = false)
    private String mensaje;

    /** Fecha y hora de envío del mensaje */
    @Column(nullable = false)
    private LocalDateTime fechaEnvio;

    /** Conversación a la que pertenece este mensaje */
    @ManyToOne
    @JoinColumn(name = "idChat")
    private Chat chat;

    /** Usuario que envió el mensaje */
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    /**
     * Obtiene el identificador único del mensaje.
     * 
     * @return ID del mensaje
     */
    public Long getIdMensaje() {
        return idMensaje;
    }

    /**
     * Establece el identificador único del mensaje.
     * 
     * @param idMensaje ID del mensaje a establecer
     */
    public void setIdMensaje(Long idMensaje) {
        this.idMensaje = idMensaje;
    }

    /**
     * Obtiene el contenido del mensaje.
     * 
     * @return Contenido del mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el contenido del mensaje.
     * 
     * @param mensaje Contenido a establecer
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene la fecha y hora de envío.
     * 
     * @return Fecha y hora de envío
     */
    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * Establece la fecha y hora de envío.
     * 
     * @param fechaEnvio Fecha y hora a establecer
     */
    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     * Obtiene la conversación asociada.
     * 
     * @return Conversación relacionada
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * Establece la conversación asociada.
     * 
     * @param chat Conversación a establecer
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }

    /**
     * Obtiene el usuario emisor.
     * 
     * @return Usuario que envió el mensaje
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario emisor.
     * 
     * @param usuario Usuario a establecer
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
