package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;


@Entity
@Table(name = "procedimientos")
public class Procedimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcedimiento;

    @NotBlank(message = "")
    @Column(nullable = false, length = 50)
    private String descripcion;
    private boolean estado;
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name ="idCita")
    private Cita cita;
}
