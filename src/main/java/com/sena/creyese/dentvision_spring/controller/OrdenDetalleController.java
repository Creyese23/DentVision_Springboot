package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.OrdenDetalle;
import com.sena.creyese.dentvision_spring.service.OrdenDetalleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de detalles de órdenes en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre detalles específicos de órdenes de trabajo dental, incluyendo
 * descripción de procedimientos, cantidades y seguimiento de ejecución. Todos los
 * endpoints están bajo la ruta base /api/orden-detalles y siguen los estándares RESTful.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/orden-detalles: Obtener todos los detalles de órdenes
 * - GET /api/orden-detalles/{id}: Obtener detalle de orden por ID
 * - POST /api/orden-detalles: Crear nuevo detalle de orden
 * - PUT /api/orden-detalles/{id}: Actualizar detalle de orden existente
 * - DELETE /api/orden-detalles/{id}: Eliminar detalle de orden
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/orden-detalles")
public class OrdenDetalleController {

    /** Servicio de negocio para la gestión de detalles de órdenes */
    private final OrdenDetalleService ordenDetalleService;

    /**
     * Constructor que inyecta el servicio de detalles de órdenes.
     * 
     * @param ordenDetalleService Servicio de detalles de órdenes a inyectar
     */
    public OrdenDetalleController(OrdenDetalleService ordenDetalleService) {
        this.ordenDetalleService = ordenDetalleService;
    }

    /**
     * Endpoint para obtener todos los detalles de órdenes del sistema.
     * 
     * Este método retorna una lista completa de todos los detalles de órdenes
     * registrados en la base de datos. Útil para administración y seguimiento
     * de trabajos.
     * 
     * @return Lista de todos los detalles de órdenes
     * @see OrdenDetalleService#listarTodos()
     */
    @GetMapping
    public List<OrdenDetalle> listarTodos() {
        return ordenDetalleService.listarTodos();
    }

    /**
     * Endpoint para obtener un detalle de orden específico por su ID.
     * 
     * Este método busca un detalle de orden por su identificador único y
     * retorna los datos del detalle si existe. Si el detalle no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del detalle de orden a buscar
     * @return ResponseEntity con el detalle encontrado (200) o 404 si no existe
     * @see OrdenDetalleService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdenDetalle> obtenerPorId(@PathVariable Long id) {
        return ordenDetalleService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo detalle de orden en el sistema.
     * 
     * Este método recibe los datos de un nuevo detalle de orden, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el detalle creado con su ID asignado.
     * 
     * @param ordenDetalle Datos del detalle de orden a crear (validados con @Valid)
     * @return ResponseEntity con el detalle creado (200) y sus datos
     * @see OrdenDetalleService#guardar(OrdenDetalle)
     */
    @PostMapping
    public ResponseEntity<OrdenDetalle> crear(@Valid @RequestBody OrdenDetalle ordenDetalle) {
        OrdenDetalle nuevo = ordenDetalleService.guardar(ordenDetalle);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un detalle de orden existente.
     * 
     * Este método actualiza los datos de un detalle de orden existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos. Si el detalle no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del detalle de orden a actualizar
     * @param ordenDetalle Datos actualizados del detalle de orden (validados con @Valid)
     * @return ResponseEntity con el detalle actualizado (200) o 404 si no existe
     * @see OrdenDetalleService#obtenerPorId(Long)
     * @see OrdenDetalleService#guardar(OrdenDetalle)
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrdenDetalle> actualizar(@PathVariable Long id, @Valid @RequestBody OrdenDetalle ordenDetalle) {
        return ordenDetalleService.obtenerPorId(id)
                .map(existente -> {
                    existente.setDescripcion(ordenDetalle.getDescripcion());
                    existente.setCantidad(ordenDetalle.getCantidad());
                    existente.setOrden(ordenDetalle.getOrden());
                    OrdenDetalle actualizado = ordenDetalleService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un detalle de orden del sistema.
     * 
     * Este método elimina un detalle de orden del sistema por su ID.
     * Primero verifica que el detalle exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el detalle no existe.
     * 
     * @param id Identificador único del detalle de orden a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see OrdenDetalleService#obtenerPorId(Long)
     * @see OrdenDetalleService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (ordenDetalleService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ordenDetalleService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
