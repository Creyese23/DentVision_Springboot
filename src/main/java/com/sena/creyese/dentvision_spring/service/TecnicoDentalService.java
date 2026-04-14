package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.TecnicoDental;
import com.sena.creyese.dentvision_spring.repository.TecnicoDentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de técnicos dentales en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con técnicos dentales, incluyendo registro, búsqueda por documento,
 * gestión de especializaciones y asignación de trabajos de laboratorio.
 * Actúa como una capa de abstracción entre los controladores y el repositorio.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de técnicos dentales
 * - Búsqueda por documento de identificación
 * - Gestión de especializaciones y certificaciones
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 * - Integración con sistema de autenticación
 */
@Service
public class TecnicoDentalService {
    
    /** Repositorio para el acceso a datos de técnicos dentales */
    private final TecnicoDentalRepository tecnicoDentalRepository;

    /**
     * Constructor que inyecta el repositorio de técnicos dentales.
     * 
     * @param tecnicoDentalRepository Repositorio de técnicos dentales a inyectar
     */
    public TecnicoDentalService(TecnicoDentalRepository tecnicoDentalRepository) {
        this.tecnicoDentalRepository = tecnicoDentalRepository;
    }

    /**
     * Obtiene todos los técnicos dentales del sistema.
     * 
     * Este método retorna una lista con todos los técnicos registrados
     * en la base de datos. Útil para administración y reportes de personal.
     * 
     * @return Lista de todos los técnicos dentales
     */
    public List<TecnicoDental> listarTodos() {
        return tecnicoDentalRepository.findAll();
    }

    /**
     * Busca un técnico dental por su identificador único.
     * 
     * @param id Identificador único del técnico dental
     * @return Optional con el técnico encontrado o vacío si no existe
     */
    public Optional<TecnicoDental> obtenerPorId(Long id) {
        return tecnicoDentalRepository.findById(id);
    }

    /**
     * Busca un técnico dental por su número de documento de identificación.
     * 
     * Este método es utilizado principalmente para el proceso de registro
     * y búsqueda de técnicos, donde el documento de identificación es
     * único y sirve como identificador principal del técnico.
     * 
     * @param documento Número de documento del técnico a buscar
     * @return Optional con el técnico encontrado o vacío si no existe
     */
    public Optional<TecnicoDental> obtenerPorDocumento(String documento) {
        return tecnicoDentalRepository.findByDocumento(documento);
    }

    /**
     * Guarda o actualiza un técnico dental en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos técnicos
     * como la actualización de técnicos existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del técnico.
     * 
     * @param tecnicoDental Técnico dental a guardar o actualizar
     * @return Técnico dental guardado con su ID asignado
     */
    public TecnicoDental guardar(TecnicoDental tecnicoDental) {
        return tecnicoDentalRepository.save(tecnicoDental);
    }

    /**
     * Elimina un técnico dental del sistema por su identificador.
     * 
     * @param id Identificador único del técnico dental a eliminar
     */
    public void eliminar(Long id) {
        tecnicoDentalRepository.deleteById(id);
    }
}
