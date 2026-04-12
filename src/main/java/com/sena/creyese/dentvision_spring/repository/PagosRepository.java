package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Pagos;
import com.sena.creyese.dentvision_spring.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagosRepository extends JpaRepository<Pagos,Long> {

    List<Pagos> findByFacturaOrderByFechaPagoDesc(Factura factura);
    
    List<Pagos> findByMetodoPagoOrderByFechaPagoDesc(String metodoPago);
    
    List<Pagos> findByFechaPagoBetweenOrderByFechaPagoDesc(LocalDate inicio, LocalDate fin);
    
    @Query("SELECT p FROM Pagos p WHERE p.estado = 'COMPLETADO' ORDER BY p.fechaPago DESC")
    List<Pagos> findPagosCompletados();
    
    @Query("SELECT p FROM Pagos p WHERE p.estado = 'PENDIENTE' ORDER BY p.fechaPago ASC")
    List<Pagos> findPagosPendientes();
    
    @Query("SELECT p FROM Pagos p WHERE p.estado = 'RECHAZADO' ORDER BY p.fechaPago DESC")
    List<Pagos> findPagosRechazados();
    
    @Query("SELECT SUM(p.monto) FROM Pagos p WHERE p.estado = 'COMPLETADO' AND p.fechaPago BETWEEN :inicio AND :fin")
    Double sumTotalPagosCompletadosByRangoFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
    
    @Query("SELECT SUM(p.monto) FROM Pagos p WHERE p.estado = 'PENDIENTE'")
    Double sumTotalPagosPendientes();
    
    @Query("SELECT COUNT(p) FROM Pagos p WHERE p.estado = 'PENDIENTE'")
    Long countPagosPendientes();
    
    List<Pagos> findByReferenciaContainingIgnoreCase(String referencia);
    
    @Query("SELECT p FROM Pagos p WHERE p.fechaPago = :fecha")
    List<Pagos> findByFechaPago(@Param("fecha") LocalDate fecha);
    
    @Query("SELECT p FROM Pagos p WHERE p.factura.paciente.idPaciente = :idPaciente ORDER BY p.fechaPago DESC")
    List<Pagos> findByPaciente(@Param("idPaciente") Long idPaciente);
    
    List<Pagos> findByEstadoOrderByFechaPagoDesc(String estado);
}
