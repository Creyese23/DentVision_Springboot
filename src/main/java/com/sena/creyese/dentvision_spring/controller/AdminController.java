package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Admin;
import com.sena.creyese.dentvision_spring.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de administradores en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre administradores del sistema, incluyendo registro, consulta, actualización
 * y eliminación. Todos los endpoints están bajo la ruta base /api/admins y siguen
 * los estándares RESTful para una mejor integración con clientes web y móviles.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Endpoints disponibles:
 * - GET /api/admins: Obtener todos los administradores
 * - GET /api/admins/{id}: Obtener administrador por ID
 * - POST /api/admins: Crear nuevo administrador
 * - PUT /api/admins/{id}: Actualizar administrador existente
 * - DELETE /api/admins/{id}: Eliminar administrador
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/admins")
public class AdminController {
    
    /** Servicio de negocio para la gestión de administradores */
    private final AdminService adminService;

    /**
     * Constructor que inyecta el servicio de administradores.
     * 
     * @param adminService Servicio de administradores a inyectar
     */
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Endpoint para obtener todos los administradores del sistema.
     * 
     * Este método retorna una lista completa de todos los administradores
     * registrados en la base de datos. Útil para administración y
     * gestión de personal del sistema.
     * 
     * @return Lista de todos los administradores registrados
     * @see AdminService#listarTodos()
     */
    @GetMapping
    public List<Admin> listarTodos() {
        return adminService.listarTodos();
    }

    /**
     * Endpoint para obtener un administrador específico por su ID.
     * 
     * Este método busca un administrador por su identificador único y
     * retorna los datos del administrador si existe. Si el administrador no
     * se encuentra, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del administrador a buscar
     * @return ResponseEntity con el administrador encontrado (200) o 404 si no existe
     * @see AdminService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Admin> obtenerPorId(@PathVariable Long id) {
        return adminService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo administrador en el sistema.
     * 
     * Este método recibe los datos de un nuevo administrador, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el administrador creado con su ID asignado.
     * 
     * @param admin Datos del administrador a crear (validados con @Valid)
     * @return ResponseEntity con el administrador creado (200) y sus datos
     * @see AdminService#guardar(Admin)
     */
    @PostMapping
    public ResponseEntity<Admin> crear(@Valid @RequestBody Admin admin) {
        Admin nuevo = adminService.guardar(admin);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un administrador existente.
     * 
     * Este método actualiza los datos de un administrador existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos del administrador. Si el administrador
     * no existe, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del administrador a actualizar
     * @param admin Datos actualizados del administrador (validados con @Valid)
     * @return ResponseEntity con el administrador actualizado (200) o 404 si no existe
     * @see AdminService#obtenerPorId(Long)
     * @see AdminService#guardar(Admin)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Admin> actualizar(@PathVariable Long id, @Valid @RequestBody Admin admin) {
        return adminService.obtenerPorId(id)
                .map(existente -> {
                    existente.setTipoDocumento(admin.getTipoDocumento());
                    existente.setDocumento(admin.getDocumento());
                    existente.setTelefono(admin.getTelefono());
                    existente.setDireccion(admin.getDireccion());
                    existente.setFecha_nacimiento(admin.getFecha_nacimiento());
                    existente.setUsuario(admin.getUsuario());
                    Admin actualizado = adminService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un administrador del sistema.
     * 
     * Este método elimina un administrador del sistema por su ID.
     * Primero verifica que el administrador exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el administrador no existe.
     * 
     * @param id Identificador único del administrador a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see AdminService#obtenerPorId(Long)
     * @see AdminService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (adminService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        adminService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
