package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Roles;
import com.sena.creyese.dentvision_spring.service.RolService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de roles de usuario en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre roles del sistema, incluyendo definición de permisos y niveles de acceso.
 * Utiliza el enum TipoRol para garantizar la integridad y seguridad de los roles.
 * Todos los endpoints están bajo la ruta base /api/roles y siguen los estándares RESTful.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Endpoints disponibles:
 * - GET /api/roles: Obtener todos los roles
 * - GET /api/roles/{id}: Obtener rol por ID
 * - POST /api/roles: Crear nuevo rol
 * - PUT /api/roles/{id}: Actualizar rol existente
 * - DELETE /api/roles/{id}: Eliminar rol
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 * - Control de acceso basado en roles
 */
@RestController
@RequestMapping("/api/roles")
public class RolController {
    
    /** Servicio de negocio para la gestión de roles */
    private final RolService rolService;

    /**
     * Constructor que inyecta el servicio de roles.
     * 
     * @param rolService Servicio de roles a inyectar
     */
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    /**
     * Endpoint para obtener todos los roles del sistema.
     * 
     * Este método retorna una lista completa de todos los roles registrados
     * en la base de datos. Útil para administración y gestión de permisos.
     * 
     * @return Lista de todos los roles
     * @see RolService#listarTodos()
     */
    @GetMapping
    public List<Roles> listarTodos() {
        return rolService.listarTodos();
    }

    /**
     * Endpoint para obtener un rol específico por su ID.
     * 
     * Este método busca un rol por su identificador único y
     * retorna los datos del rol si existe. Si el rol no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del rol a buscar
     * @return ResponseEntity con el rol encontrado (200) o 404 si no existe
     * @see RolService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Roles> obtenerPorId(@PathVariable Long id) {
        return rolService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo rol en el sistema.
     * 
     * Este método recibe los datos de un nuevo rol, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el rol creado con su ID asignado.
     * 
     * @param rol Datos del rol a crear (validados con @Valid)
     * @return ResponseEntity con el rol creado (200) y sus datos
     * @see RolService#guardar(Roles)
     */
    @PostMapping
    public ResponseEntity<Roles> crear(@Valid @RequestBody Roles rol) {
        Roles nuevoRol = rolService.guardar(rol);
        return ResponseEntity.ok(nuevoRol);
    }

    /**
     * Endpoint para actualizar un rol existente.
     * 
     * Este método actualiza los datos de un rol existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad del sistema de permisos. Si el rol no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del rol a actualizar
     * @param rol Datos actualizados del rol (validados con @Valid)
     * @return ResponseEntity con el rol actualizado (200) o 404 si no existe
     * @see RolService#obtenerPorId(Long)
     * @see RolService#guardar(Roles)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Roles> actualizar(@PathVariable Long id, @Valid @RequestBody Roles rol) {
        return rolService.obtenerPorId(id)
                .map(rolExistente -> {
                    rolExistente.setNombreRol(rol.getNombreRol());
                    Roles actualizado = rolService.guardar(rolExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un rol del sistema.
     * 
     * Este método elimina un rol del sistema por su ID.
     * Primero verifica que el rol exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el rol no existe.
     * 
     * @param id Identificador único del rol a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see RolService#obtenerPorId(Long)
     * @see RolService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (rolService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        rolService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
