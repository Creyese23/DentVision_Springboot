package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Inventario;
import com.sena.creyese.dentvision_spring.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de inventarios en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre registros de inventario, incluyendo control de stock, seguimiento
 * de movimientos y gestión de existencias. Todos los endpoints están bajo la
 * ruta base /api/inventarios y siguen los estándares RESTful.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Endpoints disponibles:
 * - GET /api/inventarios: Obtener todos los registros de inventario
 * - GET /api/inventarios/{id}: Obtener registro de inventario por ID
 * - POST /api/inventarios: Crear nuevo registro de inventario
 * - PUT /api/inventarios/{id}: Actualizar registro de inventario existente
 * - DELETE /api/inventarios/{id}: Eliminar registro de inventario
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    /** Servicio de negocio para la gestión de inventarios */
    private final InventarioService inventarioService;

    /**
     * Constructor que inyecta el servicio de inventarios.
     * 
     * @param inventarioService Servicio de inventarios a inyectar
     */
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    /**
     * Endpoint para obtener todos los registros de inventario del sistema.
     * 
     * Este método retorna una lista completa de todos los registros de inventario
     * en la base de datos. Útil para administración y control de existencias.
     * 
     * @return Lista de todos los registros de inventario
     * @see InventarioService#listarTodos()
     */
    @GetMapping
    public List<Inventario> listarTodos() {
        return inventarioService.listarTodos();
    }

    /**
     * Endpoint para obtener un registro de inventario específico por su ID.
     * 
     * Este método busca un registro de inventario por su identificador único y
     * retorna los datos del registro si existe. Si el registro no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del registro de inventario a buscar
     * @return ResponseEntity con el registro encontrado (200) o 404 si no existe
     * @see InventarioService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable Long id) {
        return inventarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo registro de inventario en el sistema.
     * 
     * Este método recibe los datos de un nuevo registro de inventario, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el registro creado con su ID asignado.
     * 
     * @param inventario Datos del registro de inventario a crear (validados con @Valid)
     * @return ResponseEntity con el registro creado (200) y sus datos
     * @see InventarioService#guardar(Inventario)
     */
    @PostMapping
    public ResponseEntity<Inventario> crear(@Valid @RequestBody Inventario inventario) {
        Inventario nuevo = inventarioService.guardar(inventario);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un registro de inventario existente.
     * 
     * Este método actualiza los datos de un registro de inventario existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos de stock. Si el registro
     * no existe, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del registro de inventario a actualizar
     * @param inventario Datos actualizados del registro de inventario (validados con @Valid)
     * @return ResponseEntity con el registro actualizado (200) o 404 si no existe
     * @see InventarioService#obtenerPorId(Long)
     * @see InventarioService#guardar(Inventario)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Long id, @Valid @RequestBody Inventario inventario) {
        return inventarioService.obtenerPorId(id)
                .map(existente -> {
                    existente.setCantidad(inventario.getCantidad());
                    existente.setFechaInventario(inventario.getFechaInventario());
                    existente.setInsumos(inventario.getInsumos());
                    Inventario actualizado = inventarioService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un registro de inventario del sistema.
     * 
     * Este método elimina un registro de inventario del sistema por su ID.
     * Primero verifica que el registro exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el registro no existe.
     * 
     * @param id Identificador único del registro de inventario a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see InventarioService#obtenerPorId(Long)
     * @see InventarioService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (inventarioService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
