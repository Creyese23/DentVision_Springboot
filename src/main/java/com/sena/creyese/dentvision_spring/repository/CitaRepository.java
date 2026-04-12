package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Cita;
import com.sena.creyese.dentvision_spring.modelo.Odontologo;
import com.sena.creyese.dentvision_spring.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    
    List<Cita> findByPacienteOrderByFechaAscHoraAsc(Paciente paciente);
    
    List<Cita> findByOdontologoOrderByFechaAscHoraAsc(Odontologo odontologo);
    
    List<Cita> findByFechaOrderByHoraAsc(LocalDate fecha);
    
    List<Cita> findByFechaBetweenOrderByFechaAscHoraAsc(LocalDate inicio, LocalDate fin);
    
    List<Cita> findByOdontologoAndFechaOrderByHoraAsc(Odontologo odontologo, LocalDate fecha);
    
    List<Cita> findByEstadoTrueOrderByFechaAscHoraAsc();
    
    List<Cita> findByEstadoFalseOrderByFechaAscHoraAsc();
    
    @Query("SELECT c FROM Cita c WHERE c.odontologo.idOdontologo = :idOdontologo AND c.fecha = :fecha AND c.hora = :hora")
    Cita findByOdontologoAndFechaAndHora(@Param("idOdontologo") Long idOdontologo, 
                                        @Param("fecha") LocalDate fecha, 
                                        @Param("hora") LocalTime hora);
    
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.odontologo.idOdontologo = :idOdontologo AND c.fecha = :fecha")
    Long countByOdontologoAndFecha(@Param("idOdontologo") Long idOdontologo, @Param("fecha") LocalDate fecha);
    
    @Query("SELECT c FROM Cita c WHERE c.paciente.idPaciente = :idPaciente AND c.estado = true ORDER BY c.fecha ASC")
    List<Cita> findProximasCitasByPaciente(@Param("idPaciente") Long idPaciente);
    
    List<Cita> findByMotivoContainingIgnoreCase(String motivo);
}
