package com.sena.creyese.dentvision_spring.modelo;

import com.sena.creyese.dentvision_spring.enums.Estado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Entidad que representa un administrador en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "Admin" en la base de datos y contiene la información
 * personal y profesional de los administradores del sistema. Cada administrador
 * está asociado a un usuario para autenticación y acceso al sistema.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Identificación única mediante documento
 * - Datos personales básicos
 * - Relación uno a uno con entidad Usuario
 * - Validaciones de integridad de datos
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "Admin")
public class Admin {
    
    /** Identificador único del administrador (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdmin;

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

    /** Número de teléfono de contacto */
    @NotBlank(message = "Es obligatorio diligenciar telefono")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String telefono;

    /** Dirección de residencia */
    @NotBlank(message = "Es obligatorio diligenciar la direccion")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String direccion;

    /** Nombres del administrador */
    @NotBlank(message = "Es obligatorio diligenciar los nombres")
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombres;

    /** Apellidos del administrador */
    @NotBlank(message = "Es obligatorio diligenciar los apellidos")
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String apellidos;

    /** Fecha de nacimiento del administrador */
    @Column(nullable = false)
    private LocalDate fecha_nacimiento;

    /** Nivel de acceso del administrador */
    @NotBlank(message = "Es obligatorio diligenciar el nivel de acceso")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nivelAcceso;

    /** Departamento al que pertenece el administrador */
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(length = 100)
    private String departamento;

    /** Estado del administrador (activo/inactivo) */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    /** Usuario asociado para autenticación y acceso al sistema */
    @OneToOne
    @JoinColumn(name ="idUsuario")
    private Usuario usuario;

    /**
     * Obtiene el identificador único del administrador.
     * 
     * @return ID del administrador
     */
    public Long getIdAdmin() {
        return idAdmin;
    }

    /**
     * Establece el identificador único del administrador.
     * 
     * @param idAdmin ID del administrador a establecer
     */
    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    /**
     * Obtiene el tipo de documento del administrador.
     * 
     * @return Tipo de documento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Establece el tipo de documento del administrador.
     * 
     * @param tipoDocumento Tipo de documento a establecer
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Obtiene el número de documento del administrador.
     * 
     * @return Número de documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el número de documento del administrador.
     * 
     * @param documento Número de documento a establecer
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el número de teléfono del administrador.
     * 
     * @return Número de teléfono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del administrador.
     * 
     * @param telefono Número de teléfono a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la dirección del administrador.
     * 
     * @return Dirección
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del administrador.
     * 
     * @param direccion Dirección a establecer
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene los nombres del administrador.
     * 
     * @return Nombres del administrador
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del administrador.
     * 
     * @param nombres Nombres a establecer
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del administrador.
     * 
     * @return Apellidos del administrador
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del administrador.
     * 
     * @param apellidos Apellidos a establecer
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene la fecha de nacimiento del administrador.
     * 
     * @return Fecha de nacimiento
     */
    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Establece la fecha de nacimiento del administrador.
     * 
     * @param fecha_nacimiento Fecha de nacimiento a establecer
     */
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * Obtiene el nivel de acceso del administrador.
     * 
     * @return Nivel de acceso
     */
    public String getNivelAcceso() {
        return nivelAcceso;
    }

    /**
     * Establece el nivel de acceso del administrador.
     * 
     * @param nivelAcceso Nivel de acceso a establecer
     */
    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    /**
     * Obtiene el departamento del administrador.
     * 
     * @return Departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Establece el departamento del administrador.
     * 
     * @param departamento Departamento a establecer
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Obtiene el estado del administrador.
     * 
     * @return true si está activo, false si está inactivo
     */
    public Estado isEstado() {
        return estado;
    }

    /**
     * Establece el estado del administrador.
     * 
     * @param estado Estado a establecer (true=activo, false=inactivo)
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el usuario asociado al administrador.
     * 
     * @return Usuario asociado
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al administrador.
     * 
     * @param usuario Usuario a establecer
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
