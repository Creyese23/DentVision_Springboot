package com.sena.creyese.dentvision_spring.modelo;

import com.sena.creyese.dentvision_spring.enums.Estado;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Entidad que representa una orden de trabajo en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "ordenesTrabajo" en la base de datos y contiene la información
 * sobre órdenes de trabajo dental, incluyendo fecha, estado, observaciones y las relaciones
 * con el paciente, odontólogo y técnico dental involucrados. Facilita el seguimiento
 * completo del ciclo de vida de los tratamientos dentales.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Gestión completa de órdenes de trabajo dental
 * - Control de estado y seguimiento de tratamientos
 * - Relaciones con pacientes, odontólogos y técnicos
 * - Registro de observaciones y notas
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "ordenesTrabajo")
public class OrdenTrabajo {
    
    /** Identificador único de la orden de trabajo (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenTrabajo;

    /** Fecha de creación de la orden de trabajo */
    @Column(nullable = false)
    private Date fecha;

    /** Estado de la orden (true: activa, false: completada/cancelada) */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    /** Observaciones adicionales sobre la orden de trabajo */
    @Column(nullable = false, length = 50)
    private String observaciones;

    /** Paciente asociado a esta orden de trabajo */
    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;
    
    /** Odontólogo responsable de la orden de trabajo */
    @ManyToOne
    @JoinColumn(name = "idOdontologo")
    private Odontologo odontologo;
    
    /** Técnico dental asignado a la orden de trabajo */
    @ManyToOne
    @JoinColumn(name = "idTecnico")
    private TecnicoDental tecnico;

    /**
     * Obtiene el identificador único de la orden de trabajo.
     * 
     * @return ID de la orden de trabajo
     */
    public Long getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    /**
     * Establece el identificador único de la orden de trabajo.
     * 
     * @param idOrdenTrabajo ID de la orden de trabajo a establecer
     */
    public void setIdOrdenTrabajo(Long idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    /**
     * Obtiene la fecha de la orden de trabajo.
     * 
     * @return Fecha de creación
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la orden de trabajo.
     * 
     * @param fecha Fecha a establecer
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el estado de la orden de trabajo.
     * 
     * @return true si está activa, false si está completada/cancelada
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la orden de trabajo.
     * 
     * @param estado Estado a establecer (true: activa, false: completada/cancelada)
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene las observaciones de la orden de trabajo.
     * 
     * @return Observaciones adicionales
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones de la orden de trabajo.
     * 
     * @param observaciones Observaciones a establecer
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Obtiene el paciente asociado.
     * 
     * @return Paciente relacionado
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Establece el paciente asociado.
     * 
     * @param paciente Paciente a establecer
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Obtiene el odontólogo responsable.
     * 
     * @return Odontólogo relacionado
     */
    public Odontologo getOdontologo() {
        return odontologo;
    }

    /**
     * Establece el odontólogo responsable.
     * 
     * @param odontologo Odontólogo a establecer
     */
    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    /**
     * Obtiene el técnico dental asignado.
     * 
     * @return Técnico dental relacionado
     */
    public TecnicoDental getTecnico() {
        return tecnico;
    }

    /**
     * Establece el técnico dental asignado.
     * 
     * @param tecnico Técnico dental a establecer
     */
    public void setTecnico(TecnicoDental tecnico) {
        this.tecnico = tecnico;
    }
}
