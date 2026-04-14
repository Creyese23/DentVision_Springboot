package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Odontologo;
import com.sena.creyese.dentvision_spring.service.OdontologoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de odontólogos en el sistema DentVision.
 * 
 * Esta clase proporciona los endpoints de la API REST para realizar operaciones
 * CRUD sobre odontólogos, incluyendo registro, consulta, actualización y eliminación.
 * Todos los endpoints están bajo la ruta base /api/odontologos y siguen los
 * estándares RESTful para una mejor integración con clientes web y móviles.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Endpoints disponibles:
 * - GET /api/odontologos: Obtener todos los odontólogos
 * - GET /api/odontologos/{id}: Obtener odontólogo por ID
 * - POST /api/odontologos: Crear nuevo odontólogo
 * - PUT /api/odontologos/{id}: Actualizar odontólogo existente
 * - DELETE /api/odontologos/{id}: Eliminar odontólogo
 * 
 * Características de seguridad:
 * - Validación de datos de entrada con @Valid
 * - Manejo adecuado de respuestas HTTP
 * - Protección contra inyección de datos
 * - Gestión de errores y respuestas 404
 */
@RestController
@RequestMapping("/api/odontologos")
public class OdontologoController {

    /** Servicio de negocio para la gestión de odontólogos */
    private final OdontologoService odontologoService;

    /**
     * Constructor que inyecta el servicio de odontólogos.
     * 
     * @param odontologoService Servicio de odontólogos a inyectar
     */
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    /**
     * Endpoint para obtener todos los odontólogos del sistema.
     * 
     * Este método retorna una lista completa de todos los odontólogos
     * registrados en la base de datos. Útil para administración y
     * generación de reportes de personal clínico.
     * 
     * @return Lista de todos los odontólogos registrados
     * @see OdontologoService#listarTodos()
     */
    @GetMapping
    public List<Odontologo> listarTodos() {
        return odontologoService.listarTodos();
    }

    /**
     * Endpoint para obtener un odontólogo específico por su ID.
     * 
     * Este método busca un odontólogo por su identificador único y
     * retorna los datos del profesional si existe. Si el odontólogo no
     * se encuentra, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del odontólogo a buscar
     * @return ResponseEntity con el odontólogo encontrado (200) o 404 si no existe
     * @see OdontologoService#obtenerPorId(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> obtenerPorId(@PathVariable Long id) {
        return odontologoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo odontólogo en el sistema.
     * 
     * Este método recibe los datos de un nuevo odontólogo, valida la
     * información de entrada y lo registra en la base de datos.
     * Retorna el odontólogo creado con su ID asignado.
     * 
     * @param odontologo Datos del odontólogo a crear (validados con @Valid)
     * @return ResponseEntity con el odontólogo creado (200) y sus datos
     * @see OdontologoService#guardar(Odontologo)
     */
    @PostMapping
    public ResponseEntity<Odontologo> crear(@Valid @RequestBody Odontologo odontologo) {
        Odontologo nuevo = odontologoService.guardar(odontologo);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para actualizar un odontólogo existente.
     * 
     * Este método actualiza los datos de un odontólogo existente
     * identificado por su ID. Solo actualiza los campos permitidos
     * y mantiene la integridad de los datos profesionales. Si el odontólogo
     * no existe, retorna un código de estado HTTP 404 (Not Found).
     * 
     * @param id Identificador único del odontólogo a actualizar
     * @param odontologo Datos actualizados del odontólogo (validados con @Valid)
     * @return ResponseEntity con el odontólogo actualizado (200) o 404 si no existe
     * @see OdontologoService#obtenerPorId(Long)
     * @see OdontologoService#guardar(Odontologo)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizar(@PathVariable Long id, @Valid @RequestBody Odontologo odontologo) {
        return odontologoService.obtenerPorId(id)
                .map(existente -> {
                    existente.setTipoDocumento(odontologo.getTipoDocumento());
                    existente.setDocumento(odontologo.getDocumento());
                    existente.setGenero(odontologo.getGenero());
                    existente.setTelefono(odontologo.getTelefono());
                    existente.setDireccion(odontologo.getDireccion());
                    existente.setEspecialidad(odontologo.getEspecialidad());
                    existente.setN_Licencia(odontologo.getN_Licencia());
                    existente.setFecha_nacimiento(odontologo.getFecha_nacimiento());
                    existente.setUsuario(odontologo.getUsuario());
                    Odontologo actualizado = odontologoService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un odontólogo del sistema.
     * 
     * Este método elimina un odontólogo del sistema por su ID.
     * Primero verifica que el odontólogo exista antes de proceder
     * con la eliminación. Retorna 204 (No Content) si la eliminación
     * fue exitosa o 404 (Not Found) si el odontólogo no existe.
     * 
     * @param id Identificador único del odontólogo a eliminar
     * @return ResponseEntity con 204 si se eliminó correctamente o 404 si no existe
     * @see OdontologoService#obtenerPorId(Long)
     * @see OdontologoService#eliminar(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (odontologoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        odontologoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
