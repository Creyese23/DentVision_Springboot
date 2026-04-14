package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Pagos;
import com.sena.creyese.dentvision_spring.service.PagosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de pagos en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre pagos de servicios dentales, incluyendo registro de transacciones,
 * métodos de pago, montos y seguimiento de facturas asociadas. Todos los endpoints
 * están bajo la ruta base /api/pagos y siguen los estándares RESTful.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Endpoints disponibles:
 * - GET /api/pagos: Obtener todos los pagos
 * - GET /api/pagos/{id}: Obtener pago por ID
 * - POST /api/pagos: Crear nuevo pago
 * - PUT /api/pagos/{id}: Actualizar pago existente
 * - DELETE /api/pagos/{id}: Eliminar pago
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    /** Servicio de negocio para la gestión de pagos */
    private final PagosService pagosService;

    /**
     * Constructor que inyecta el servicio de pagos.
     * 
     * @param pagosService Servicio de pagos a inyectar
     */
    public PagosController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    /**
     * Endpoint para obtener todos los pagos del sistema.
     * 
     * Este método retorna una lista completa de todos los pagos registrados
     * en la base de datos. Útil para administración y reportes financieros.
     * 
     * @return Lista de todos los pagos
     * @see PagosService#listarTodos()
     */
    @GetMapping
    public List<Pagos> listarTodos() {
        return pagosService.listarTodos();
    }

    /**
     * Endpoint para obtener un pago específico por su ID.
     * 
     * Este método busca un pago por su identificador único y
     * retorna los datos del pago si existe. Si el pago no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del pago a buscar
     * @return ResponseEntity con el pago encontrado (200) o 404 si no existe
     * @see PagosService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pagos> obtenerPorId(@PathVariable Long id) {
        return pagosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo pago en el sistema.
     * 
     * Este método recibe los datos de un nuevo pago, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el pago creado con su ID asignado.
     * 
     * @param pagos Datos del pago a crear (validados con @Valid)
     * @return ResponseEntity con el pago creado (200) y sus datos
     * @see PagosService#guardar(Pagos)
     */
    @PostMapping
    public ResponseEntity<Pagos> crear(@Valid @RequestBody Pagos pagos) {
        Pagos nuevo = pagosService.guardar(pagos);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un pago existente.
     * 
     * Este método actualiza los datos de un pago existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos financieros. Si el pago no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del pago a actualizar
     * @param pagos Datos actualizados del pago (validados con @Valid)
     * @return ResponseEntity con el pago actualizado (200) o 404 si no existe
     * @see PagosService#obtenerPorId(Long)
     * @see PagosService#guardar(Pagos)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pagos> actualizar(@PathVariable Long id, @Valid @RequestBody Pagos pagos) {
        return pagosService.obtenerPorId(id)
                .map(existente -> {
                    existente.setFecha(pagos.getFecha());
                    existente.setMetodPago(pagos.getMetodPago());
                    existente.setMonto(pagos.getMonto());
                    existente.setFacturas(pagos.getFacturas());
                    Pagos actualizado = pagosService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un pago del sistema.
     * 
     * Este método elimina un pago del sistema por su ID.
     * Primero verifica que el pago exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el pago no existe.
     * 
     * @param id Identificador único del pago a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see PagosService#obtenerPorId(Long)
     * @see PagosService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pagosService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pagosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
