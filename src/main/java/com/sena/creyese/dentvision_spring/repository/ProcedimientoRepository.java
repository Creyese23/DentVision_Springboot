package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Procedimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedimientoRepository extends JpaRepository<Procedimiento,Long> {
    
    List<Procedimiento> findByNombreContainingIgnoreCase(String nombre);
    
    List<Procedimiento> findByDescripcionContainingIgnoreCase(String descripcion);
    
    List<Procedimiento> findByEstadoTrue();
    
    List<Procedimiento> findByEstadoFalse();
    
    @Query("SELECT p FROM Procedimiento p WHERE p.estado = true ORDER BY p.nombre ASC")
    List<Procedimiento> findActivosOrderByNombre();
    
    @Query("SELECT COUNT(p) FROM Procedimiento p WHERE p.estado = true")
    Long countProcedimientosActivos();
    
    List<Procedimiento> findByCategoriaContainingIgnoreCase(String categoria);
    
    @Query("SELECT p FROM Procedimiento p WHERE p.precio BETWEEN :min AND :max ORDER BY p.precio ASC")
    List<Procedimiento> findByPrecioBetween(@Param("min") Double min, @Param("max") Double max);
    
    @Query("SELECT p FROM Procedimiento p WHERE p.duracionMinutos BETWEEN :min AND :max ORDER BY p.duracionMinutos ASC")
    List<Procedimiento> findByDuracionBetween(@Param("min") Integer min, @Param("max") Integer max);
    
    List<Procedimiento> findByDuracionMinutosLessThan(Integer duracionMinutos);
    
    List<Procedimiento> findByDuracionMinutosGreaterThan(Integer duracionMinutos);
    
    @Query("SELECT p FROM Procedimiento p WHERE p.requiereAnestesia = true AND p.estado = true")
    List<Procedimiento> findProcedimientosConAnestesia();
    
    @Query("SELECT p FROM Procedimiento p WHERE p.requiereRadiografia = true AND p.estado = true")
    List<Procedimiento> findProcedimientosConRadiografia();
    
    @Query("SELECT p FROM Procedimiento p WHERE p.prioridad = 'ALTA' AND p.estado = true")
    List<Procedimiento> findProcedimientosDeAltaPrioridad();
    
    List<Procedimiento> findByPrioridadContainingIgnoreCase(String prioridad);
    
    @Query("SELECT p FROM Procedimiento p WHERE p.nombre LIKE %:keyword% OR p.descripcion LIKE %:keyword%")
    List<Procedimiento> findByNombreOrDescripcionContaining(@Param("keyword") String keyword);
    
    @Query("SELECT p FROM Procedimiento p WHERE p.estado = true ORDER BY p.precio ASC")
    List<Procedimiento> findActivosOrderByPrecio();
    
    @Query("SELECT p FROM Procedimiento p WHERE p.estado = true ORDER BY p.precio DESC")
    List<Procedimiento> findActivosOrderByPrecioDesc();
}
