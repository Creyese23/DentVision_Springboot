package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    Optional<Admin> findByDocumento(String documento);
    
    List<Admin> findByNombresContainingIgnoreCase(String nombres);
    
    List<Admin> findByApellidosContainingIgnoreCase(String apellidos);
    
    List<Admin> findByEstadoTrue();
    
    List<Admin> findByEstadoFalse();
    
    @Query("SELECT a FROM Admin a WHERE a.estado = true ORDER BY a.nombres ASC")
    List<Admin> findActivosOrderByNombres();
    
    @Query("SELECT COUNT(a) FROM Admin a WHERE a.estado = true")
    Long countAdminsActivos();
    
    List<Admin> findByNivelAccesoContainingIgnoreCase(String nivelAcceso);
    
    @Query("SELECT a FROM Admin a WHERE a.usuario.email = :email")
    Optional<Admin> findByUsuarioEmail(@Param("email") String email);
    
    @Query("SELECT a FROM Admin a WHERE a.nivelAcceso = 'SUPER_ADMIN' AND a.estado = true")
    List<Admin> findSuperAdminsActivos();
    
    List<Admin> findByDepartamentoContainingIgnoreCase(String departamento);
}
