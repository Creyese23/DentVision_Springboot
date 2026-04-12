package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.OrdenDetalle;
import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;
import com.sena.creyese.dentvision_spring.modelo.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle,Long> {

    List<OrdenDetalle> findByOrdenTrabajoOrderByServicioNombre(OrdenTrabajo ordenTrabajo);
    
    List<OrdenDetalle> findByServicioOrderByOrdenTrabajoFechaCreacionDesc(Servicios servicio);
    
    @Query("SELECT SUM(od.subtotal) FROM OrdenDetalle od WHERE od.ordenTrabajo.idOrdenTrabajo = :idOrdenTrabajo")
    Double sumSubtotalByOrdenTrabajo(@Param("idOrdenTrabajo") Long idOrdenTrabajo);
    
    @Query("SELECT COUNT(od) FROM OrdenDetalle od WHERE od.ordenTrabajo.idOrdenTrabajo = :idOrdenTrabajo")
    Long countDetallesByOrdenTrabajo(@Param("idOrdenTrabajo") Long idOrdenTrabajo);
    
    @Query("SELECT od FROM OrdenDetalle od WHERE od.ordenTrabajo.idOrdenTrabajo = :idOrdenTrabajo")
    List<OrdenDetalle> findByOrdenTrabajoId(@Param("idOrdenTrabajo") Long idOrdenTrabajo);
    
    @Query("SELECT od FROM OrdenDetalle od WHERE od.servicio.idServicio = :idServicio")
    List<OrdenDetalle> findByServicioId(@Param("idServicio") Long idServicio);
    
    List<OrdenDetalle> findByCantidadGreaterThan(Integer cantidad);
    
    @Query("SELECT od FROM OrdenDetalle od WHERE od.precioUnitario BETWEEN :min AND :max ORDER BY od.precioUnitario ASC")
    List<OrdenDetalle> findByPrecioUnitarioBetween(@Param("min") Double min, @Param("max") Double max);
    
    @Query("SELECT od FROM OrdenDetalle od WHERE od.descuento > 0 ORDER BY od.descuento DESC")
    List<OrdenDetalle> findDetallesConDescuento();
    
    @Query("SELECT SUM(od.descuento) FROM OrdenDetalle od WHERE od.ordenTrabajo.idOrdenTrabajo = :idOrdenTrabajo")
    Double sumDescuentosByOrdenTrabajo(@Param("idOrdenTrabajo") Long idOrdenTrabajo);
    
    @Query("SELECT od FROM OrdenDetalle od WHERE od.ordenTrabajo.paciente.idPaciente = :idPaciente ORDER BY od.ordenTrabajo.fechaCreacion DESC")
    List<OrdenDetalle> findByPacienteId(@Param("idPaciente") Long idPaciente);
    
    List<OrdenDetalle> findByDescripcionContainingIgnoreCase(String descripcion);
    
    @Query("SELECT od FROM OrdenDetalle od WHERE od.subtotal BETWEEN :min AND :max ORDER BY od.subtotal DESC")
    List<OrdenDetalle> findBySubtotalBetween(@Param("min") Double min, @Param("max") Double max);
    
    @Query("SELECT od FROM OrdenDetalle od WHERE od.estado = 'COMPLETADO' AND od.ordenTrabajo.idOrdenTrabajo = :idOrdenTrabajo")
    List<OrdenDetalle> findDetallesCompletadosByOrdenTrabajo(@Param("idOrdenTrabajo") Long idOrdenTrabajo);
    
    @Query("SELECT od FROM OrdenDetalle od WHERE od.estado = 'PENDIENTE' AND od.ordenTrabajo.idOrdenTrabajo = :idOrdenTrabajo")
    List<OrdenDetalle> findDetallesPendientesByOrdenTrabajo(@Param("idOrdenTrabajo") Long idOrdenTrabajo);
}
