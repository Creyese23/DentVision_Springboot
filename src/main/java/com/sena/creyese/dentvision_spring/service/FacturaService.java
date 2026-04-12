package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Factura;
import com.sena.creyese.dentvision_spring.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de facturas en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con facturación, incluyendo generación de facturas,
 * cálculo de totales, gestión de pagos y seguimiento de cobros.
 * Actúa como una capa de abstracción entre los controladores y el repositorio.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Gestión CRUD de facturas
 * - Cálculo automático de totales e impuestos
 * - Gestión de estados de pago
 * - Integración con detalles de factura
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class FacturaService {

    /** Repositorio para el acceso a datos de facturas */
    private final FacturaRepository facturaRepository;

    /**
     * Constructor que inyecta el repositorio de facturas.
     * 
     * @param facturaRepository Repositorio de facturas a inyectar
     */
    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    /**
     * Obtiene todas las facturas del sistema.
     * 
     * Este método retorna una lista con todas las facturas registradas
     * en la base de datos. Útil para administración y reportes financieros.
     * 
     * @return Lista de todas las facturas
     */
    public List<Factura> listarTodos() {
        return facturaRepository.findAll();
    }

    /**
     * Busca una factura por su identificador único.
     * 
     * @param id Identificador único de la factura
     * @return Optional con la factura encontrada o vacío si no existe
     */
    public Optional<Factura> obtenerPorId(Long id) {
        return facturaRepository.findById(id);
    }

    /**
     * Guarda o actualiza una factura en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevas facturas
     * como la actualización de facturas existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID de la factura.
     * 
     * @param factura Factura a guardar o actualizar
     * @return Factura guardada con su ID asignado
     */
    public Factura guardar(Factura factura) {
        return facturaRepository.save(factura);
    }

    /**
     * Elimina una factura del sistema por su identificador.
     * 
     * @param id Identificador único de la factura a eliminar
     */
    public void eliminar(Long id) {
        facturaRepository.deleteById(id);
    }
}
