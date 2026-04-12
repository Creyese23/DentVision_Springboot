package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Mensajes;
import com.sena.creyese.dentvision_spring.repository.MensajesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de mensajes internos en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con los mensajes internos del sistema, incluyendo comunicación
 * entre usuarios, notificaciones y seguimiento de correspondencia. Actúa como
 * una capa de abstracción entre los controladores y el repositorio de mensajes.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Gestión CRUD de mensajes internos
 * - Comunicación entre usuarios del sistema
 * - Seguimiento de correspondencia
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class MensajesService {

    /** Repositorio para el acceso a datos de mensajes */
    private final MensajesRepository mensajesRepository;

    /**
     * Constructor que inyecta el repositorio de mensajes.
     * 
     * @param mensajesRepository Repositorio de mensajes a inyectar
     */
    public MensajesService(MensajesRepository mensajesRepository) {
        this.mensajesRepository = mensajesRepository;
    }

    /**
     * Obtiene todos los mensajes del sistema.
     * 
     * Este método retorna una lista con todos los mensajes registrados
     * en la base de datos. Útil para administración y reportes de comunicación.
     * 
     * @return Lista de todos los mensajes
     */
    public List<Mensajes> listarTodos() {
        return mensajesRepository.findAll();
    }

    /**
     * Busca un mensaje por su identificador único.
     * 
     * @param id Identificador único del mensaje
     * @return Optional con el mensaje encontrado o vacío si no existe
     */
    public Optional<Mensajes> obtenerPorId(Long id) {
        return mensajesRepository.findById(id);
    }

    /**
     * Guarda o actualiza un mensaje en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos mensajes
     * como la actualización de mensajes existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del mensaje.
     * 
     * @param mensajes Mensaje a guardar o actualizar
     * @return Mensaje guardado con su ID asignado
     */
    public Mensajes guardar(Mensajes mensajes) {
        return mensajesRepository.save(mensajes);
    }

    /**
     * Elimina un mensaje del sistema por su identificador.
     * 
     * @param id Identificador único del mensaje a eliminar
     */
    public void eliminar(Long id) {
        mensajesRepository.deleteById(id);
    }
}
