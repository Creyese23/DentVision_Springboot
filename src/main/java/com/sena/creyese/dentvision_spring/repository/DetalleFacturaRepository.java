package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de detalles de facturas en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad DetalleFactura, incluyendo consultas por cantidad.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de detalles por cantidad
 */
@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

    /**
     * Obtiene todos los detalles cuya cantidad sea mayor al valor especificado.
     * 
     * @param cantidad Cantidad mínima para filtrar
     * @return Lista de detalles con cantidad mayor a la especificada
     */
    List<DetalleFactura> findByCantidadGreaterThan(Integer cantidad);
}
