package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa un servicio dental en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "servicios" en la base de datos y contiene la información
 * sobre servicios dentales ofrecidos por la clínica, incluyendo nombre, descripción
 * detallada y precio. Facilita la gestión del catálogo de servicios y su facturación.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Catálogo completo de servicios dentales
 * - Control de precios y descripciones
 * - Validación de integridad de datos
 * - Gestión de servicios facturables
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "servicios")
public class Servicios {
    
    /** Identificador único del servicio (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicios;

    /** Nombre del servicio dental */
    @NotBlank(message = "Es obligatorio diligenciar el nombre del servicio")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombreServicios;

    /** Descripción detallada del servicio */
    @NotBlank(message = "Es obligatorio diligenciar la descripcion")
    @Size(max=255, message = "Maximo 255 caracteres")
    @Column(nullable = false, length = 255)
    private String descripcionServicios;

    /** Precio del servicio dental */
    @NotBlank(message = "Es obligatorio diligenciar el precio")
    @Column(nullable = false)
    private Double precioServicios;

    /**
     * Obtiene el identificador único del servicio.
     * 
     * @return ID del servicio
     */
    public Long getIdServicios() {
        return idServicios;
    }

    /**
     * Establece el identificador único del servicio.
     * 
     * @param idServicios ID del servicio a establecer
     */
    public void setIdServicios(Long idServicios) {
        this.idServicios = idServicios;
    }

    /**
     * Obtiene el nombre del servicio.
     * 
     * @return Nombre del servicio
     */
    public String getNombreServicios() {
        return nombreServicios;
    }

    /**
     * Establece el nombre del servicio.
     * 
     * @param nombreServicios Nombre del servicio a establecer
     */
    public void setNombreServicios(String nombreServicios) {
        this.nombreServicios = nombreServicios;
    }

    /**
     * Obtiene la descripción del servicio.
     * 
     * @return Descripción detallada
     */
    public String getDescripcionServicios() {
        return descripcionServicios;
    }

    /**
     * Establece la descripción del servicio.
     * 
     * @param descripcionServicios Descripción a establecer
     */
    public void setDescripcionServicios(String descripcionServicios) {
        this.descripcionServicios = descripcionServicios;
    }

    /**
     * Obtiene el precio del servicio.
     * 
     * @return Precio del servicio
     */
    public Double getPrecioServicios() {
        return precioServicios;
    }

    /**
     * Establece el precio del servicio.
     * 
     * @param precioServicios Precio a establecer
     */
    public void setPrecioServicios(Double precioServicios) {
        this.precioServicios = precioServicios;
    }
}
