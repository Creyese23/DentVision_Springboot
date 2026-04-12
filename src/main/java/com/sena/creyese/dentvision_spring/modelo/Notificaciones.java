package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Entidad que representa una notificación en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "notificaciones" en la base de datos y contiene la información
 * sobre notificaciones enviadas a usuarios, incluyendo el mensaje, estado de lectura,
 * fecha y hora de creación, y relación con el usuario destinatario. Facilita la gestión
 * de alertas y comunicaciones importantes del sistema.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Características de la entidad:
 * - Gestión de notificaciones del sistema
 * - Control de estado de lectura
 * - Timestamps precisos con LocalDateTime
 * - Relaciones con usuarios destinatarios
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "notificaciones")
public class Notificaciones {
    
    /** Identificador único de la notificación (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificaciones;

    /** Contenido del mensaje de notificación */
    @Column(nullable = false, length = 255)
    private String mensaje;

    /** Estado de lectura de la notificación */
    @Column(nullable = false)
    private boolean leido;

    /** Fecha y hora de creación de la notificación */
    @Column(nullable = false)
    private LocalDateTime fechaNotificacion;

    /** Usuario destinatario de la notificación */
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    /**
     * Obtiene el identificador único de la notificación.
     * 
     * @return ID de la notificación
     */
    public Long getIdNotificaciones() {
        return idNotificaciones;
    }

    /**
     * Establece el identificador único de la notificación.
     * 
     * @param idNotificaciones ID de la notificación a establecer
     */
    public void setIdNotificaciones(Long idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }

    /**
     * Obtiene el mensaje de la notificación.
     * 
     * @return Contenido del mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje de la notificación.
     * 
     * @param mensaje Mensaje a establecer
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el estado de lectura de la notificación.
     * 
     * @return true si está leída, false si no está leída
     */
    public boolean isLeido() {
        return leido;
    }

    /**
     * Establece el estado de lectura de la notificación.
     * 
     * @param leido Estado a establecer (true: leída, false: no leída)
     */
    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    /**
     * Obtiene la fecha y hora de la notificación.
     * 
     * @return Fecha y hora de creación
     */
    public LocalDateTime getFechaNotificacion() {
        return fechaNotificacion;
    }

    /**
     * Establece la fecha y hora de la notificación.
     * 
     * @param fechaNotificacion Fecha y hora a establecer
     */
    public void setFechaNotificacion(LocalDateTime fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    /**
     * Obtiene el usuario destinatario.
     * 
     * @return Usuario relacionado
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario destinatario.
     * 
     * @param usuario Usuario a establecer
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
