package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Entrega;
import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de entregas de trabajos dentales en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Entrega, incluyendo consultas por orden y gestión de estados.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de entregas por orden de trabajo
 * - Filtrado por estados (pendiente/entregado)
 * - Búsqueda por observaciones
 */
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    /**
     * Obtiene todas las entregas asociadas a una orden de trabajo específica.
     * 
     * @param ordenTrabajo Orden de trabajo de la cual se desean consultar las entregas
     * @return Lista de entregas de la orden
     */
    List<Entrega> findByOrden(OrdenTrabajo ordenTrabajo);
    
    /**
     * Obtiene todas las entregas con un estado específico.
     * 
     * @param estado Estado de las entregas a buscar (PENDIENTE, ENTREGADO, etc.)
     * @return Lista de entregas con ese estado
     */
    List<Entrega> findByEstado(boolean estado);
    
    /**
     * Obtiene todas las entregas con estado PENDIENTE.
     * 
     * @return Lista de entregas pendientes
     */
    List<Entrega> findByEstadoTrue();
    
    /**
     * Obtiene todas las entregas con estado ENTREGADO.
     * 
     * @return Lista de entregas realizadas
     */
    List<Entrega> findByEstadoFalse();
    
    /**
     * Busca entregas cuyas observaciones contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param observaciones Texto a buscar en las observaciones de las entregas
     * @return Lista de entregas que coinciden con la búsqueda
     */
    List<Entrega> findByObservacionesContainingIgnoreCase(String observaciones);
}
