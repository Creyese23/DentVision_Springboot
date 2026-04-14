package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Pagos;
import com.sena.creyese.dentvision_spring.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de pagos en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Pagos, incluyendo consultas por factura/método, gestión de estados,
 * filtrado por fechas y cálculo de totales.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de pagos por factura y método de pago
 * - Gestión de estados (COMPLETADO, PENDIENTE, RECHAZADO)
 * - Filtrado por rangos de fechas
 * - Cálculo de totales por estado y rangos
 * - Búsqueda por referencia y paciente
 * - Conteo de pagos pendientes
 */
@Repository
public interface PagosRepository extends JpaRepository<Pagos,Long> {

    /**
     * Obtiene todos los pagos de una factura específica ordenados por fecha descendente.
     * 
     * @param factura Factura de la cual se desean consultar los pagos
     * @return Lista de pagos ordenados del más reciente al más antiguo
     */
    List<Pagos> findByFacturaOrderByFechaPagoDesc(Factura factura);
    
    /**
     * Obtiene todos los pagos realizados con un método específico ordenados por fecha descendente.
     * 
     * @param metodoPago Método de pago utilizado (EFECTIVO, TARJETA, TRANSFERENCIA)
     * @return Lista de pagos con ese método ordenados del más reciente al más antiguo
     */
    List<Pagos> findByMetodoPagoOrderByFechaPagoDesc(String metodoPago);
    
    /**
     * Obtiene todos los pagos dentro de un rango de fechas ordenados por fecha descendente.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de pagos en el rango ordenados del más reciente al más antiguo
     */
    List<Pagos> findByFechaPagoBetweenOrderByFechaPagoDesc(LocalDate inicio, LocalDate fin);
    
    /**
     * Obtiene todos los pagos con estado COMPLETADO ordenados por fecha descendente (más recientes primero).
     * 
     * @return Lista de pagos completados ordenados por fecha más reciente
     */
    @Query("SELECT p FROM Pagos p WHERE p.estado = 'COMPLETADO' ORDER BY p.fechaPago DESC")
    List<Pagos> findPagosCompletados();
    
    /**
     * Obtiene todos los pagos con estado PENDIENTE ordenados por fecha ascendente (más antiguos primero).
     * 
     * @return Lista de pagos pendientes ordenados por fecha más antigua
     */
    @Query("SELECT p FROM Pagos p WHERE p.estado = 'PENDIENTE' ORDER BY p.fechaPago ASC")
    List<Pagos> findPagosPendientes();
    
    /**
     * Obtiene todos los pagos con estado RECHAZADO ordenados por fecha descendente (más recientes primero).
     * 
     * @return Lista de pagos rechazados ordenados por fecha más reciente
     */
    @Query("SELECT p FROM Pagos p WHERE p.estado = 'RECHAZADO' ORDER BY p.fechaPago DESC")
    List<Pagos> findPagosRechazados();
    
    /**
     * Calcula la suma total de pagos completados dentro de un rango de fechas específico.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Suma total de pagos completados en el rango
     */
    @Query("SELECT SUM(p.monto) FROM Pagos p WHERE p.estado = 'COMPLETADO' AND p.fechaPago BETWEEN :inicio AND :fin")
    Double sumTotalPagosCompletadosByRangoFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
    
    /**
     * Calcula la suma total de todos los pagos con estado PENDIENTE.
     * 
     * @return Suma total de pagos pendientes
     */
    @Query("SELECT SUM(p.monto) FROM Pagos p WHERE p.estado = 'PENDIENTE'")
    Double sumTotalPagosPendientes();
    
    /**
     * Cuenta el número total de pagos con estado PENDIENTE en el sistema.
     * 
     * @return Número de pagos pendientes
     */
    @Query("SELECT COUNT(p) FROM Pagos p WHERE p.estado = 'PENDIENTE'")
    Long countPagosPendientes();
    
    /**
     * Busca pagos cuya referencia contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param referencia Referencia de pago a buscar
     * @return Lista de pagos que coinciden con la búsqueda
     */
    List<Pagos> findByReferenciaContainingIgnoreCase(String referencia);
    
    /**
     * Obtiene todos los pagos realizados en una fecha específica.
     * 
     * @param fecha Fecha para la cual se desean consultar los pagos
     * @return Lista de pagos de esa fecha
     */
    @Query("SELECT p FROM Pagos p WHERE p.fechaPago = :fecha")
    List<Pagos> findByFechaPago(@Param("fecha") LocalDate fecha);
    
    /**
     * Obtiene todos los pagos de un paciente específico ordenados por fecha descendente.
     * 
     * @param idPaciente ID del paciente
     * @return Lista de pagos del paciente ordenados del más reciente al más antiguo
     */
    @Query("SELECT p FROM Pagos p WHERE p.factura.paciente.idPaciente = :idPaciente ORDER BY p.fechaPago DESC")
    List<Pagos> findByPaciente(@Param("idPaciente") Long idPaciente);
    
    /**
     * Obtiene todos los pagos con un estado específico ordenados por fecha descendente.
     * 
     * @param estado Estado de los pagos a buscar (COMPLETADO, PENDIENTE, RECHAZADO)
     * @return Lista de pagos con ese estado ordenados del más reciente al más antiguo
     */
    List<Pagos> findByEstadoOrderByFechaPagoDesc(String estado);
}
