package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.TecnicoDental;
import com.sena.creyese.dentvision_spring.service.TecnicoDentalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de técnicos dentales en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre técnicos dentales, incluyendo información personal, profesional
 * y datos de contacto. Todos los endpoints están bajo la ruta base
 * /api/tecnicos-dentales y siguen los estándares RESTful.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/tecnicos-dentales: Obtener todos los técnicos dentales
 * - GET /api/tecnicos-dentales/{id}: Obtener técnico dental por ID
 * - POST /api/tecnicos-dentales: Crear nuevo técnico dental
 * - PUT /api/tecnicos-dentales/{id}: Actualizar técnico dental existente
 * - DELETE /api/tecnicos-dentales/{id}: Eliminar técnico dental
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/tecnicos-dentales")
public class TecnicoDentalController {

    /** Servicio de negocio para la gestión de técnicos dentales */
    private final TecnicoDentalService tecnicoDentalService;

    /**
     * Constructor que inyecta el servicio de técnicos dentales.
     * 
     * @param tecnicoDentalService Servicio de técnicos dentales a inyectar
     */
    public TecnicoDentalController(TecnicoDentalService tecnicoDentalService) {
        this.tecnicoDentalService = tecnicoDentalService;
    }

    /**
     * Endpoint para obtener todos los técnicos dentales del sistema.
     * 
     * Este método retorna una lista completa de todos los técnicos registrados
     * en la base de datos. Útil para administración y gestión de personal.
     * 
     * @return Lista de todos los técnicos dentales
     * @see TecnicoDentalService#listarTodos()
     */
    @GetMapping
    public List<TecnicoDental> listarTodos() {
        return tecnicoDentalService.listarTodos();
    }

    /**
     * Endpoint para obtener un técnico dental específico por su ID.
     * 
     * Este método busca un técnico dental por su identificador único y
     * retorna los datos del técnico si existe. Si el técnico no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del técnico dental a buscar
     * @return ResponseEntity con el técnico encontrado (200) o 404 si no existe
     * @see TecnicoDentalService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDental> obtenerPorId(@PathVariable Long id) {
        return tecnicoDentalService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo técnico dental en el sistema.
     * 
     * Este método recibe los datos de un nuevo técnico dental, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el técnico creado con su ID asignado.
     * 
     * @param tecnicoDental Datos del técnico dental a crear (validados con @Valid)
     * @return ResponseEntity con el técnico creado (200) y sus datos
     * @see TecnicoDentalService#guardar(TecnicoDental)
     */
    @PostMapping
    public ResponseEntity<TecnicoDental> crear(@Valid @RequestBody TecnicoDental tecnicoDental) {
        TecnicoDental nuevo = tecnicoDentalService.guardar(tecnicoDental);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un técnico dental existente.
     * 
     * Este método actualiza los datos de un técnico dental existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos. Si el técnico no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del técnico dental a actualizar
     * @param tecnicoDental Datos actualizados del técnico dental (validados con @Valid)
     * @return ResponseEntity con el técnico actualizado (200) o 404 si no existe
     * @see TecnicoDentalService#obtenerPorId(Long)
     * @see TecnicoDentalService#guardar(TecnicoDental)
     */
    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDental> actualizar(@PathVariable Long id, @Valid @RequestBody TecnicoDental tecnicoDental) {
        return tecnicoDentalService.obtenerPorId(id)
                .map(existente -> {
                    existente.setTipoDocumento(tecnicoDental.getTipoDocumento());
                    existente.setDocumento(tecnicoDental.getDocumento());
                    existente.setTelefono(tecnicoDental.getTelefono());
                    existente.setGenero(tecnicoDental.getGenero());
                    existente.setEspecialidad(tecnicoDental.getEspecialidad());
                    existente.setFecha_nacimiento(tecnicoDental.getFecha_nacimiento());
                    existente.setUsuario(tecnicoDental.getUsuario());
                    TecnicoDental actualizado = tecnicoDentalService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un técnico dental del sistema.
     * 
     * Este método elimina un técnico dental del sistema por su ID.
     * Primero verifica que el técnico exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el técnico no existe.
     * 
     * @param id Identificador único del técnico dental a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see TecnicoDentalService#obtenerPorId(Long)
     * @see TecnicoDentalService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (tecnicoDentalService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tecnicoDentalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
