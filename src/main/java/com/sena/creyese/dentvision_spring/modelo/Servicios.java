package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "servicios")
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicios;

    @NotBlank(message = "Es obligatorio diligenciar el nombre del servicio")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombreServicios;

    @NotBlank(message = "Es obligatorio diligenciar la descripcion")
    @Size(max=50, message = "Maximo255 caracteres")
    @Column(nullable = false, length = 50)
    private String descripcionServicios;

    @NotBlank(message = "Es obligatorio diligenciar el precio")
    @Size(max=50, message = "Maximo255 caracteres")
    @Column(nullable = false, length = 50)
    private Double precioServicios;

    public Long getIdServicios() {
        return idServicios;
    }

    public void setIdServicios(Long idServicios) {
        this.idServicios = idServicios;
    }

    public String getNombreServicios() {
        return nombreServicios;
    }

    public void setNombreServicios(String nombreServicios) {
        this.nombreServicios = nombreServicios;
    }

    public String getDescripcionServicios() {
        return descripcionServicios;
    }

    public void setDescripcionServicios(String descripcionServicios) {
        this.descripcionServicios = descripcionServicios;
    }

    public Double getPrecioServicios() {
        return precioServicios;
    }

    public void setPrecioServicios(Double precioServicios) {
        this.precioServicios = precioServicios;
    }
}
