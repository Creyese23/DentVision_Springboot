package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

/**
 * Entidad que representa un detalle de orden de trabajo en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "ordenDetalle" en la base de datos y contiene la información
 * detallada de cada componente dentro de una orden de trabajo dental, incluyendo descripción,
 * cantidad y relación con la orden principal. Permite el desglose específico de los
 * procedimientos y materiales necesarios para cada trabajo dental.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Desglose detallado de órdenes de trabajo
 * - Control de cantidades y descripciones
 * - Relaciones con órdenes principales
 * - Especificación de procedimientos y materiales
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "ordenDetalle")
public class OrdenDetalle {
    
    /** Identificador único del detalle de orden (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    /** Descripción detallada del procedimiento o material */
    @Column(nullable = false, length = 255)
    private String descripcion;
    
    /** Cantidad necesaria para este detalle */
    @Column(nullable = false)
    private int cantidad;

    /** Estado del detalle (COMPLETADO, PENDIENTE, etc.) */
    @Column(nullable = false, length = 50)
    private String estado;

    /** Precio unitario del detalle */
    @Column(nullable = false)
    private double precioUnitario;

    /** Subtotal del detalle (cantidad * precio unitario) */
    @Column(nullable = false)
    private double subtotal;

    /** Descuento aplicado al detalle */
    @Column(nullable = false)
    private double descuento;

    /** Servicio asociado a este detalle */
    @ManyToOne
    @JoinColumn(name = "idServicio")
    private Servicios servicio;

    /** Orden de trabajo principal a la que pertenece este detalle */
    @ManyToOne
    @JoinColumn(name = "idOrden")
    private OrdenTrabajo orden;

    /**
     * Obtiene el identificador único del detalle de orden.
     * 
     * @return ID del detalle de orden
     */
    public Long getIdDetalle() {
        return idDetalle;
    }

    /**
     * Establece el identificador único del detalle de orden.
     * 
     * @param idDetalle ID del detalle de orden a establecer
     */
    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    /**
     * Obtiene la descripción del detalle.
     * 
     * @return Descripción detallada
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del detalle.
     * 
     * @param descripcion Descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la cantidad del detalle.
     * 
     * @return Cantidad necesaria
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del detalle.
     * 
     * @param cantidad Cantidad a establecer
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el estado del detalle.
     * 
     * @return Estado del detalle
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del detalle.
     * 
     * @param estado Estado a establecer
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el precio unitario del detalle.
     * 
     * @return Precio unitario
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del detalle.
     * 
     * @param precioUnitario Precio unitario a establecer
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el subtotal del detalle.
     * 
     * @return Subtotal del detalle
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Establece el subtotal del detalle.
     * 
     * @param subtotal Subtotal a establecer
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Obtiene el descuento del detalle.
     * 
     * @return Descuento aplicado
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * Establece el descuento del detalle.
     * 
     * @param descuento Descuento a establecer
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    /**
     * Obtiene el servicio asociado.
     * 
     * @return Servicio relacionado
     */
    public Servicios getServicio() {
        return servicio;
    }

    /**
     * Establece el servicio asociado.
     * 
     * @param servicio Servicio a establecer
     */
    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    /**
     * Obtiene la orden de trabajo principal.
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
