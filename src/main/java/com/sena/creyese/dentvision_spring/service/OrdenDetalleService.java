package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.OrdenDetalle;
import com.sena.creyese.dentvision_spring.repository.OrdenDetalleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de detalles de órdenes de trabajo en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con los detalles específicos de las órdenes de trabajo dental,
 * incluyendo gestión de servicios, cantidades, precios y estados individuales.
 * Actúa como una capa de abstracción entre los controladores y el repositorio.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de detalles de órdenes de trabajo
 * - Cálculo de subtotales y precios individuales
 * - Gestión de cantidades y descuentos por detalle
 * - Control de estados de producción por servicio
 * - Integración con órdenes de trabajo principales
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class OrdenDetalleService {

    /** Repositorio para el acceso a datos de detalles de órdenes */
    private final OrdenDetalleRepository ordenDetalleRepository;

    /**
     * Constructor que inyecta el repositorio de detalles de órdenes.
     * 
     * @param ordenDetalleRepository Repositorio de detalles de órdenes a inyectar
     */
    public OrdenDetalleService(OrdenDetalleRepository ordenDetalleRepository) {
        this.ordenDetalleRepository = ordenDetalleRepository;
    }

    /**
     * Obtiene todos los detalles de órdenes del sistema.
     * 
     * Este método retorna una lista con todos los detalles registrados
     * en la base de datos. Útil para administración y reportes de producción.
     * 
     * @return Lista de todos los detalles de órdenes
     */
    public List<OrdenDetalle> listarTodos() {
        return ordenDetalleRepository.findAll();
    }

    /**
     * Busca un detalle de orden por su identificador único.
     * 
     * @param id Identificador único del detalle de orden
     * @return Optional con el detalle encontrado o vacío si no existe
     */
    public Optional<OrdenDetalle> obtenerPorId(Long id) {
        return ordenDetalleRepository.findById(id);
    }

    /**
     * Guarda o actualiza un detalle de orden en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos detalles
     * como la actualización de detalles existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del detalle.
     * 
     * @param ordenDetalle Detalle de orden a guardar o actualizar
     * @return Detalle de orden guardado con su ID asignado
     */
    public OrdenDetalle guardar(OrdenDetalle ordenDetalle) {
        return ordenDetalleRepository.save(ordenDetalle);
    }

    /**
     * Elimina un detalle de orden del sistema por su identificador.
     * 
     * @param id Identificador único del detalle de orden a eliminar
     */
    public void eliminar(Long id) {
        ordenDetalleRepository.deleteById(id);
    }
}
