package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.ImagenesDiseno;
import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;
import com.sena.creyese.dentvision_spring.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de imágenes de diseño en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad ImagenesDiseno, incluyendo consultas por orden/paciente, gestión de estados,
 * filtrado por tipos de imagen y seguimiento de aprobaciones.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de imágenes por orden de trabajo y paciente
 * - Gestión de estados (APROBADA, PENDIENTE, RECHAZADA)
 * - Filtrado por tipo de imagen y fechas
 * - Búsqueda por nombre de archivo y notas
 * - Conteo de imágenes pendientes
 * - Consultas por ID de entidades relacionadas
 */
@Repository
public interface ImagenesDisenoRepository extends JpaRepository<ImagenesDiseno, Long> {

    /**
     * Obtiene todas las imágenes de una orden de trabajo específica ordenadas por fecha de creación descendente.
     * 
     * @param ordenTrabajo Orden de trabajo de la cual se desean consultar las imágenes
     * @return Lista de imágenes ordenadas de la más reciente a la más antigua
     */
    List<ImagenesDiseno> findByOrdenTrabajoOrderByFechaCreacionDesc(OrdenTrabajo ordenTrabajo);
    
    /**
     * Obtiene todas las imágenes de un paciente específico ordenadas por fecha de creación descendente.
     * 
     * @param paciente Paciente del cual se desean consultar las imágenes
     * @return Lista de imágenes ordenadas de la más reciente a la más antigua
     */
    List<ImagenesDiseno> findByPacienteOrderByFechaCreacionDesc(Paciente paciente);
    
    /**
     * Busca imágenes cuyo tipo contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param tipoImagen Tipo de imagen a buscar (RADIOGRAFÍA, FOTO, ESCANEO 3D)
     * @return Lista de imágenes que coinciden con la búsqueda
     */
    List<ImagenesDiseno> findByTipoImagenContainingIgnoreCase(String tipoImagen);
    
    /**
     * Obtiene todas las imágenes con un estado específico ordenadas por fecha de creación descendente.
     * 
     * @param estado Estado de las imágenes a buscar (APROBADA, PENDIENTE, RECHAZADA)
     * @return Lista de imágenes con ese estado ordenadas de la más reciente a la más antigua
     */
    List<ImagenesDiseno> findByEstadoOrderByFechaCreacionDesc(String estado);
    
    /**
     * Obtiene todas las imágenes dentro de un rango de fechas ordenadas por fecha de creación descendente.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de imágenes en el rango ordenadas de la más reciente a la más antigua
     */
    List<ImagenesDiseno> findByFechaCreacionBetweenOrderByFechaCreacionDesc(LocalDate inicio, LocalDate fin);
    
    /**
     * Obtiene todas las imágenes con estado APROBADA ordenadas por fecha de creación descendente.
     * 
     * @return Lista de imágenes aprobadas ordenadas de la más reciente a la más antigua
     */
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.estado = 'APROBADA' ORDER BY i.fechaCreacion DESC")
    List<ImagenesDiseno> findImagenesAprobadas();
    
    /**
     * Obtiene todas las imágenes con estado PENDIENTE ordenadas por fecha de creación ascendente (más antiguas primero).
     * 
     * @return Lista de imágenes pendientes ordenadas por fecha más antigua
     */
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.estado = 'PENDIENTE' ORDER BY i.fechaCreacion ASC")
    List<ImagenesDiseno> findImagenesPendientes();
    
    /**
     * Obtiene todas las imágenes con estado RECHAZADA ordenadas por fecha de creación descendente.
     * 
     * @return Lista de imágenes rechazadas ordenadas de la más reciente a la más antigua
     */
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.estado = 'RECHAZADA' ORDER BY i.fechaCreacion DESC")
    List<ImagenesDiseno> findImagenesRechazadas();
    
    /**
     * Cuenta el número total de imágenes con estado PENDIENTE en el sistema.
     * 
     * @return Número de imágenes pendientes
     */
    @Query("SELECT COUNT(i) FROM ImagenesDiseno i WHERE i.estado = 'PENDIENTE'")
    Long countImagenesPendientes();
    
    /**
     * Busca imágenes cuyo nombre de archivo contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombreArchivo Texto a buscar en los nombres de archivo
     * @return Lista de imágenes que coinciden con la búsqueda
     */
    List<ImagenesDiseno> findByNombreArchivoContainingIgnoreCase(String nombreArchivo);
    
    /**
     * Obtiene todas las imágenes de un paciente específico por su ID ordenadas por fecha de creación descendente.
     * 
     * @param idPaciente ID del paciente
     * @return Lista de imágenes del paciente ordenadas de la más reciente a la más antigua
     */
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.ordenTrabajo.paciente.idPaciente = :idPaciente ORDER BY i.fechaCreacion DESC")
    List<ImagenesDiseno> findByPacienteId(@Param("idPaciente") Long idPaciente);
    
    /**
     * Obtiene todas las imágenes de una orden de trabajo específica por su ID ordenadas por fecha de creación descendente.
     * 
     * @param idOrdenTrabajo ID de la orden de trabajo
     * @return Lista de imágenes de la orden ordenadas de la más reciente a la más antigua
     */
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.ordenTrabajo.idOrdenTrabajo = :idOrdenTrabajo ORDER BY i.fechaCreacion DESC")
    List<ImagenesDiseno> findByOrdenTrabajoId(@Param("idOrdenTrabajo") Long idOrdenTrabajo);
    
    /**
     * Busca imágenes cuyas notas contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param notas Texto a buscar en las notas de las imágenes
     * @return Lista de imágenes que coinciden con la búsqueda
     */
    List<ImagenesDiseno> findByNotasContainingIgnoreCase(String notas);
    
    /**
     * Obtiene todas las imágenes creadas en una fecha específica.
     * 
     * @param fecha Fecha para la cual se desean consultar las imágenes
     * @return Lista de imágenes creadas en esa fecha
     */
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.fechaCreacion = :fecha")
    List<ImagenesDiseno> findByFechaCreacion(@Param("fecha") LocalDate fecha);
    
    /**
     * Obtiene todas las imágenes aprobadas cuya orden de trabajo asociada no esté completada.
     * 
     * @return Lista de imágenes aprobadas con órdenes pendientes
     */
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.estado = 'APROBADA' AND i.ordenTrabajo.estado = true")
    List<ImagenesDiseno> findImagenesAprobadasConOrdenPendiente();
}
