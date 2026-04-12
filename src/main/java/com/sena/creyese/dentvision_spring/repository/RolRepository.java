package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Roles;
import com.sena.creyese.dentvision_spring.enums.TipoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Roles,Long> {
    
    Optional<Roles> findByNombreRol(TipoRol nombreRol);
    
    List<Roles> findByNombreRolIn(List<TipoRol> nombresRol);
    
    List<Roles> findByNombreRolContainingIgnoreCase(String nombreRol);
    
    @Query("SELECT r FROM Roles r WHERE r.nombreRol = :nombreRol")
    Optional<Roles> findByNombreRolQuery(@Param("nombreRol") TipoRol nombreRol);
    
    @Query("SELECT COUNT(r) FROM Roles r WHERE r.nombreRol = :nombreRol")
    Long countByNombreRol(@Param("nombreRol") TipoRol nombreRol);
    
    @Query("SELECT r FROM Roles r ORDER BY r.nombreRol ASC")
    List<Roles> findAllOrderByNombreRol();
    
    @Query("SELECT r FROM Roles r WHERE r.descripcion IS NOT NULL ORDER BY r.nombreRol ASC")
    List<Roles> findRolesWithDescripcion();
    
    List<Roles> findByDescripcionContainingIgnoreCase(String descripcion);
    
    @Query("SELECT r FROM Roles r WHERE r.nombreRol IN ('ADMIN', 'SUPER_ADMIN')")
    List<Roles> findAdminRoles();
    
    @Query("SELECT r FROM Roles r WHERE r.nombreRol IN ('ODONTOLOGO', 'TECNICO_DENTAL')")
    List<Roles> findClinicalRoles();
    
    @Query("SELECT r FROM Roles r WHERE r.nombreRol = 'PACIENTE'")
    Optional<Roles> findPacienteRole();
}
