package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Long> {
    
    List<Servicios> findByNombreContainingIgnoreCase(String nombre);
    
    List<Servicios> findByCategoriaContainingIgnoreCase(String categoria);
    
    List<Servicios> findByEstadoTrue();
    
    List<Servicios> findByEstadoFalse();
    
    @Query("SELECT s FROM Servicios s WHERE s.estado = true ORDER BY s.nombre ASC")
    List<Servicios> findActivosOrderByNombre();
    
    @Query("SELECT s FROM Servicios s WHERE s.estado = true ORDER BY s.precio ASC")
    List<Servicios> findActivosOrderByPrecio();
    
    @Query("SELECT s FROM Servicios s WHERE s.estado = true ORDER BY s.precio DESC")
    List<Servicios> findActivosOrderByPrecioDesc();
    
    @Query("SELECT COUNT(s) FROM Servicios s WHERE s.estado = true")
    Long countServiciosActivos();
    
    @Query("SELECT s FROM Servicios s WHERE s.precio BETWEEN :min AND :max ORDER BY s.precio ASC")
    List<Servicios> findByPrecioBetween(@Param("min") Double min, @Param("max") Double max);
    
    List<Servicios> findByDuracionMinutos(Integer duracionMinutos);
    
    List<Servicios> findByDuracionMinutosLessThan(Integer duracionMinutos);
    
    List<Servicios> findByDuracionMinutosGreaterThan(Integer duracionMinutos);
    
    @Query("SELECT s FROM Servicios s WHERE s.descripcion LIKE %:keyword% OR s.nombre LIKE %:keyword%")
    List<Servicios> findByNombreOrDescripcionContaining(@Param("keyword") String keyword);
    
    List<Servicios> findByRequiereAutorizacionTrue();
    
    List<Servicios> findByRequiereAutorizacionFalse();
    
    @Query("SELECT s FROM Servicios s WHERE s.estado = true AND s.requiereAutorizacion = false ORDER BY s.nombre ASC")
    List<Servicios> findServiciosDisponiblesDirectamente();
}
