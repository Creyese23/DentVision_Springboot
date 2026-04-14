package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la gestión de pacientes en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Paciente, incluyendo consultas por documento/datos personales,
 * gestión de estados, filtrado por fechas y búsqueda por contacto.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Búsqueda por documento de identidad
 * - Consulta por nombres, apellidos y datos de contacto
 * - Filtrado por estado (activos/inactivos)
 * - Búsqueda por rangos de fechas de nacimiento y registro
 * - Consultas por ciudad, dirección y ocupación
 * - Gestión de contactos de emergencia
 */
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
     * Busca pacientes cuyos nombres contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombres Texto a buscar en los nombres
     * @return Lista de pacientes que coinciden con la búsqueda
     */
    List<Paciente> findByNombresContainingIgnoreCase(String nombres);
    
    /**
     * Busca pacientes cuyos apellidos contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param apellidos Texto a buscar en los apellidos
     * @return Lista de pacientes que coinciden con la búsqueda
     */
    List<Paciente> findByApellidosContainingIgnoreCase(String apellidos);
    
    /**
     * Obtiene todos los pacientes con estado activo (true).
     * 
     * @return Lista de pacientes activos
     */
    List<Paciente> findByEstadoTrue();
    
    /**
     * Obtiene todos los pacientes con estado inactivo (false).
     * 
     * @return Lista de pacientes inactivos
     */
    List<Paciente> findByEstadoFalse();
    
    /**
     * Obtiene todos los pacientes activos ordenados alfabéticamente por nombres.
     * 
     * @return Lista de pacientes activos ordenados por nombres
     */
    @Query("SELECT p FROM Paciente p WHERE p.estado = true ORDER BY p.nombres ASC")
    List<Paciente> findActivosOrderByNombres();
    
    /**
     * Cuenta el número total de pacientes activos en el sistema.
     * 
     * @return Número de pacientes con estado true
     */
    @Query("SELECT COUNT(p) FROM Paciente p WHERE p.estado = true")
    Long countPacientesActivos();
    
    /**
     * Obtiene todos los pacientes con fecha de nacimiento dentro de un rango específico ordenados por fecha ascendente.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de pacientes en el rango ordenados por fecha de nacimiento
     */
    @Query("SELECT p FROM Paciente p WHERE p.fechaNacimiento BETWEEN :inicio AND :fin ORDER BY p.fechaNacimiento ASC")
    List<Paciente> findByFechaNacimientoBetween(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
    
    /**
     * Obtiene todos los pacientes nacidos en un año específico.
     * 
     * @param anio Año de nacimiento a buscar
     * @return Lista de pacientes nacidos en ese año
     */
    @Query("SELECT p FROM Paciente p WHERE YEAR(p.fechaNacimiento) = :anio")
    List<Paciente> findByAnioNacimiento(@Param("anio") Integer anio);
    
    /**
     * Busca pacientes cuya ciudad contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param ciudad Ciudad a buscar
     * @return Lista de pacientes de esa ciudad
     */
    List<Paciente> findByCiudadContainingIgnoreCase(String ciudad);
    
    /**
     * Busca pacientes cuya dirección contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param direccion Dirección a buscar
     * @return Lista de pacientes que coinciden con la búsqueda
     */
    List<Paciente> findByDireccionContainingIgnoreCase(String direccion);
    
    /**
     * Busca pacientes cuyo teléfono contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param telefono Teléfono a buscar
     * @return Lista de pacientes que coinciden con la búsqueda
     */
    List<Paciente> findByTelefonoContainingIgnoreCase(String telefono);
    
    /**
     * Busca pacientes cuyo email pertenezca a un dominio específico.
     * 
     * @param domain Dominio del email a buscar
     * @return Lista de pacientes con email de ese dominio
     */
    @Query("SELECT p FROM Paciente p WHERE p.email LIKE %:domain%")
    List<Paciente> findByEmailDomain(@Param("domain") String domain);
    
    /**
     * Obtiene todos los pacientes registrados en una fecha específica.
     * 
     * @param fecha Fecha de registro a consultar
     * @return Lista de pacientes registrados en esa fecha
     */
    @Query("SELECT p FROM Paciente p WHERE p.fechaRegistro = :fecha")
    List<Paciente> findByFechaRegistro(@Param("fecha") LocalDate fecha);
    
    /**
     * Obtiene todos los pacientes registrados dentro de un rango de fechas ordenados por fecha descendente.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de pacientes en el rango ordenados por fecha más reciente
     */
    @Query("SELECT p FROM Paciente p WHERE p.fechaRegistro BETWEEN :inicio AND :fin ORDER BY p.fechaRegistro DESC")
    List<Paciente> findByFechaRegistroBetween(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
    
    /**
     * Obtiene todos los pacientes activos con fecha de nacimiento dentro de un rango específico.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de pacientes activos en el rango
     */
    @Query("SELECT p FROM Paciente p WHERE p.estado = true AND p.fechaNacimiento BETWEEN :inicio AND :fin")
    List<Paciente> findActivosByFechaNacimientoBetween(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
    
    /**
     * Busca pacientes cuya ocupación contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param ocupacion Ocupación a buscar
     * @return Lista de pacientes que coinciden con la búsqueda
     */
    List<Paciente> findByOcupacionContainingIgnoreCase(String ocupacion);
    
    /**
     * Obtiene todos los pacientes activos que tienen contacto de emergencia registrado.
     * 
     * @return Lista de pacientes activos con contacto de emergencia
     */
    @Query("SELECT p FROM Paciente p WHERE p.nombreContactoEmergencia IS NOT NULL AND p.estado = true")
    List<Paciente> findActivosConContactoEmergencia();
}
