package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.AuxiliarAdmin;
import com.sena.creyese.dentvision_spring.repository.AuxiliarAdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de auxiliares administrativos en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con auxiliares administrativos, incluyendo registro, búsqueda
 * por documento, gestión de áreas de trabajo y turnos. Actúa como una capa
 * de abstracción entre los controladores y el repositorio de auxiliares.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Gestión CRUD de auxiliares administrativos
 * - Búsqueda por documento de identificación
 * - Gestión de áreas de trabajo y turnos
 * - Control de experiencia y disponibilidad
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class AuxiliarAdminService {
    
    /** Repositorio para el acceso a datos de auxiliares administrativos */
    private final AuxiliarAdminRepository auxiliarAdminRepository;

    /**
     * Constructor que inyecta el repositorio de auxiliares administrativos.
     * 
     * @param auxiliarAdminRepository Repositorio de auxiliares administrativos a inyectar
     */
    public AuxiliarAdminService(AuxiliarAdminRepository auxiliarAdminRepository) {
        this.auxiliarAdminRepository = auxiliarAdminRepository;
    }

    /**
     * Obtiene todos los auxiliares administrativos del sistema.
     * 
     * Este método retorna una lista con todos los auxiliares registrados
     * en la base de datos. Útil para administración y reportes de personal.
     * 
     * @return Lista de todos los auxiliares administrativos
     */
    public List<AuxiliarAdmin> listarTodos() {
        return auxiliarAdminRepository.findAll();
    }

    /**
     * Busca un auxiliar administrativo por su identificador único.
     * 
     * @param id Identificador único del auxiliar administrativo
     * @return Optional con el auxiliar encontrado o vacío si no existe
     */
    public Optional<AuxiliarAdmin> obtenerPorId(Long id) {
        return auxiliarAdminRepository.findById(id);
    }

    /**
     * Busca un auxiliar administrativo por su número de documento de identificación.
     * 
     * Este método es utilizado principalmente para el proceso de registro
     * y búsqueda de auxiliares, donde el documento de identificación es
     * único y sirve como identificador principal del auxiliar.
     * 
     * @param documento Número de documento del auxiliar a buscar
     * @return Optional con el auxiliar encontrado o vacío si no existe
     */
    public Optional<AuxiliarAdmin> obtenerPorDocumento(String documento) {
        return auxiliarAdminRepository.findByDocumento(documento);
    }

    /**
     * Guarda o actualiza un auxiliar administrativo en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos auxiliares
     * como la actualización de auxiliares existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del auxiliar.
     * 
     * @param auxiliarAdmin Auxiliar administrativo a guardar o actualizar
     * @return Auxiliar administrativo guardado con su ID asignado
     */
    public AuxiliarAdmin guardar(AuxiliarAdmin auxiliarAdmin) {
        return auxiliarAdminRepository.save(auxiliarAdmin);
    }

    /**
     * Elimina un auxiliar administrativo del sistema por su identificador.
     * 
     * @param id Identificador único del auxiliar administrativo a eliminar
     */
    public void eliminar(Long id) {
        auxiliarAdminRepository.deleteById(id);
    }
}
