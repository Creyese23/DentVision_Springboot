package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;
import com.sena.creyese.dentvision_spring.repository.OrdenTrabajoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de órdenes de trabajo en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con órdenes de trabajo dental, incluyendo creación, seguimiento,
 * asignación a técnicos y gestión de estados. Actúa como una capa de
 * abstracción entre los controladores y el repositorio de órdenes de trabajo.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Gestión CRUD de órdenes de trabajo
 * - Asignación de trabajos a técnicos dentales
 * - Seguimiento de estados de producción
 * - Integración con detalles de orden
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class OrdenTrabajoService {

    /** Repositorio para el acceso a datos de órdenes de trabajo */
    private final OrdenTrabajoRepository ordenTrabajoRepository;

    /**
     * Constructor que inyecta el repositorio de órdenes de trabajo.
     * 
     * @param ordenTrabajoRepository Repositorio de órdenes de trabajo a inyectar
     */
    public OrdenTrabajoService(OrdenTrabajoRepository ordenTrabajoRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    /**
     * Obtiene todas las órdenes de trabajo del sistema.
     * 
     * Este método retorna una lista con todas las órdenes registradas
     * en la base de datos. Útil para administración y reportes de producción.
     * 
     * @return Lista de todas las órdenes de trabajo
     */
    public List<OrdenTrabajo> listarTodos() {
        return ordenTrabajoRepository.findAll();
    }

    /**
     * Busca una orden de trabajo por su identificador único.
     * 
     * @param id Identificador único de la orden de trabajo
     * @return Optional con la orden encontrada o vacío si no existe
     */
    public Optional<OrdenTrabajo> obtenerPorId(Long id) {
        return ordenTrabajoRepository.findById(id);
    }

    /**
     * Guarda o actualiza una orden de trabajo en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevas órdenes
     * como la actualización de órdenes existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID de la orden.
     * 
     * @param ordenTrabajo Orden de trabajo a guardar o actualizar
     * @return Orden de trabajo guardada con su ID asignado
     */
    public OrdenTrabajo guardar(OrdenTrabajo ordenTrabajo) {
        return ordenTrabajoRepository.save(ordenTrabajo);
    }

    /**
     * Elimina una orden de trabajo del sistema por su identificador.
     * 
     * @param id Identificador único de la orden de trabajo a eliminar
     */
    public void eliminar(Long id) {
        ordenTrabajoRepository.deleteById(id);
    }
}
