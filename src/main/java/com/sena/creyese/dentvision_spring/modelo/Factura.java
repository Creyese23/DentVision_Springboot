package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    @Column(nullable = false, length = 50)
    private Date fecha;

    @Column(nullable = false, length = 100)
    private Double valor;

    @Column(nullable = false, length = 50)
    private boolean estado;
}
