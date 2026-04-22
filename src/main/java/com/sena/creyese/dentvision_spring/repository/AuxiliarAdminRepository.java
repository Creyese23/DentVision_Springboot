package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.AuxiliarAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la gestión de auxiliares administrativos del sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad AuxiliarAdmin, incluyendo búsquedas por documento y consultas
 * por email de usuario asociado.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Búsqueda por documento de identidad
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
     * Busca un auxiliar por el email de su usuario asociado.
     * 
     * @param email Email del usuario asociado al auxiliar
     * @return Optional con el auxiliar encontrado o vacío si no existe
     */
    @Query("SELECT a FROM AuxiliarAdmin a WHERE a.usuario.email = :email")
    Optional<AuxiliarAdmin> findByUsuarioEmail(@Param("email") String email);
}
