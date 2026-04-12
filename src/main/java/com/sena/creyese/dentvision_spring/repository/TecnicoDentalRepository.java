package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.TecnicoDental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TecnicoDentalRepository extends JpaRepository<TecnicoDental,Long> {
    
    Optional<TecnicoDental> findByDocumento(String documento);
    
    List<TecnicoDental> findByNombresContainingIgnoreCase(String nombres);
    
    List<TecnicoDental> findByApellidosContainingIgnoreCase(String apellidos);
    
    List<TecnicoDental> findByEspecializacionContainingIgnoreCase(String especializacion);
    
    List<TecnicoDental> findByEstadoTrue();
    
    List<TecnicoDental> findByEstadoFalse();
    
    @Query("SELECT t FROM TecnicoDental t WHERE t.estado = true ORDER BY t.nombres ASC")
    List<TecnicoDental> findActivosOrderByNombres();
    
    @Query("SELECT COUNT(t) FROM TecnicoDental t WHERE t.estado = true")
    Long countTecnicosActivos();
    
    List<TecnicoDental> findByNumeroCertificadoContainingIgnoreCase(String numeroCertificado);
    
    @Query("SELECT t FROM TecnicoDental t WHERE t.usuario.email = :email")
    Optional<TecnicoDental> findByUsuarioEmail(@Param("email") String email);
    
    List<TecnicoDental> findByExperienciaYearsGreaterThan(Integer years);
    
    @Query("SELECT t FROM TecnicoDental t WHERE t.disponibilidad = true AND t.estado = true")
    List<TecnicoDental> findTecnicosDisponibles();
}
