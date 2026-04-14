package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Admin;
import com.sena.creyese.dentvision_spring.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de administradores en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con administradores del sistema, incluyendo registro, búsqueda
 * por documento, gestión de niveles de acceso y departamentos. Actúa como una
 * capa de abstracción entre los controladores y el repositorio de administradores.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de administradores
 * - Búsqueda por documento de identificación
 * - Gestión de niveles de acceso y departamentos
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 * - Integración con sistema de autenticación
 */
@Service
public class AdminService {
    
    /** Repositorio para el acceso a datos de administradores */
    private final AdminRepository adminRepository;

    /**
     * Constructor que inyecta el repositorio de administradores.
     * 
     * @param adminRepository Repositorio de administradores a inyectar
     */
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * Obtiene todos los administradores del sistema.
     * 
     * Este método retorna una lista con todos los administradores registrados
     * en la base de datos. Útil para administración y reportes de personal.
     * 
     * @return Lista de todos los administradores
     */
    public List<Admin> listarTodos() {
        return adminRepository.findAll();
    }

    /**
     * Busca un administrador por su identificador único.
     * 
     * @param id Identificador único del administrador
     * @return Optional con el administrador encontrado o vacío si no existe
     */
    public Optional<Admin> obtenerPorId(Long id) {
        return adminRepository.findById(id);
    }

    /**
     * Busca un administrador por su número de documento de identificación.
     * 
     * Este método es utilizado principalmente para el proceso de registro
     * y búsqueda de administradores, donde el documento de identificación es
     * único y sirve como identificador principal del administrador.
     * 
     * @param documento Número de documento del administrador a buscar
     * @return Optional con el administrador encontrado o vacío si no existe
     */
    public Optional<Admin> obtenerPorDocumento(String documento) {
        return adminRepository.findByDocumento(documento);
    }

    /**
     * Guarda o actualiza un administrador en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos administradores
     * como la actualización de administradores existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del administrador.
     * 
     * @param admin Administrador a guardar o actualizar
     * @return Administrador guardado con su ID asignado
     */
    public Admin guardar(Admin admin) {
        return adminRepository.save(admin);
    }

    /**
     * Elimina un administrador del sistema por su identificador.
     * 
     * @param id Identificador único del administrador a eliminar
     */
    public void eliminar(Long id) {
        adminRepository.deleteById(id);
    }
}
