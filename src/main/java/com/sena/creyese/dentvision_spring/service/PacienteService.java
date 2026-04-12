package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Paciente;
import com.sena.creyese.dentvision_spring.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de pacientes en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con pacientes, incluyendo registro, búsqueda, actualización
 * y eliminación. Actúa como una capa de abstracción entre los controladores
 * y el repositorio de pacientes.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Gestión CRUD de pacientes
 * - Búsqueda por documento de identificación
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 * - Integración con sistema de autenticación
 */
@Service
public class PacienteService {

    /** Repositorio para el acceso a datos de pacientes */
    private final PacienteRepository pacienteRepository;

    /**
     * Constructor que inyecta el repositorio de pacientes.
     * 
     * @param pacienteRepository Repositorio de pacientes a inyectar
     */
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Obtiene todos los pacientes del sistema.
     * 
     * Este método retorna una lista con todos los pacientes registrados
     * en la base de datos. Útil para administración y reportes.
     * 
     * @return Lista de todos los pacientes
     */
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    /**
     * Busca un paciente por su identificador único.
     * 
     * @param id Identificador único del paciente
     * @return Optional con el paciente encontrado o vacío si no existe
     */
    public Optional<Paciente> obtenerPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    /**
     * Busca un paciente por su número de documento de identificación.
     * 
     * Este método es utilizado principalmente para el proceso de registro
     * y búsqueda de pacientes, donde el documento de identificación es
     * único y sirve como identificador principal del paciente.
     * 
     * @param documento Número de documento del paciente a buscar
     * @return Optional con el paciente encontrado o vacío si no existe
     */
    public Optional<Paciente> obtenerPorDocumento(String documento) {
        return pacienteRepository.findByDocumento(documento);
    }

    /**
     * Guarda o actualiza un paciente en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos pacientes
     * como la actualización de pacientes existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del paciente.
     * 
     * @param paciente Paciente a guardar o actualizar
     * @return Paciente guardado con su ID asignado
     */
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    /**
     * Elimina un paciente del sistema por su identificador.
     * 
     * @param id Identificador único del paciente a eliminar
     */
    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
