package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

/**
 * Entidad que representa un detalle de factura en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "detalleFactura" en la base de datos y contiene la información
 * detallada de cada servicio facturado, incluyendo cantidad, precio unitario y relaciones
 * con la factura principal y el servicio específico. Permite el desglose de costos
 * por cada procedimiento dental facturado.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Desglose detallado de servicios facturados
 * - Control de cantidades y precios unitarios
 * - Relaciones con factura y servicios
 * - Cálculo automático de subtotales
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "detalleFactura")
public class DetalleFactura {
    
    /** Identificador único del detalle de factura (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleFactura;

    /** Cantidad de unidades del servicio facturado */
    @Column(nullable = false)
    private int cantidad;

    /** Precio unitario del servicio facturado */
    @Column(nullable = false)
    private double precio;

    /** Factura principal a la que pertenece este detalle */
    @ManyToOne
    @JoinColumn(name = "idFactura")
    private Factura factura;

    /** Servicio específico que está siendo facturado */
    @ManyToOne
    @JoinColumn(name = "idServicios")
    private Servicios servicios;

    /**
     * Obtiene el identificador único del detalle de factura.
     * 
     * @return ID del detalle de factura
     */
    public Long getIdDetalleFactura() {
        return idDetalleFactura;
    }

    /**
     * Establece el identificador único del detalle de factura.
     * 
     * @param idDetalleFactura ID del detalle de factura a establecer
     */
    public void setIdDetalleFactura(Long idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    /**
     * Obtiene la cantidad del servicio facturado.
     * 
     * @return Cantidad de unidades
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del servicio facturado.
     * 
     * @param cantidad Cantidad a establecer
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del servicio.
     * 
     * @return Precio unitario
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio unitario del servicio.
     * 
     * @param precio Precio unitario a establecer
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la factura principal asociada.
     * 
     * @return Factura relacionada
     */
    public Factura getFactura() {
        return factura;
    }

    /**
     * Establece la factura principal asociada.
     * 
     * @param factura Factura a establecer
     */
    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    /**
     * Obtiene el servicio facturado.
     * 
     * @return Servicio relacionado
     */
    public Servicios getServicios() {
        return servicios;
    }

    /**
     * Establece el servicio facturado.
     * 
     * @param servicios Servicio a establecer
     */
    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }
}
