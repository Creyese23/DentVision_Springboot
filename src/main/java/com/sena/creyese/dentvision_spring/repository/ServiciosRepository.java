package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de servicios en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Servicios, incluyendo consultas por nombre y descripción.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de servicios por nombre y descripción
 */
@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Long> {
    
    /**
     * Busca servicios cuyo nombre contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombreServicios Texto a buscar en los nombres
     * @return Lista de servicios que coinciden con la búsqueda
     */
    List<Servicios> findByNombreServiciosContainingIgnoreCase(String nombreServicios);
    
    /**
     * Busca servicios cuya descripción contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param descripcionServicios Texto a buscar en las descripciones
     * @return Lista de servicios que coinciden con la búsqueda
     */
    List<Servicios> findByDescripcionServiciosContainingIgnoreCase(String descripcionServicios);
}
