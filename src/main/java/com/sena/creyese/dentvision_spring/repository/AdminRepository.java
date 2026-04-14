package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la gestión de administradores del sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Admin, incluyendo búsquedas especializadas, filtros por estado y consultas
 * personalizadas para la gestión de usuarios administrativos.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Búsqueda por documento de identidad
 * - Búsqueda por nombres y apellidos (case insensitive)
 * - Filtrado por estado (activos/inactivos)
 * - Consultas personalizadas con ordenamiento
 * - Conteo de administradores activos
 * - Búsqueda por nivel de acceso y departamento
 * - Consultas por email de usuario asociado
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    /**
     * Busca un administrador por su número de documento de identidad.
     * 
     * @param documento Número de documento del administrador
     * @return Optional con el administrador encontrado o vacío si no existe
     */
    Optional<Admin> findByDocumento(String documento);
    
    /**
     * Busca administradores cuyos nombres contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombres Texto a buscar en los nombres
     * @return Lista de administradores que coinciden con la búsqueda
     */
    List<Admin> findByNombresContainingIgnoreCase(String nombres);
    
    /**
     * Busca administradores cuyos apellidos contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param apellidos Texto a buscar en los apellidos
     * @return Lista de administradores que coinciden con la búsqueda
     */
    List<Admin> findByApellidosContainingIgnoreCase(String apellidos);
    
    /**
     * Obtiene todos los administradores con estado activo (true).
     * 
     * @return Lista de administradores activos
     */
    List<Admin> findByEstadoTrue();
    
    /**
     * Obtiene todos los administradores con estado inactivo (false).
     * 
     * @return Lista de administradores inactivos
     */
    List<Admin> findByEstadoFalse();
    
    /**
     * Obtiene todos los administradores activos ordenados alfabéticamente por nombres.
     * 
     * @return Lista de administradores activos ordenados por nombres
     */
    @Query("SELECT a FROM Admin a WHERE a.estado = true ORDER BY a.nombres ASC")
    List<Admin> findActivosOrderByNombres();
    
    /**
     * Cuenta el número total de administradores activos en el sistema.
     * 
     * @return Número de administradores con estado true
     */
    @Query("SELECT COUNT(a) FROM Admin a WHERE a.estado = true")
    Long countAdminsActivos();
    
    /**
     * Busca administradores por nivel de acceso (ignora mayúsculas/minúsculas).
     * 
     * @param nivelAcceso Nivel de acceso a buscar
     * @return Lista de administradores con ese nivel de acceso
     */
    List<Admin> findByNivelAccesoContainingIgnoreCase(String nivelAcceso);
    
    /**
     * Busca un administrador por el email de su usuario asociado.
     * 
     * @param email Email del usuario asociado al administrador
     * @return Optional con el administrador encontrado o vacío si no existe
     */
    @Query("SELECT a FROM Admin a WHERE a.usuario.email = :email")
    Optional<Admin> findByUsuarioEmail(@Param("email") String email);
    
    /**
     * Obtiene todos los super administradores que están activos.
     * 
     * @return Lista de super administradores activos
     */
    @Query("SELECT a FROM Admin a WHERE a.nivelAcceso = 'SUPER_ADMIN' AND a.estado = true")
    List<Admin> findSuperAdminsActivos();
    
    /**
     * Busca administradores por departamento (ignora mayúsculas/minúsculas).
     * 
     * @param departamento Departamento al que pertenece el administrador
     * @return Lista de administradores de ese departamento
     */
    List<Admin> findByDepartamentoContainingIgnoreCase(String departamento);
}
