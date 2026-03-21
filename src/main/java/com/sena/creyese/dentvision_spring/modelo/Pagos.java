package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pagos")
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPagos;

    @Column(nullable = false)
    private Date fecha;
    @Column(nullable = false)
    private String metodPago;
    @Column(nullable = false)
    private Double monto;

    @OneToMany
    @JoinColumn(name = "idFactura")
    private List<Factura> facturas;

    public Long getIdPagos() {
        return idPagos;
    }

    public void setIdPagos(Long idPagos) {
        this.idPagos = idPagos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMetodPago() {
        return metodPago;
    }

    public void setMetodPago(String metodPago) {
        this.metodPago = metodPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}
