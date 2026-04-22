package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de inventario en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Inventario, incluyendo consultas por cantidad.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de inventario por cantidad
 */
@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {

    /**
     * Obtiene todos los registros de inventario cuya cantidad sea mayor al valor especificado.
     * 
     * @param cantidad Cantidad mínima para filtrar
     * @return Lista de registros con cantidad mayor a la especificada
     */
    List<Inventario> findByCantidadGreaterThan(Integer cantidad);
}
