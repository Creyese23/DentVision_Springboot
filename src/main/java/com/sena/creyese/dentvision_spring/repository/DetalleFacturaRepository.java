package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.DetalleFactura;
import com.sena.creyese.dentvision_spring.modelo.Factura;
import com.sena.creyese.dentvision_spring.modelo.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de detalles de facturas en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad DetalleFactura, incluyendo consultas por factura/servicio, cálculos de totales,
 * filtrado por precios y gestión de descuentos.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de detalles por factura y servicio
 * - Cálculo de subtotales y descuentos
 * - Filtrado por rangos de precios
 * - Búsqueda por cantidades y descripciones
 * - Conteo de detalles por factura
 * - Consultas por paciente asociado
 */
@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

    /**
     * Obtiene todos los detalles de una factura ordenados por nombre de servicio.
     * 
     * @param factura Factura de la cual se desean consultar los detalles
     * @return Lista de detalles ordenados alfabéticamente por nombre de servicio
     */
    List<DetalleFactura> findByFacturaOrderByServicioNombre(Factura factura);
    
    /**
     * Obtiene todos los detalles que contienen un servicio específico ordenados por fecha de factura descendente.
     * 
     * @param servicio Servicio del cual se desean consultar los detalles
     * @return Lista de detalles ordenados por fecha de factura más reciente
     */
    List<DetalleFactura> findByServicioOrderByFacturaFechaDesc(Servicios servicio);
    
    /**
     * Calcula la suma total de los subtotales de todos los detalles de una factura específica.
     * 
     * @param idFactura ID de la factura
     * @return Suma total de subtotales de la factura
     */
    @Query("SELECT SUM(df.subtotal) FROM DetalleFactura df WHERE df.factura.idFactura = :idFactura")
    Double sumSubtotalByFactura(@Param("idFactura") Long idFactura);
    
    /**
     * Cuenta el número total de detalles que tiene una factura específica.
     * 
     * @param idFactura ID de la factura
     * @return Número de detalles de la factura
     */
    @Query("SELECT COUNT(df) FROM DetalleFactura df WHERE df.factura.idFactura = :idFactura")
    Long countDetallesByFactura(@Param("idFactura") Long idFactura);
    
    /**
     * Obtiene todos los detalles de una factura específica por su ID.
     * 
     * @param idFactura ID de la factura
     * @return Lista de detalles de la factura
     */
    @Query("SELECT df FROM DetalleFactura df WHERE df.factura.idFactura = :idFactura")
    List<DetalleFactura> findByFacturaId(@Param("idFactura") Long idFactura);
    
    /**
     * Obtiene todos los detalles que contienen un servicio específico por su ID.
     * 
     * @param idServicio ID del servicio
     * @return Lista de detalles que contienen ese servicio
     */
    @Query("SELECT df FROM DetalleFactura df WHERE df.servicio.idServicio = :idServicio")
    List<DetalleFactura> findByServicioId(@Param("idServicio") Long idServicio);
    
    /**
     * Obtiene todos los detalles cuya cantidad sea mayor al valor especificado.
     * 
     * @param cantidad Cantidad mínima para filtrar
     * @return Lista de detalles con cantidad mayor a la especificada
     */
    List<DetalleFactura> findByCantidadGreaterThan(Integer cantidad);
    
    /**
     * Obtiene todos los detalles cuyo precio unitario esté dentro de un rango específico ordenados ascendentemente.
     * 
     * @param min Precio mínimo del rango
     * @param max Precio máximo del rango
     * @return Lista de detalles ordenados por precio unitario ascendente
     */
    @Query("SELECT df FROM DetalleFactura df WHERE df.precioUnitario BETWEEN :min AND :max ORDER BY df.precioUnitario ASC")
    List<DetalleFactura> findByPrecioUnitarioBetween(@Param("min") Double min, @Param("max") Double max);
    
    /**
     * Obtiene todos los detalles que tienen descuento aplicado ordenados por monto de descuento descendente.
     * 
     * @return Lista de detalles con descuento ordenados por mayor descuento
     */
    @Query("SELECT df FROM DetalleFactura df WHERE df.descuento > 0 ORDER BY df.descuento DESC")
    List<DetalleFactura> findDetallesConDescuento();
    
    /**
     * Calcula la suma total de descuentos aplicados en todos los detalles de una factura específica.
     * 
     * @param idFactura ID de la factura
     * @return Suma total de descuentos de la factura
     */
    @Query("SELECT SUM(df.descuento) FROM DetalleFactura df WHERE df.factura.idFactura = :idFactura")
    Double sumDescuentosByFactura(@Param("idFactura") Long idFactura);
    
    /**
     * Obtiene todos los detalles de facturas de un paciente específico ordenados por fecha de factura descendente.
     * 
     * @param idPaciente ID del paciente
     * @return Lista de detalles ordenados por fecha de factura más reciente
     */
    @Query("SELECT df FROM DetalleFactura df WHERE df.factura.paciente.idPaciente = :idPaciente ORDER BY df.factura.fechaFactura DESC")
    List<DetalleFactura> findByPacienteId(@Param("idPaciente") Long idPaciente);
    
    /**
     * Busca detalles cuya descripción contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param descripcion Texto a buscar en las descripciones
     * @return Lista de detalles que coinciden con la búsqueda
     */
    List<DetalleFactura> findByDescripcionContainingIgnoreCase(String descripcion);
    
    /**
     * Obtiene todos los detalles cuyo subtotal esté dentro de un rango específico ordenados por subtotal descendente.
     * 
     * @param min Subtotal mínimo del rango
     * @param max Subtotal máximo del rango
     * @return Lista de detalles ordenados por subtotal descendente
     */
    @Query("SELECT df FROM DetalleFactura df WHERE df.subtotal BETWEEN :min AND :max ORDER BY df.subtotal DESC")
    List<DetalleFactura> findBySubtotalBetween(@Param("min") Double min, @Param("max") Double max);
}
