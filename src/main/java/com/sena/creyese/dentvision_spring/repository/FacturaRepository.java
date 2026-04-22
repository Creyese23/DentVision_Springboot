package com.sena.creyese.dentvision_spring.repository;



import com.sena.creyese.dentvision_spring.modelo.Factura;

import com.sena.creyese.dentvision_spring.modelo.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;



import java.util.List;



/**

 * Repositorio Spring Data JPA para la gestión de facturas en el sistema DentVision.

 * 

 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos

 * sobre la entidad Factura, incluyendo consultas por paciente, gestión de estados,

 * cálculos de totales y seguimiento de facturas vencidas.

 * 

 * @author Creyese

 * @version 1.0

 * @since 2026

 * 

 * Funcionalidades principales:

 * - Consulta de facturas por paciente y estado

 * - Filtrado por rangos de fechas

 * - Gestión de facturas pendientes/pagadas/vencidas

 * - Cálculo de totales por estado y rangos

 * - Búsqueda por método de pago y número de factura

 * - Control de vencimientos automáticos

 */

@Repository

public interface FacturaRepository extends JpaRepository<Factura, Long> {

    

    /**

     * Obtiene todas las facturas de un paciente específico ordenadas por fecha descendente.

     * 

     * @param paciente Paciente del cual se desean consultar las facturas

     * @return Lista de facturas ordenadas de la más reciente a la más antigua

     */

    List<Factura> findByPacienteOrderByFechaDesc(Paciente paciente);

    

    /**

     * Obtiene todas las facturas con un estado específico ordenadas por fecha descendente.

     * 

     * @param estado Estado de las facturas a buscar (true=pagada, false=pendiente)

     * @return Lista de facturas con ese estado ordenadas de la más reciente a la más antigua

     */

    List<Factura> findByEstadoOrderByFechaDesc(boolean estado);

    

    /**

     * Obtiene todas las facturas dentro de un rango de fechas ordenadas por fecha descendente.

     * 

     * @param inicio Fecha de inicio del rango

     * @param fin Fecha de fin del rango

     * @return Lista de facturas en el rango ordenadas de la más reciente a la más antigua

     */

    List<Factura> findByFechaBetweenOrderByFechaDesc(java.util.Date inicio, java.util.Date fin);

    

    /**

     * Obtiene todas las facturas con estado PENDIENTE ordenadas por fecha ascendente (más antiguas primero).

     * 

     * @return Lista de facturas pendientes ordenadas por fecha más antigua

     */

    @Query("SELECT f FROM Factura f WHERE f.estado = false ORDER BY f.fecha ASC")

    List<Factura> findFacturasPendientes();

    

    /**

     * Obtiene todas las facturas con estado PAGADA ordenadas por fecha descendente (más recientes primero).

     * 

     * @return Lista de facturas pagadas ordenadas por fecha más reciente

     */

    @Query("SELECT f FROM Factura f WHERE f.estado = true ORDER BY f.fecha DESC")

    List<Factura> findFacturasPagadas();

    

    /**

     * Obtiene todas las facturas con estado VENCIDA ordenadas por fecha descendente (más recientes primero).

     * 

     * @return Lista de facturas vencidas ordenadas por fecha más reciente

     */

    @Query("SELECT f FROM Factura f WHERE f.estado = false ORDER BY f.fecha DESC")

    List<Factura> findFacturasVencidas();

    

    /**

     * Cuenta el número total de facturas con estado PENDIENTE en el sistema.

     * 

     * @return Número de facturas pendientes

     */

    @Query("SELECT COUNT(f) FROM Factura f WHERE f.estado = false")

    Long countFacturasPendientes();

    

    /**

     * Calcula la suma total de todas las facturas con estado PENDIENTE.

     * 

     * @return Suma total de facturas pendientes

     */

    @Query("SELECT SUM(f.valor) FROM Factura f WHERE f.estado = false")

    Double sumTotalFacturasPendientes();

    

    /**

     * Calcula la suma total de facturas pagadas dentro de un rango de fechas específico.

     * 

     * @param inicio Fecha de inicio del rango

     * @param fin Fecha de fin del rango

     * @return Suma total de facturas pagadas en el rango

     */

    @Query("SELECT SUM(f.valor) FROM Factura f WHERE f.estado = true AND f.fecha BETWEEN :inicio AND :fin")

    Double sumTotalPagadasByRangoFechas(@Param("inicio") java.util.Date inicio, @Param("fin") java.util.Date fin);

    

    /**

     * Obtiene todas las facturas pagadas con un método específico ordenadas por fecha descendente.

     * 

     * @param metodoPago Método de pago utilizado (EFECTIVO, TARJETA, TRANSFERENCIA)

     * @return Lista de facturas con ese método de pago ordenadas por fecha más reciente

     */

    List<Factura> findByMetodoPagoOrderByFechaDesc(String metodoPago);

    

    /**

     * Obtiene todas las facturas cuya fecha de vencimiento es anterior a la fecha actual y no están pagadas.

     * 

     * @param currentDate Fecha actual para comparación

     * @return Lista de facturas vencidas por fecha

     */

    @Query("SELECT f FROM Factura f WHERE f.fechaVencimiento < :currentDate AND f.estado = false")

    List<Factura> findFacturasVencidasPorFecha(@Param("currentDate") java.util.Date currentDate);

    

    /**

     * Busca facturas cuyo número contenga el texto especificado (ignora mayúsculas/minúsculas).

     * 

     * @param numeroFactura Texto a buscar en los números de factura

     * @return Lista de facturas que coinciden con la búsqueda

     */

    List<Factura> findByNumeroFacturaContainingIgnoreCase(String numeroFactura);

}

