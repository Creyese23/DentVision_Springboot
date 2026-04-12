package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.DetalleFactura;
import com.sena.creyese.dentvision_spring.service.DetalleFacturaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de detalles de facturas en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre detalles específicos de facturas, incluyendo servicios facturados,
 * cantidades, precios unitarios y subtotales. Todos los endpoints están bajo la
 * ruta base /api/detalles-factura y siguen los estándares RESTful.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/detalles-factura: Obtener todos los detalles de facturas
 * - GET /api/detalles-factura/{id}: Obtener detalle de factura por ID
 * - POST /api/detalles-factura: Crear nuevo detalle de factura
 * - PUT /api/detalles-factura/{id}: Actualizar detalle de factura existente
 * - DELETE /api/detalles-factura/{id}: Eliminar detalle de factura
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/detalles-factura")
public class DetalleFacturaController {

    /** Servicio de negocio para la gestión de detalles de facturas */
    private final DetalleFacturaService detalleFacturaService;

    /**
     * Constructor que inyecta el servicio de detalles de facturas.
     * 
     * @param detalleFacturaService Servicio de detalles de facturas a inyectar
     */
    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    /**
     * Endpoint para obtener todos los detalles de facturas del sistema.
     * 
     * Este método retorna una lista completa de todos los detalles registrados
     * en la base de datos. Útil para administración y reportes financieros.
     * 
     * @return Lista de todos los detalles de facturas
     * @see DetalleFacturaService#listarTodos()
     */
    @GetMapping
    public List<DetalleFactura> listarTodos() {
        return detalleFacturaService.listarTodos();
    }

    /**
     * Endpoint para obtener un detalle de factura específico por su ID.
     * 
     * Este método busca un detalle de factura por su identificador único y
     * retorna los datos del detalle si existe. Si el detalle no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del detalle de factura a buscar
     * @return ResponseEntity con el detalle encontrado (200) o 404 si no existe
     * @see DetalleFacturaService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> obtenerPorId(@PathVariable Long id) {
        return detalleFacturaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo detalle de factura en el sistema.
     * 
     * Este método recibe los datos de un nuevo detalle de factura, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el detalle creado con su ID asignado.
     * 
     * @param detalleFactura Datos del detalle de factura a crear (validados con @Valid)
     * @return ResponseEntity con el detalle creado (200) y sus datos
     * @see DetalleFacturaService#guardar(DetalleFactura)
     */
    @PostMapping
    public ResponseEntity<DetalleFactura> crear(@Valid @RequestBody DetalleFactura detalleFactura) {
        DetalleFactura nuevo = detalleFacturaService.guardar(detalleFactura);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un detalle de factura existente.
     * 
     * Este método actualiza los datos de un detalle de factura existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos financieros. Si el detalle
     * no existe, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del detalle de factura a actualizar
     * @param detalleFactura Datos actualizados del detalle de factura (validados con @Valid)
     * @return ResponseEntity con el detalle actualizado (200) o 404 si no existe
     * @see DetalleFacturaService#obtenerPorId(Long)
     * @see DetalleFacturaService#guardar(DetalleFactura)
     */
    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> actualizar(@PathVariable Long id, @Valid @RequestBody DetalleFactura detalleFactura) {
        return detalleFacturaService.obtenerPorId(id)
                .map(existente -> {
                    existente.setCantidad(detalleFactura.getCantidad());
                    existente.setPrecio(detalleFactura.getPrecio());
                    existente.setFactura(detalleFactura.getFactura());
                    existente.setServicios(detalleFactura.getServicios());
                    DetalleFactura actualizado = detalleFacturaService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un detalle de factura del sistema.
     * 
     * Este método elimina un detalle de factura del sistema por su ID.
     * Primero verifica que el detalle exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el detalle no existe.
     * 
     * @param id Identificador único del detalle de factura a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see DetalleFacturaService#obtenerPorId(Long)
     * @see DetalleFacturaService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (detalleFacturaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        detalleFacturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
