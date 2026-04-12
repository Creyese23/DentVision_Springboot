package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Usuario;
import com.sena.creyese.dentvision_spring.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre usuarios, incluyendo registro, consulta, actualización y eliminación.
 * Todos los endpoints están bajo la ruta base /api/usuarios y siguen los
 * estándares RESTful para una mejor integración con clientes web y móviles.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/usuarios: Obtener todos los usuarios
 * - GET /api/usuarios/{id}: Obtener usuario por ID
 * - POST /api/usuarios: Crear nuevo usuario
 * - PUT /api/usuarios/{id}: Actualizar usuario existente
 * - DELETE /api/usuarios/{id}: Eliminar usuario
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    /** Servicio de negocio para la gestión de usuarios */
    private final UsuarioService usuarioService;

    /**
     * Constructor que inyecta el servicio de usuarios.
     * 
     * @param usuarioService Servicio de usuarios a inyectar
     */
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para obtener todos los usuarios del sistema.
     * 
     * Este método retorna una lista completa de todos los usuarios
     * registrados en la base de datos. Útil para administración y
     * gestión de personal del sistema.
     * 
     * @return Lista de todos los usuarios registrados
     * @see UsuarioService#listarTodos()
     */
    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    /**
     * Endpoint para obtener un usuario específico por su ID.
     * 
     * Este método busca un usuario por su identificador único y
     * retorna los datos del usuario si existe. Si el usuario no
     * se encuentra, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del usuario a buscar
     * @return ResponseEntity con el usuario encontrado (200) o 404 si no existe
     * @see UsuarioService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo usuario en el sistema.
     * 
     * Este método recibe los datos de un nuevo usuario, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el usuario creado con su ID asignado.
     * 
     * @param usuario Datos del usuario a crear (validados con @Valid)
     * @return ResponseEntity con el usuario creado (200) y sus datos
     * @see UsuarioService#guardar(Usuario)
     */
    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    /**
     * Endpoint para actualizar un usuario existente.
     * 
     * Este método actualiza los datos de un usuario existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos del usuario. Si el usuario
     * no existe, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del usuario a actualizar
     * @param usuario Datos actualizados del usuario (validados con @Valid)
     * @return ResponseEntity con el usuario actualizado (200) o 404 si no existe
     * @see UsuarioService#obtenerPorId(Long)
     * @see UsuarioService#guardar(Usuario)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        return usuarioService.obtenerPorId(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNombres(usuario.getNombres());
                    usuarioExistente.setApellidos(usuario.getApellidos());
                    usuarioExistente.setEmail(usuario.getEmail());
                    usuarioExistente.setConfirmar_email(usuario.getConfirmar_email());
                    usuarioExistente.setPassword(usuario.getPassword());
                    usuarioExistente.setConfirmar_password(usuario.getConfirmar_password());
                    usuarioExistente.setEstado(usuario.getEstado());
                    usuarioExistente.setFecha_registro(usuario.getFecha_registro());
                    usuarioExistente.setRol(usuario.getRol());
                    Usuario actualizado = usuarioService.guardar(usuarioExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un usuario del sistema.
     * 
     * Este método elimina un usuario del sistema por su ID.
     * Primero verifica que el usuario exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el usuario no existe.
     * 
     * @param id Identificador único del usuario a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see UsuarioService#obtenerPorId(Long)
     * @see UsuarioService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (usuarioService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
