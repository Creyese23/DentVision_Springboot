package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Servicios;
import com.sena.creyese.dentvision_spring.repository.ServiciosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de servicios dentales en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con servicios dentales, incluyendo gestión de tratamientos,
 * precios, categorías y disponibilidad de servicios. Actúa como una capa
 * de abstracción entre los controladores y el repositorio de servicios.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Gestión CRUD de servicios dentales
 * - Gestión de precios y categorías
 * - Control de disponibilidad de servicios
 * - Integración con facturación y citas
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class ServiciosService {

    /** Repositorio para el acceso a datos de servicios */
    private final ServiciosRepository serviciosRepository;

    /**
     * Constructor que inyecta el repositorio de servicios.
     * 
     * @param serviciosRepository Repositorio de servicios a inyectar
     */
    public ServiciosService(ServiciosRepository serviciosRepository) {
        this.serviciosRepository = serviciosRepository;
    }

    /**
     * Obtiene todos los servicios dentales del sistema.
     * 
     * Este método retorna una lista con todos los servicios registrados
     * en la base de datos. Útil para administración y catálogo de servicios.
     * 
     * @return Lista de todos los servicios dentales
     */
    public List<Servicios> listarTodos() {
        return serviciosRepository.findAll();
    }

    /**
     * Busca un servicio dental por su identificador único.
     * 
     * @param id Identificador único del servicio
     * @return Optional con el servicio encontrado o vacío si no existe
     */
    public Optional<Servicios> obtenerPorId(Long id) {
        return serviciosRepository.findById(id);
    }

    /**
     * Guarda o actualiza un servicio dental en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos servicios
     * como la actualización de servicios existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del servicio.
     * 
     * @param servicios Servicio dental a guardar o actualizar
     * @return Servicio guardado con su ID asignado
     */
    public Servicios guardar(Servicios servicios) {
        return serviciosRepository.save(servicios);
    }

    /**
     * Elimina un servicio dental del sistema por su identificador.
     * 
     * @param id Identificador único del servicio a eliminar
     */
    public void eliminar(Long id) {
        serviciosRepository.deleteById(id);
    }
}
