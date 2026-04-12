package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.DetalleFactura;
import com.sena.creyese.dentvision_spring.modelo.Factura;
import com.sena.creyese.dentvision_spring.modelo.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

    List<DetalleFactura> findByFacturaOrderByServicioNombre(Factura factura);
    
    List<DetalleFactura> findByServicioOrderByFacturaFechaDesc(Servicios servicio);
    
    @Query("SELECT SUM(df.subtotal) FROM DetalleFactura df WHERE df.factura.idFactura = :idFactura")
    Double sumSubtotalByFactura(@Param("idFactura") Long idFactura);
    
    @Query("SELECT COUNT(df) FROM DetalleFactura df WHERE df.factura.idFactura = :idFactura")
    Long countDetallesByFactura(@Param("idFactura") Long idFactura);
    
    @Query("SELECT df FROM DetalleFactura df WHERE df.factura.idFactura = :idFactura")
    List<DetalleFactura> findByFacturaId(@Param("idFactura") Long idFactura);
    
    @Query("SELECT df FROM DetalleFactura df WHERE df.servicio.idServicio = :idServicio")
    List<DetalleFactura> findByServicioId(@Param("idServicio") Long idServicio);
    
    List<DetalleFactura> findByCantidadGreaterThan(Integer cantidad);
    
    @Query("SELECT df FROM DetalleFactura df WHERE df.precioUnitario BETWEEN :min AND :max ORDER BY df.precioUnitario ASC")
    List<DetalleFactura> findByPrecioUnitarioBetween(@Param("min") Double min, @Param("max") Double max);
    
    @Query("SELECT df FROM DetalleFactura df WHERE df.descuento > 0 ORDER BY df.descuento DESC")
    List<DetalleFactura> findDetallesConDescuento();
    
    @Query("SELECT SUM(df.descuento) FROM DetalleFactura df WHERE df.factura.idFactura = :idFactura")
    Double sumDescuentosByFactura(@Param("idFactura") Long idFactura);
    
    @Query("SELECT df FROM DetalleFactura df WHERE df.factura.paciente.idPaciente = :idPaciente ORDER BY df.factura.fechaFactura DESC")
    List<DetalleFactura> findByPacienteId(@Param("idPaciente") Long idPaciente);
    
    List<DetalleFactura> findByDescripcionContainingIgnoreCase(String descripcion);
    
    @Query("SELECT df FROM DetalleFactura df WHERE df.subtotal BETWEEN :min AND :max ORDER BY df.subtotal DESC")
    List<DetalleFactura> findBySubtotalBetween(@Param("min") Double min, @Param("max") Double max);
}
