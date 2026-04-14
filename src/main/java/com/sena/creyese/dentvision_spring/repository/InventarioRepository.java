package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Inventario;
import com.sena.creyese.dentvision_spring.modelo.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de movimientos de inventario en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Inventario, incluyendo consultas por insumo/tipo movimiento, control de stock,
 * filtrado por fechas y seguimiento de entradas/salidas.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de movimientos por insumo y tipo
 * - Gestión de entradas y salidas de inventario
 * - Filtrado por rangos de fechas
 * - Control de movimientos de insumos con bajo stock
 * - Cálculo de totales por insumo y tipo
 * - Búsqueda por responsable y notas
 */
@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {

    /**
     * Obtiene todos los movimientos de un insumo específico ordenados por fecha descendente.
     * 
     * @param insumo Insumo del cual se desean consultar los movimientos
     * @return Lista de movimientos ordenados del más reciente al más antiguo
     */
    List<Inventario> findByInsumoOrderByFechaMovimientoDesc(Insumo insumo);
    
    /**
     * Obtiene todos los movimientos de un tipo específico ordenados por fecha descendente.
     * 
     * @param tipoMovimiento Tipo de movimiento (ENTRADA, SALIDA)
     * @return Lista de movimientos ordenados del más reciente al más antiguo
     */
    List<Inventario> findByTipoMovimientoOrderByFechaMovimientoDesc(String tipoMovimiento);
    
    /**
     * Obtiene todos los movimientos dentro de un rango de fechas ordenados por fecha descendente.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de movimientos en el rango ordenados del más reciente al más antiguo
     */
    List<Inventario> findByFechaMovimientoBetweenOrderByFechaMovimientoDesc(LocalDate inicio, LocalDate fin);
    
    /**
     * Obtiene todos los movimientos de insumos que tienen stock bajo o igual al mínimo.
     * 
     * @return Lista de movimientos de insumos con bajo stock
     */
    @Query("SELECT i FROM Inventario i WHERE i.insumo.stockActual <= i.insumo.stockMinimo")
    List<Inventario> findMovimientosDeInsumosConBajoStock();
    
    /**
     * Obtiene todos los movimientos de tipo ENTRADA ordenados por fecha descendente.
     * 
     * @return Lista de entradas de inventario ordenadas de la más reciente a la más antigua
     */
    @Query("SELECT i FROM Inventario i WHERE i.tipoMovimiento = 'ENTRADA' ORDER BY i.fechaMovimiento DESC")
    List<Inventario> findEntradas();
    
    /**
     * Obtiene todos los movimientos de tipo SALIDA ordenados por fecha descendente.
     * 
     * @return Lista de salidas de inventario ordenadas de la más reciente a la más antigua
     */
    @Query("SELECT i FROM Inventario i WHERE i.tipoMovimiento = 'SALIDA' ORDER BY i.fechaMovimiento DESC")
    List<Inventario> findSalidas();
    
    /**
     * Obtiene todos los movimientos de un insumo específico y tipo específico ordenados por fecha descendente.
     * 
     * @param idInsumo ID del insumo
     * @param tipoMovimiento Tipo de movimiento (ENTRADA, SALIDA)
     * @return Lista de movimientos filtrados ordenados del más reciente al más antiguo
     */
    @Query("SELECT i FROM Inventario i WHERE i.insumo.idInsumo = :idInsumo AND i.tipoMovimiento = :tipoMovimiento ORDER BY i.fechaMovimiento DESC")
    List<Inventario> findByInsumoAndTipoMovimiento(@Param("idInsumo") Long idInsumo, @Param("tipoMovimiento") String tipoMovimiento);
    
    /**
     * Calcula la suma total de cantidades de entradas para un insumo específico.
     * 
     * @param idInsumo ID del insumo
     * @return Suma total de cantidades de entradas
     */
    @Query("SELECT SUM(i.cantidad) FROM Inventario i WHERE i.insumo.idInsumo = :idInsumo AND i.tipoMovimiento = 'ENTRADA'")
    Long sumTotalEntradasByInsumo(@Param("idInsumo") Long idInsumo);
    
    /**
     * Calcula la suma total de cantidades de salidas para un insumo específico.
     * 
     * @param idInsumo ID del insumo
     * @return Suma total de cantidades de salidas
     */
    @Query("SELECT SUM(i.cantidad) FROM Inventario i WHERE i.insumo.idInsumo = :idInsumo AND i.tipoMovimiento = 'SALIDA'")
    Long sumTotalSalidasByInsumo(@Param("idInsumo") Long idInsumo);
    
    /**
     * Busca movimientos cuyo responsable contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param responsable Nombre del responsable a buscar
     * @return Lista de movimientos que coinciden con la búsqueda
     */
    List<Inventario> findByResponsableContainingIgnoreCase(String responsable);
    
    /**
     * Obtiene todos los movimientos realizados en una fecha específica ordenados por fecha descendente.
     * 
     * @param fecha Fecha para la cual se desean consultar los movimientos
     * @return Lista de movimientos de esa fecha
     */
    @Query("SELECT i FROM Inventario i WHERE i.fechaMovimiento = :fecha ORDER BY i.fechaMovimiento DESC")
    List<Inventario> findByFechaMovimiento(@Param("fecha") LocalDate fecha);
    
    /**
     * Busca movimientos cuyas notas contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param notas Texto a buscar en las notas de los movimientos
     * @return Lista de movimientos que coinciden con la búsqueda
     */
    List<Inventario> findByNotasContainingIgnoreCase(String notas);
}
