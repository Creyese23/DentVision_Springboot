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

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo,Long> {
    
    List<OrdenTrabajo> findByPacienteOrderByFechaCreacionDesc(Paciente paciente);
    
    List<OrdenTrabajo> findByOdontologoOrderByFechaCreacionDesc(Odontologo odontologo);
    
    List<OrdenTrabajo> findByTecnicoDentalOrderByFechaCreacionDesc(TecnicoDental tecnicoDental);
    
    List<OrdenTrabajo> findByEstadoOrderByFechaCreacionDesc(String estado);
    
    List<OrdenTrabajo> findByFechaCreacionBetweenOrderByFechaCreacionDesc(LocalDate inicio, LocalDate fin);
    
    List<OrdenTrabajo> findByFechaEntregaBetweenOrderByFechaCreacionDesc(LocalDate inicio, LocalDate fin);
    
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.estado = 'PENDIENTE' ORDER BY o.fechaCreacion ASC")
    List<OrdenTrabajo> findOrdenesPendientes();
    
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.estado = 'EN_PROGRESO' ORDER BY o.fechaCreacion ASC")
    List<OrdenTrabajo> findOrdenesEnProgreso();
    
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.estado = 'COMPLETADO' ORDER BY o.fechaCreacion DESC")
    List<OrdenTrabajo> findOrdenesCompletadas();
    
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.estado = 'ENTREGADO' ORDER BY o.fechaCreacion DESC")
    List<OrdenTrabajo> findOrdenesEntregadas();
    
    @Query("SELECT COUNT(o) FROM OrdenTrabajo o WHERE o.estado = 'PENDIENTE'")
    Long countOrdenesPendientes();
    
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.fechaEntrega < :currentDate AND o.estado != 'ENTREGADO'")
    List<OrdenTrabajo> findOrdenesVencidas(@Param("currentDate") LocalDate currentDate);
    
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.prioridad = 'ALTA' ORDER BY o.fechaCreacion ASC")
    List<OrdenTrabajo> findOrdenesDeAltaPrioridad();
    
    List<OrdenTrabajo> findByTipoTrabajoContainingIgnoreCase(String tipoTrabajo);
    
    List<OrdenTrabajo> findByDescripcionContainingIgnoreCase(String descripcion);
    
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.odontologo.idOdontologo = :idOdontologo AND o.estado = 'PENDIENTE' ORDER BY o.fechaCreacion ASC")
    List<OrdenTrabajo> findOrdenesPendientesByOdontologo(@Param("idOdontologo") Long idOdontologo);
}
