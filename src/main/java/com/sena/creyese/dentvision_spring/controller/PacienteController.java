package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Paciente;
import com.sena.creyese.dentvision_spring.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de pacientes en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre pacientes, incluyendo registro, consulta, actualización y eliminación.
 * Todos los endpoints están bajo la ruta base /api/pacientes y siguen los
 * estándares RESTful para una mejor integración con clientes web y móviles.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Endpoints disponibles:
 * - GET /api/pacientes: Obtener todos los pacientes
 * - GET /api/pacientes/{id}: Obtener paciente por ID
 * - POST /api/pacientes: Crear nuevo paciente
 * - PUT /api/pacientes/{id}: Actualizar paciente existente
 * - DELETE /api/pacientes/{id}: Eliminar paciente
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    /** Servicio de negocio para la gestión de pacientes */
    private final PacienteService pacienteService;

    /**
     * Constructor que inyecta el servicio de pacientes.
     * 
     * @param pacienteService Servicio de pacientes a inyectar
     */
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /**
     * Endpoint para obtener todos los pacientes del sistema.
     * 
     * Este método retorna una lista completa de todos los pacientes
     * registrados en la base de datos. Útil para administración y
     * generación de reportes.
     * 
     * @return Lista de todos los pacientes registrados
     * @see PacienteService#listarTodos()
     */
    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    /**
     * Endpoint para obtener un paciente específico por su ID.
     * 
     * Este método busca un paciente por su identificador único y
     * retorna los datos del paciente si existe. Si el paciente no
     * se encuentra, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del paciente a buscar
     * @return ResponseEntity con el paciente encontrado (200) o 404 si no existe
     * @see PacienteService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable Long id) {
        return pacienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo paciente en el sistema.
     * 
     * Este método recibe los datos de un nuevo paciente, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el paciente creado con su ID asignado.
     * 
     * @param paciente Datos del paciente a crear (validados con @Valid)
     * @return ResponseEntity con el paciente creado (200) y sus datos
     * @see PacienteService#guardar(Paciente)
     */
    @PostMapping
    public ResponseEntity<Paciente> crear(@Valid @RequestBody Paciente paciente) {
        Paciente nuevo = pacienteService.guardar(paciente);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un paciente existente.
     * 
     * Este método actualiza los datos de un paciente existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos. Si el paciente no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del paciente a actualizar
     * @param paciente Datos actualizados del paciente (validados con @Valid)
     * @return ResponseEntity con el paciente actualizado (200) o 404 si no existe
     * @see PacienteService#obtenerPorId(Long)
     * @see PacienteService#guardar(Paciente)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
        return pacienteService.obtenerPorId(id)
                .map(existente -> {
                    existente.setTipoDocumento(paciente.getTipoDocumento());
                    existente.setDocumento(paciente.getDocumento());
                    existente.setTelefono(paciente.getTelefono());
                    existente.setDireccion(paciente.getDireccion());
                    existente.setGenero(paciente.getGenero());
                    existente.setFecha_nacimiento(paciente.getFecha_nacimiento());
                    existente.setUsuario(paciente.getUsuario());
                    Paciente actualizado = pacienteService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un paciente del sistema.
     * 
     * Este método elimina un paciente del sistema por su ID.
     * Primero verifica que el paciente exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el paciente no existe.
     * 
     * @param id Identificador único del paciente a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see PacienteService#obtenerPorId(Long)
     * @see PacienteService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pacienteService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
