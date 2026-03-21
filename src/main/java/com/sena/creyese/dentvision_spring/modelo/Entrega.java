package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "entregas")
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrega;

    @Column(nullable = false)
    private Date fecha;
    @Column(nullable = false)
    private boolean estado;
    @Column(nullable = false)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "idOrden")
    private OrdenTrabajo orden;

    public Long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public OrdenTrabajo getOrden() {
        return orden;
    }

    public void setOrden(OrdenTrabajo orden) {
        this.orden = orden;
    }
}
