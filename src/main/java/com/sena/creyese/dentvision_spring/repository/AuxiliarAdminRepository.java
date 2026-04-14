package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.AuxiliarAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la gestión de auxiliares administrativos del sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad AuxiliarAdmin, incluyendo búsquedas especializadas, filtros por estado,
 * consultas por área de trabajo y gestión de turnos laborales.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Búsqueda por documento de identidad
 * - Búsqueda por nombres y apellidos (case insensitive)
 * - Filtrado por estado (activos/inactivos)
 * - Consultas por área de trabajo y turno
 * - Búsqueda por experiencia laboral
 * - Consultas personalizadas con ordenamiento
 * - Búsqueda por email de usuario asociado
 */
@Repository
public interface AuxiliarAdminRepository extends JpaRepository<AuxiliarAdmin, Long> {
    
    /**
     * Busca un auxiliar administrativo por su número de documento de identidad.
     * 
     * @param documento Número de documento del auxiliar
     * @return Optional con el auxiliar encontrado o vacío si no existe
     */
    Optional<AuxiliarAdmin> findByDocumento(String documento);
    
    /**
     * Busca auxiliares cuyos nombres contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombres Texto a buscar en los nombres
     * @return Lista de auxiliares que coinciden con la búsqueda
     */
    List<AuxiliarAdmin> findByNombresContainingIgnoreCase(String nombres);
    
    /**
     * Busca auxiliares cuyos apellidos contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param apellidos Texto a buscar en los apellidos
     * @return Lista de auxiliares que coinciden con la búsqueda
     */
    List<AuxiliarAdmin> findByApellidosContainingIgnoreCase(String apellidos);
    
    /**
     * Obtiene todos los auxiliares con estado activo (true).
     * 
     * @return Lista de auxiliares activos
     */
    List<AuxiliarAdmin> findByEstadoTrue();
    
    /**
     * Obtiene todos los auxiliares con estado inactivo (false).
     * 
     * @return Lista de auxiliares inactivos
     */
    List<AuxiliarAdmin> findByEstadoFalse();
    
    /**
     * Obtiene todos los auxiliares activos ordenados alfabéticamente por nombres.
     * 
     * @return Lista de auxiliares activos ordenados por nombres
     */
    @Query("SELECT a FROM AuxiliarAdmin a WHERE a.estado = true ORDER BY a.nombres ASC")
    List<AuxiliarAdmin> findActivosOrderByNombres();
    
    /**
     * Cuenta el número total de auxiliares activos en el sistema.
     * 
     * @return Número de auxiliares con estado true
     */
    @Query("SELECT COUNT(a) FROM AuxiliarAdmin a WHERE a.estado = true")
    Long countAuxiliaresActivos();
    
    /**
     * Busca auxiliares por área de trabajo (ignora mayúsculas/minúsculas).
     * 
     * @param areaTrabajo Área de trabajo a buscar
     * @return Lista de auxiliares de esa área de trabajo
     */
    List<AuxiliarAdmin> findByAreaTrabajoContainingIgnoreCase(String areaTrabajo);
    
    /**
     * Busca un auxiliar por el email de su usuario asociado.
     * 
     * @param email Email del usuario asociado al auxiliar
     * @return Optional con el auxiliar encontrado o vacío si no existe
     */
    @Query("SELECT a FROM AuxiliarAdmin a WHERE a.usuario.email = :email")
    Optional<AuxiliarAdmin> findByUsuarioEmail(@Param("email") String email);
    
    /**
     * Busca auxiliares por turno laboral (ignora mayúsculas/minúsculas).
     * 
     * @param turno Turno laboral a buscar
     * @return Lista de auxiliares con ese turno
     */
    List<AuxiliarAdmin> findByTurnoContainingIgnoreCase(String turno);
    
    /**
     * Obtiene auxiliares activos filtrados por área de trabajo específica.
     * 
     * @param area Área de trabajo a filtrar
     * @return Lista de auxiliares activos de esa área
     */
    @Query("SELECT a FROM AuxiliarAdmin a WHERE a.areaTrabajo = :area AND a.estado = true")
    List<AuxiliarAdmin> findAuxiliaresByAreaYActivos(@Param("area") String area);
    
    /**
     * Busca auxiliares con experiencia laboral superior al número de años especificado.
     * 
     * @param years Número mínimo de años de experiencia
     * @return Lista de auxiliares con más experiencia que el valor especificado
     */
    List<AuxiliarAdmin> findByExperienciaYearsGreaterThan(Integer years);
}
