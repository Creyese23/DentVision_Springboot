package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;
import com.sena.creyese.dentvision_spring.service.OrdenTrabajoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de órdenes de trabajo en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre órdenes de trabajo dental, incluyendo asignación de pacientes,
 * odontólogos y técnicos, seguimiento de estados y observaciones. Todos los
 * endpoints están bajo la ruta base /api/ordenes-trabajo y siguen los estándares RESTful.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/ordenes-trabajo: Obtener todas las órdenes de trabajo
 * - GET /api/ordenes-trabajo/{id}: Obtener orden de trabajo por ID
 * - POST /api/ordenes-trabajo: Crear nueva orden de trabajo
 * - PUT /api/ordenes-trabajo/{id}: Actualizar orden de trabajo existente
 * - DELETE /api/ordenes-trabajo/{id}: Eliminar orden de trabajo
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/ordenes-trabajo")
public class OrdenTrabajoController {

    /** Servicio de negocio para la gestión de órdenes de trabajo */
    private final OrdenTrabajoService ordenTrabajoService;

    /**
     * Constructor que inyecta el servicio de órdenes de trabajo.
     * 
     * @param ordenTrabajoService Servicio de órdenes de trabajo a inyectar
     */
    public OrdenTrabajoController(OrdenTrabajoService ordenTrabajoService) {
        this.ordenTrabajoService = ordenTrabajoService;
    }

    /**
     * Endpoint para obtener todas las órdenes de trabajo del sistema.
     * 
     * Este método retorna una lista completa de todas las órdenes registradas
     * en la base de datos. Útil para administración y seguimiento de trabajos.
     * 
     * @return Lista de todas las órdenes de trabajo
     * @see OrdenTrabajoService#listarTodos()
     */
    @GetMapping
    public List<OrdenTrabajo> listarTodos() {
        return ordenTrabajoService.listarTodos();
    }

    /**
     * Endpoint para obtener una orden de trabajo específica por su ID.
     * 
     * Este método busca una orden de trabajo por su identificador único y
     * retorna los datos de la orden si existe. Si la orden no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la orden de trabajo a buscar
     * @return ResponseEntity con la orden encontrada (200) o 404 si no existe
     * @see OrdenTrabajoService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> obtenerPorId(@PathVariable Long id) {
        return ordenTrabajoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear una nueva orden de trabajo en el sistema.
     * 
     * Este método recibe los datos de una nueva orden de trabajo, valida la
     * información de entrada y la registra en la base de datos.
     * Retorna la orden creada con su ID asignado.
     * 
     * @param ordenTrabajo Datos de la orden de trabajo a crear (validados con @Valid)
     * @return ResponseEntity con la orden creada (200) y sus datos
     * @see OrdenTrabajoService#guardar(OrdenTrabajo)
     */
    @PostMapping
    public ResponseEntity<OrdenTrabajo> crear(@Valid @RequestBody OrdenTrabajo ordenTrabajo) {
        OrdenTrabajo nueva = ordenTrabajoService.guardar(ordenTrabajo);
        return ResponseEntity.ok(nueva);
    }

    /**
     * Endpoint para actualizar una orden de trabajo existente.
     * 
     * Este método actualiza los datos de una orden de trabajo existente
     * identificada por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de las relaciones. Si la orden no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la orden de trabajo a actualizar
     * @param ordenTrabajo Datos actualizados de la orden de trabajo (validados con @Valid)
     * @return ResponseEntity con la orden actualizada (200) o 404 si no existe
     * @see OrdenTrabajoService#obtenerPorId(Long)
     * @see OrdenTrabajoService#guardar(OrdenTrabajo)
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> actualizar(@PathVariable Long id, @Valid @RequestBody OrdenTrabajo ordenTrabajo) {
        return ordenTrabajoService.obtenerPorId(id)
                .map(existente -> {
                    existente.setFecha(ordenTrabajo.getFecha());
                    existente.setEstado(ordenTrabajo.getEstado());
                    existente.setObservaciones(ordenTrabajo.getObservaciones());
                    existente.setPaciente(ordenTrabajo.getPaciente());
                    existente.setOdontologo(ordenTrabajo.getOdontologo());
                    existente.setTecnico(ordenTrabajo.getTecnico());
                    OrdenTrabajo actualizado = ordenTrabajoService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar una orden de trabajo del sistema.
     * 
     * Este método elimina una orden de trabajo del sistema por su ID.
     * Primero verifica que la orden exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si la orden no existe.
     * 
     * @param id Identificador único de la orden de trabajo a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see OrdenTrabajoService#obtenerPorId(Long)
     * @see OrdenTrabajoService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (ordenTrabajoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ordenTrabajoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
