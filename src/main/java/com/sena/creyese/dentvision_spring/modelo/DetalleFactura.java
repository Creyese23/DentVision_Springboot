package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "detalleFactura")
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleFactura;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precio;

    @ManyToOne
    @JoinColumn(name = "idFactura")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "idServicios")
    private Servicios servicios;

    public Long getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(Long idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }
}
