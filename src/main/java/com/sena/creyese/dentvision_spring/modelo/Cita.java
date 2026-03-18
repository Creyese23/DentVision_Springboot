package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @Column(nullable = false, length = 50)
    private Date fecha;
    @Column(nullable = false, length = 50)
    private Time hora;
    @Column(nullable = false, length = 50)
    private boolean estado;
    @Column(nullable = false, length = 50)
    private String motivo;

    @ManyToOne
    @JoinColumn(name ="idPaciente")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name ="idOdontologo")
    private Odontologo odontologo;

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
}
