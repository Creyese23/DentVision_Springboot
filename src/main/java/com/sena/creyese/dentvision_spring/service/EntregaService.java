package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Entrega;
import com.sena.creyese.dentvision_spring.repository.EntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de entregas en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con entregas de trabajos dentales, incluyendo registro,
 * seguimiento, confirmación de recepción y gestión de estados. Actúa como
 * una capa de abstracción entre los controladores y el repositorio de entregas.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de entregas de trabajos
 * - Seguimiento de estados de entrega
 * - Confirmación de recepción por pacientes
 * - Integración con órdenes de trabajo
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class EntregaService {

    /** Repositorio para el acceso a datos de entregas */
    private final EntregaRepository entregaRepository;

    /**
     * Constructor que inyecta el repositorio de entregas.
     * 
     * @param entregaRepository Repositorio de entregas a inyectar
     */
    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    /**
     * Obtiene todas las entregas del sistema.
     * 
     * Este método retorna una lista con todas las entregas registradas
     * en la base de datos. Útil para administración y reportes de entregas.
     * 
     * @return Lista de todas las entregas
     */
    public List<Entrega> listarTodos() {
        return entregaRepository.findAll();
    }

    /**
     * Busca una entrega por su identificador único.
     * 
     * @param id Identificador único de la entrega
     * @return Optional con la entrega encontrada o vacío si no existe
     */
    public Optional<Entrega> obtenerPorId(Long id) {
        return entregaRepository.findById(id);
    }

    /**
     * Guarda o actualiza una entrega en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevas entregas
     * como la actualización de entregas existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID de la entrega.
     * 
     * @param entrega Entrega a guardar o actualizar
     * @return Entrega guardada con su ID asignado
     */
    public Entrega guardar(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    /**
     * Elimina una entrega del sistema por su identificador.
     * 
     * @param id Identificador único de la entrega a eliminar
     */
    public void eliminar(Long id) {
        entregaRepository.deleteById(id);
    }
}
