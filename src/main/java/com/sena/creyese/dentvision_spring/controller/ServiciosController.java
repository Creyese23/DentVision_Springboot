package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Servicios;
import com.sena.creyese.dentvision_spring.service.ServiciosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de servicios dentales en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre servicios dentales, incluyendo nombre, descripción y precios.
 * Todos los endpoints están bajo la ruta base /api/servicios y siguen los estándares RESTful.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/servicios: Obtener todos los servicios
 * - GET /api/servicios/{id}: Obtener servicio por ID
 * - POST /api/servicios: Crear nuevo servicio
 * - PUT /api/servicios/{id}: Actualizar servicio existente
 * - DELETE /api/servicios/{id}: Eliminar servicio
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/servicios")
public class ServiciosController {

    /** Servicio de negocio para la gestión de servicios */
    private final ServiciosService serviciosService;

    /**
     * Constructor que inyecta el servicio de servicios.
     * 
     * @param serviciosService Servicio de servicios a inyectar
     */
    public ServiciosController(ServiciosService serviciosService) {
        this.serviciosService = serviciosService;
    }

    /**
     * Endpoint para obtener todos los servicios del sistema.
     * 
     * Este método retorna una lista completa de todos los servicios registrados
     * en la base de datos. Útil para administración y catálogo de servicios.
     * 
     * @return Lista de todos los servicios
     * @see ServiciosService#listarTodos()
     */
    @GetMapping
    public List<Servicios> listarTodos() {
        return serviciosService.listarTodos();
    }

    /**
     * Endpoint para obtener un servicio específico por su ID.
     * 
     * Este método busca un servicio por su identificador único y
     * retorna los datos del servicio si existe. Si el servicio no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del servicio a buscar
     * @return ResponseEntity con el servicio encontrado (200) o 404 si no existe
     * @see ServiciosService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Servicios> obtenerPorId(@PathVariable Long id) {
        return serviciosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo servicio en el sistema.
     * 
     * Este método recibe los datos de un nuevo servicio, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el servicio creado con su ID asignado.
     * 
     * @param servicios Datos del servicio a crear (validados con @Valid)
     * @return ResponseEntity con el servicio creado (200) y sus datos
     * @see ServiciosService#guardar(Servicios)
     */
    @PostMapping
    public ResponseEntity<Servicios> crear(@Valid @RequestBody Servicios servicios) {
        Servicios nuevo = serviciosService.guardar(servicios);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un servicio existente.
     * 
     * Este método actualiza los datos de un servicio existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos. Si el servicio no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del servicio a actualizar
     * @param servicios Datos actualizados del servicio (validados con @Valid)
     * @return ResponseEntity con el servicio actualizado (200) o 404 si no existe
     * @see ServiciosService#obtenerPorId(Long)
     * @see ServiciosService#guardar(Servicios)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Servicios> actualizar(@PathVariable Long id, @Valid @RequestBody Servicios servicios) {
        return serviciosService.obtenerPorId(id)
                .map(existente -> {
                    existente.setNombreServicios(servicios.getNombreServicios());
                    existente.setDescripcionServicios(servicios.getDescripcionServicios());
                    existente.setPrecioServicios(servicios.getPrecioServicios());
                    Servicios actualizado = serviciosService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un servicio del sistema.
     * 
     * Este método elimina un servicio del sistema por su ID.
     * Primero verifica que el servicio exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el servicio no existe.
     * 
     * @param id Identificador único del servicio a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see ServiciosService#obtenerPorId(Long)
     * @see ServiciosService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (serviciosService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        serviciosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
