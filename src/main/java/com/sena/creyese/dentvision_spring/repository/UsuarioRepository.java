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
 * Repositorio Spring Data JPA para la gestión de usuarios en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Usuario, incluyendo consultas por email/datos personales, gestión de estados,
 * filtrado por roles y control de accesos al sistema.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Autenticación y búsqueda por email
 * - Consulta por nombres y apellidos
 * - Filtrado por estado (activos/inactivos)
 * - Gestión de roles y permisos
 * - Búsqueda por rangos de fechas de registro
 * - Consultas por dominio de email
 * - Gestión de administradores y personal clínico
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    /**
     * Busca un usuario por su dirección de email.
     * 
     * @param email Email del usuario a buscar
     * @return Optional con el usuario encontrado o vacío si no existe
     */
    Optional<Usuario> findByEmail(String email);
    
    /**
     * Busca usuarios cuyos nombres contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombres Texto a buscar en los nombres
     * @return Lista de usuarios que coinciden con la búsqueda
     */
    List<Usuario> findByNombresContainingIgnoreCase(String nombres);
    
    /**
     * Busca usuarios cuyos apellidos contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param apellidos Texto a buscar en los apellidos
     * @return Lista de usuarios que coinciden con la búsqueda
     */
    List<Usuario> findByApellidosContainingIgnoreCase(String apellidos);
    
    /**
     * Obtiene todos los usuarios con estado activo (true).
     * 
     * @return Lista de usuarios activos
     */
    List<Usuario> findByEstadoTrue();
    
    /**
     * Obtiene todos los usuarios con estado inactivo (false).
     * 
     * @return Lista de usuarios inactivos
     */
    List<Usuario> findByEstadoFalse();
    
    /**
     * Obtiene todos los usuarios activos ordenados alfabéticamente por nombres.
     * 
     * @return Lista de usuarios activos ordenados por nombres
     */
    @Query("SELECT u FROM Usuario u WHERE u.estado = true ORDER BY u.nombres ASC")
    List<Usuario> findActivosOrderByNombres();
    
    /**
     * Cuenta el número total de usuarios activos en el sistema.
     * 
     * @return Número de usuarios con estado true
     */
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.estado = true")
    Long countUsuariosActivos();
    
    /**
     * Obtiene todos los usuarios activos de un rol específico.
     * 
     * @param rol Tipo de rol a buscar
     * @return Lista de usuarios activos con ese rol
     */
    @Query("SELECT u FROM Usuario u WHERE u.rol.nombreRol = :rol AND u.estado = true")
    List<Usuario> findByRolAndEstado(@Param("rol") TipoRol rol);
    
    /**
     * Obtiene todos los usuarios de un rol específico (sin filtrar por estado).
     * 
     * @param rol Tipo de rol a buscar
     * @return Lista de usuarios con ese rol
     */
    @Query("SELECT u FROM Usuario u WHERE u.rol.nombreRol = :rol")
    List<Usuario> findByRol(@Param("rol") TipoRol rol);
    
    /**
     * Obtiene todos los usuarios registrados en una fecha específica.
     * 
     * @param fecha Fecha de registro a consultar
     * @return Lista de usuarios registrados en esa fecha
     */
    @Query("SELECT u FROM Usuario u WHERE u.fechaRegistro = :fecha")
    List<Usuario> findByFechaRegistro(@Param("fecha") java.time.LocalDate fecha);
    
    /**
     * Obtiene todos los usuarios registrados dentro de un rango de fechas ordenados por fecha descendente.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de usuarios en el rango ordenados del más reciente al más antiguo
     */
    @Query("SELECT u FROM Usuario u WHERE u.fechaRegistro BETWEEN :inicio AND :fin ORDER BY u.fechaRegistro DESC")
    List<Usuario> findByFechaRegistroBetween(@Param("inicio") java.time.LocalDate inicio, @Param("fin") java.time.LocalDate fin);
    
    /**
     * Busca usuarios cuyo email pertenezca a un dominio específico.
     * 
     * @param domain Dominio del email a buscar
     * @return Lista de usuarios con email de ese dominio
     */
    @Query("SELECT u FROM Usuario u WHERE u.email LIKE %:domain%")
    List<Usuario> findByEmailDomain(@Param("domain") String domain);
    
    /**
     * Obtiene todos los usuarios activos con rol administrativo.
     * 
     * @return Lista de administradores activos
     */
    @Query("SELECT u FROM Usuario u WHERE u.estado = true AND u.rol.nombreRol IN ('ADMIN', 'SUPER_ADMIN')")
    List<Usuario> findAdminsActivos();
    
    /**
     * Obtiene todo el personal clínico activo del sistema.
     * 
     * @return Lista de personal clínico activo
     */
    @Query("SELECT u FROM Usuario u WHERE u.estado = true AND u.rol.nombreRol IN ('ODONTOLOGO', 'TECNICO_DENTAL', 'AUXILIAR_ADMIN')")
    List<Usuario> findPersonalClinicoActivo();
}
