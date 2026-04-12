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

@Repository
public interface ImagenesDisenoRepository extends JpaRepository<ImagenesDiseno, Long> {

    List<ImagenesDiseno> findByOrdenTrabajoOrderByFechaCreacionDesc(OrdenTrabajo ordenTrabajo);
    
    List<ImagenesDiseno> findByPacienteOrderByFechaCreacionDesc(Paciente paciente);
    
    List<ImagenesDiseno> findByTipoImagenContainingIgnoreCase(String tipoImagen);
    
    List<ImagenesDiseno> findByEstadoOrderByFechaCreacionDesc(String estado);
    
    List<ImagenesDiseno> findByFechaCreacionBetweenOrderByFechaCreacionDesc(LocalDate inicio, LocalDate fin);
    
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.estado = 'APROBADA' ORDER BY i.fechaCreacion DESC")
    List<ImagenesDiseno> findImagenesAprobadas();
    
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.estado = 'PENDIENTE' ORDER BY i.fechaCreacion ASC")
    List<ImagenesDiseno> findImagenesPendientes();
    
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.estado = 'RECHAZADA' ORDER BY i.fechaCreacion DESC")
    List<ImagenesDiseno> findImagenesRechazadas();
    
    @Query("SELECT COUNT(i) FROM ImagenesDiseno i WHERE i.estado = 'PENDIENTE'")
    Long countImagenesPendientes();
    
    List<ImagenesDiseno> findByNombreArchivoContainingIgnoreCase(String nombreArchivo);
    
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.ordenTrabajo.paciente.idPaciente = :idPaciente ORDER BY i.fechaCreacion DESC")
    List<ImagenesDiseno> findByPacienteId(@Param("idPaciente") Long idPaciente);
    
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.ordenTrabajo.idOrdenTrabajo = :idOrdenTrabajo ORDER BY i.fechaCreacion DESC")
    List<ImagenesDiseno> findByOrdenTrabajoId(@Param("idOrdenTrabajo") Long idOrdenTrabajo);
    
    List<ImagenesDiseno> findByNotasContainingIgnoreCase(String notas);
    
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.fechaCreacion = :fecha")
    List<ImagenesDiseno> findByFechaCreacion(@Param("fecha") LocalDate fecha);
    
    @Query("SELECT i FROM ImagenesDiseno i WHERE i.estado = 'APROBADA' AND i.ordenTrabajo.estado != 'COMPLETADO'")
    List<ImagenesDiseno> findImagenesAprobadasConOrdenPendiente();
}
