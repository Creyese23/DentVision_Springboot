package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Entidad que representa un odontólogo en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "Odontologo" en la base de datos y contiene la información
 * profesional y personal de los odontólogos del sistema. Cada odontólogo está asociado
 * a un usuario para autenticación y acceso al sistema, incluyendo su especialidad y
 * licencia profesional.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Características de la entidad:
 * - Identificación única mediante documento
 * - Información profesional (especialidad, licencia)
 * - Datos personales básicos
 * - Relación uno a uno con entidad Usuario
 * - Validaciones de integridad de datos
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "Odontologo")
public class Odontologo {
    
    /** Identificador único del odontólogo (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOdontologo;

    /** Tipo de documento de identificación (CC, TI, etc.) */
    @NotBlank(message = "Es obligatorio diligenciar tipo de documento")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String tipoDocumento;

    /** Número de documento de identificación (único) */
    @NotBlank(message = "Es obligatorio diligenciar el numero de documento")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(unique = true, nullable = false, length = 50)
    private String documento;

    /** Género del odontólogo */
    @NotBlank(message = "Es obligatorio diligenciar genero")
    @Size(max=20, message = "Maximo 20 caracteres")
    @Column(nullable = false, length = 20)
    private String genero;

    /** Número de teléfono de contacto */
    @NotBlank(message = "Es obligatorio diligenciar telefono")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String telefono;

    /** Dirección de residencia o consulta */
    @NotBlank(message = "Es obligatorio diligenciar direccion")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String direccion;

    /** Especialidad odontológica del profesional */
    @NotBlank(message = "Es obligatorio diligenciar la especialidad")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String especialidad;

    /** Número de licencia profesional */
    @NotBlank(message = "Es obligatorio diligenciar el numero de la licencia profesional")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String N_Licencia;

    /** Fecha de nacimiento del odontólogo */
    @Column(nullable = false)
    private Date fecha_nacimiento;

    /** Usuario asociado para autenticación y acceso al sistema */
    @OneToOne
    @JoinColumn(name ="idUsuario")
    private Usuario usuario;

    /**
     * Obtiene el identificador único del odontólogo.
     * 
     * @return ID del odontólogo
     */
    public Long getIdOdontologo() {
        return idOdontologo;
    }

    /**
     * Establece el identificador único del odontólogo.
     * 
     * @param idOdontologo ID del odontólogo a establecer
     */
    public void setIdOdontologo(Long idOdontologo) {
        this.idOdontologo = idOdontologo;
    }

    /**
     * Obtiene el tipo de documento del odontólogo.
     * 
     * @return Tipo de documento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Establece el tipo de documento del odontólogo.
     * 
     * @param tipoDocumento Tipo de documento a establecer
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Obtiene el número de documento del odontólogo.
     * 
     * @return Número de documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el número de documento del odontólogo.
     * 
     * @param documento Número de documento a establecer
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el género del odontólogo.
     * 
     * @return Género
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del odontólogo.
     * 
     * @param genero Género a establecer
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene el número de teléfono del odontólogo.
     * 
     * @return Número de teléfono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del odontólogo.
     * 
     * @param telefono Número de teléfono a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la dirección del odontólogo.
     * 
     * @return Dirección
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del odontólogo.
     * 
     * @param direccion Dirección a establecer
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la especialidad del odontólogo.
     * 
     * @return Especialidad odontológica
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad del odontólogo.
     * 
     * @param especialidad Especialidad a establecer
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene el número de licencia profesional del odontólogo.
     * 
     * @return Número de licencia profesional
     */
    public String getN_Licencia() {
        return N_Licencia;
    }

    /**
     * Establece el número de licencia profesional del odontólogo.
     * 
     * @param N_Licencia Número de licencia a establecer
     */
    public void setN_Licencia(String N_Licencia) {
        this.N_Licencia = N_Licencia;
    }

    /**
     * Obtiene la fecha de nacimiento del odontólogo.
     * 
     * @return Fecha de nacimiento
     */
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Establece la fecha de nacimiento del odontólogo.
     * 
     * @param fecha_nacimiento Fecha de nacimiento a establecer
     */
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * Obtiene el usuario asociado al odontólogo.
     * 
     * @return Usuario asociado
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al odontólogo.
     * 
     * @param usuario Usuario a establecer
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
