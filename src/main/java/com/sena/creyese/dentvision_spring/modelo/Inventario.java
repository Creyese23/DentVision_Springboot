package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Entidad que representa un registro de inventario en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "inventario" en la base de datos y contiene la información
 * sobre los movimientos y registros de inventario, incluyendo cantidad total,
 * fecha del registro y relación con múltiples insumos. Permite el seguimiento
 * detallado de las existencias y movimientos de materiales en la clínica.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Registro de movimientos de inventario
 * - Control de cantidades y fechas
 * - Relación con múltiples insumos
 * - Seguimiento histórico de existencias
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "inventario")
public class Inventario {
    
    /** Identificador único del registro de inventario (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    /** Cantidad total de items en este registro de inventario */
    @Column(nullable = false)
    private int cantidad;

    /** Fecha en que se realiza el registro de inventario */
    @Column(nullable = false)
    private Date fechaInventario;

    /** Lista de insumos asociados a este registro de inventario */
    @OneToMany
    @JoinColumn(name = "idInsumos")
    private List<Insumo> insumos;

    /**
     * Obtiene el identificador único del registro de inventario.
     * 
     * @return ID del registro de inventario
     */
    public Long getIdInventario() {
        return idInventario;
    }

    /**
     * Establece el identificador único del registro de inventario.
     * 
     * @param idInventario ID del registro de inventario a establecer
     */
    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }

    /**
     * Obtiene la cantidad total del registro.
     * 
     * @return Cantidad total de items
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad total del registro.
     * 
     * @param cantidad Cantidad a establecer
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene la fecha del registro de inventario.
     * 
     * @return Fecha del registro
     */
    public Date getFechaInventario() {
        return fechaInventario;
    }

    /**
     * Establece la fecha del registro de inventario.
     * 
     * @param fechaInventario Fecha a establecer
     */
    public void setFechaInventario(Date fechaInventario) {
        this.fechaInventario = fechaInventario;
    }

    /**
     * Obtiene la lista de insumos asociados.
     * 
     * @return Lista de insumos relacionados
     */
    public List<Insumo> getInsumos() {
        return insumos;
    }

    /**
     * Establece la lista de insumos asociados.
     * 
     * @param insumos Lista de insumos a establecer
     */
    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
    }
}
