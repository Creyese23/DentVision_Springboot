package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.DetalleFactura;
import com.sena.creyese.dentvision_spring.repository.DetalleFacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de detalles de facturas en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con los detalles específicos de las facturas, incluyendo gestión
 * de servicios facturados, cantidades, precios unitarios, subtotales y descuentos.
 * Actúa como una capa de abstracción entre los controladores y el repositorio.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Gestión CRUD de detalles de facturas
 * - Cálculo automático de subtotales y totales
 * - Gestión de descuentos por detalle
 * - Control de impuestos y cargos adicionales
 * - Integración con facturas principales
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class DetalleFacturaService {

    /** Repositorio para el acceso a datos de detalles de facturas */
    private final DetalleFacturaRepository detalleFacturaRepository;

    /**
     * Constructor que inyecta el repositorio de detalles de facturas.
     * 
     * @param detalleFacturaRepository Repositorio de detalles de facturas a inyectar
     */
    public DetalleFacturaService(DetalleFacturaRepository detalleFacturaRepository) {
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    /**
     * Obtiene todos los detalles de facturas del sistema.
     * 
     * Este método retorna una lista con todos los detalles registrados
     * en la base de datos. Útil para administración y reportes financieros.
     * 
     * @return Lista de todos los detalles de facturas
     */
    public List<DetalleFactura> listarTodos() {
        return detalleFacturaRepository.findAll();
    }

    /**
     * Busca un detalle de factura por su identificador único.
     * 
     * @param id Identificador único del detalle de factura
     * @return Optional con el detalle encontrado o vacío si no existe
     */
    public Optional<DetalleFactura> obtenerPorId(Long id) {
        return detalleFacturaRepository.findById(id);
    }

    /**
     * Guarda o actualiza un detalle de factura en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos detalles
     * como la actualización de detalles existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del detalle.
     * 
     * @param detalleFactura Detalle de factura a guardar o actualizar
     * @return Detalle de factura guardado con su ID asignado
     */
    public DetalleFactura guardar(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    /**
     * Elimina un detalle de factura del sistema por su identificador.
     * 
     * @param id Identificador único del detalle de factura a eliminar
     */
    public void eliminar(Long id) {
        detalleFacturaRepository.deleteById(id);
    }
}
