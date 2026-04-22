package com.sena.creyese.dentvision_spring.modelo;

import com.sena.creyese.dentvision_spring.enums.Estado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Entidad que representa un técnico dental en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "TecnicoDental" en la base de datos y contiene la información
 * personal y profesional de los técnicos dentales que apoyan en los procedimientos
 * de laboratorio y fabricación de prótesis. Incluye datos de identificación,
 * especialidad y relación con el usuario del sistema.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Información personal completa del técnico dental
 * - Especialidad y área de expertise
 * - Validaciones de integridad de datos
 * - Relación uno a uno con entidad Usuario
 * - Mapeo JPA completo con restricciones
 */
@Entity
@Table(name = "TecnicoDental")
public class TecnicoDental {
    
    /** Identificador único del técnico dental (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTecnico;

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

    /** Apellidos del técnico dental */
    @NotBlank(message = "Es obligatorio diligenciar apellidos")
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String apellidos;

    /** Nombres del técnico dental */
    @NotBlank(message = "Es obligatorio diligenciar nombres")
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombres;

    /** Teléfono de contacto del técnico dental */
    @NotBlank(message = "Es obligatorio diligenciar telefono")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String telefono;

    /** Género del técnico dental */
    @NotBlank(message = "Es obligatorio diligenciar genero")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String genero;

    /** Especialidad del técnico dental */
    @NotBlank(message = "Es obligatorio diligenciar la especialidad")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String especialidad;

    /** Fecha de nacimiento del técnico dental */
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fecha_nacimiento;

    /** Estado del técnico dental (true=activo, false=inactivo) */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    /** Años de experiencia del técnico dental */
    @Column(nullable = false)
    private int experienciaYears;

    /** Disponibilidad del técnico dental (true=disponible, false=no disponible) */
    @Column(nullable = false)
    private boolean disponibilidad;

    /** Especialización del técnico dental */
    @Column(length = 100)
    private String especializacion;

    /** Número de certificado del técnico dental */
    @Column(length = 50)
    private String numeroCertificado;

    /** Usuario asociado al técnico dental */
    @OneToOne
    @JoinColumn(name ="idUsuario")
    private Usuario usuario;

    /**
     * Obtiene el identificador único del técnico dental.
     * 
     * @return ID del técnico dental
     */
    public Long getIdTecnico() {
        return idTecnico;
    }

    /**
     * Establece el identificador único del técnico dental.
     * 
     * @param idTecnico ID del técnico dental a establecer
     */
    public void setIdTecnico(Long idTecnico) {
        this.idTecnico = idTecnico;
    }

    /**
     * Obtiene el tipo de documento del técnico dental.
     * 
     * @return Tipo de documento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Establece el tipo de documento del técnico dental.
     * 
     * @param tipoDocumento Tipo de documento a establecer
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Obtiene el número de documento del técnico dental.
     * 
     * @return Número de documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el número de documento del técnico dental.
     * 
     * @param documento Número de documento a establecer
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene los apellidos del técnico dental.
     * 
     * @return Apellidos del técnico dental
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del técnico dental.
     * 
     * @param apellidos Apellidos a establecer
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene los nombres del técnico dental.
     * 
     * @return Nombres del técnico dental
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del técnico dental.
     * 
     * @param nombres Nombres a establecer
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el teléfono del técnico dental.
     * 
     * @return Teléfono de contacto
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del técnico dental.
     * 
     * @param telefono Teléfono a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el género del técnico dental.
     * 
     * @return Género
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del técnico dental.
     * 
     * @param genero Género a establecer
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la especialidad del técnico dental.
     * 
     * @return Especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad del técnico dental.
     * 
     * @param especialidad Especialidad a establecer
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene la fecha de nacimiento del técnico dental.
     * 
     * @return Fecha de nacimiento
     */
    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Establece la fecha de nacimiento del técnico dental.
     * 
     * @param fecha_nacimiento Fecha de nacimiento a establecer
     */
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * Obtiene el estado del técnico dental.
     * 
     * @return true si está activo, false si está inactivo
     */
    public Estado isEstado() {
        return estado;
    }

    /**
     * Establece el estado del técnico dental.
     * 
     * @param estado Estado a establecer
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene los años de experiencia del técnico dental.
     * 
     * @return Años de experiencia
     */
    public int getExperienciaYears() {
        return experienciaYears;
    }

    /**
     * Establece los años de experiencia del técnico dental.
     * 
     * @param experienciaYears Años de experiencia a establecer
     */
    public void setExperienciaYears(int experienciaYears) {
        this.experienciaYears = experienciaYears;
    }

    /**
     * Obtiene la disponibilidad del técnico dental.
     * 
     * @return true si está disponible, false si no
     */
    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    /**
     * Establece la disponibilidad del técnico dental.
     * 
     * @param disponibilidad Disponibilidad a establecer
     */
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * Obtiene la especialización del técnico dental.
     * 
     * @return Especialización del técnico dental
     */
    public String getEspecializacion() {
        return especializacion;
    }

    /**
     * Establece la especialización del técnico dental.
     * 
     * @param especializacion Especialización a establecer
     */
    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    /**
     * Obtiene el número de certificado del técnico dental.
     * 
     * @return Número de certificado
     */
    public String getNumeroCertificado() {
        return numeroCertificado;
    }

    /**
     * Establece el número de certificado del técnico dental.
     * 
     * @param numeroCertificado Número de certificado a establecer
     */
    public void setNumeroCertificado(String numeroCertificado) {
        this.numeroCertificado = numeroCertificado;
    }

    /**
     * Obtiene el usuario asociado al técnico dental.
     * 
     * @return Usuario relacionado
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al técnico dental.
     * 
     * @param usuario Usuario a establecer
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
