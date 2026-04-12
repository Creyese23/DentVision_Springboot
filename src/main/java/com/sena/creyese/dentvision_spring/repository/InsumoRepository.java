package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    
    List<Insumo> findByNombreContainingIgnoreCase(String nombre);
    
    List<Insumo> findByCategoriaContainingIgnoreCase(String categoria);
    
    List<Insumo> findByStockMinimoGreaterThan(Integer stockMinimo);
    
    List<Insumo> findByStockActualLessThanStockMinimo();
    
    @Query("SELECT i FROM Insumo i WHERE i.stockActual <= i.stockMinimo")
    List<Insumo> findInsumosConBajoStock();
    
    @Query("SELECT COUNT(i) FROM Insumo i WHERE i.stockActual <= i.stockMinimo")
    Long countInsumosConBajoStock();
    
    List<Insumo> findByProveedorContainingIgnoreCase(String proveedor);
    
    @Query("SELECT i FROM Insumo i WHERE i.precioUnitario BETWEEN :min AND :max ORDER BY i.precioUnitario ASC")
    List<Insumo> findByPrecioBetween(@Param("min") Double min, @Param("max") Double max);
    
    @Query("SELECT i FROM Insumo i WHERE i.estado = true ORDER BY i.nombre ASC")
    List<Insumo> findActivosOrderByNombre();
    
    List<Insumo> findByEstadoTrue();
    
    List<Insumo> findByEstadoFalse();
}
