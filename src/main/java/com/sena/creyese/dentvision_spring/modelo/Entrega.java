package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Entidad que representa una entrega de trabajo dental en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "entregas" en la base de datos y contiene la información
 * sobre la entrega de trabajos dentales, incluyendo fecha de entrega, estado de
 * completitud, observaciones y relación con la orden de trabajo correspondiente.
 * Permite el seguimiento del ciclo de vida de los trabajos dentales.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Características de la entidad:
 * - Registro de entregas de trabajos dentales
 * - Control de estado de completitud
 * - Seguimiento de fechas y observaciones
 * - Relación con órdenes de trabajo
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "entregas")
public class Entrega {
    
    /** Identificador único de la entrega (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrega;

    /** Fecha en que se realiza la entrega */
    @Column(nullable = false)
    private Date fecha;
    
    /** Estado de la entrega (true: completada, false: pendiente) */
    @Column(nullable = false)
    private boolean estado;
    
    /** Observaciones adicionales sobre la entrega */
    @Column(nullable = false)
    private String observaciones;

    /** Orden de trabajo asociada a esta entrega */
    @ManyToOne
    @JoinColumn(name = "idOrden")
    private OrdenTrabajo orden;

    /**
     * Obtiene el identificador único de la entrega.
     * 
     * @return ID de la entrega
     */
    public Long getIdEntrega() {
        return idEntrega;
    }

    /**
     * Establece el identificador único de la entrega.
     * 
     * @param idEntrega ID de la entrega a establecer
     */
    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
    }

    /**
     * Obtiene la fecha de la entrega.
     * 
     * @return Fecha de entrega
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la entrega.
     * 
     * @param fecha Fecha de entrega a establecer
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el estado de la entrega.
     * 
     * @return true si está completada, false si está pendiente
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado de la entrega.
     * 
     * @param estado Estado a establecer (true: completada, false: pendiente)
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene las observaciones de la entrega.
     * 
     * @return Observaciones adicionales
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones de la entrega.
     * 
     * @param observaciones Observaciones a establecer
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Obtiene la orden de trabajo asociada.
     * 
     * @return Orden de trabajo relacionada
     */
    public OrdenTrabajo getOrden() {
        return orden;
    }

    /**
     * Establece la orden de trabajo asociada.
     * 
     * @param orden Orden de trabajo a establecer
     */
    public void setOrden(OrdenTrabajo orden) {
        this.orden = orden;
    }
}
