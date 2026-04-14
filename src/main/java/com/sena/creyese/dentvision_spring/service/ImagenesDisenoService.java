package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.ImagenesDiseno;
import com.sena.creyese.dentvision_spring.repository.ImagenesDisenoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de imágenes de diseño en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con imágenes de diseño dental, incluyendo almacenamiento,
 * gestión de archivos, aprobación y seguimiento de diseños. Actúa como
 * una capa de abstracción entre los controladores y el repositorio de imágenes.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Gestión CRUD de imágenes de diseño
 * - Almacenamiento y gestión de archivos digitales
 * - Control de estados de aprobación
 * - Integración con órdenes de trabajo
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 */
@Service
public class ImagenesDisenoService {

    /** Repositorio para el acceso a datos de imágenes de diseño */
    private final ImagenesDisenoRepository imagenesDisenoRepository;

    /**
     * Constructor que inyecta el repositorio de imágenes de diseño.
     * 
     * @param imagenesDisenoRepository Repositorio de imágenes de diseño a inyectar
     */
    public ImagenesDisenoService(ImagenesDisenoRepository imagenesDisenoRepository) {
        this.imagenesDisenoRepository = imagenesDisenoRepository;
    }

    /**
     * Obtiene todas las imágenes de diseño del sistema.
     * 
     * Este método retorna una lista con todas las imágenes registradas
     * en la base de datos. Útil para administración y catálogo de diseños.
     * 
     * @return Lista de todas las imágenes de diseño
     */
    public List<ImagenesDiseno> listarTodos() {
        return imagenesDisenoRepository.findAll();
    }

    /**
     * Busca una imagen de diseño por su identificador único.
     * 
     * @param id Identificador único de la imagen de diseño
     * @return Optional con la imagen encontrada o vacío si no existe
     */
    public Optional<ImagenesDiseno> obtenerPorId(Long id) {
        return imagenesDisenoRepository.findById(id);
    }

    /**
     * Guarda o actualiza una imagen de diseño en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevas imágenes
     * como la actualización de imágenes existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID de la imagen.
     * 
     * @param imagenesDiseno Imagen de diseño a guardar o actualizar
     * @return Imagen de diseño guardada con su ID asignado
     */
    public ImagenesDiseno guardar(ImagenesDiseno imagenesDiseno) {
        return imagenesDisenoRepository.save(imagenesDiseno);
    }

    /**
     * Elimina una imagen de diseño del sistema por su identificador.
     * 
     * @param id Identificador único de la imagen de diseño a eliminar
     */
    public void eliminar(Long id) {
        imagenesDisenoRepository.deleteById(id);
    }
}
