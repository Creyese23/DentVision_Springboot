package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Inventario;
import com.sena.creyese.dentvision_spring.modelo.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {

    List<Inventario> findByInsumoOrderByFechaMovimientoDesc(Insumo insumo);
    
    List<Inventario> findByTipoMovimientoOrderByFechaMovimientoDesc(String tipoMovimiento);
    
    List<Inventario> findByFechaMovimientoBetweenOrderByFechaMovimientoDesc(LocalDate inicio, LocalDate fin);
    
    @Query("SELECT i FROM Inventario i WHERE i.insumo.stockActual <= i.insumo.stockMinimo")
    List<Inventario> findMovimientosDeInsumosConBajoStock();
    
    @Query("SELECT i FROM Inventario i WHERE i.tipoMovimiento = 'ENTRADA' ORDER BY i.fechaMovimiento DESC")
    List<Inventario> findEntradas();
    
    @Query("SELECT i FROM Inventario i WHERE i.tipoMovimiento = 'SALIDA' ORDER BY i.fechaMovimiento DESC")
    List<Inventario> findSalidas();
    
    @Query("SELECT i FROM Inventario i WHERE i.insumo.idInsumo = :idInsumo AND i.tipoMovimiento = :tipoMovimiento ORDER BY i.fechaMovimiento DESC")
    List<Inventario> findByInsumoAndTipoMovimiento(@Param("idInsumo") Long idInsumo, @Param("tipoMovimiento") String tipoMovimiento);
    
    @Query("SELECT SUM(i.cantidad) FROM Inventario i WHERE i.insumo.idInsumo = :idInsumo AND i.tipoMovimiento = 'ENTRADA'")
    Long sumTotalEntradasByInsumo(@Param("idInsumo") Long idInsumo);
    
    @Query("SELECT SUM(i.cantidad) FROM Inventario i WHERE i.insumo.idInsumo = :idInsumo AND i.tipoMovimiento = 'SALIDA'")
    Long sumTotalSalidasByInsumo(@Param("idInsumo") Long idInsumo);
    
    List<Inventario> findByResponsableContainingIgnoreCase(String responsable);
    
    @Query("SELECT i FROM Inventario i WHERE i.fechaMovimiento = :fecha ORDER BY i.fechaMovimiento DESC")
    List<Inventario> findByFechaMovimiento(@Param("fecha") LocalDate fecha);
    
    List<Inventario> findByNotasContainingIgnoreCase(String notas);
}
