package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.AuxiliarAdmin;
import com.sena.creyese.dentvision_spring.service.AuxiliarAdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de auxiliares administrativos en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre auxiliares administrativos, incluyendo registro, consulta, actualización
 * y eliminación. Todos los endpoints están bajo la ruta base /api/auxiliares-admin y siguen
 * los estándares RESTful para una mejor integración con clientes web y móviles.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Endpoints disponibles:
 * - GET /api/auxiliares-admin: Obtener todos los auxiliares administrativos
 * - GET /api/auxiliares-admin/{id}: Obtener auxiliar administrativo por ID
 * - POST /api/auxiliares-admin: Crear nuevo auxiliar administrativo
 * - PUT /api/auxiliares-admin/{id}: Actualizar auxiliar administrativo existente
 * - DELETE /api/auxiliares-admin/{id}: Eliminar auxiliar administrativo
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/auxiliares-admin")
public class AuxiliarAdminController {

    /** Servicio de negocio para la gestión de auxiliares administrativos */
    private final AuxiliarAdminService auxiliarAdminService;

    /**
     * Constructor que inyecta el servicio de auxiliares administrativos.
     * 
     * @param auxiliarAdminService Servicio de auxiliares administrativos a inyectar
     */
    public AuxiliarAdminController(AuxiliarAdminService auxiliarAdminService) {
        this.auxiliarAdminService = auxiliarAdminService;
    }

    /**
     * Endpoint para obtener todos los auxiliares administrativos del sistema.
     * 
     * Este método retorna una lista completa de todos los auxiliares registrados
     * en la base de datos. Útil para administración y gestión de personal del sistema.
     * 
     * @return Lista de todos los auxiliares administrativos registrados
     * @see AuxiliarAdminService#listarTodos()
     */
    @GetMapping
    public List<AuxiliarAdmin> listarTodos() {
        return auxiliarAdminService.listarTodos();
    }

    /**
     * Endpoint para obtener un auxiliar administrativo específico por su ID.
     * 
     * Este método busca un auxiliar administrativo por su identificador único y
     * retorna los datos del auxiliar si existe. Si el auxiliar no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del auxiliar administrativo a buscar
     * @return ResponseEntity con el auxiliar encontrado (200) o 404 si no existe
     * @see AuxiliarAdminService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuxiliarAdmin> obtenerPorId(@PathVariable Long id) {
        return auxiliarAdminService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo auxiliar administrativo en el sistema.
     * 
     * Este método recibe los datos de un nuevo auxiliar administrativo, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el auxiliar creado con su ID asignado.
     * 
     * @param auxiliarAdmin Datos del auxiliar administrativo a crear (validados con @Valid)
     * @return ResponseEntity con el auxiliar creado (200) y sus datos
     * @see AuxiliarAdminService#guardar(AuxiliarAdmin)
     */
    @PostMapping
    public ResponseEntity<AuxiliarAdmin> crear(@Valid @RequestBody AuxiliarAdmin auxiliarAdmin) {
        AuxiliarAdmin nuevo = auxiliarAdminService.guardar(auxiliarAdmin);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un auxiliar administrativo existente.
     * 
     * Este método actualiza los datos de un auxiliar administrativo existente
     * identificado por su ID. Solo actualiza los campos permitidos y mantiene
     * la integridad de los datos del auxiliar. Si el auxiliar no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del auxiliar administrativo a actualizar
     * @param auxiliarAdmin Datos actualizados del auxiliar administrativo (validados con @Valid)
     * @return ResponseEntity con el auxiliar actualizado (200) o 404 si no existe
     * @see AuxiliarAdminService#obtenerPorId(Long)
     * @see AuxiliarAdminService#guardar(AuxiliarAdmin)
     */
    @PutMapping("/{id}")
    public ResponseEntity<AuxiliarAdmin> actualizar(@PathVariable Long id, @Valid @RequestBody AuxiliarAdmin auxiliarAdmin) {
        return auxiliarAdminService.obtenerPorId(id)
                .map(existente -> {
                    existente.setTipoDocumento(auxiliarAdmin.getTipoDocumento());
                    existente.setDocumento(auxiliarAdmin.getDocumento());
                    existente.setTelefono(auxiliarAdmin.getTelefono());
                    existente.setDireccion(auxiliarAdmin.getDireccion());
                    existente.setGenero(auxiliarAdmin.getGenero());
                    existente.setFechaNacimiento(auxiliarAdmin.getFechaNacimiento());
                    existente.setUsuario(auxiliarAdmin.getUsuario());
                    AuxiliarAdmin actualizado = auxiliarAdminService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un auxiliar administrativo del sistema.
     * 
     * Este método elimina un auxiliar administrativo del sistema por su ID.
     * Primero verifica que el auxiliar exista antes de proceder con la eliminación.
     * Retorna 204 (No Content) si la eliminación fue exitosa o 404 (Not Found)
     * si el auxiliar no existe.
     * 
     * @param id Identificador único del auxiliar administrativo a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see AuxiliarAdminService#obtenerPorId(Long)
     * @see AuxiliarAdminService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (auxiliarAdminService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        auxiliarAdminService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
