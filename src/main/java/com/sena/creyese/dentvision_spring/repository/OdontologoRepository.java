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
 * búsqueda por licencia y consultas por email de usuario.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Búsqueda por documento de identidad
 * - Consulta por especialidad
 * - Búsqueda por número de licencia
 * - Consultas por email de usuario asociado
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
     * Busca odontólogos cuya especialidad contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param especialidad Especialidad a buscar (ORTODONCIA, ENDODONCIA, CIRUGÍA)
     * @return Lista de odontólogos con esa especialidad
     */
    List<Odontologo> findByEspecialidadContainingIgnoreCase(String especialidad);
    
    /**
     * Busca odontólogos cuyo número de licencia contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param N_Licencia Número de licencia a buscar
     * @return Lista de odontólogos que coinciden con la búsqueda
     */
    @Query("SELECT o FROM Odontologo o WHERE LOWER(o.N_Licencia) LIKE LOWER(CONCAT('%', :N_Licencia, '%'))")
    List<Odontologo> findByN_LicenciaContainingIgnoreCase(@Param("N_Licencia") String N_Licencia);
    
    /**
     * Busca un odontólogo por el email de su usuario asociado.
     * 
     * @param email Email del usuario asociado al odontólogo
     * @return Optional con el odontólogo encontrado o vacío si no existe
     */
    @Query("SELECT o FROM Odontologo o WHERE o.usuario.email = :email")
    Optional<Odontologo> findByUsuarioEmail(@Param("email") String email);
}
