package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Entidad que representa un pago en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "pagos" en la base de datos y contiene la información
 * sobre transacciones financieras realizadas por servicios dentales, incluyendo fecha,
 * método de pago, monto y relación con las facturas correspondientes. Facilita
 * el seguimiento completo de las transacciones y gestión financiera.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Características de la entidad:
 * - Gestión completa de transacciones financieras
 * - Control de métodos de pago y montos
 * - Relaciones con facturas asociadas
 * - Registro histórico de pagos
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "pagos")
public class Pagos {
    
    /** Identificador único del pago (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPagos;

    /** Fecha en que se realiza el pago */
    @Column(nullable = false)
    private Date fecha;
    
    /** Método de pago utilizado */
    @Column(nullable = false)
    private String metodPago;
    
    /** Monto total del pago */
    @Column(nullable = false)
    private Double monto;

    /** Facturas asociadas a este pago */
    @OneToMany
    @JoinColumn(name = "idFactura")
    private List<Factura> facturas;

    /**
     * Obtiene el identificador único del pago.
     * 
     * @return ID del pago
     */
    public Long getIdPagos() {
        return idPagos;
    }

    /**
     * Establece el identificador único del pago.
     * 
     * @param idPagos ID del pago a establecer
     */
    public void setIdPagos(Long idPagos) {
        this.idPagos = idPagos;
    }

    /**
     * Obtiene la fecha del pago.
     * 
     * @return Fecha del pago
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del pago.
     * 
     * @param fecha Fecha a establecer
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el método de pago.
     * 
     * @return Método de pago utilizado
     */
    public String getMetodPago() {
        return metodPago;
    }

    /**
     * Establece el método de pago.
     * 
     * @param metodPago Método de pago a establecer
     */
    public void setMetodPago(String metodPago) {
        this.metodPago = metodPago;
    }

    /**
     * Obtiene el monto del pago.
     * 
     * @return Monto total
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Establece el monto del pago.
     * 
     * @param monto Monto a establecer
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la lista de facturas asociadas.
     * 
     * @return Lista de facturas relacionadas
     */
    public List<Factura> getFacturas() {
        return facturas;
    }

    /**
     * Establece la lista de facturas asociadas.
     * 
     * @param facturas Lista de facturas a establecer
     */
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}
