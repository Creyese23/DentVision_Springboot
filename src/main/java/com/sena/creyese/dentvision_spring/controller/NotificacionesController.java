package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Notificaciones;
import com.sena.creyese.dentvision_spring.service.NotificacionesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de notificaciones en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre notificaciones del sistema, incluyendo envío, seguimiento de lectura,
 * gestión de alertas y comunicaciones importantes. Todos los endpoints están bajo la
 * ruta base /api/notificaciones y siguen los estándares RESTful.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/notificaciones: Obtener todas las notificaciones
 * - GET /api/notificaciones/{id}: Obtener notificación por ID
 * - POST /api/notificaciones: Crear nueva notificación
 * - PUT /api/notificaciones/{id}: Actualizar notificación existente
 * - DELETE /api/notificaciones/{id}: Eliminar notificación
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionesController {

    /** Servicio de negocio para la gestión de notificaciones */
    private final NotificacionesService notificacionesService;

    /**
     * Constructor que inyecta el servicio de notificaciones.
     * 
     * @param notificacionesService Servicio de notificaciones a inyectar
     */
    public NotificacionesController(NotificacionesService notificacionesService) {
        this.notificacionesService = notificacionesService;
    }

    /**
     * Endpoint para obtener todas las notificaciones del sistema.
     * 
     * Este método retorna una lista completa de todas las notificaciones registradas
     * en la base de datos. Útil para administración y seguimiento de comunicaciones.
     * 
     * @return Lista de todas las notificaciones
     * @see NotificacionesService#listarTodos()
     */
    @GetMapping
    public List<Notificaciones> listarTodos() {
        return notificacionesService.listarTodos();
    }

    /**
     * Endpoint para obtener una notificación específica por su ID.
     * 
     * Este método busca una notificación por su identificador único y
     * retorna los datos de la notificación si existe. Si la notificación no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la notificación a buscar
     * @return ResponseEntity con la notificación encontrada (200) o 404 si no existe
     * @see NotificacionesService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Notificaciones> obtenerPorId(@PathVariable Long id) {
        return notificacionesService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear una nueva notificación en el sistema.
     * 
     * Este método recibe los datos de una nueva notificación, valida la
     * información de entrada y la registra en la base de datos.
     * Retorna la notificación creada con su ID asignado.
     * 
     * @param notificaciones Datos de la notificación a crear (validados con @Valid)
     * @return ResponseEntity con la notificación creada (200) y sus datos
     * @see NotificacionesService#guardar(Notificaciones)
     */
    @PostMapping
    public ResponseEntity<Notificaciones> crear(@Valid @RequestBody Notificaciones notificaciones) {
        Notificaciones nueva = notificacionesService.guardar(notificaciones);
        return ResponseEntity.ok(nueva);
    }

    /**
     * Endpoint para actualizar una notificación existente.
     * 
     * Este método actualiza los datos de una notificación existente
     * identificada por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos de comunicación. Si la notificación
     * no existe, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la notificación a actualizar
     * @param notificaciones Datos actualizados de la notificación (validados con @Valid)
     * @return ResponseEntity con la notificación actualizada (200) o 404 si no existe
     * @see NotificacionesService#obtenerPorId(Long)
     * @see NotificacionesService#guardar(Notificaciones)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Notificaciones> actualizar(@PathVariable Long id, @Valid @RequestBody Notificaciones notificaciones) {
        return notificacionesService.obtenerPorId(id)
                .map(existente -> {
                    existente.setMensaje(notificaciones.getMensaje());
                    existente.setLeido(notificaciones.isLeido());
                    existente.setFechaNotificacion(notificaciones.getFechaNotificacion());
                    existente.setUsuario(notificaciones.getUsuario());
                    Notificaciones actualizada = notificacionesService.guardar(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar una notificación del sistema.
     * 
     * Este método elimina una notificación del sistema por su ID.
     * Primero verifica que la notificación exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si la notificación no existe.
     * 
     * @param id Identificador único de la notificación a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see NotificacionesService#obtenerPorId(Long)
     * @see NotificacionesService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (notificacionesService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        notificacionesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
