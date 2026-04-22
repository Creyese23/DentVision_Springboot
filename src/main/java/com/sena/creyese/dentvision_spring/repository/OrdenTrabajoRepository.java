package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;
import com.sena.creyese.dentvision_spring.modelo.Paciente;
import com.sena.creyese.dentvision_spring.modelo.Odontologo;
import com.sena.creyese.dentvision_spring.modelo.TecnicoDental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de órdenes de trabajo en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad OrdenTrabajo, incluyendo consultas por paciente/odontólogo/técnico,
 * gestión de estados y búsqueda por descripción.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de órdenes por paciente, odontólogo y técnico
 * - Gestión de estados (PENDIENTE, EN_PROGRESO, COMPLETADO, ENTREGADO)
 * - Búsqueda por descripción
 */
@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo,Long> {
    
    /**
     * Obtiene todas las órdenes de trabajo de un paciente específico.
     * 
     * @param paciente Paciente del cual se desean consultar las órdenes
     * @return Lista de órdenes del paciente
     */
    List<OrdenTrabajo> findByPaciente(Paciente paciente);
    
    /**
     * Obtiene todas las órdenes de trabajo de un odontólogo específico.
     * 
     * @param odontologo Odontólogo del cual se desean consultar las órdenes
     * @return Lista de órdenes del odontólogo
     */
    List<OrdenTrabajo> findByOdontologo(Odontologo odontologo);
    
    /**
     * Obtiene todas las órdenes de trabajo de un técnico dental específico.
     * 
     * @param tecnico Técnico del cual se desean consultar las órdenes
     * @return Lista de órdenes del técnico
     */
    List<OrdenTrabajo> findByTecnico(TecnicoDental tecnico);
    
    /**
     * Obtiene todas las órdenes de trabajo con un estado específico.
     * 
     * @param estado Estado de las órdenes a buscar (PENDIENTE, EN_PROGRESO, COMPLETADO, ENTREGADO)
     * @return Lista de órdenes con ese estado
     */
    List<OrdenTrabajo> findByEstado(String estado);
    
    /**
     * Busca órdenes de trabajo cuya descripción contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param observaciones Texto a buscar en las observaciones
     * @return Lista de órdenes que coinciden con la búsqueda
     */
    List<OrdenTrabajo> findByObservacionesContainingIgnoreCase(String observaciones);
}
