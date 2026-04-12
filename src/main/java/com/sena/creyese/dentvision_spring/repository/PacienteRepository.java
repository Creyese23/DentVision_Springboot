package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    
    Optional<Paciente> findByDocumento(String documento);
    
    List<Paciente> findByNombresContainingIgnoreCase(String nombres);
    
    List<Paciente> findByApellidosContainingIgnoreCase(String apellidos);
    
    List<Paciente> findByEstadoTrue();
    
    List<Paciente> findByEstadoFalse();
    
    @Query("SELECT p FROM Paciente p WHERE p.estado = true ORDER BY p.nombres ASC")
    List<Paciente> findActivosOrderByNombres();
    
    @Query("SELECT COUNT(p) FROM Paciente p WHERE p.estado = true")
    Long countPacientesActivos();
    
    @Query("SELECT p FROM Paciente p WHERE p.fechaNacimiento BETWEEN :inicio AND :fin ORDER BY p.fechaNacimiento ASC")
    List<Paciente> findByFechaNacimientoBetween(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
    
    @Query("SELECT p FROM Paciente p WHERE YEAR(p.fechaNacimiento) = :anio")
    List<Paciente> findByAnioNacimiento(@Param("anio") Integer anio);
    
    List<Paciente> findByCiudadContainingIgnoreCase(String ciudad);
    
    List<Paciente> findByDireccionContainingIgnoreCase(String direccion);
    
    List<Paciente> findByTelefonoContainingIgnoreCase(String telefono);
    
    @Query("SELECT p FROM Paciente p WHERE p.email LIKE %:domain%")
    List<Paciente> findByEmailDomain(@Param("domain") String domain);
    
    @Query("SELECT p FROM Paciente p WHERE p.fechaRegistro = :fecha")
    List<Paciente> findByFechaRegistro(@Param("fecha") LocalDate fecha);
    
    @Query("SELECT p FROM Paciente p WHERE p.fechaRegistro BETWEEN :inicio AND :fin ORDER BY p.fechaRegistro DESC")
    List<Paciente> findByFechaRegistroBetween(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
    
    @Query("SELECT p FROM Paciente p WHERE p.estado = true AND p.fechaNacimiento BETWEEN :inicio AND :fin")
    List<Paciente> findActivosByFechaNacimientoBetween(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
    
    List<Paciente> findByOcupacionContainingIgnoreCase(String ocupacion);
    
    @Query("SELECT p FROM Paciente p WHERE p.nombreContactoEmergencia IS NOT NULL AND p.estado = true")
    List<Paciente> findActivosConContactoEmergencia();
}
