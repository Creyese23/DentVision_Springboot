package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Mensajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de mensajes del sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Mensajes, incluyendo consultas por contenido y fecha.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de mensajes por contenido
 * - Filtrado por rangos de fechas
 */
@Repository
public interface MensajesRepository extends JpaRepository<Mensajes,Long> {

    /**
     * Busca mensajes cuyo contenido contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param mensaje Texto a buscar en el contenido de los mensajes
     * @return Lista de mensajes que coinciden con la búsqueda
     */
    List<Mensajes> findByMensajeContainingIgnoreCase(String mensaje);
    
    /**
     * Obtiene todos los mensajes dentro de un rango de fechas ordenados por fecha descendente.
     * 
     * @param inicio Fecha y hora de inicio del rango
     * @param fin Fecha y hora de fin del rango
     * @return Lista de mensajes en el rango ordenados del más reciente al más antiguo
     */
    List<Mensajes> findByFechaEnvioBetweenOrderByFechaEnvioDesc(LocalDateTime inicio, LocalDateTime fin);
}
