package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

/**
 * Entidad que representa un insumo dental en el sistema DentVision.
 * 
 * Esta clase mapea la tabla "insumos" en la base de datos y contiene la información
 * sobre materiales y suministros utilizados en la clínica dental, incluyendo nombre,
 * descripción, control de stock actual y nivel mínimo de reabastecimiento. Facilita
 * la gestión eficiente del inventario y alertas de reposición.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de la entidad:
 * - Gestión completa de inventario de insumos
 * - Control de niveles de stock y reabastecimiento
 * - Descripción detallada de materiales dentales
 * - Alertas automáticas de stock bajo
 * - Mapeo JPA completo
 */
@Entity
@Table(name = "insumos")
public class Insumo {
    
    /** Identificador único del insumo (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInsumo;

    /** Nombre del insumo dental */
    @Column(nullable = false, length = 100)
    private String nombre;

    /** Descripción detallada del insumo y su uso */
    @Column(nullable = false, length = 100)
    private String descripcion;

    /** Cantidad actual disponible en stock */
    @Column(nullable = false)
    private int stock;

    /** Nivel mínimo de stock para alertas de reabastecimiento */
    @Column(nullable = false)
    private int stockMinimo;

    /**
     * Obtiene el identificador único del insumo.
     * 
     * @return ID del insumo
     */
    public Long getIdInsumo() {
        return idInsumo;
    }

    /**
     * Establece el identificador único del insumo.
     * 
     * @param idInsumo ID del insumo a establecer
     */
    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }

    /**
     * Obtiene el nombre del insumo.
     * 
     * @return Nombre del insumo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del insumo.
     * 
     * @param nombre Nombre del insumo a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del insumo.
     * 
     * @return Descripción detallada
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del insumo.
     * 
     * @param descripcion Descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el stock actual del insumo.
     * 
     * @return Cantidad disponible
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece el stock actual del insumo.
     * 
     * @param stock Cantidad a establecer
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtiene el stock mínimo del insumo.
     * 
     * @return Nivel mínimo de stock
     */
    public int getStockMinimo() {
        return stockMinimo;
    }

    /**
     * Establece el stock mínimo del insumo.
     * 
     * @param stockMinimo Nivel mínimo a establecer
     */
    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
