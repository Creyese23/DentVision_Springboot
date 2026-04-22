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

/**
 * Repositorio Spring Data JPA para la gestión de citas médicas en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Cita, incluyendo consultas por paciente/odontólogo, filtrado por fechas,
 * y gestión de estados de citas.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de citas por paciente y odontólogo
 * - Búsqueda por rangos de fechas y horas específicas
 * - Filtrado por estado de citas
 * - Búsqueda por motivo de consulta
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    
    /**
     * Obtiene todas las citas de un paciente específico.
     * 
     * @param paciente Paciente del cual se desean consultar las citas
     * @return Lista de citas del paciente
     */
    List<Cita> findByPaciente(Paciente paciente);
    
    /**
     * Obtiene todas las citas de un odontólogo específico.
     * 
     * @param odontologo Odontólogo del cual se desean consultar las citas
     * @return Lista de citas del odontólogo
     */
    List<Cita> findByOdontologo(Odontologo odontologo);
    
    /**
     * Obtiene todas las citas programadas para una fecha específica.
     * 
     * @param fecha Fecha para la cual se desean consultar las citas
     * @return Lista de citas del día
     */
    List<Cita> findByFecha(LocalDate fecha);
    
    /**
     * Obtiene todas las citas activas (estado true).
     * 
     * @return Lista de citas activas
     */
    List<Cita> findByEstadoTrue();
    
    /**
     * Obtiene todas las citas inactivas (estado false).
     * 
     * @return Lista de citas inactivas
     */
    List<Cita> findByEstadoFalse();
    
    /**
     * Busca una cita específica por odontólogo, fecha y hora exactos.
     * 
     * @param idOdontologo ID del odontólogo
     * @param fecha Fecha de la cita
     * @param hora Hora de la cita
     * @return Cita que coincide con los parámetros especificados
     */
    @Query("SELECT c FROM Cita c WHERE c.odontologo.idOdontologo = :idOdontologo AND c.fecha = :fecha AND c.hora = :hora")
    Cita findByOdontologoAndFechaAndHora(@Param("idOdontologo") Long idOdontologo, 
                                        @Param("fecha") LocalDate fecha, 
                                        @Param("hora") LocalTime hora);
    
    /**
     * Obtiene todas las próximas citas activas de un paciente específico.
     * 
     * @param idPaciente ID del paciente
     * @return Lista de próximas citas activas ordenadas por fecha
     */
    @Query("SELECT c FROM Cita c WHERE c.paciente.idPaciente = :idPaciente AND c.estado = true ORDER BY c.fecha ASC")
    List<Cita> findProximasCitasByPaciente(@Param("idPaciente") Long idPaciente);
    
    /**
     * Cuenta el número de citas que tiene un odontólogo en una fecha específica.
     * 
     * @param idOdontologo ID del odontólogo
     * @param fecha Fecha para la cual se cuentan las citas
     * @return Número de citas del odontólogo en esa fecha
     */
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.odontologo.idOdontologo = :idOdontologo AND c.fecha = :fecha")
    Long countByOdontologoAndFecha(@Param("idOdontologo") Long idOdontologo, @Param("fecha") LocalDate fecha);
    
    /**
     * Busca citas cuyo motivo contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param motivo Texto a buscar en el motivo de la cita
     * @return Lista de citas que coinciden con la búsqueda
     */
    List<Cita> findByMotivoContainingIgnoreCase(String motivo);
}
