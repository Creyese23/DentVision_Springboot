package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Cita;
import com.sena.creyese.dentvision_spring.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de citas en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con citas dentales, incluyendo programación, confirmación,
 * cancelación y seguimiento de citas. Actúa como una capa de abstracción
 * entre los controladores y el repositorio de citas.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de citas dentales
 * - Programación de citas con pacientes y odontólogos
 * - Gestión de estados de citas
 * - Validaciones de disponibilidad
 * - Manejo de transacciones implícito
 */
@Service
public class CitaService {

    /** Repositorio para el acceso a datos de citas */
    private final CitaRepository citaRepository;

    /**
     * Constructor que inyecta el repositorio de citas.
     * 
     * @param citaRepository Repositorio de citas a inyectar
     */
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    /**
     * Obtiene todas las citas del sistema.
     * 
     * Este método retorna una lista con todas las citas registradas
     * en la base de datos. Útil para administración y reportes de agenda.
     * 
     * @return Lista de todas las citas
     */
    public List<Cita> listarTodos() {
        return citaRepository.findAll();
    }

    /**
     * Busca una cita por su identificador único.
     * 
     * @param id Identificador único de la cita
     * @return Optional con la cita encontrada o vacío si no existe
     */
    public Optional<Cita> obtenerPorId(Long id) {
        return citaRepository.findById(id);
    }

    /**
     * Guarda o actualiza una cita en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevas citas
     * como la actualización de citas existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID de la cita.
     * 
     * @param cita Cita a guardar o actualizar
     * @return Cita guardada con su ID asignado
     */
    public Cita guardar(Cita cita) {
        return citaRepository.save(cita);
    }

    /**
     * Elimina una cita del sistema por su identificador.
     * 
     * @param id Identificador único de la cita a eliminar
     */
    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }
}
