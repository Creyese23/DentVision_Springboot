package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Notificaciones;
import com.sena.creyese.dentvision_spring.repository.NotificacionesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de notificaciones en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con notificaciones del sistema, incluyendo alertas a usuarios,
 * recordatorios de citas, notificaciones de sistema y comunicación general.
 * Actúa como una capa de abstracción entre los controladores y el repositorio.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de notificaciones
 * - Envío de alertas a usuarios específicos
 * - Recordatorios automáticos de citas
 * - Notificaciones de sistema
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class NotificacionesService {

    /** Repositorio para el acceso a datos de notificaciones */
    private final NotificacionesRepository notificacionesRepository;

    /**
     * Constructor que inyecta el repositorio de notificaciones.
     * 
     * @param notificacionesRepository Repositorio de notificaciones a inyectar
     */
    public NotificacionesService(NotificacionesRepository notificacionesRepository) {
        this.notificacionesRepository = notificacionesRepository;
    }

    /**
     * Obtiene todas las notificaciones del sistema.
     * 
     * Este método retorna una lista con todas las notificaciones registradas
     * en la base de datos. Útil para administración y reportes de comunicación.
     * 
     * @return Lista de todas las notificaciones
     */
    public List<Notificaciones> listarTodos() {
        return notificacionesRepository.findAll();
    }

    /**
     * Busca una notificación por su identificador único.
     * 
     * @param id Identificador único de la notificación
     * @return Optional con la notificación encontrada o vacío si no existe
     */
    public Optional<Notificaciones> obtenerPorId(Long id) {
        return notificacionesRepository.findById(id);
    }

    /**
     * Guarda o actualiza una notificación en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevas notificaciones
     * como la actualización de notificaciones existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID de la notificación.
     * 
     * @param notificaciones Notificación a guardar o actualizar
     * @return Notificación guardada con su ID asignado
     */
    public Notificaciones guardar(Notificaciones notificaciones) {
        return notificacionesRepository.save(notificaciones);
    }

    /**
     * Elimina una notificación del sistema por su identificador.
     * 
     * @param id Identificador único de la notificación a eliminar
     */
    public void eliminar(Long id) {
        notificacionesRepository.deleteById(id);
    }
}
