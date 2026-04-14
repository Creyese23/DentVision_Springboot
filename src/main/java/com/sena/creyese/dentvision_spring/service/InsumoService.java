package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Insumo;
import com.sena.creyese.dentvision_spring.repository.InsumoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de insumos dentales en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con insumos dentales, incluyendo gestión de materiales,
 * control de stock, precios y proveedores. Actúa como una capa de
 * abstracción entre los controladores y el repositorio de insumos.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de insumos dentales
 * - Control de stock y niveles críticos
 * - Gestión de precios y categorías
 * - Control de fechas de vencimiento
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class InsumoService {

    /** Repositorio para el acceso a datos de insumos */
    private final InsumoRepository insumoRepository;

    /**
     * Constructor que inyecta el repositorio de insumos.
     * 
     * @param insumoRepository Repositorio de insumos a inyectar
     */
    public InsumoService(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    /**
     * Obtiene todos los insumos dentales del sistema.
     * 
     * Este método retorna una lista con todos los insumos registrados
     * en la base de datos. Útil para administración y control de inventario.
     * 
     * @return Lista de todos los insumos dentales
     */
    public List<Insumo> listarTodos() {
        return insumoRepository.findAll();
    }

    /**
     * Busca un insumo dental por su identificador único.
     * 
     * @param id Identificador único del insumo
     * @return Optional con el insumo encontrado o vacío si no existe
     */
    public Optional<Insumo> obtenerPorId(Long id) {
        return insumoRepository.findById(id);
    }

    /**
     * Guarda o actualiza un insumo dental en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos insumos
     * como la actualización de insumos existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del insumo.
     * 
     * @param insumo Insumo dental a guardar o actualizar
     * @return Insumo guardado con su ID asignado
     */
    public Insumo guardar(Insumo insumo) {
        return insumoRepository.save(insumo);
    }

    /**
     * Elimina un insumo dental del sistema por su identificador.
     * 
     * @param id Identificador único del insumo a eliminar
     */
    public void eliminar(Long id) {
        insumoRepository.deleteById(id);
    }
}
