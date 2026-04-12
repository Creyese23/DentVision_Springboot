package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Usuario;
import com.sena.creyese.dentvision_spring.enums.TipoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Usuario.
 * 
 * Esta interfaz extiende JpaRepository y proporciona métodos personalizados
 * para acceder y manipular datos de usuarios en la base de datos. Incluye
 * consultas optimizadas para operaciones comunes de autenticación y gestión.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Búsqueda por email para autenticación
 * - Búsquedas por nombres y apellidos (case-insensitive)
 * - Filtrado por estado (activo/inactivo)
 * - Consultas por rol y combinaciones de rol/estado
 * - Búsquedas por rangos de fechas de registro
 * - Consultas especializadas para administración
 * - Estadísticas y conteos de usuarios
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    /**
     * Busca un usuario por su dirección de email.
     * 
     * Método principal utilizado para el proceso de autenticación.
     * El email debe ser único en el sistema.
     * 
     * @param email Email del usuario a buscar
     * @return Optional con el usuario encontrado o vacío si no existe
     */
    Optional<Usuario> findByEmail(String email);
    
    /**
     * Busca usuarios por nombres (ignorando mayúsculas/minúsculas).
     * 
     * @param nombres Texto a buscar en los nombres de los usuarios
     * @return Lista de usuarios que contienen el texto especificado
     */
    List<Usuario> findByNombresContainingIgnoreCase(String nombres);
    
    /**
     * Busca usuarios por apellidos (ignorando mayúsculas/minúsculas).
     * 
     * @param apellidos Texto a buscar en los apellidos de los usuarios
     * @return Lista de usuarios que contienen el texto especificado
     */
    List<Usuario> findByApellidosContainingIgnoreCase(String apellidos);
    
    /**
     * Obtiene todos los usuarios con estado activo.
     * 
     * @return Lista de usuarios activos
     */
    List<Usuario> findByEstadoTrue();
    
    /**
     * Obtiene todos los usuarios con estado inactivo.
     * 
     * @return Lista de usuarios inactivos
     */
    List<Usuario> findByEstadoFalse();
    
    /**
     * Obtiene usuarios activos ordenados alfabéticamente por nombres.
     * 
     * @return Lista de usuarios activos ordenados por nombres ascendente
     */
    @Query("SELECT u FROM Usuario u WHERE u.estado = true ORDER BY u.nombres ASC")
    List<Usuario> findActivosOrderByNombres();
    
    /**
     * Cuenta el total de usuarios activos en el sistema.
     * 
     * @return Número de usuarios activos
     */
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.estado = true")
    Long countUsuariosActivos();
    
    /**
     * Busca usuarios activos de un rol específico.
     * 
     * @param rol Tipo de rol a buscar
     * @return Lista de usuarios activos con el rol especificado
     */
    @Query("SELECT u FROM Usuario u WHERE u.rol.nombreRol = :rol AND u.estado = true")
    List<Usuario> findByRolAndEstado(@Param("rol") TipoRol rol);
    
    /**
     * Busca todos los usuarios de un rol específico (sin filtrar por estado).
     * 
     * @param rol Tipo de rol a buscar
     * @return Lista de usuarios con el rol especificado
     */
    @Query("SELECT u FROM Usuario u WHERE u.rol.nombreRol = :rol")
    List<Usuario> findByRol(@Param("rol") TipoRol rol);
    
    /**
     * Busca usuarios registrados en una fecha específica.
     * 
     * @param fecha Fecha de registro a buscar
     * @return Lista de usuarios registrados en la fecha especificada
     */
    @Query("SELECT u FROM Usuario u WHERE u.fechaRegistro = :fecha")
    List<Usuario> findByFechaRegistro(@Param("fecha") java.time.LocalDate fecha);
    
    /**
     * Busca usuarios registrados en un rango de fechas.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de usuarios registrados en el rango de fechas, ordenados por fecha descendente
     */
    @Query("SELECT u FROM Usuario u WHERE u.fechaRegistro BETWEEN :inicio AND :fin ORDER BY u.fechaRegistro DESC")
    List<Usuario> findByFechaRegistroBetween(@Param("inicio") java.time.LocalDate inicio, @Param("fin") java.time.LocalDate fin);
    
    /**
     * Busca usuarios por dominio de email.
     * 
     * @param domain Dominio de email a buscar (ej: "gmail.com")
     * @return Lista de usuarios con email del dominio especificado
     */
    @Query("SELECT u FROM Usuario u WHERE u.email LIKE %:domain%")
    List<Usuario> findByEmailDomain(@Param("domain") String domain);
    
    /**
     * Obtiene todos los administradores activos del sistema.
     * 
     * @return Lista de usuarios activos con rol ADMIN o SUPER_ADMIN
     */
    @Query("SELECT u FROM Usuario u WHERE u.estado = true AND u.rol.nombreRol IN ('ADMIN', 'SUPER_ADMIN')")
    List<Usuario> findAdminsActivos();
    
    /**
     * Obtiene todo el personal clínico activo del sistema.
     * 
     * @return Lista de usuarios activos con rol ODONTOLOGO, TECNICO_DENTAL o AUXILIAR_ADMIN
     */
    @Query("SELECT u FROM Usuario u WHERE u.estado = true AND u.rol.nombreRol IN ('ODONTOLOGO', 'TECNICO_DENTAL', 'AUXILIAR_ADMIN')")
    List<Usuario> findPersonalClinicoActivo();
}
