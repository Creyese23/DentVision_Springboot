package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Factura;
import com.sena.creyese.dentvision_spring.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    
    List<Factura> findByPacienteOrderByFechaFacturaDesc(Paciente paciente);
    
    List<Factura> findByEstadoOrderByFechaFacturaDesc(String estado);
    
    List<Factura> findByFechaFacturaBetweenOrderByFechaFacturaDesc(LocalDate inicio, LocalDate fin);
    
    @Query("SELECT f FROM Factura f WHERE f.estado = 'PENDIENTE' ORDER BY f.fechaFactura ASC")
    List<Factura> findFacturasPendientes();
    
    @Query("SELECT f FROM Factura f WHERE f.estado = 'PAGADA' ORDER BY f.fechaFactura DESC")
    List<Factura> findFacturasPagadas();
    
    @Query("SELECT f FROM Factura f WHERE f.estado = 'VENCIDA' ORDER BY f.fechaFactura DESC")
    List<Factura> findFacturasVencidas();
    
    @Query("SELECT COUNT(f) FROM Factura f WHERE f.estado = 'PENDIENTE'")
    Long countFacturasPendientes();
    
    @Query("SELECT SUM(f.total) FROM Factura f WHERE f.estado = 'PENDIENTE'")
    Double sumTotalFacturasPendientes();
    
    @Query("SELECT SUM(f.total) FROM Factura f WHERE f.estado = 'PAGADA' AND f.fechaFactura BETWEEN :inicio AND :fin")
    Double sumTotalPagadasByRangoFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
    
    List<Factura> findByMetodoPagoOrderByFechaFacturaDesc(String metodoPago);
    
    @Query("SELECT f FROM Factura f WHERE f.fechaVencimiento < :currentDate AND f.estado != 'PAGADA'")
    List<Factura> findFacturasVencidasPorFecha(@Param("currentDate") LocalDate currentDate);
    
    List<Factura> findByNumeroFacturaContainingIgnoreCase(String numeroFactura);
}
