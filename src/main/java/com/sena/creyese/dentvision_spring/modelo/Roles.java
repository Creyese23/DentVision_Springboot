package com.sena.creyese.dentvision_spring.modelo;

import com.sena.creyese.dentvision_spring.enums.TipoRol;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa un rol de usuario en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "Roles" en la base de datos y contiene la información
 * de los diferentes roles que pueden tener los usuarios en el sistema. Utiliza
 * el enum TipoRol para definir los roles disponibles y sus permisos asociados.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Definición de roles mediante enumeración tipada
 * - Control de acceso y permisos por rol
 * - Validación de integridad de datos
 * - Mapeo JPA completo
 * - Integración con sistema de seguridad
 */
@Entity
@Table(name = "Roles")
public class Roles {
    
    /** Identificador único del rol (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    /** Nombre del rol utilizando enumeración tipada para seguridad */
    @NotBlank(message = "Es obligatorio diligenciar el nombre del rol")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TipoRol nombreRol;

    /**
     * Obtiene el identificador único del rol.
     * 
     * @return ID del rol
     */
    public Long getIdRol() {
        return idRol;
    }

    /**
     * Establece el identificador único del rol.
     * 
     * @param idRol ID del rol a establecer
     */
    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    /**
     * Obtiene el nombre del rol.
     * 
     * @return Nombre del rol como enumeración TipoRol
     */
    public TipoRol getNombreRol() {
        return nombreRol;
    }

    /**
     * Establece el nombre del rol.
     * 
     * @param nombreRol Nombre del rol a establecer
     */
    public void setNombreRol(TipoRol nombreRol) {
        this.nombreRol = nombreRol;
    }
}
