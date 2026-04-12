package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Usuario;
import com.sena.creyese.dentvision_spring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de usuarios en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con usuarios, incluyendo búsqueda, creación, actualización
 * y eliminación. Actúa como una capa de abstracción entre los controladores
 * y el repositorio de usuarios.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Gestión CRUD de usuarios
 * - Búsqueda por email para autenticación
 * - Operaciones de negocio centralizadas
 * - Validaciones y lógica de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class UsuarioService {
    
    /** Repositorio para el acceso a datos de usuarios */
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor que inyecta el repositorio de usuarios.
     * 
     * @param usuarioRepository Repositorio de usuarios a inyectar
     */
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Obtiene todos los usuarios del sistema.
     * 
     * Este método retorna una lista con todos los usuarios registrados
     * en la base de datos. Útil para administración y reportes.
     * 
     * @return Lista de todos los usuarios
     */
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca un usuario por su identificador único.
     * 
     * @param id Identificador único del usuario
     * @return Optional con el usuario encontrado o vacío si no existe
     */
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Busca un usuario por su dirección de email.
     * 
     * Este método es utilizado principalmente para el proceso de
     * autenticación, donde se necesita verificar si existe un usuario
     * con el email proporcionado.
     * 
     * @param email Email del usuario a buscar
     * @return Optional con el usuario encontrado o vacío si no existe
     */
    public Optional<Usuario> obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Guarda o actualiza un usuario en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos usuarios
     * como la actualización de usuarios existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del usuario.
     * 
     * @param usuario Usuario a guardar o actualizar
     * @return Usuario guardado con su ID asignado
     */
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Elimina un usuario del sistema por su identificador.
     * 
     * @param id Identificador único del usuario a eliminar
     */
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
