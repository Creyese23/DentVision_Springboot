package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Inventario;
import com.sena.creyese.dentvision_spring.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de inventario en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con el inventario dental, incluyendo el seguimiento de
 * movimientos de suministros, control de stock y gestión de entradas/salidas.
 * Actúa como una capa de abstracción entre los controladores y el repositorio.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de movimientos de inventario
 * - Seguimiento de stock de suministros
 * - Control de entradas y salidas
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class InventarioService {

    /** Repositorio para el acceso a datos de inventario */
    private final InventarioRepository inventarioRepository;

    /**
     * Constructor que inyecta el repositorio de inventario.
     * 
     * @param inventarioRepository Repositorio de inventario a inyectar
     */
    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    /**
     * Obtiene todos los movimientos de inventario del sistema.
     * 
     * Este método retorna una lista con todos los movimientos registrados
     * en la base de datos. Útil para auditoría y reportes de inventario.
     * 
     * @return Lista de todos los movimientos de inventario
     */
    public List<Inventario> listarTodos() {
        return inventarioRepository.findAll();
    }

    /**
     * Busca un movimiento de inventario por su identificador único.
     * 
     * @param id Identificador único del movimiento de inventario
     * @return Optional con el movimiento encontrado o vacío si no existe
     */
    public Optional<Inventario> obtenerPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    /**
     * Guarda o actualiza un movimiento de inventario en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos movimientos
     * como la actualización de movimientos existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del movimiento.
     * 
     * @param inventario Movimiento de inventario a guardar o actualizar
     * @return Movimiento de inventario guardado con su ID asignado
     */
    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    /**
     * Elimina un movimiento de inventario del sistema por su identificador.
     * 
     * @param id Identificador único del movimiento de inventario a eliminar
     */
    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }
}
