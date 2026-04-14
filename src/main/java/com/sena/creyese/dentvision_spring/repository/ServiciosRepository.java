package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de servicios en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Servicios, incluyendo consultas por nombre/categoría, gestión de estados,
 * filtrado por precios/duración y control de autorizaciones.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de servicios por nombre y categoría
 * - Filtrado por estado (activos/inactivos)
 * - Búsqueda por rangos de precios y duración
 * - Gestión de autorizaciones requeridas
 * - Búsqueda por texto en nombre o descripción
 * - Consultas de servicios disponibles directamente
 */
@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Long> {
    
    /**
     * Busca servicios cuyo nombre contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombre Texto a buscar en los nombres
     * @return Lista de servicios que coinciden con la búsqueda
     */
    List<Servicios> findByNombreContainingIgnoreCase(String nombre);
    
    /**
     * Busca servicios cuya categoría contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param categoria Categoría a buscar (RESTAURATIVA, QUIRÚRGICA, ESTÉTICA)
     * @return Lista de servicios de esa categoría
     */
    List<Servicios> findByCategoriaContainingIgnoreCase(String categoria);
    
    /**
     * Obtiene todos los servicios con estado activo (true).
     * 
     * @return Lista de servicios activos
     */
    List<Servicios> findByEstadoTrue();
    
    /**
     * Obtiene todos los servicios con estado inactivo (false).
     * 
     * @return Lista de servicios inactivos
     */
    List<Servicios> findByEstadoFalse();
    
    /**
     * Obtiene todos los servicios activos ordenados alfabéticamente por nombre.
     * 
     * @return Lista de servicios activos ordenados por nombre
     */
    @Query("SELECT s FROM Servicios s WHERE s.estado = true ORDER BY s.nombre ASC")
    List<Servicios> findActivosOrderByNombre();
    
    /**
     * Obtiene todos los servicios activos ordenados por precio ascendente (más económicos primero).
     * 
     * @return Lista de servicios activos ordenados por precio
     */
    @Query("SELECT s FROM Servicios s WHERE s.estado = true ORDER BY s.precio ASC")
    List<Servicios> findActivosOrderByPrecio();
    
    /**
     * Obtiene todos los servicios activos ordenados por precio descendente (más caros primero).
     * 
     * @return Lista de servicios activos ordenados por precio descendente
     */
    @Query("SELECT s FROM Servicios s WHERE s.estado = true ORDER BY s.precio DESC")
    List<Servicios> findActivosOrderByPrecioDesc();
    
    /**
     * Cuenta el número total de servicios activos en el sistema.
     * 
     * @return Número de servicios con estado true
     */
    @Query("SELECT COUNT(s) FROM Servicios s WHERE s.estado = true")
    Long countServiciosActivos();
    
    /**
     * Obtiene todos los servicios cuyo precio esté dentro de un rango específico ordenados ascendentemente.
     * 
     * @param min Precio mínimo del rango
     * @param max Precio máximo del rango
     * @return Lista de servicios ordenados por precio ascendente
     */
    @Query("SELECT s FROM Servicios s WHERE s.precio BETWEEN :min AND :max ORDER BY s.precio ASC")
    List<Servicios> findByPrecioBetween(@Param("min") Double min, @Param("max") Double max);
    
    /**
     * Obtiene todos los servicios con una duración específica en minutos.
     * 
     * @param duracionMinutos Duración exacta en minutos
     * @return Lista de servicios con esa duración
     */
    List<Servicios> findByDuracionMinutos(Integer duracionMinutos);
    
    /**
     * Obtiene todos los servicios cuya duración sea menor al valor especificado.
     * 
     * @param duracionMinutos Duración máxima en minutos
     * @return Lista de servicios con duración menor a la especificada
     */
    List<Servicios> findByDuracionMinutosLessThan(Integer duracionMinutos);
    
    /**
     * Obtiene todos los servicios cuya duración sea mayor al valor especificado.
     * 
     * @param duracionMinutos Duración mínima en minutos
     * @return Lista de servicios con duración mayor a la especificada
     */
    List<Servicios> findByDuracionMinutosGreaterThan(Integer duracionMinutos);
    
    /**
     * Busca servicios cuyo nombre o descripción contenga el texto especificado.
     * 
     * @param keyword Texto a buscar en nombre o descripción
     * @return Lista de servicios que coinciden con la búsqueda
     */
    @Query("SELECT s FROM Servicios s WHERE s.descripcion LIKE %:keyword% OR s.nombre LIKE %:keyword%")
    List<Servicios> findByNombreOrDescripcionContaining(@Param("keyword") String keyword);
    
    /**
     * Obtiene todos los servicios que requieren autorización.
     * 
     * @return Lista de servicios que requieren autorización
     */
    List<Servicios> findByRequiereAutorizacionTrue();
    
    /**
     * Obtiene todos los servicios que no requieren autorización.
     * 
     * @return Lista de servicios que no requieren autorización
     */
    List<Servicios> findByRequiereAutorizacionFalse();
    
    /**
     * Obtiene todos los servicios activos que no requieren autorización ordenados alfabéticamente.
     * 
     * @return Lista de servicios disponibles directamente ordenados por nombre
     */
    @Query("SELECT s FROM Servicios s WHERE s.estado = true AND s.requiereAutorizacion = false ORDER BY s.nombre ASC")
    List<Servicios> findServiciosDisponiblesDirectamente();
}
