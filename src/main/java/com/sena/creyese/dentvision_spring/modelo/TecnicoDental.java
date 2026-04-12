package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Entidad que representa un técnico dental en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "TecnicoDental" en la base de datos y contiene la información
 * personal y profesional de los técnicos dentales que apoyan en los procedimientos
 * de laboratorio y fabricación de prótesis. Incluye datos de identificación,
 * especialidad y relación con el usuario del sistema.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
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
    private Date fecha_nacimiento;

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
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Establece la fecha de nacimiento del técnico dental.
     * 
     * @param fecha_nacimiento Fecha de nacimiento a establecer
     */
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
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
