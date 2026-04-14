package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la gestión de odontólogos en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Odontologo, incluyendo consultas por documento/especialidad,
 * gestión de estados, búsqueda por licencia y consultas por email de usuario.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Búsqueda por documento de identidad
 * - Consulta por nombres, apellidos y especialidad
 * - Filtrado por estado (activos/inactivos)
 * - Búsqueda por número de licencia
 * - Consultas por email de usuario asociado
 * - Conteo de odontólogos activos
 */
@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    
    /**
     * Busca un odontólogo por su número de documento de identidad.
     * 
     * @param documento Número de documento del odontólogo
     * @return Optional con el odontólogo encontrado o vacío si no existe
     */
    Optional<Odontologo> findByDocumento(String documento);
    
    /**
     * Busca odontólogos cuyos nombres contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombres Texto a buscar en los nombres
     * @return Lista de odontólogos que coinciden con la búsqueda
     */
    List<Odontologo> findByNombresContainingIgnoreCase(String nombres);
    
    /**
     * Busca odontólogos cuyos apellidos contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param apellidos Texto a buscar en los apellidos
     * @return Lista de odontólogos que coinciden con la búsqueda
     */
    List<Odontologo> findByApellidosContainingIgnoreCase(String apellidos);
    
    /**
     * Busca odontólogos cuya especialidad contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param especialidad Especialidad a buscar (ORTODONCIA, ENDODONCIA, CIRUGÍA)
     * @return Lista de odontólogos con esa especialidad
     */
    List<Odontologo> findByEspecialidadContainingIgnoreCase(String especialidad);
    
    /**
     * Obtiene todos los odontólogos con estado activo (true).
     * 
     * @return Lista de odontólogos activos
     */
    List<Odontologo> findByEstadoTrue();
    
    /**
     * Obtiene todos los odontólogos con estado inactivo (false).
     * 
     * @return Lista de odontólogos inactivos
     */
    List<Odontologo> findByEstadoFalse();
    
    /**
     * Obtiene todos los odontólogos activos ordenados alfabéticamente por nombres.
     * 
     * @return Lista de odontólogos activos ordenados por nombres
     */
    @Query("SELECT o FROM Odontologo o WHERE o.estado = true ORDER BY o.nombres ASC")
    List<Odontologo> findActivosOrderByNombres();
    
    /**
     * Cuenta el número total de odontólogos activos en el sistema.
     * 
     * @return Número de odontólogos con estado true
     */
    @Query("SELECT COUNT(o) FROM Odontologo o WHERE o.estado = true")
    Long countOdontologosActivos();
    
    /**
     * Busca odontólogos cuyo número de licencia contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param numeroLicencia Número de licencia a buscar
     * @return Lista de odontólogos que coinciden con la búsqueda
     */
    List<Odontologo> findByNumeroLicenciaContainingIgnoreCase(String numeroLicencia);
    
    /**
     * Busca un odontólogo por el email de su usuario asociado.
     * 
     * @param email Email del usuario asociado al odontólogo
     * @return Optional con el odontólogo encontrado o vacío si no existe
     */
    @Query("SELECT o FROM Odontologo o WHERE o.usuario.email = :email")
    Optional<Odontologo> findByUsuarioEmail(@Param("email") String email);
}
