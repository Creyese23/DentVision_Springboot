package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Insumo;
import com.sena.creyese.dentvision_spring.service.InsumoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de insumos dentales en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre insumos y materiales dentales, incluyendo control de stock,
 * gestión de inventario y alertas de reabastecimiento. Todos los endpoints
 * están bajo la ruta base /api/insumos y siguen los estándares RESTful.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Endpoints disponibles:
 * - GET /api/insumos: Obtener todos los insumos
 * - GET /api/insumos/{id}: Obtener insumo por ID
 * - POST /api/insumos: Crear nuevo insumo
 * - PUT /api/insumos/{id}: Actualizar insumo existente
 * - DELETE /api/insumos/{id}: Eliminar insumo
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/insumos")
public class InsumoController {

    /** Servicio de negocio para la gestión de insumos */
    private final InsumoService insumoService;

    /**
     * Constructor que inyecta el servicio de insumos.
     * 
     * @param insumoService Servicio de insumos a inyectar
     */
    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
    }

    /**
     * Endpoint para obtener todos los insumos del sistema.
     * 
     * Este método retorna una lista completa de todos los insumos registrados
     * en la base de datos. Útil para administración y control de inventario.
     * 
     * @return Lista de todos los insumos
     * @see InsumoService#listarTodos()
     */
    @GetMapping
    public List<Insumo> listarTodos() {
        return insumoService.listarTodos();
    }

    /**
     * Endpoint para obtener un insumo específico por su ID.
     * 
     * Este método busca un insumo por su identificador único y
     * retorna los datos del insumo si existe. Si el insumo no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del insumo a buscar
     * @return ResponseEntity con el insumo encontrado (200) o 404 si no existe
     * @see InsumoService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Insumo> obtenerPorId(@PathVariable Long id) {
        return insumoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo insumo en el sistema.
     * 
     * Este método recibe los datos de un nuevo insumo, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el insumo creado con su ID asignado.
     * 
     * @param insumo Datos del insumo a crear (validados con @Valid)
     * @return ResponseEntity con el insumo creado (200) y sus datos
     * @see InsumoService#guardar(Insumo)
     */
    @PostMapping
    public ResponseEntity<Insumo> crear(@Valid @RequestBody Insumo insumo) {
        Insumo nuevo = insumoService.guardar(insumo);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un insumo existente.
     * 
     * Este método actualiza los datos de un insumo existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos de inventario. Si el insumo
     * no existe, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del insumo a actualizar
     * @param insumo Datos actualizados del insumo (validados con @Valid)
     * @return ResponseEntity con el insumo actualizado (200) o 404 si no existe
     * @see InsumoService#obtenerPorId(Long)
     * @see InsumoService#guardar(Insumo)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Insumo> actualizar(@PathVariable Long id, @Valid @RequestBody Insumo insumo) {
        return insumoService.obtenerPorId(id)
                .map(existente -> {
                    existente.setNombre(insumo.getNombre());
                    existente.setDescripcion(insumo.getDescripcion());
                    existente.setStock(insumo.getStock());
                    existente.setStockMinimo(insumo.getStockMinimo());
                    Insumo actualizado = insumoService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un insumo del sistema.
     * 
     * Este método elimina un insumo del sistema por su ID.
     * Primero verifica que el insumo exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el insumo no existe.
     * 
     * @param id Identificador único del insumo a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see InsumoService#obtenerPorId(Long)
     * @see InsumoService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (insumoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        insumoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
