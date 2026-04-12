package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entidad que representa una cita dental en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "citas" en la base de datos y contiene la información
 * de las citas programadas entre pacientes y odontólogos. Cada cita incluye
 * fecha, hora, estado y motivo, además de las relaciones con el paciente
 * y el odontólogo involucrados.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Características de la entidad:
 * - Programación de fechas y horas con API moderna (LocalDate/LocalTime)
 * - Control de estado de la cita (activa/cancelada/completada)
 * - Relaciones bidireccionales con Paciente y Odontólogo
 * - Registro de motivos y propósitos de la cita
 * - Mapeo JPA completo con validaciones
 */
@Entity
@Table(name = "citas")
public class Cita {
    
    /** Identificador único de la cita (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    /** Fecha programada para la cita */
    @Column(nullable = false)
    private LocalDate fecha;
    
    /** Hora programada para la cita */
    @Column(nullable = false)
    private LocalTime hora;
    
    /** Estado actual de la cita (true=activa, false=cancelada/completada) */
    @Column(nullable = false)
    private boolean estado;
    
    /** Motivo o descripción del propósito de la cita */
    @Column(nullable = false, length = 200)
    private String motivo;

    /** Paciente asociado a la cita */
    @ManyToOne
    @JoinColumn(name ="idPaciente")
    private Paciente paciente;
    
    /** Odontólogo asignado a la cita */
    @ManyToOne
    @JoinColumn(name ="idOdontologo")
    private Odontologo odontologo;

    /**
     * Obtiene el identificador único de la cita.
     * 
     * @return ID de la cita
     */
    public Long getIdCita() {
        return idCita;
    }

    /**
     * Establece el identificador único de la cita.
     * 
     * @param idCita ID de la cita a establecer
     */
    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    /**
     * Obtiene la fecha programada de la cita.
     * 
     * @return Fecha de la cita
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha programada de la cita.
     * 
     * @param fecha Fecha de la cita a establecer
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la hora programada de la cita.
     * 
     * @return Hora de la cita
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Establece la hora programada de la cita.
     * 
     * @param hora Hora de la cita a establecer
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * Obtiene el estado actual de la cita.
     * 
     * @return true si la cita está activa, false si está cancelada o completada
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la cita.
     * 
     * @param estado Estado a establecer (true=activa, false=cancelada/completada)
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el motivo de la cita.
     * 
     * @return Motivo o descripción de la cita
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Establece el motivo de la cita.
     * 
     * @param motivo Motivo o descripción a establecer
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Obtiene el paciente asociado a la cita.
     * 
     * @return Paciente de la cita
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Establece el paciente asociado a la cita.
     * 
     * @param paciente Paciente a establecer
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Obtiene el odontólogo asignado a la cita.
     * 
     * @return Odontólogo de la cita
     */
    public Odontologo getOdontologo() {
        return odontologo;
    }

    /**
     * Establece el odontólogo asignado a la cita.
     * 
     * @param odontologo Odontólogo a establecer
     */
    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }
}
