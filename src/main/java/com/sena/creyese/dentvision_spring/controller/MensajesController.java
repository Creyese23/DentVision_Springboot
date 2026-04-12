package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Mensajes;
import com.sena.creyese.dentvision_spring.service.MensajesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de mensajes en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre mensajes del sistema, incluyendo envío, recepción, seguimiento
 * y gestión de comunicaciones internas. Todos los endpoints están bajo la
 * ruta base /api/mensajes y siguen los estándares RESTful.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/mensajes: Obtener todos los mensajes
 * - GET /api/mensajes/{id}: Obtener mensaje por ID
 * - POST /api/mensajes: Crear nuevo mensaje
 * - PUT /api/mensajes/{id}: Actualizar mensaje existente
 * - DELETE /api/mensajes/{id}: Eliminar mensaje
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/mensajes")
public class MensajesController {

    /** Servicio de negocio para la gestión de mensajes */
    private final MensajesService mensajesService;

    /**
     * Constructor que inyecta el servicio de mensajes.
     * 
     * @param mensajesService Servicio de mensajes a inyectar
     */
    public MensajesController(MensajesService mensajesService) {
        this.mensajesService = mensajesService;
    }

    /**
     * Endpoint para obtener todos los mensajes del sistema.
     * 
     * Este método retorna una lista completa de todos los mensajes registrados
     * en la base de datos. Útil para administración y seguimiento de comunicaciones.
     * 
     * @return Lista de todos los mensajes
     * @see MensajesService#listarTodos()
     */
    @GetMapping
    public List<Mensajes> listarTodos() {
        return mensajesService.listarTodos();
    }

    /**
     * Endpoint para obtener un mensaje específico por su ID.
     * 
     * Este método busca un mensaje por su identificador único y
     * retorna los datos del mensaje si existe. Si el mensaje no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del mensaje a buscar
     * @return ResponseEntity con el mensaje encontrado (200) o 404 si no existe
     * @see MensajesService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Mensajes> obtenerPorId(@PathVariable Long id) {
        return mensajesService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo mensaje en el sistema.
     * 
     * Este método recibe los datos de un nuevo mensaje, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el mensaje creado con su ID asignado.
     * 
     * @param mensajes Datos del mensaje a crear (validados con @Valid)
     * @return ResponseEntity con el mensaje creado (200) y sus datos
     * @see MensajesService#guardar(Mensajes)
     */
    @PostMapping
    public ResponseEntity<Mensajes> crear(@Valid @RequestBody Mensajes mensajes) {
        Mensajes nuevo = mensajesService.guardar(mensajes);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un mensaje existente.
     * 
     * Este método actualiza los datos de un mensaje existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos de comunicación. Si el mensaje
     * no existe, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del mensaje a actualizar
     * @param mensajes Datos actualizados del mensaje (validados con @Valid)
     * @return ResponseEntity con el mensaje actualizado (200) o 404 si no existe
     * @see MensajesService#obtenerPorId(Long)
     * @see MensajesService#guardar(Mensajes)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Mensajes> actualizar(@PathVariable Long id, @Valid @RequestBody Mensajes mensajes) {
        return mensajesService.obtenerPorId(id)
                .map(existente -> {
                    existente.setMensaje(mensajes.getMensaje());
                    existente.setFechaEnvio(mensajes.getFechaEnvio());
                    existente.setChat(mensajes.getChat());
                    existente.setUsuario(mensajes.getUsuario());
                    Mensajes actualizado = mensajesService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un mensaje del sistema.
     * 
     * Este método elimina un mensaje del sistema por su ID.
     * Primero verifica que el mensaje exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el mensaje no existe.
     * 
     * @param id Identificador único del mensaje a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see MensajesService#obtenerPorId(Long)
     * @see MensajesService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (mensajesService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mensajesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
