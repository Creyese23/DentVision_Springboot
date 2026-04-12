package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Entidad que representa una factura en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "facturas" en la base de datos y contiene la información
 * financiera de las transacciones realizadas a los pacientes por los servicios
 * odontológicos prestados. Cada factura incluye fecha, valor, estado y está
 * asociada a un paciente específico.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Características de la entidad:
 * - Registro de transacciones financieras
 * - Control de estado de pago (pagada/pendiente)
 * - Relación con pacientes para facturación
 * - Manejo de valores monetarios
 * - Mapeo JPA completo con validaciones
 */
@Entity
@Table(name = "facturas")
public class Factura {

    /** Identificador único de la factura (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    /** Fecha de emisión de la factura */
    @Column(nullable = false, length = 50)
    private Date fecha;

    /** Valor total de la factura */
    @Column(nullable = false)
    private Double valor;

    /** Estado de pago de la factura (true=pagada, false=pendiente) */
    @Column(nullable = false, length = 50)
    private boolean estado;

    /** Paciente asociado a la factura */
    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    /**
     * Obtiene el identificador único de la factura.
     * 
     * @return ID de la factura
     */
    public Long getIdFactura() {
        return idFactura;
    }

    /**
     * Establece el identificador único de la factura.
     * 
     * @param idFactura ID de la factura a establecer
     */
    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    /**
     * Obtiene la fecha de emisión de la factura.
     * 
     * @return Fecha de la factura
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de emisión de la factura.
     * 
     * @param fecha Fecha de la factura a establecer
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el valor total de la factura.
     * 
     * @return Valor total de la factura
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Establece el valor total de la factura.
     * 
     * @param valor Valor total a establecer
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * Obtiene el estado de pago de la factura.
     * 
     * @return true si está pagada, false si está pendiente
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado de pago de la factura.
     * 
     * @param estado Estado a establecer (true=pagada, false=pendiente)
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el paciente asociado a la factura.
     * 
     * @return Paciente de la factura
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Establece el paciente asociado a la factura.
     * 
     * @param paciente Paciente a establecer
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
