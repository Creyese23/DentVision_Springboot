package com.sena.creyese.dentvision_spring.repository;



import com.sena.creyese.dentvision_spring.modelo.OrdenDetalle;

import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;

import com.sena.creyese.dentvision_spring.modelo.Servicios;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;



import java.util.List;



/**

 * Repositorio Spring Data JPA para la gestión de detalles de órdenes de trabajo en el sistema DentVision.

 * 

 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos

 * sobre la entidad OrdenDetalle, incluyendo consultas por orden/servicio, cálculos de totales,

 * filtrado por precios y gestión de estados de detalles.

 * 

 * @author Creyese

 * @version 1.0

 * @since 2026

 * 

 * Funcionalidades principales:

 * - Consulta de detalles por orden de trabajo y servicio

 * - Cálculo de subtotales y descuentos por orden

 * - Filtrado por rangos de precios

 * - Búsqueda por cantidades y descripciones

 * - Conteo de detalles por orden

 * - Gestión de estados de detalles

 * - Consultas por paciente asociado

 */

@Repository

public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle,Long> {



    /**

     * Obtiene todos los detalles de una orden de trabajo específica ordenados por nombre de servicio.

     * 

     * @param ordenTrabajo Orden de trabajo de la cual se desean consultar los detalles

     * @return Lista de detalles ordenados alfabéticamente por nombre de servicio

     */

    @Query("SELECT od FROM OrdenDetalle od WHERE od.orden = :ordenTrabajo ORDER BY od.servicio.nombreServicios")
    List<OrdenDetalle> findByOrdenTrabajoOrderByServicioNombre(OrdenTrabajo ordenTrabajo);

    

    /**

     * Obtiene todos los detalles que contienen un servicio específico ordenados por fecha de creación de orden descendente.

     * 

     * @param servicio Servicio del cual se desean consultar los detalles

     * @return Lista de detalles ordenados por fecha de orden más reciente

     */

    @Query("SELECT od FROM OrdenDetalle od WHERE od.servicio = :servicio ORDER BY od.orden.fecha DESC")
    List<OrdenDetalle> findByServicioOrderByOrdenTrabajoFechaCreacionDesc(Servicios servicio);

    

    /**

     * Calcula la suma total de los subtotales de todos los detalles de una orden de trabajo específica.

     * 

     * @param idOrdenTrabajo ID de la orden de trabajo

     * @return Suma total de subtotales de la orden

     */

    @Query("SELECT SUM(od.subtotal) FROM OrdenDetalle od WHERE od.orden.idOrdenTrabajo = :idOrdenTrabajo")

    Double sumSubtotalByOrdenTrabajo(@Param("idOrdenTrabajo") Long idOrdenTrabajo);

    

    /**

     * Cuenta el número total de detalles que tiene una orden de trabajo específica.

     * 

     * @param idOrdenTrabajo ID de la orden de trabajo

     * @return Número de detalles de la orden

     */

    @Query("SELECT COUNT(od) FROM OrdenDetalle od WHERE od.orden.idOrdenTrabajo = :idOrdenTrabajo")

    Long countDetallesByOrdenTrabajo(@Param("idOrdenTrabajo") Long idOrdenTrabajo);

    

    /**

     * Obtiene todos los detalles de una orden de trabajo específica por su ID.

     * 

     * @param idOrdenTrabajo ID de la orden de trabajo

     * @return Lista de detalles de la orden

     */

    @Query("SELECT od FROM OrdenDetalle od WHERE od.orden.idOrdenTrabajo = :idOrdenTrabajo")

    List<OrdenDetalle> findByOrdenTrabajoId(@Param("idOrdenTrabajo") Long idOrdenTrabajo);

    

    /**

     * Obtiene todos los detalles que contienen un servicio específico por su ID.

     * 

     * @param idServicio ID del servicio

     * @return Lista de detalles que contienen ese servicio

     */

    @Query("SELECT od FROM OrdenDetalle od WHERE od.servicio.idServicios = :idServicio")

    List<OrdenDetalle> findByServicioId(@Param("idServicio") Long idServicio);

    

    /**

     * Obtiene todos los detalles cuya cantidad sea mayor al valor especificado.

     * 

     * @param cantidad Cantidad mínima para filtrar

     * @return Lista de detalles con cantidad mayor a la especificada

     */

    List<OrdenDetalle> findByCantidadGreaterThan(Integer cantidad);

    

    /**

     * Obtiene todos los detalles cuyo precio unitario esté dentro de un rango específico ordenados ascendentemente.

     * 

     * @param min Precio mínimo del rango

     * @param max Precio máximo del rango

     * @return Lista de detalles ordenados por precio unitario ascendente

     */

    @Query("SELECT od FROM OrdenDetalle od WHERE od.precioUnitario BETWEEN :min AND :max ORDER BY od.precioUnitario ASC")

    List<OrdenDetalle> findByPrecioUnitarioBetween(@Param("min") Double min, @Param("max") Double max);

    

    /**

     * Obtiene todos los detalles que tienen descuento aplicado ordenados por monto de descuento descendente.

     * 

     * @return Lista de detalles con descuento ordenados por mayor descuento

     */

    @Query("SELECT od FROM OrdenDetalle od WHERE od.descuento > 0 ORDER BY od.descuento DESC")

    List<OrdenDetalle> findDetallesConDescuento();

    

    /**

     * Calcula la suma total de descuentos aplicados en todos los detalles de una orden de trabajo específica.

     * 

     * @param idOrdenTrabajo ID de la orden de trabajo

     * @return Suma total de descuentos de la orden

     */

    @Query("SELECT SUM(od.descuento) FROM OrdenDetalle od WHERE od.orden.idOrdenTrabajo = :idOrdenTrabajo")

    Double sumDescuentosByOrdenTrabajo(@Param("idOrdenTrabajo") Long idOrdenTrabajo);

    

    /**

     * Obtiene todos los detalles de órdenes de un paciente específico ordenados por fecha de creación de orden descendente.

     * 

     * @param idPaciente ID del paciente

     * @return Lista de detalles ordenados por fecha de orden más reciente

     */

    @Query("SELECT od FROM OrdenDetalle od WHERE od.orden.paciente.idPaciente = :idPaciente ORDER BY od.orden.fecha DESC")

    List<OrdenDetalle> findByPacienteId(@Param("idPaciente") Long idPaciente);

    

    /**

     * Busca detalles cuya descripción contenga el texto especificado (ignora mayúsculas/minúsculas).

     * 

     * @param descripcion Texto a buscar en las descripciones

     * @return Lista de detalles que coinciden con la búsqueda

     */

    List<OrdenDetalle> findByDescripcionContainingIgnoreCase(String descripcion);

    

    /**

     * Obtiene todos los detalles cuyo subtotal esté dentro de un rango específico ordenados por subtotal descendente.

     * 

     * @param min Subtotal mínimo del rango

     * @param max Subtotal máximo del rango

     * @return Lista de detalles ordenados por subtotal descendente

     */

    @Query("SELECT od FROM OrdenDetalle od WHERE od.subtotal BETWEEN :min AND :max ORDER BY od.subtotal DESC")

    List<OrdenDetalle> findBySubtotalBetween(@Param("min") Double min, @Param("max") Double max);

    

    /**

     * Obtiene todos los detalles completados de una orden de trabajo específica.

     * 

     * @param idOrdenTrabajo ID de la orden de trabajo

     * @return Lista de detalles completados de la orden

     */

    @Query("SELECT od FROM OrdenDetalle od WHERE od.estado = 'COMPLETADO' AND od.orden.idOrdenTrabajo = :idOrdenTrabajo")

    List<OrdenDetalle> findDetallesCompletadosByOrdenTrabajo(@Param("idOrdenTrabajo") Long idOrdenTrabajo);

    

    /**

     * Obtiene todos los detalles pendientes de una orden de trabajo específica.

     * 

     * @param idOrdenTrabajo ID de la orden de trabajo

     * @return Lista de detalles pendientes de la orden

     */

    @Query("SELECT od FROM OrdenDetalle od WHERE od.estado = 'PENDIENTE' AND od.orden.idOrdenTrabajo = :idOrdenTrabajo")

    List<OrdenDetalle> findDetallesPendientesByOrdenTrabajo(@Param("idOrdenTrabajo") Long idOrdenTrabajo);

}

