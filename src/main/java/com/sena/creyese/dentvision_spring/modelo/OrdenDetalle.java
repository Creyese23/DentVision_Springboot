package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "ordenDetalle")
public class OrdenDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @Column(nullable = false, length = 255)
    private String descripcion;
    @Column(nullable = false, length = 255)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "idOrden")
    private OrdenTrabajo orden;

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public OrdenTrabajo getOrden() {
        return orden;
    }

    public void setOrden(OrdenTrabajo orden) {
        this.orden = orden;
    }
}
