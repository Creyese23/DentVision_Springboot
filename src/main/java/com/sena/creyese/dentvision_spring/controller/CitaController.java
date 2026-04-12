package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Cita;
import com.sena.creyese.dentvision_spring.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de citas dentales en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre citas dentales, incluyendo programación, consulta, actualización
 * y cancelación. Todos los endpoints están bajo la ruta base /api/citas y siguen
 * los estándares RESTful para una mejor integración con clientes web y móviles.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/citas: Obtener todas las citas
 * - GET /api/citas/{id}: Obtener cita por ID
 * - POST /api/citas: Crear nueva cita
 * - PUT /api/citas/{id}: Actualizar cita existente
 * - DELETE /api/citas/{id}: Eliminar cita
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/citas")
public class CitaController {

    /** Servicio de negocio para la gestión de citas */
    private final CitaService citaService;

    /**
     * Constructor que inyecta el servicio de citas.
     * 
     * @param citaService Servicio de citas a inyectar
     */
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    /**
     * Endpoint para obtener todas las citas del sistema.
     * 
     * Este método retorna una lista completa de todas las citas
     * registradas en la base de datos. Útil para administración y
     * gestión de la agenda clínica.
     * 
     * @return Lista de todas las citas registradas
     * @see CitaService#listarTodos()
     */
    @GetMapping
    public List<Cita> listarTodos() {
        return citaService.listarTodos();
    }

    /**
     * Endpoint para obtener una cita específica por su ID.
     * 
     * Este método busca una cita por su identificador único y
     * retorna los datos de la cita si existe. Si la cita no
     * se encuentra, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la cita a buscar
     * @return ResponseEntity con la cita encontrada (200) o 404 si no existe
     * @see CitaService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable Long id) {
        return citaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear una nueva cita en el sistema.
     * 
     * Este método recibe los datos de una nueva cita, valida la
     * información de entrada y la registra en la base de datos.
     * Retorna la cita creada con su ID asignado.
     * 
     * @param cita Datos de la cita a crear (validados con @Valid)
     * @return ResponseEntity con la cita creada (200) y sus datos
     * @see CitaService#guardar(Cita)
     */
    @PostMapping
    public ResponseEntity<Cita> crear(@Valid @RequestBody Cita cita) {
        Cita nueva = citaService.guardar(cita);
        return ResponseEntity.ok(nueva);
    }

    /**
     * Endpoint para actualizar una cita existente.
     * 
     * Este método actualiza los datos de una cita existente
     * identificada por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos de programación. Si la cita
     * no existe, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la cita a actualizar
     * @param cita Datos actualizados de la cita (validados con @Valid)
     * @return ResponseEntity con la cita actualizada (200) o 404 si no existe
     * @see CitaService#obtenerPorId(Long)
     * @see CitaService#guardar(Cita)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(@PathVariable Long id, @Valid @RequestBody Cita cita) {
        return citaService.obtenerPorId(id)
                .map(existente -> {
                    existente.setFecha(cita.getFecha());
                    existente.setHora(cita.getHora());
                    existente.setEstado(cita.getEstado());
                    existente.setMotivo(cita.getMotivo());
                    existente.setPaciente(cita.getPaciente());
                    existente.setOdontologo(cita.getOdontologo());
                    Cita actualizada = citaService.guardar(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar una cita del sistema.
     * 
     * Este método elimina una cita del sistema por su ID.
     * Primero verifica que la cita exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si la cita no existe.
     * 
     * @param id Identificador único de la cita a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see CitaService#obtenerPorId(Long)
     * @see CitaService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (citaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
