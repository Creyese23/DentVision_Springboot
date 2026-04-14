package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

/**
 * Entidad que representa un procedimiento dental en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "procedimientos" en la base de datos y contiene la información
 * sobre procedimientos dentales realizados, incluyendo descripción detallada, estado,
 * fecha de realización y relación con la cita correspondiente. Facilita el seguimiento
 * de tratamientos y gestión de procedimientos clínicos.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Gestión completa de procedimientos dentales
 * - Control de estado y fechas de realización
 * - Relaciones con citas específicas
 * - Descripción detallada de tratamientos
 * - Mapeo JPA completo con validaciones
 */
@Entity
@Table(name = "procedimientos")
public class Procedimiento {

    /** Identificador único del procedimiento (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcedimiento;

    /** Descripción detallada del procedimiento dental */
    @NotBlank(message = "Es obligatorio diligenciar la descripcion")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String descripcion;

    /** Estado del procedimiento (true: activo, false: inactivo/completado) */
    private boolean estado;

    /** Fecha de realización del procedimiento */
    private LocalDateTime fecha;

    /** Cita asociada a este procedimiento */
    @ManyToOne
    @JoinColumn(name ="idCita")
    private Cita cita;

    /**
     * Obtiene el identificador único del procedimiento.
     * 
     * @return ID del procedimiento
     */
    public Long getIdProcedimiento() {
        return idProcedimiento;
    }

    /**
     * Establece el identificador único del procedimiento.
     * 
     * @param idProcedimiento ID del procedimiento a establecer
     */
    public void setIdProcedimiento(Long idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    /**
     * Obtiene la descripción del procedimiento.
     * 
     * @return Descripción detallada
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del procedimiento.
     * 
     * @param descripcion Descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el estado del procedimiento.
     * 
     * @return true si está activo, false si está inactivo/completado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado del procedimiento.
     * 
     * @param estado Estado a establecer (true: activo, false: inactivo/completado)
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha del procedimiento.
     * 
     * @return Fecha de realización
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del procedimiento.
     * 
     * @param fecha Fecha a establecer
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la cita asociada.
     * 
     * @return Cita relacionada
     */
    public Cita getCita() {
        return cita;
    }

    /**
     * Establece la cita asociada.
     * 
     * @param cita Cita a establecer
     */
    public void setCita(Cita cita) {
        this.cita = cita;
    }
}
