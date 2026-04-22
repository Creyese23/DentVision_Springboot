package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de pagos en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Pagos, incluyendo consultas por método de pago y monto.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de pagos por método de pago
 * - Filtrado por montos
 */
@Repository
public interface PagosRepository extends JpaRepository<Pagos,Long> {

    /**
     * Obtiene todos los pagos realizados con un método específico.
     * 
     * @param metodPago Método de pago utilizado (EFECTIVO, TARJETA, TRANSFERENCIA)
     * @return Lista de pagos con ese método
     */
    List<Pagos> findByMetodPago(String metodPago);
    
    /**
     * Obtiene todos los pagos cuyo monto sea mayor al valor especificado.
     * 
     * @param monto Monto mínimo para filtrar
     * @return Lista de pagos con monto mayor al especificado
     */
    List<Pagos> findByMontoGreaterThan(Double monto);
}
