package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.ImagenesDiseno;
import com.sena.creyese.dentvision_spring.service.ImagenesDisenoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de imágenes de diseño en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre imágenes de diseño dental, incluyendo almacenamiento, gestión de archivos,
 * aprobación y seguimiento de diseños. Todos los endpoints están bajo la ruta base
 * /api/imagenes-diseno y siguen los estándares RESTful.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Endpoints disponibles:
 * - GET /api/imagenes-diseno: Obtener todas las imágenes de diseño
 * - GET /api/imagenes-diseno/{id}: Obtener imagen de diseño por ID
 * - POST /api/imagenes-diseno: Crear nueva imagen de diseño
 * - PUT /api/imagenes-diseno/{id}: Actualizar imagen de diseño existente
 * - DELETE /api/imagenes-diseno/{id}: Eliminar imagen de diseño
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/imagenes-diseno")
public class ImagenesDisenoController {

    /** Servicio de negocio para la gestión de imágenes de diseño */
    private final ImagenesDisenoService imagenesDisenoService;

    /**
     * Constructor que inyecta el servicio de imágenes de diseño.
     * 
     * @param imagenesDisenoService Servicio de imágenes de diseño a inyectar
     */
    public ImagenesDisenoController(ImagenesDisenoService imagenesDisenoService) {
        this.imagenesDisenoService = imagenesDisenoService;
    }

    /**
     * Endpoint para obtener todas las imágenes de diseño del sistema.
     * 
     * Este método retorna una lista completa de todas las imágenes registradas
     * en la base de datos. Útil para administración y catálogo de diseños.
     * 
     * @return Lista de todas las imágenes de diseño
     * @see ImagenesDisenoService#listarTodos()
     */
    @GetMapping
    public List<ImagenesDiseno> listarTodos() {
        return imagenesDisenoService.listarTodos();
    }

    /**
     * Endpoint para obtener una imagen de diseño específica por su ID.
     * 
     * Este método busca una imagen de diseño por su identificador único y
     * retorna los datos de la imagen si existe. Si la imagen no se encuentra,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la imagen de diseño a buscar
     * @return ResponseEntity con la imagen encontrada (200) o 404 si no existe
     * @see ImagenesDisenoService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ImagenesDiseno> obtenerPorId(@PathVariable Long id) {
        return imagenesDisenoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear una nueva imagen de diseño en el sistema.
     * 
     * Este método recibe los datos de una nueva imagen de diseño, valida la
     * información de entrada y la registra en la base de datos.
     * Retorna la imagen creada con su ID asignado.
     * 
     * @param imagenesDiseno Datos de la imagen de diseño a crear (validados con @Valid)
     * @return ResponseEntity con la imagen creada (200) y sus datos
     * @see ImagenesDisenoService#guardar(ImagenesDiseno)
     */
    @PostMapping
    public ResponseEntity<ImagenesDiseno> crear(@Valid @RequestBody ImagenesDiseno imagenesDiseno) {
        ImagenesDiseno nueva = imagenesDisenoService.guardar(imagenesDiseno);
        return ResponseEntity.ok(nueva);
    }

    /**
     * Endpoint para actualizar una imagen de diseño existente.
     * 
     * Este método actualiza los datos de una imagen de diseño existente
     * identificada por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos. Si la imagen no existe,
     * retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único de la imagen de diseño a actualizar
     * @param imagenesDiseno Datos actualizados de la imagen de diseño (validados con @Valid)
     * @return ResponseEntity con la imagen actualizada (200) o 404 si no existe
     * @see ImagenesDisenoService#obtenerPorId(Long)
     * @see ImagenesDisenoService#guardar(ImagenesDiseno)
     */
    @PutMapping("/{id}")
    public ResponseEntity<ImagenesDiseno> actualizar(@PathVariable Long id, @Valid @RequestBody ImagenesDiseno imagenesDiseno) {
        return imagenesDisenoService.obtenerPorId(id)
                .map(existente -> {
                    existente.setImagen(imagenesDiseno.getImagen());
                    existente.setDescripcion(imagenesDiseno.getDescripcion());
                    existente.setProcedimiento(imagenesDiseno.getProcedimiento());
                    ImagenesDiseno actualizada = imagenesDisenoService.guardar(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar una imagen de diseño del sistema.
     * 
     * Este método elimina una imagen de diseño del sistema por su ID.
     * Primero verifica que la imagen exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si la imagen no existe.
     * 
     * @param id Identificador único de la imagen de diseño a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see ImagenesDisenoService#obtenerPorId(Long)
     * @see ImagenesDisenoService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (imagenesDisenoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        imagenesDisenoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
