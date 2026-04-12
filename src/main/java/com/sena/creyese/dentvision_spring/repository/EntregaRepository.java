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

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    List<Entrega> findByTecnicoDentalOrderByFechaEntregaDesc(TecnicoDental tecnicoDental);
    
    List<Entrega> findByOrdenTrabajoOrderByFechaEntregaDesc(OrdenTrabajo ordenTrabajo);
    
    List<Entrega> findByFechaEntregaBetweenOrderByFechaEntregaDesc(LocalDate inicio, LocalDate fin);
    
    List<Entrega> findByEstadoOrderByFechaEntregaDesc(String estado);
    
    List<Entrega> findByEstadoAndFechaEntregaBetweenOrderByFechaEntregaDesc(String estado, LocalDate inicio, LocalDate fin);
    
    @Query("SELECT e FROM Entrega e WHERE e.estado = 'PENDIENTE' ORDER BY e.fechaEntrega ASC")
    List<Entrega> findEntregasPendientes();
    
    @Query("SELECT e FROM Entrega e WHERE e.estado = 'ENTREGADO' ORDER BY e.fechaEntrega DESC")
    List<Entrega> findEntregasRealizadas();
    
    @Query("SELECT COUNT(e) FROM Entrega e WHERE e.estado = 'PENDIENTE'")
    Long countEntregasPendientes();
    
    @Query("SELECT e FROM Entrega e WHERE e.fechaEntrega = :fecha")
    List<Entrega> findByFechaEntrega(@Param("fecha") LocalDate fecha);
    
    @Query("SELECT e FROM Entrega e WHERE e.ordenTrabajo.paciente.idPaciente = :idPaciente ORDER BY e.fechaEntrega DESC")
    List<Entrega> findByPaciente(@Param("idPaciente") Long idPaciente);
    
    List<Entrega> findByNotasContainingIgnoreCase(String notas);
}
