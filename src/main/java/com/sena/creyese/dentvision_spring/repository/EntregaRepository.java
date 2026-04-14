package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Entrega;
import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;
import com.sena.creyese.dentvision_spring.modelo.TecnicoDental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de entregas de trabajos dentales en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Entrega, incluyendo consultas por técnico/orden, filtrado por estados,
 * gestión por fechas y seguimiento de entregas pendientes.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de entregas por técnico y orden de trabajo
 * - Filtrado por estados (pendiente/entregado)
 * - Búsqueda por rangos de fechas
 * - Conteo de entregas pendientes
 * - Consultas por paciente asociado
 * - Búsqueda por notas descriptivas
 */
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    /**
     * Obtiene todas las entregas de un técnico específico ordenadas por fecha descendente.
     * 
     * @param tecnicoDental Técnico del cual se desean consultar las entregas
     * @return Lista de entregas ordenadas de la más reciente a la más antigua
     */
    List<Entrega> findByTecnicoDentalOrderByFechaEntregaDesc(TecnicoDental tecnicoDental);
    
    /**
     * Obtiene todas las entregas asociadas a una orden de trabajo específica ordenadas por fecha descendente.
     * 
     * @param ordenTrabajo Orden de trabajo de la cual se desean consultar las entregas
     * @return Lista de entregas ordenadas de la más reciente a la más antigua
     */
    List<Entrega> findByOrdenTrabajoOrderByFechaEntregaDesc(OrdenTrabajo ordenTrabajo);
    
    /**
     * Obtiene todas las entregas dentro de un rango de fechas ordenadas por fecha descendente.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de entregas en el rango ordenadas de la más reciente a la más antigua
     */
    List<Entrega> findByFechaEntregaBetweenOrderByFechaEntregaDesc(LocalDate inicio, LocalDate fin);
    
    /**
     * Obtiene todas las entregas con un estado específico ordenadas por fecha descendente.
     * 
     * @param estado Estado de las entregas a buscar (PENDIENTE, ENTREGADO, etc.)
     * @return Lista de entregas con ese estado ordenadas de la más reciente a la más antigua
     */
    List<Entrega> findByEstadoOrderByFechaEntregaDesc(String estado);
    
    /**
     * Obtiene todas las entregas con un estado específico dentro de un rango de fechas ordenadas por fecha descendente.
     * 
     * @param estado Estado de las entregas a buscar
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de entregas filtradas ordenadas de la más reciente a la más antigua
     */
    List<Entrega> findByEstadoAndFechaEntregaBetweenOrderByFechaEntregaDesc(String estado, LocalDate inicio, LocalDate fin);
    
    /**
     * Obtiene todas las entregas con estado PENDIENTE ordenadas por fecha ascendente (más urgentes primero).
     * 
     * @return Lista de entregas pendientes ordenadas por fecha más cercana
     */
    @Query("SELECT e FROM Entrega e WHERE e.estado = 'PENDIENTE' ORDER BY e.fechaEntrega ASC")
    List<Entrega> findEntregasPendientes();
    
    /**
     * Obtiene todas las entregas con estado ENTREGADO ordenadas por fecha descendente (más recientes primero).
     * 
     * @return Lista de entregas realizadas ordenadas por fecha más reciente
     */
    @Query("SELECT e FROM Entrega e WHERE e.estado = 'ENTREGADO' ORDER BY e.fechaEntrega DESC")
    List<Entrega> findEntregasRealizadas();
    
    /**
     * Cuenta el número total de entregas con estado PENDIENTE en el sistema.
     * 
     * @return Número de entregas pendientes
     */
    @Query("SELECT COUNT(e) FROM Entrega e WHERE e.estado = 'PENDIENTE'")
    Long countEntregasPendientes();
    
    /**
     * Obtiene todas las entregas programadas para una fecha específica.
     * 
     * @param fecha Fecha para la cual se desean consultar las entregas
     * @return Lista de entregas de esa fecha
     */
    @Query("SELECT e FROM Entrega e WHERE e.fechaEntrega = :fecha")
    List<Entrega> findByFechaEntrega(@Param("fecha") LocalDate fecha);
    
    /**
     * Obtiene todas las entregas asociadas a un paciente específico ordenadas por fecha descendente.
     * 
     * @param idPaciente ID del paciente
     * @return Lista de entregas del paciente ordenadas de la más reciente a la más antigua
     */
    @Query("SELECT e FROM Entrega e WHERE e.ordenTrabajo.paciente.idPaciente = :idPaciente ORDER BY e.fechaEntrega DESC")
    List<Entrega> findByPaciente(@Param("idPaciente") Long idPaciente);
    
    /**
     * Busca entregas cuyas notas contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param notas Texto a buscar en las notas de las entregas
     * @return Lista de entregas que coinciden con la búsqueda
     */
    List<Entrega> findByNotasContainingIgnoreCase(String notas);
}
