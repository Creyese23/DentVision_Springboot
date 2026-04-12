package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Factura;
import com.sena.creyese.dentvision_spring.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de facturas en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre facturas, incluyendo generación, consulta, actualización y cancelación.
 * Todos los endpoints están bajo la ruta base /api/facturas y siguen los
 * estándares RESTful para una mejor integración con clientes web y móviles.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/facturas: Obtener todas las facturas
 * - GET /api/facturas/{id}: Obtener factura por ID
 * - POST /api/facturas: Crear nueva factura
 * - PUT /api/facturas/{id}: Actualizar factura existente
 * - DELETE /api/facturas/{id}: Eliminar factura
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    /** Servicio de negocio para la gestión de facturas */
    private final FacturaService facturaService;

    /**
     * Constructor que inyecta el servicio de facturas.
     * 
     * @param facturaService Servicio de facturas a inyectar
     */
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    /**
     * Endpoint para obtener todas las facturas del sistema.
     * 
     * Este método retorna una lista completa de todas las facturas
     * registradas en la base de datos. Útil para administración y
     * generación de reportes financieros.
     * 
     * @return Lista de todas las facturas registradas
     * @see FacturaService#listarTodos()
     */
    @GetMapping
    public List<Factura> listarTodos() {
        return facturaService.listarTodos();
    }

    /**
     * Endpoint para obtener una factura específica por su ID.
     * 
     * Este método busca una factura por su identificador único y
     * retorna los datos de la factura si existe. Si la factura no
     * se encuentra, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la factura a buscar
     * @return ResponseEntity con la factura encontrada (200) o 404 si no existe
     * @see FacturaService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerPorId(@PathVariable Long id) {
        return facturaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear una nueva factura en el sistema.
     * 
     * Este método recibe los datos de una nueva factura, valida la
     * información de entrada y la registra en la base de datos.
     * Retorna la factura creada con su ID asignado.
     * 
     * @param factura Datos de la factura a crear (validados con @Valid)
     * @return ResponseEntity con la factura creada (200) y sus datos
     * @see FacturaService#guardar(Factura)
     */
    @PostMapping
    public ResponseEntity<Factura> crear(@Valid @RequestBody Factura factura) {
        Factura nueva = facturaService.guardar(factura);
        return ResponseEntity.ok(nueva);
    }

    /**
     * Endpoint para actualizar una factura existente.
     * 
     * Este método actualiza los datos de una factura existente
     * identificada por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos financieros. Si la factura
     * no existe, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la factura a actualizar
     * @param factura Datos actualizados de la factura (validados con @Valid)
     * @return ResponseEntity con la factura actualizada (200) o 404 si no existe
     * @see FacturaService#obtenerPorId(Long)
     * @see FacturaService#guardar(Factura)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Factura> actualizar(@PathVariable Long id, @Valid @RequestBody Factura factura) {
        return facturaService.obtenerPorId(id)
                .map(existente -> {
                    existente.setFecha(factura.getFecha());
                    existente.setValor(factura.getValor());
                    existente.setEstado(factura.isEstado());
                    existente.setPaciente(factura.getPaciente());
                    Factura actualizada = facturaService.guardar(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar una factura del sistema.
     * 
     * Este método elimina una factura del sistema por su ID.
     * Primero verifica que la factura exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si la factura no existe.
     * 
     * @param id Identificador único de la factura a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see FacturaService#obtenerPorId(Long)
     * @see FacturaService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (facturaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        facturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
