package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Roles;
import com.sena.creyese.dentvision_spring.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de roles de usuario en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con roles de usuario, incluyendo gestión de permisos,
 * asignación de roles y control de acceso. Actúa como una capa de
 * abstracción entre los controladores y el repositorio de roles.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de roles de usuario
 * - Control de permisos y accesos
 * - Validaciones de seguridad
 * - Integración con sistema de autenticación
 * - Manejo de transacciones implícito
 */
@Service
public class RolService {
    
    /** Repositorio para el acceso a datos de roles */
    private final RolRepository rolRepository;

    /**
     * Constructor que inyecta el repositorio de roles.
     * 
     * @param rolRepository Repositorio de roles a inyectar
     */
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    /**
     * Obtiene todos los roles de usuario del sistema.
     * 
     * Este método retorna una lista con todos los roles registrados
     * en la base de datos. Útil para administración y gestión de permisos.
     * 
     * @return Lista de todos los roles de usuario
     */
    public List<Roles> listarTodos() {
        return rolRepository.findAll();
    }

    /**
     * Busca un rol de usuario por su identificador único.
     * 
     * @param id Identificador único del rol
     * @return Optional con el rol encontrado o vacío si no existe
     */
    public Optional<Roles> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

    /**
     * Guarda o actualiza un rol de usuario en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos roles
     * como la actualización de roles existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del rol.
     * 
     * @param rol Rol de usuario a guardar o actualizar
     * @return Rol guardado con su ID asignado
     */
    public Roles guardar(Roles rol) {
        return rolRepository.save(rol);
    }

    /**
     * Elimina un rol de usuario del sistema por su identificador.
     * 
     * @param id Identificador único del rol a eliminar
     */
    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }
}
