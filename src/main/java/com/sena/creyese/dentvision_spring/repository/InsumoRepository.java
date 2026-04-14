package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de insumos en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Insumo, incluyendo consultas por nombre/categoría, control de stock,
 * filtrado por proveedores y gestión de precios.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de insumos por nombre y categoría
 * - Control de niveles de stock y alertas
 * - Filtrado por proveedor y rangos de precios
 * - Gestión de estados de insumos
 * - Identificación de insumos con bajo stock
 */
@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    
    /**
     * Busca insumos cuyo nombre contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombre Texto a buscar en los nombres de insumos
     * @return Lista de insumos que coinciden con la búsqueda
     */
    List<Insumo> findByNombreContainingIgnoreCase(String nombre);
    
    /**
     * Busca insumos cuya categoría contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param categoria Categoría a buscar (MATERIAL, EQUIPO, CONSUMIBLE)
     * @return Lista de insumos de esa categoría
     */
    List<Insumo> findByCategoriaContainingIgnoreCase(String categoria);
    
    /**
     * Obtiene todos los insumos cuyo stock mínimo sea mayor al valor especificado.
     * 
     * @param stockMinimo Valor mínimo de stock para filtrar
     * @return Lista de insumos con stock mínimo mayor al especificado
     */
    List<Insumo> findByStockMinimoGreaterThan(Integer stockMinimo);
    
    /**
     * Obtiene todos los insumos cuyo stock actual es menor que su stock mínimo.
     * 
     * @return Lista de insumos con stock por debajo del mínimo requerido
     */
    List<Insumo> findByStockActualLessThanStockMinimo();
    
    /**
     * Obtiene todos los insumos cuyo stock actual es igual o menor que su stock mínimo.
     * 
     * @return Lista de insumos con bajo stock que requieren reposición
     */
    @Query("SELECT i FROM Insumo i WHERE i.stockActual <= i.stockMinimo")
    List<Insumo> findInsumosConBajoStock();
    
    /**
     * Cuenta el número total de insumos con stock bajo o igual al mínimo.
     * 
     * @return Número de insumos que requieren reposición
     */
    @Query("SELECT COUNT(i) FROM Insumo i WHERE i.stockActual <= i.stockMinimo")
    Long countInsumosConBajoStock();
    
    /**
     * Busca insumos cuyo proveedor contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param proveedor Nombre del proveedor a buscar
     * @return Lista de insumos de ese proveedor
     */
    List<Insumo> findByProveedorContainingIgnoreCase(String proveedor);
    
    /**
     * Obtiene todos los insumos cuyo precio unitario esté dentro de un rango específico ordenados ascendentemente.
     * 
     * @param min Precio mínimo del rango
     * @param max Precio máximo del rango
     * @return Lista de insumos ordenados por precio unitario ascendente
     */
    @Query("SELECT i FROM Insumo i WHERE i.precioUnitario BETWEEN :min AND :max ORDER BY i.precioUnitario ASC")
    List<Insumo> findByPrecioBetween(@Param("min") Double min, @Param("max") Double max);
    
    /**
     * Obtiene todos los insumos activos ordenados alfabéticamente por nombre.
     * 
     * @return Lista de insumos activos ordenados por nombre
     */
    @Query("SELECT i FROM Insumo i WHERE i.estado = true ORDER BY i.nombre ASC")
    List<Insumo> findActivosOrderByNombre();
    
    /**
     * Obtiene todos los insumos con estado activo (true).
     * 
     * @return Lista de insumos activos
     */
    List<Insumo> findByEstadoTrue();
    
    /**
     * Obtiene todos los insumos con estado inactivo (false).
     * 
     * @return Lista de insumos inactivos
     */
    List<Insumo> findByEstadoFalse();
}
