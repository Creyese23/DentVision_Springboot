package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Procedimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de procedimientos en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Procedimiento, incluyendo consultas por nombre/descripción,
 * gestión de estados, filtrado por precios/duración y búsqueda por categorías.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de procedimientos por nombre y descripción
 * - Filtrado por estado (activos/inactivos)
 * - Búsqueda por categorías y prioridades
 * - Filtrado por rangos de precios y duración
 * - Consultas por requisitos especiales (anestesia, radiografía)
 * - Búsqueda por texto en nombre o descripción
 */
@Repository
public interface ProcedimientoRepository extends JpaRepository<Procedimiento,Long> {
    
    /**
     * Busca procedimientos cuyo nombre contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombre Texto a buscar en los nombres
     * @return Lista de procedimientos que coinciden con la búsqueda
     */
    List<Procedimiento> findByNombreContainingIgnoreCase(String nombre);
    
    /**
     * Busca procedimientos cuya descripción contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param descripcion Texto a buscar en las descripciones
     * @return Lista de procedimientos que coinciden con la búsqueda
     */
    List<Procedimiento> findByDescripcionContainingIgnoreCase(String descripcion);
    
    /**
     * Obtiene todos los procedimientos con estado activo (true).
     * 
     * @return Lista de procedimientos activos
     */
    List<Procedimiento> findByEstadoTrue();
    
    /**
     * Obtiene todos los procedimientos con estado inactivo (false).
     * 
     * @return Lista de procedimientos inactivos
     */
    List<Procedimiento> findByEstadoFalse();
    
    /**
     * Obtiene todos los procedimientos activos ordenados alfabéticamente por nombre.
     * 
     * @return Lista de procedimientos activos ordenados por nombre
     */
    @Query("SELECT p FROM Procedimiento p WHERE p.estado = true ORDER BY p.nombre ASC")
    List<Procedimiento> findActivosOrderByNombre();
    
    /**
     * Cuenta el número total de procedimientos activos en el sistema.
     * 
     * @return Número de procedimientos con estado true
     */
    @Query("SELECT COUNT(p) FROM Procedimiento p WHERE p.estado = true")
    Long countProcedimientosActivos();
    
    /**
     * Busca procedimientos cuya categoría contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param categoria Categoría a buscar (RESTAURATIVA, QUIRÚRGICA, ESTÉTICA)
     * @return Lista de procedimientos de esa categoría
     */
    List<Procedimiento> findByCategoriaContainingIgnoreCase(String categoria);
    
    /**
     * Obtiene todos los procedimientos cuyo precio esté dentro de un rango específico ordenados ascendentemente.
     * 
     * @param min Precio mínimo del rango
     * @param max Precio máximo del rango
     * @return Lista de procedimientos ordenados por precio ascendente
     */
    @Query("SELECT p FROM Procedimiento p WHERE p.precio BETWEEN :min AND :max ORDER BY p.precio ASC")
    List<Procedimiento> findByPrecioBetween(@Param("min") Double min, @Param("max") Double max);
    
    /**
     * Obtiene todos los procedimientos cuya duración esté dentro de un rango específico ordenados ascendentemente.
     * 
     * @param min Duración mínima en minutos
     * @param max Duración máxima en minutos
     * @return Lista de procedimientos ordenados por duración ascendente
     */
    @Query("SELECT p FROM Procedimiento p WHERE p.duracionMinutos BETWEEN :min AND :max ORDER BY p.duracionMinutos ASC")
    List<Procedimiento> findByDuracionBetween(@Param("min") Integer min, @Param("max") Integer max);
    
    /**
     * Obtiene todos los procedimientos cuya duración sea menor al valor especificado.
     * 
     * @param duracionMinutos Duración máxima en minutos
     * @return Lista de procedimientos con duración menor a la especificada
     */
    List<Procedimiento> findByDuracionMinutosLessThan(Integer duracionMinutos);
    
    /**
     * Obtiene todos los procedimientos cuya duración sea mayor al valor especificado.
     * 
     * @param duracionMinutos Duración mínima en minutos
     * @return Lista de procedimientos con duración mayor a la especificada
     */
    List<Procedimiento> findByDuracionMinutosGreaterThan(Integer duracionMinutos);
    
    /**
     * Obtiene todos los procedimientos activos que requieren anestesia.
     * 
     * @return Lista de procedimientos que requieren anestesia
     */
    @Query("SELECT p FROM Procedimiento p WHERE p.requiereAnestesia = true AND p.estado = true")
    List<Procedimiento> findProcedimientosConAnestesia();
    
    /**
     * Obtiene todos los procedimientos activos que requieren radiografía.
     * 
     * @return Lista de procedimientos que requieren radiografía
     */
    @Query("SELECT p FROM Procedimiento p WHERE p.requiereRadiografia = true AND p.estado = true")
    List<Procedimiento> findProcedimientosConRadiografia();
    
    /**
     * Obtiene todos los procedimientos activos con prioridad ALTA.
     * 
     * @return Lista de procedimientos de alta prioridad
     */
    @Query("SELECT p FROM Procedimiento p WHERE p.prioridad = 'ALTA' AND p.estado = true")
    List<Procedimiento> findProcedimientosDeAltaPrioridad();
    
    /**
     * Busca procedimientos cuya prioridad contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param prioridad Prioridad a buscar (ALTA, MEDIA, BAJA)
     * @return Lista de procedimientos con esa prioridad
     */
    List<Procedimiento> findByPrioridadContainingIgnoreCase(String prioridad);
    
    /**
     * Busca procedimientos cuyo nombre o descripción contenga el texto especificado.
     * 
     * @param keyword Texto a buscar en nombre o descripción
     * @return Lista de procedimientos que coinciden con la búsqueda
     */
    @Query("SELECT p FROM Procedimiento p WHERE p.nombre LIKE %:keyword% OR p.descripcion LIKE %:keyword%")
    List<Procedimiento> findByNombreOrDescripcionContaining(@Param("keyword") String keyword);
    
    /**
     * Obtiene todos los procedimientos activos ordenados por precio ascendente (más económicos primero).
     * 
     * @return Lista de procedimientos activos ordenados por precio
     */
    @Query("SELECT p FROM Procedimiento p WHERE p.estado = true ORDER BY p.precio ASC")
    List<Procedimiento> findActivosOrderByPrecio();
    
    /**
     * Obtiene todos los procedimientos activos ordenados por precio descendente (más caros primero).
     * 
     * @return Lista de procedimientos activos ordenados por precio descendente
     */
    @Query("SELECT p FROM Procedimiento p WHERE p.estado = true ORDER BY p.precio DESC")
    List<Procedimiento> findActivosOrderByPrecioDesc();
}
