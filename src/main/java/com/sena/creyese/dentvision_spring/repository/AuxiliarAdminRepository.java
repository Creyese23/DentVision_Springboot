package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.AuxiliarAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuxiliarAdminRepository extends JpaRepository<AuxiliarAdmin, Long> {
    
    Optional<AuxiliarAdmin> findByDocumento(String documento);
    
    List<AuxiliarAdmin> findByNombresContainingIgnoreCase(String nombres);
    
    List<AuxiliarAdmin> findByApellidosContainingIgnoreCase(String apellidos);
    
    List<AuxiliarAdmin> findByEstadoTrue();
    
    List<AuxiliarAdmin> findByEstadoFalse();
    
    @Query("SELECT a FROM AuxiliarAdmin a WHERE a.estado = true ORDER BY a.nombres ASC")
    List<AuxiliarAdmin> findActivosOrderByNombres();
    
    @Query("SELECT COUNT(a) FROM AuxiliarAdmin a WHERE a.estado = true")
    Long countAuxiliaresActivos();
    
    List<AuxiliarAdmin> findByAreaTrabajoContainingIgnoreCase(String areaTrabajo);
    
    @Query("SELECT a FROM AuxiliarAdmin a WHERE a.usuario.email = :email")
    Optional<AuxiliarAdmin> findByUsuarioEmail(@Param("email") String email);
    
    List<AuxiliarAdmin> findByTurnoContainingIgnoreCase(String turno);
    
    @Query("SELECT a FROM AuxiliarAdmin a WHERE a.areaTrabajo = :area AND a.estado = true")
    List<AuxiliarAdmin> findAuxiliaresByAreaYActivos(@Param("area") String area);
    
    List<AuxiliarAdmin> findByExperienciaYearsGreaterThan(Integer years);
}
