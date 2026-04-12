package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

/**
 * Entidad que representa imágenes de diseño dental en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "imagenes" en la base de datos y contiene la información
 * sobre imágenes utilizadas para diseño y planificación dental, incluyendo la ruta
 * del archivo, descripción detallada y relación con el procedimiento correspondiente.
 * Facilita el almacenamiento y gestión de recursos visuales para tratamientos.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Características de la entidad:
 * - Almacenamiento de imágenes de diseño dental
 * - Gestión de recursos visuales para tratamientos
 * - Relación con procedimientos específicos
 * - Control de descripciones y metadatos
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "imagenes")
public class ImagenesDiseno {
    
    /** Identificador único de la imagen (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagenes;

    /** Ruta o nombre del archivo de imagen */
    @Column(nullable = false, length = 255)
    private String imagen;

    /** Descripción detallada de la imagen y su propósito */
    @Column(nullable = false, length = 255)
    private String descripcion;

    /** Procedimiento dental asociado a esta imagen */
    @ManyToOne
    @JoinColumn(name = "idProcedimiento")
    private Procedimiento procedimiento;

    /**
     * Obtiene el identificador único de la imagen.
     * 
     * @return ID de la imagen
     */
    public Long getIdImagenes() {
        return idImagenes;
    }

    /**
     * Establece el identificador único de la imagen.
     * 
     * @param idImagenes ID de la imagen a establecer
     */
    public void setIdImagenes(Long idImagenes) {
        this.idImagenes = idImagenes;
    }

    /**
     * Obtiene la ruta o nombre del archivo de imagen.
     * 
     * @return Ruta del archivo de imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Establece la ruta o nombre del archivo de imagen.
     * 
     * @param imagen Ruta del archivo a establecer
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene la descripción de la imagen.
     * 
     * @return Descripción detallada
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la imagen.
     * 
     * @param descripcion Descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el procedimiento asociado.
     * 
     * @return Procedimiento relacionado
     */
    public Procedimiento getProcedimiento() {
        return procedimiento;
    }

    /**
     * Establece el procedimiento asociado.
     * 
     * @param procedimiento Procedimiento a establecer
     */
    public void setProcedimiento(Procedimiento procedimiento) {
        this.procedimiento = procedimiento;
    }
}
