package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Date;

/**
 * Entidad que representa un paciente en el sistema DentVision.
 * 
 * Esta clase contiene la información específica de los pacientes del sistema
 * dental, incluyendo datos demográficos, contacto e información médica básica.
 * Está relacionada con la entidad Usuario para la autenticación.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características principales:
 * - Información de identificación personal
 * - Datos de contacto
 * - Información demográfica
 * - Relación uno a uno con Usuario
 * - Validación de datos con Bean Validation
 */
@Entity
@Table(name = "Paciente")
public class Paciente {
    
    /** Identificador único del paciente (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    /** Tipo de documento del paciente (obligatorio, máximo 50 caracteres) */
    @NotBlank(message = "Es obligatorio diligenciar tipo de documento")
    @Size(max=50, message = "Máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String tipoDocumento;

    /** Número de documento del paciente (único, obligatorio, máximo 50 caracteres) */
    @NotBlank(message = "Es obligatorio diligenciar el número de documento")
    @Size(max=50, message = "Máximo 50 caracteres")
    @Column(unique = true, nullable = false, length = 50)
    private String documento;

    /** Teléfono de contacto del paciente (obligatorio, máximo 50 caracteres) */
    @NotBlank(message = "Es obligatorio diligenciar teléfono")
    @Size(max=50, message = "Máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String telefono;

    /** Dirección del paciente (obligatorio, máximo 50 caracteres) */
    @NotBlank(message = "Es obligatorio diligenciar dirección")
    @Size(max=50, message = "Máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String direccion;

    /** Género del paciente (máximo 50 caracteres) */
    @Column(nullable = false, length = 50)
    private String genero;

    /** Fecha de nacimiento del paciente (obligatoria) */
    @Column(nullable = false)
    private Date fecha_nacimiento;

    /** Usuario asociado al paciente (relación uno a uno) */
    @OneToOne
    @JoinColumn(name ="idUsuario")
    private Usuario usuario;

    /**
     * Obtiene el identificador único del paciente.
     * 
     * @return ID del paciente
     */
    public Long getIdPaciente() {
        return idPaciente;
    }

    /**
     * Establece el identificador único del paciente.
     * 
     * @param idPaciente ID del paciente a establecer
     */
    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * Obtiene el tipo de documento del paciente.
     * 
     * @return Tipo de documento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Establece el tipo de documento del paciente.
     * 
     * @param tipoDocumento Tipo de documento a establecer
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Obtiene el número de documento del paciente.
     * 
     * @return Número de documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el número de documento del paciente.
     * 
     * @param documento Número de documento a establecer
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el teléfono del paciente.
     * 
     * @return Teléfono de contacto
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del paciente.
     * 
     * @param telefono Teléfono a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la dirección del paciente.
     * 
     * @return Dirección del paciente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del paciente.
     * 
     * @param direccion Dirección a establecer
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el género del paciente.
     * 
     * @return Género del paciente
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del paciente.
     * 
     * @param genero Género a establecer
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la fecha de nacimiento del paciente.
     * 
     * @return Fecha de nacimiento
     */
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Establece la fecha de nacimiento del paciente.
     * 
     * @param fecha_nacimiento Fecha de nacimiento a establecer
     */
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * Obtiene el usuario asociado al paciente.
     * 
     * @return Usuario del paciente
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al paciente.
     * 
     * @param usuario Usuario a establecer
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
