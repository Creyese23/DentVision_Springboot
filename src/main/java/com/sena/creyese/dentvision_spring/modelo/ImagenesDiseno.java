package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Entidad que representa imágenes de diseño dental en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "imagenes" en la base de datos y contiene la información
 * sobre imágenes utilizadas para diseño y planificación dental, incluyendo la ruta
 * del archivo, descripción detallada y relación con el procedimiento correspondiente.
 * Facilita el almacenamiento y gestión de recursos visuales para tratamientos.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
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

    /** Nombre del archivo de imagen */
    @Column(nullable = false, length = 255)
    private String nombreArchivo;

    /** Descripción detallada de la imagen y su propósito */
    @Column(nullable = false, length = 255)
    private String descripcion;

    /** Fecha de creación de la imagen */
    @Column(nullable = false)
    private Date fechaCreacion;

    /** Estado de la imagen (APROBADA, PENDIENTE, RECHAZADA) */
    @Column(nullable = false, length = 50)
    private String estado;

    /** Notas adicionales sobre la imagen */
    @Column(length = 500)
    private String notas;

    /** Tipo de imagen */
    @Column(length = 50)
    private String tipoImagen;

    /** Orden de trabajo asociada a esta imagen */
    @ManyToOne
    @JoinColumn(name = "idOrdenTrabajo")
    private OrdenTrabajo ordenTrabajo;

    /** Paciente asociado a esta imagen */
    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

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
     * Obtiene el nombre del archivo de imagen.
     * 
     * @return Nombre del archivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Establece el nombre del archivo de imagen.
     * 
     * @param nombreArchivo Nombre del archivo a establecer
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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
     * Obtiene la fecha de creación de la imagen.
     * 
     * @return Fecha de creación
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación de la imagen.
     * 
     * @param fechaCreacion Fecha de creación a establecer
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene el estado de la imagen.
     * 
     * @return Estado de la imagen
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la imagen.
     * 
     * @param estado Estado a establecer
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene las notas adicionales de la imagen.
     * 
     * @return Notas adicionales
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece las notas adicionales de la imagen.
     * 
     * @param notas Notas a establecer
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Obtiene el tipo de imagen.
     * 
     * @return Tipo de imagen
     */
    public String getTipoImagen() {
        return tipoImagen;
    }

    /**
     * Establece el tipo de imagen.
     * 
     * @param tipoImagen Tipo de imagen a establecer
     */
    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    /**
     * Obtiene la orden de trabajo asociada.
     * 
     * @return Orden de trabajo relacionada
     */
    public OrdenTrabajo getOrdenTrabajo() {
        return ordenTrabajo;
    }

    /**
     * Establece la orden de trabajo asociada.
     * 
     * @param ordenTrabajo Orden de trabajo a establecer
     */
    public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
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
