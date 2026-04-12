package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    
    Optional<Odontologo> findByDocumento(String documento);
    
    List<Odontologo> findByNombresContainingIgnoreCase(String nombres);
    
    List<Odontologo> findByApellidosContainingIgnoreCase(String apellidos);
    
    List<Odontologo> findByEspecialidadContainingIgnoreCase(String especialidad);
    
    List<Odontologo> findByEstadoTrue();
    
    List<Odontologo> findByEstadoFalse();
    
    @Query("SELECT o FROM Odontologo o WHERE o.estado = true ORDER BY o.nombres ASC")
    List<Odontologo> findActivosOrderByNombres();
    
    @Query("SELECT COUNT(o) FROM Odontologo o WHERE o.estado = true")
    Long countOdontologosActivos();
    
    List<Odontologo> findByNumeroLicenciaContainingIgnoreCase(String numeroLicencia);
    
    @Query("SELECT o FROM Odontologo o WHERE o.usuario.email = :email")
    Optional<Odontologo> findByUsuarioEmail(@Param("email") String email);
}
