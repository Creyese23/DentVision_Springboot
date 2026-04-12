package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Entrega;
import com.sena.creyese.dentvision_spring.service.EntregaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de entregas en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre entregas de trabajos dentales, incluyendo registro, seguimiento,
 * confirmación de recepción y gestión de estados. Todos los endpoints están bajo
 * la ruta base /api/entregas y siguen los estándares RESTful.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/entregas: Obtener todas las entregas
 * - GET /api/entregas/{id}: Obtener entrega por ID
 * - POST /api/entregas: Crear nueva entrega
 * - PUT /api/entregas/{id}: Actualizar entrega existente
 * - DELETE /api/entregas/{id}: Eliminar entrega
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    /** Servicio de negocio para la gestión de entregas */
    private final EntregaService entregaService;

    /**
     * Constructor que inyecta el servicio de entregas.
     * 
     * @param entregaService Servicio de entregas a inyectar
     */
    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    /**
     * Endpoint para obtener todas las entregas del sistema.
     * 
     * Este método retorna una lista completa de todas las entregas registradas
     * en la base de datos. Útil para administración y reportes de entregas.
     * 
     * @return Lista de todas las entregas
     * @see EntregaService#listarTodos()
     */
    @GetMapping
    public List<Entrega> listarTodos() {
        return entregaService.listarTodos();
    }

    /**
     * Endpoint para obtener una entrega específica por su ID.
     * 
     * Este método busca una entrega por su identificador único y
     * retorna los datos de la entrega si existe. Si la entrega no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la entrega a buscar
     * @return ResponseEntity con la entrega encontrada (200) o 404 si no existe
     * @see EntregaService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Entrega> obtenerPorId(@PathVariable Long id) {
        return entregaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear una nueva entrega en el sistema.
     * 
     * Este método recibe los datos de una nueva entrega, valida la
     * información de entrada y la registra en la base de datos.
     * Retorna la entrega creada con su ID asignado.
     * 
     * @param entrega Datos de la entrega a crear (validados con @Valid)
     * @return ResponseEntity con la entrega creada (200) y sus datos
     * @see EntregaService#guardar(Entrega)
     */
    @PostMapping
    public ResponseEntity<Entrega> crear(@Valid @RequestBody Entrega entrega) {
        Entrega nueva = entregaService.guardar(entrega);
        return ResponseEntity.ok(nueva);
    }

    /**
     * Endpoint para actualizar una entrega existente.
     * 
     * Este método actualiza los datos de una entrega existente
     * identificada por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos. Si la entrega no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la entrega a actualizar
     * @param entrega Datos actualizados de la entrega (validados con @Valid)
     * @return ResponseEntity con la entrega actualizada (200) o 404 si no existe
     * @see EntregaService#obtenerPorId(Long)
     * @see EntregaService#guardar(Entrega)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Entrega> actualizar(@PathVariable Long id, @Valid @RequestBody Entrega entrega) {
        return entregaService.obtenerPorId(id)
                .map(existente -> {
                    existente.setFecha(entrega.getFecha());
                    existente.setEstado(entrega.isEstado());
                    existente.setObservaciones(entrega.getObservaciones());
                    existente.setOrden(entrega.getOrden());
                    Entrega actualizada = entregaService.guardar(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar una entrega del sistema.
     * 
     * Este método elimina una entrega del sistema por su ID.
     * Primero verifica que la entrega exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si la entrega no existe.
     * 
     * @param id Identificador único de la entrega a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see EntregaService#obtenerPorId(Long)
     * @see EntregaService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (entregaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        entregaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
