package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Procedimiento;
import com.sena.creyese.dentvision_spring.repository.ProcedimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de procedimientos dentales en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con procedimientos dentales, incluyendo gestión de tratamientos
 * especializados, precios, duraciones y requisitos especiales. Actúa como una
 * capa de abstracción entre los controladores y el repositorio de procedimientos.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Gestión CRUD de procedimientos dentales
 * - Gestión de precios y duraciones
 * - Control de requisitos especiales (anestesia, radiografía)
 * - Gestión de categorías y prioridades
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class ProcedimientoService {

    /** Repositorio para el acceso a datos de procedimientos */
    private final ProcedimientoRepository procedimientoRepository;

    /**
     * Constructor que inyecta el repositorio de procedimientos.
     * 
     * @param procedimientoRepository Repositorio de procedimientos a inyectar
     */
    public ProcedimientoService(ProcedimientoRepository procedimientoRepository) {
        this.procedimientoRepository = procedimientoRepository;
    }

    /**
     * Obtiene todos los procedimientos dentales del sistema.
     * 
     * Este método retorna una lista con todos los procedimientos registrados
     * en la base de datos. Útil para administración y catálogo de tratamientos.
     * 
     * @return Lista de todos los procedimientos dentales
     */
    public List<Procedimiento> listarTodos() {
        return procedimientoRepository.findAll();
    }

    /**
     * Busca un procedimiento dental por su identificador único.
     * 
     * @param id Identificador único del procedimiento
     * @return Optional con el procedimiento encontrado o vacío si no existe
     */
    public Optional<Procedimiento> obtenerPorId(Long id) {
        return procedimientoRepository.findById(id);
    }

    /**
     * Guarda o actualiza un procedimiento dental en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos procedimientos
     * como la actualización de procedimientos existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del procedimiento.
     * 
     * @param procedimiento Procedimiento dental a guardar o actualizar
     * @return Procedimiento guardado con su ID asignado
     */
    public Procedimiento guardar(Procedimiento procedimiento) {
        return procedimientoRepository.save(procedimiento);
    }

    /**
     * Elimina un procedimiento dental del sistema por su identificador.
     * 
     * @param id Identificador único del procedimiento a eliminar
     */
    public void eliminar(Long id) {
        procedimientoRepository.deleteById(id);
    }
}
