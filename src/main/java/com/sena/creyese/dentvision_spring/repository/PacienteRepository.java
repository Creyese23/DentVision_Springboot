package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la gestión de pacientes en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Paciente, incluyendo consultas por documento y contacto.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Búsqueda por documento de identidad
 * - Consulta por teléfono y dirección
 */
@SuppressWarnings("unused")
@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    
    /**
     * Busca un paciente por su número de documento de identidad.
     * 
     * @param documento Número de documento del paciente
     * @return Optional con el paciente encontrado o vacío si no existe
     */
    Optional<Paciente> findByDocumento(String documento);
    
    /**
     * Busca pacientes cuyo teléfono contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param telefono Teléfono a buscar
     * @return Lista de pacientes que coinciden con la búsqueda
     */
    List<Paciente> findByTelefonoContainingIgnoreCase(String telefono);
    
    /**
     * Busca pacientes cuya dirección contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param direccion Dirección a buscar
     * @return Lista de pacientes que coinciden con la búsqueda
     */
    List<Paciente> findByDireccionContainingIgnoreCase(String direccion);
}
