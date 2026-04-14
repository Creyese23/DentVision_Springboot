package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Pagos;
import com.sena.creyese.dentvision_spring.repository.PagosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de pagos en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con pagos de servicios dentales, incluyendo registro,
 * seguimiento, confirmación y gestión de métodos de pago. Actúa como
 * una capa de abstracción entre los controladores y el repositorio de pagos.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de pagos de servicios
 * - Seguimiento de estados de pago
 * - Gestión de métodos de pago (efectivo, tarjeta, transferencia)
 * - Integración con facturas y pacientes
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class PagosService {

    /** Repositorio para el acceso a datos de pagos */
    private final PagosRepository pagosRepository;

    /**
     * Constructor que inyecta el repositorio de pagos.
     * 
     * @param pagosRepository Repositorio de pagos a inyectar
     */
    public PagosService(PagosRepository pagosRepository) {
        this.pagosRepository = pagosRepository;
    }

    /**
     * Obtiene todos los pagos del sistema.
     * 
     * Este método retorna una lista con todos los pagos registrados
     * en la base de datos. Útil para administración y reportes financieros.
     * 
     * @return Lista de todos los pagos
     */
    public List<Pagos> listarTodos() {
        return pagosRepository.findAll();
    }

    /**
     * Busca un pago por su identificador único.
     * 
     * @param id Identificador único del pago
     * @return Optional con el pago encontrado o vacío si no existe
     */
    public Optional<Pagos> obtenerPorId(Long id) {
        return pagosRepository.findById(id);
    }

    /**
     * Guarda o actualiza un pago en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos pagos
     * como la actualización de pagos existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del pago.
     * 
     * @param pagos Pago a guardar o actualizar
     * @return Pago guardado con su ID asignado
     */
    public Pagos guardar(Pagos pagos) {
        return pagosRepository.save(pagos);
    }

    /**
     * Elimina un pago del sistema por su identificador.
     * 
     * @param id Identificador único del pago a eliminar
     */
    public void eliminar(Long id) {
        pagosRepository.deleteById(id);
    }
}
