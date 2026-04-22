package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * Entidad que representa un usuario en el sistema DentVision.
 * 
 * Esta clase es la entidad base para todos los usuarios del sistema,
 * incluyendo administradores, odontólogos, técnicos, auxiliares y pacientes.
 * Contiene la información básica de autenticación y perfil del usuario.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características principales:
 * - Autenticación con email y contraseña
 * - Validación de datos con Bean Validation
 * - Relación con roles de usuario
 * - Control de estado (activo/inactivo)
 * - Registro de fecha de creación
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    /** Identificador único del usuario (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    /** Nombres del usuario (obligatorio, máximo 100 caracteres) */
    @NotBlank(message = "Es obligatorio diligenciar los nombres")
    @Size(max=100, message = "Máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombres;

    /** Apellidos del usuario (obligatorio, máximo 100 caracteres) */
    @NotBlank(message = "Es obligatorio diligenciar los apellidos")
    @Size(max=100, message = "Máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String apellidos;

    /** Email del usuario (único, obligatorio, máximo 100 caracteres) */
    @NotBlank(message = "Es obligatorio diligenciar el email")
    @Size(max=100, message = "Máximo 100 caracteres")
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    /** Contraseña del usuario (obligatorio, máximo 100 caracteres) */
    @NotBlank(message = "Es obligatorio diligenciar la contraseña")
    @Size(min=8, message = "Máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String password;



    /** Estado del usuario (activo/inactivo) */
    @Column(nullable = false)
    private boolean estado;

    /** Fecha de registro del usuario (obligatoria) */
    @Column(nullable = false)
    private java.time.LocalDateTime fecha_registro;

    /** Rol asignado al usuario (relación muchos a uno) */
    @ManyToOne
    @JoinColumn(name ="idRol")
    private Roles roles;

    /**
     * Obtiene el identificador único del usuario.
     * 
     * @return ID del usuario
     */
    public Long getId() {
        return idUsuario;
    }

    /**
     * Establece el identificador único del usuario.
     * 
     * @param id ID del usuario a establecer
     */
    public void setId(Long id) {
        this.idUsuario = id;
    }

    /**
     * Obtiene los nombres del usuario.
     * 
     * @return Nombres del usuario
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del usuario.
     * 
     * @param nombres Nombres a establecer
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del usuario.
     * 
     * @return Apellidos del usuario
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del usuario.
     * 
     * @param apellidos Apellidos a establecer
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el email del usuario.
     * 
     * @return Email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     * 
     * @param email Email a establecer
     */
    public void setEmail(String email) {
        this.email = email;
    }



    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return Contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password Contraseña a establecer
     */
    public void setPassword(String password) {
        this.password = password;
    }




    /**
     * Obtiene el estado del usuario.
     * 
     * @return true si está activo, false si está inactivo
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * Establece el estado del usuario.
     * 
     * @param estado Estado a establecer (true=activo, false=inactivo)
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha de registro del usuario.
     * 
     * @return Fecha de registro
     */
    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    /**
     * Establece la fecha de registro del usuario.
     * 
     * @param fecha_registro Fecha de registro a establecer
     */
    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    /**
     * Obtiene el rol asignado al usuario.
     * 
     * @return Rol del usuario
     */
    public Roles getRol() {
        return roles;
    }

    /**
     * Establece el rol asignado al usuario.
     * 
     * @param roles Rol a establecer
     */
    public void setRol(Roles roles) {
        this.roles = roles;
    }

    /**
     * Método que se ejecuta automáticamente antes de persistir el usuario.
     * Establece la fecha de registro actual si no está definida.
     */
    @PrePersist
    public void prePersist() {
        if (this.fecha_registro == null) {
            this.fecha_registro = LocalDateTime.now();
        }
    }
}
