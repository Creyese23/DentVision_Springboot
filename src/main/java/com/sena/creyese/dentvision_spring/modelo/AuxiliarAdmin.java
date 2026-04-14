package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Entidad que representa un auxiliar administrativo en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "AuxiliarAdmin" en la base de datos y contiene la información
 * personal y profesional de los auxiliares administrativos que apoyan en la gestión
 * de la clínica dental. Incluye datos de identificación, contacto y relación con el usuario.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Información personal completa del auxiliar administrativo
 * - Validaciones de integridad de datos
 * - Relación uno a uno con entidad Usuario
 * - Mapeo JPA completo con restricciones
 * - Datos de contacto y ubicación
 */
@Entity
@Table(name = "AuxiliarAdmin")
public class AuxiliarAdmin {
    
    /** Identificador único del auxiliar administrativo (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuxiliarAdmin;

    /** Tipo de documento de identificación */
    @NotBlank(message = "Es obligatorio diligenciar tipo de documento")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String tipoDocumento;

    /** Número de documento único e identificativo */
    @NotBlank(message = "Es obligatorio diligenciar el numero de documento")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(unique = true, nullable = false, length = 50)
    private String documento;

    /** Teléfono de contacto del auxiliar administrativo */
    @NotBlank(message = "Es obligatorio diligenciar telefono")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String telefono;

    /** Dirección de residencia del auxiliar administrativo */
    @NotBlank(message = "Es obligatorio diligenciar direccion")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String direccion;

    /** Género del auxiliar administrativo */
    @NotBlank(message = "Es obligatorio diligenciar genero")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String genero;

    /** Fecha de nacimiento del auxiliar administrativo */
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    /** Usuario asociado al auxiliar administrativo */
    @OneToOne
    @JoinColumn(name ="idUsuario")
    private Usuario usuario;

    /**
     * Obtiene el identificador único del auxiliar administrativo.
     * 
     * @return ID del auxiliar administrativo
     */
    public Long getIdAuxiliarAdmin() {
        return idAuxiliarAdmin;
    }

    /**
     * Establece el identificador único del auxiliar administrativo.
     * 
     * @param idAuxiliarAdmin ID del auxiliar administrativo a establecer
     */
    public void setIdAuxiliarAdmin(Long idAuxiliarAdmin) {
        this.idAuxiliarAdmin = idAuxiliarAdmin;
    }

    /**
     * Obtiene el tipo de documento del auxiliar administrativo.
     * 
     * @return Tipo de documento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Establece el tipo de documento del auxiliar administrativo.
     * 
     * @param tipoDocumento Tipo de documento a establecer
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Obtiene el número de documento del auxiliar administrativo.
     * 
     * @return Número de documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el número de documento del auxiliar administrativo.
     * 
     * @param documento Número de documento a establecer
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el teléfono del auxiliar administrativo.
     * 
     * @return Teléfono de contacto
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del auxiliar administrativo.
     * 
     * @param telefono Teléfono a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el género del auxiliar administrativo.
     * 
     * @return Género
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del auxiliar administrativo.
     * 
     * @param genero Género a establecer
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la dirección del auxiliar administrativo.
     * 
     * @return Dirección de residencia
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del auxiliar administrativo.
     * 
     * @param direccion Dirección a establecer
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la fecha de nacimiento del auxiliar administrativo.
     * 
     * @return Fecha de nacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del auxiliar administrativo.
     * 
     * @param fechaNacimiento Fecha de nacimiento a establecer
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el usuario asociado al auxiliar administrativo.
     * 
     * @return Usuario relacionado
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al auxiliar administrativo.
     * 
     * @param usuario Usuario a establecer
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
