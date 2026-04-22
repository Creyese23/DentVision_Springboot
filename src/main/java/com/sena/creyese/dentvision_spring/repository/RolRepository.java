package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Roles;
import com.sena.creyese.dentvision_spring.enums.TipoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la gestión de roles en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Roles, incluyendo consultas por nombre de rol y validación de existencia.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de roles por nombre y tipo
 * - Validación de existencia de roles
 * - Búsqueda por múltiples roles simultáneamente
 * - Conteo de roles específicos
 * - Ordenamiento alfabético de roles
 */
@Repository
public interface RolRepository extends JpaRepository<Roles,Long> {
    
    /**
     * Busca un rol por su nombre específico del enumerado TipoRol.
     * 
     * @param nombreRol Nombre del rol a buscar (ADMIN, ODONTOLOGO, PACIENTE, etc.)
     * @return Optional con el rol encontrado o vacío si no existe
     */
    Optional<Roles> findByNombreRol(TipoRol nombreRol);
    
    /**
     * Busca roles que coincidan con cualquiera de los nombres especificados en la lista.
     * 
     * @param nombresRol Lista de nombres de roles a buscar
     * @return Lista de roles que coinciden con los nombres especificados
     */
    List<Roles> findByNombreRolIn(List<TipoRol> nombresRol);
    
    /**
     * Busca roles cuyo nombre contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombreRol Texto a buscar en los nombres de roles
     * @return Lista de roles que coinciden con la búsqueda
     */
    List<Roles> findByNombreRolContainingIgnoreCase(String nombreRol);
    
    /**
     * Busca un rol por su nombre utilizando consulta JPQL personalizada.
     * 
     * @param nombreRol Nombre del rol a buscar
     * @return Optional con el rol encontrado o vacío si no existe
     */
    @Query("SELECT r FROM Roles r WHERE r.nombreRol = :nombreRol")
    Optional<Roles> findByNombreRolQuery(@Param("nombreRol") TipoRol nombreRol);
    
    /**
     * Cuenta el número de roles que existen con un nombre específico.
     * 
     * @param nombreRol Nombre del rol a contar
     * @return Número de roles con ese nombre (debería ser 0 o 1 para nombres únicos)
     */
    @Query("SELECT COUNT(r) FROM Roles r WHERE r.nombreRol = :nombreRol")
    Long countByNombreRol(@Param("nombreRol") TipoRol nombreRol);
    
    /**
     * Obtiene todos los roles ordenados alfabéticamente por nombre.
     * 
     * @return Lista de todos los roles ordenados por nombre ascendente
     */
    @Query("SELECT r FROM Roles r ORDER BY r.nombreRol ASC")
    List<Roles> findAllOrderByNombreRol();
    
    /**
     * Obtiene todos los roles de tipo administrador (ADMIN y SUPER_ADMIN).
     * 
     * @return Lista de roles administrativos del sistema
     */
    @Query("SELECT r FROM Roles r WHERE r.nombreRol IN ('ADMIN', 'SUPER_ADMIN')")
    List<Roles> findAdminRoles();
    
    /**
     * Obtiene todos los roles clínicos del sistema (ODONTOLOGO y TECNICO_DENTAL).
     * 
     * @return Lista de roles clínicos del sistema
     */
    @Query("SELECT r FROM Roles r WHERE r.nombreRol IN ('ODONTOLOGO', 'TECNICO_DENTAL')")
    List<Roles> findClinicalRoles();
    
    /**
     * Busca el rol de tipo PACIENTE en el sistema.
     * 
     * @return Optional con el rol PACIENTE encontrado o vacío si no existe
     */
    @Query("SELECT r FROM Roles r WHERE r.nombreRol = 'PACIENTE'")
    Optional<Roles> findPacienteRole();
}
