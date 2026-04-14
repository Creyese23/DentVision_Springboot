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
 * verificación de disponibilidad y gestión de estados de citas.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de citas por paciente y odontólogo
 * - Búsqueda por rangos de fechas y horas específicas
 * - Verificación de disponibilidad de horarios
 * - Filtrado por estado de citas
 * - Conteo de citas por odontólogo y fecha
 * - Búsqueda por motivo de consulta
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    
    /**
     * Obtiene todas las citas de un paciente específico ordenadas por fecha y hora ascendentes.
     * 
     * @param paciente Paciente del cual se desean consultar las citas
     * @return Lista de citas ordenadas cronológicamente
     */
    List<Cita> findByPacienteOrderByFechaAscHoraAsc(Paciente paciente);
    
    /**
     * Obtiene todas las citas de un odontólogo específico ordenadas por fecha y hora ascendentes.
     * 
     * @param odontologo Odontólogo del cual se desean consultar las citas
     * @return Lista de citas ordenadas cronológicamente
     */
    List<Cita> findByOdontologoOrderByFechaAscHoraAsc(Odontologo odontologo);
    
    /**
     * Obtiene todas las citas programadas para una fecha específica ordenadas por hora ascendente.
     * 
     * @param fecha Fecha para la cual se desean consultar las citas
     * @return Lista de citas del día ordenadas por hora
     */
    List<Cita> findByFechaOrderByHoraAsc(LocalDate fecha);
    
    /**
     * Obtiene todas las citas dentro de un rango de fechas ordenadas cronológicamente.
     * 
     * @param inicio Fecha de inicio del rango
     * @param fin Fecha de fin del rango
     * @return Lista de citas en el rango ordenadas por fecha y hora
     */
    List<Cita> findByFechaBetweenOrderByFechaAscHoraAsc(LocalDate inicio, LocalDate fin);
    
    /**
     * Obtiene todas las citas de un odontólogo específico para una fecha determinada ordenadas por hora.
     * 
     * @param odontologo Odontólogo del cual se desean consultar las citas
     * @param fecha Fecha específica de las citas
     * @return Lista de citas del odontólogo para esa fecha ordenadas por hora
     */
    List<Cita> findByOdontologoAndFechaOrderByHoraAsc(Odontologo odontologo, LocalDate fecha);
    
    /**
     * Obtiene todas las citas activas (estado true) ordenadas cronológicamente.
     * 
     * @return Lista de citas activas ordenadas por fecha y hora
     */
    List<Cita> findByEstadoTrueOrderByFechaAscHoraAsc();
    
    /**
     * Obtiene todas las citas inactivas (estado false) ordenadas cronológicamente.
     * 
     * @return Lista de citas inactivas ordenadas por fecha y hora
     */
    List<Cita> findByEstadoFalseOrderByFechaAscHoraAsc();
    
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
     * Cuenta el número de citas que tiene un odontólogo en una fecha específica.
     * 
     * @param idOdontologo ID del odontólogo
     * @param fecha Fecha para la cual se cuentan las citas
     * @return Número de citas del odontólogo en esa fecha
     */
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.odontologo.idOdontologo = :idOdontologo AND c.fecha = :fecha")
    Long countByOdontologoAndFecha(@Param("idOdontologo") Long idOdontologo, @Param("fecha") LocalDate fecha);
    
    /**
     * Obtiene todas las próximas citas activas de un paciente específico.
     * 
     * @param idPaciente ID del paciente
     * @return Lista de próximas citas activas ordenadas por fecha
     */
    @Query("SELECT c FROM Cita c WHERE c.paciente.idPaciente = :idPaciente AND c.estado = true ORDER BY c.fecha ASC")
    List<Cita> findProximasCitasByPaciente(@Param("idPaciente") Long idPaciente);
    
    /**
     * Busca citas cuyo motivo contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param motivo Texto a buscar en el motivo de la cita
     * @return Lista de citas que coinciden con la búsqueda
     */
    List<Cita> findByMotivoContainingIgnoreCase(String motivo);
}
