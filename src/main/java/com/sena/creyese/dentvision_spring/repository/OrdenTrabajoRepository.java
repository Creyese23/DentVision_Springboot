package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;
import com.sena.creyese.dentvision_spring.modelo.Paciente;
import com.sena.creyese.dentvision_spring.modelo.Odontologo;
import com.sena.creyese.dentvision_spring.modelo.TecnicoDental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de órdenes de trabajo en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad OrdenTrabajo, incluyendo consultas por paciente/odontólogo/técnico,
 * gestión de estados, filtrado por fechas y seguimiento de prioridades.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de órdenes por paciente, odontólogo y técnico
 * - Gestión de estados (PENDIENTE, EN_PROGRESO, COMPLETADO, ENTREGADO)
 * - Filtrado por rangos de fechas de creación y entrega
 * - Control de órdenes vencidas
 * - Búsqueda por tipo y descripción
 * - Consultas por prioridad
 */
@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo,Long> {
    
    /**
     * Obtiene todas las órdenes de trabajo de un paciente específico ordenadas por fecha descendente.
     * 
     * @param paciente Paciente del cual se desean consultar las órdenes
     * @return Lista de órdenes ordenadas de la más reciente a la más antigua
     */
    List<OrdenTrabajo> findByPacienteOrderByFechaCreacionDesc(Paciente paciente);
    
    /**
     * Obtiene todas las órdenes de trabajo de un odontólogo específico ordenadas por fecha descendente.
     * 
     * @param odontologo Odontólogo del cual se desean consultar las órdenes
     * @return Lista de órdenes ordenadas de la más reciente a la más antigua
     */
    List<OrdenTrabajo> findByOdontologoOrderByFechaCreacionDesc(Odontologo odontologo);
    
    /**
     * Obtiene todas las órdenes de trabajo de un técnico dental específico ordenadas por fecha descendente.
     * 
     * @param tecnicoDental Técnico del cual se desean consultar las órdenes
     * @return Lista de órdenes ordenadas de la más reciente a la más antigua
     */
    List<OrdenTrabajo> findByTecnicoDentalOrderByFechaCreacionDesc(TecnicoDental tecnicoDental);
    
    /**
     * Obtiene todas las órdenes de trabajo con un estado específico ordenadas por fecha descendente.
     * 
     * @param estado Estado de las órdenes a buscar (PENDIENTE, EN_PROGRESO, COMPLETADO, ENTREGADO)
     * @return Lista de órdenes con ese estado ordenadas de la más reciente a la más antigua
     */
    List<OrdenTrabajo> findByEstadoOrderByFechaCreacionDesc(String estado);
    
    /**
     * Obtiene todas las órdenes de trabajo dentro de un rango de fechas ordenadas por fecha descendente.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de órdenes en el rango ordenadas de la más reciente a la más antigua
     */
    List<OrdenTrabajo> findByFechaCreacionBetweenOrderByFechaCreacionDesc(LocalDate inicio, LocalDate fin);
    
    /**
     * Obtiene todas las órdenes de trabajo con fecha de entrega dentro de un rango ordenadas por fecha descendente.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de órdenes en el rango ordenadas de la más reciente a la más antigua
     */
    List<OrdenTrabajo> findByFechaEntregaBetweenOrderByFechaCreacionDesc(LocalDate inicio, LocalDate fin);
    
    /**
     * Obtiene todas las órdenes de trabajo con estado PENDIENTE ordenadas por fecha ascendente (más antiguas primero).
     * 
     * @return Lista de órdenes pendientes ordenadas por fecha más antigua
     */
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.estado = 'PENDIENTE' ORDER BY o.fechaCreacion ASC")
    List<OrdenTrabajo> findOrdenesPendientes();
    
    /**
     * Obtiene todas las órdenes de trabajo con estado EN_PROGRESO ordenadas por fecha ascendente (más antiguas primero).
     * 
     * @return Lista de órdenes en progreso ordenadas por fecha más antigua
     */
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.estado = 'EN_PROGRESO' ORDER BY o.fechaCreacion ASC")
    List<OrdenTrabajo> findOrdenesEnProgreso();
    
    /**
     * Obtiene todas las órdenes de trabajo con estado COMPLETADO ordenadas por fecha descendente (más recientes primero).
     * 
     * @return Lista de órdenes completadas ordenadas por fecha más reciente
     */
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.estado = 'COMPLETADO' ORDER BY o.fechaCreacion DESC")
    List<OrdenTrabajo> findOrdenesCompletadas();
    
    /**
     * Obtiene todas las órdenes de trabajo con estado ENTREGADO ordenadas por fecha descendente (más recientes primero).
     * 
     * @return Lista de órdenes entregadas ordenadas por fecha más reciente
     */
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.estado = 'ENTREGADO' ORDER BY o.fechaCreacion DESC")
    List<OrdenTrabajo> findOrdenesEntregadas();
    
    /**
     * Cuenta el número total de órdenes de trabajo con estado PENDIENTE en el sistema.
     * 
     * @return Número de órdenes pendientes
     */
    @Query("SELECT COUNT(o) FROM OrdenTrabajo o WHERE o.estado = 'PENDIENTE'")
    Long countOrdenesPendientes();
    
    /**
     * Obtiene todas las órdenes de trabajo cuya fecha de entrega es anterior a la fecha actual y no están entregadas.
     * 
     * @param currentDate Fecha actual para comparación
     * @return Lista de órdenes vencidas
     */
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.fechaEntrega < :currentDate AND o.estado != 'ENTREGADO'")
    List<OrdenTrabajo> findOrdenesVencidas(@Param("currentDate") LocalDate currentDate);
    
    /**
     * Obtiene todas las órdenes de trabajo con prioridad ALTA ordenadas por fecha ascendente (más antiguas primero).
     * 
     * @return Lista de órdenes de alta prioridad ordenadas por fecha más antigua
     */
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.prioridad = 'ALTA' ORDER BY o.fechaCreacion ASC")
    List<OrdenTrabajo> findOrdenesDeAltaPrioridad();
    
    /**
     * Busca órdenes de trabajo cuyo tipo contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param tipoTrabajo Tipo de trabajo a buscar (PROTESIS, ORTODONCIA, ENDODONCIA)
     * @return Lista de órdenes que coinciden con la búsqueda
     */
    List<OrdenTrabajo> findByTipoTrabajoContainingIgnoreCase(String tipoTrabajo);
    
    /**
     * Busca órdenes de trabajo cuya descripción contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param descripcion Texto a buscar en las descripciones
     * @return Lista de órdenes que coinciden con la búsqueda
     */
    List<OrdenTrabajo> findByDescripcionContainingIgnoreCase(String descripcion);
    
    /**
     * Obtiene todas las órdenes pendientes de un odontólogo específico ordenadas por fecha ascendente.
     * 
     * @param idOdontologo ID del odontólogo
     * @return Lista de órdenes pendientes del odontólogo ordenadas por fecha más antigua
     */
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.odontologo.idOdontologo = :idOdontologo AND o.estado = 'PENDIENTE' ORDER BY o.fechaCreacion ASC")
    List<OrdenTrabajo> findOrdenesPendientesByOdontologo(@Param("idOdontologo") Long idOdontologo);
}
