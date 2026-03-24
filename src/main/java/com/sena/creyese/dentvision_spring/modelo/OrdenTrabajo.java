package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ordenes_trabajo")
public class OrdenTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    @Column(nullable = false, length = 50)
    private Date fecha;

    @Column(nullable = false, length = 50)
    private Boolean estado;

    @Column(nullable = false, length = 50)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "idOdontologo")
    private Odontologo odontologo;
    @ManyToOne
    @JoinColumn(name = "idTecnico")
    private TecnicoDental tecnico;

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public TecnicoDental getTecnico() {
        return tecnico;
    }

    public void setTecnico(TecnicoDental tecnico) {
        this.tecnico = tecnico;
    }
}
