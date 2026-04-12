package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Procedimiento;
import com.sena.creyese.dentvision_spring.service.ProcedimientoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de procedimientos dentales en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre procedimientos dentales, incluyendo descripción, estado, fecha
 * y relación con citas. Todos los endpoints están bajo la ruta base
 * /api/procedimientos y siguen los estándares RESTful.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/procedimientos: Obtener todos los procedimientos
 * - GET /api/procedimientos/{id}: Obtener procedimiento por ID
 * - POST /api/procedimientos: Crear nuevo procedimiento
 * - PUT /api/procedimientos/{id}: Actualizar procedimiento existente
 * - DELETE /api/procedimientos/{id}: Eliminar procedimiento
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/procedimientos")
public class ProcedimientoController {

    /** Servicio de negocio para la gestión de procedimientos */
    private final ProcedimientoService procedimientoService;

    /**
     * Constructor que inyecta el servicio de procedimientos.
     * 
     * @param procedimientoService Servicio de procedimientos a inyectar
     */
    public ProcedimientoController(ProcedimientoService procedimientoService) {
        this.procedimientoService = procedimientoService;
    }

    /**
     * Endpoint para obtener todos los procedimientos del sistema.
     * 
     * Este método retorna una lista completa de todos los procedimientos registrados
     * en la base de datos. Útil para administración y catálogo de servicios.
     * 
     * @return Lista de todos los procedimientos
     * @see ProcedimientoService#listarTodos()
     */
    @GetMapping
    public List<Procedimiento> listarTodos() {
        return procedimientoService.listarTodos();
    }

    /**
     * Endpoint para obtener un procedimiento específico por su ID.
     * 
     * Este método busca un procedimiento por su identificador único y
     * retorna los datos del procedimiento si existe. Si el procedimiento no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del procedimiento a buscar
     * @return ResponseEntity con el procedimiento encontrado (200) o 404 si no existe
     * @see ProcedimientoService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Procedimiento> obtenerPorId(@PathVariable Long id) {
        return procedimientoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo procedimiento en el sistema.
     * 
     * Este método recibe los datos de un nuevo procedimiento, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el procedimiento creado con su ID asignado.
     * 
     * @param procedimiento Datos del procedimiento a crear (validados con @Valid)
     * @return ResponseEntity con el procedimiento creado (200) y sus datos
     * @see ProcedimientoService#guardar(Procedimiento)
     */
    @PostMapping
    public ResponseEntity<Procedimiento> crear(@Valid @RequestBody Procedimiento procedimiento) {
        Procedimiento nuevo = procedimientoService.guardar(procedimiento);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un procedimiento existente.
     * 
     * Este método actualiza los datos de un procedimiento existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos. Si el procedimiento no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del procedimiento a actualizar
     * @param procedimiento Datos actualizados del procedimiento (validados con @Valid)
     * @return ResponseEntity con el procedimiento actualizado (200) o 404 si no existe
     * @see ProcedimientoService#obtenerPorId(Long)
     * @see ProcedimientoService#guardar(Procedimiento)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Procedimiento> actualizar(@PathVariable Long id, @Valid @RequestBody Procedimiento procedimiento) {
        return procedimientoService.obtenerPorId(id)
                .map(existente -> {
                    existente.setDescripcion(procedimiento.getDescripcion());
                    existente.setEstado(procedimiento.isEstado());
                    existente.setFecha(procedimiento.getFecha());
                    existente.setCita(procedimiento.getCita());
                    Procedimiento actualizado = procedimientoService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un procedimiento del sistema.
     * 
     * Este método elimina un procedimiento del sistema por su ID.
     * Primero verifica que el procedimiento exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el procedimiento no existe.
     * 
     * @param id Identificador único del procedimiento a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see ProcedimientoService#obtenerPorId(Long)
     * @see ProcedimientoService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (procedimientoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        procedimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
