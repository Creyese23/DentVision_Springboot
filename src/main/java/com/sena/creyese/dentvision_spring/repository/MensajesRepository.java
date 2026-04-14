package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Mensajes;
import com.sena.creyese.dentvision_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de mensajes del sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Mensajes, incluyendo consultas por remitente/destinatario, gestión de estados,
 * filtrado por prioridades y seguimiento de mensajes leídos/no leídos.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de mensajes por remitente y destinatario
 * - Gestión de estados (LEIDO, NO_LEIDO)
 * - Filtrado por prioridad y rangos de fechas
 * - Búsqueda por asunto y contenido
 * - Conteo de mensajes no leídos por usuario
 * - Consultas por usuario específico
 */
@Repository
public interface MensajesRepository extends JpaRepository<Mensajes,Long> {

    /**
     * Obtiene todos los mensajes enviados por un usuario específico ordenados por fecha descendente.
     * 
     * @param remitente Usuario que envió los mensajes
     * @return Lista de mensajes ordenados del más reciente al más antiguo
     */
    List<Mensajes> findByRemitenteOrderByFechaEnvioDesc(Usuario remitente);
    
    /**
     * Obtiene todos los mensajes recibidos por un usuario específico ordenados por fecha descendente.
     * 
     * @param destinatario Usuario que recibió los mensajes
     * @return Lista de mensajes ordenados del más reciente al más antiguo
     */
    List<Mensajes> findByDestinatarioOrderByFechaEnvioDesc(Usuario destinatario);
    
    /**
     * Busca mensajes cuyo asunto contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param asunto Texto a buscar en los asuntos
     * @return Lista de mensajes que coinciden con la búsqueda
     */
    List<Mensajes> findByAsuntoContainingIgnoreCase(String asunto);
    
    /**
     * Obtiene todos los mensajes con un estado específico ordenados por fecha descendente.
     * 
     * @param estado Estado de los mensajes a buscar (LEIDO, NO_LEIDO)
     * @return Lista de mensajes con ese estado ordenados del más reciente al más antiguo
     */
    List<Mensajes> findByEstadoOrderByFechaEnvioDesc(String estado);
    
    /**
     * Obtiene todos los mensajes dentro de un rango de fechas ordenados por fecha descendente.
     * 
     * @param inicio Fecha y hora de inicio del rango
     * @param fin Fecha y hora de fin del rango
     * @return Lista de mensajes en el rango ordenados del más reciente al más antiguo
     */
    List<Mensajes> findByFechaEnvioBetweenOrderByFechaEnvioDesc(LocalDateTime inicio, LocalDateTime fin);
    
    /**
     * Obtiene todos los mensajes no leídos de un destinatario específico ordenados por fecha ascendente.
     * 
     * @param idDestinatario ID del usuario destinatario
     * @return Lista de mensajes no leídos ordenados del más antiguo al más reciente
     */
    @Query("SELECT m FROM Mensajes m WHERE m.estado = 'NO_LEIDO' AND m.destinatario.idUsuario = :idDestinatario ORDER BY m.fechaEnvio ASC")
    List<Mensajes> findMensajesNoLeidosByDestinatario(@Param("idDestinatario") Long idDestinatario);
    
    /**
     * Obtiene todos los mensajes con estado LEIDO ordenados por fecha descendente.
     * 
     * @return Lista de mensajes leídos ordenados del más reciente al más antiguo
     */
    @Query("SELECT m FROM Mensajes m WHERE m.estado = 'LEIDO' ORDER BY m.fechaEnvio DESC")
    List<Mensajes> findMensajesLeidos();
    
    /**
     * Obtiene todos los mensajes con estado NO_LEIDO ordenados por fecha ascendente.
     * 
     * @return Lista de mensajes no leídos ordenados del más antiguo al más reciente
     */
    @Query("SELECT m FROM Mensajes m WHERE m.estado = 'NO_LEIDO' ORDER BY m.fechaEnvio ASC")
    List<Mensajes> findMensajesNoLeidos();
    
    /**
     * Cuenta el número total de mensajes no leídos de un destinatario específico.
     * 
     * @param idDestinatario ID del usuario destinatario
     * @return Número de mensajes no leídos
     */
    @Query("SELECT COUNT(m) FROM Mensajes m WHERE m.estado = 'NO_LEIDO' AND m.destinatario.idUsuario = :idDestinatario")
    Long countMensajesNoLeidosByDestinatario(@Param("idDestinatario") Long idDestinatario);
    
    /**
     * Obtiene todos los mensajes donde un usuario es remitente o destinatario ordenados por fecha descendente.
     * 
     * @param idRemitente ID del usuario como remitente
     * @param idUsuario ID del usuario como destinatario
     * @return Lista de mensajes del usuario ordenados del más reciente al más antiguo
     */
    @Query("SELECT m FROM Mensajes m WHERE m.remitente.idUsuario = :idRemitente OR m.destinatario.idUsuario = :idUsuario ORDER BY m.fechaEnvio DESC")
    List<Mensajes> findMensajesByUsuario(@Param("idRemitente") Long idRemitente, @Param("idUsuario") Long idUsuario);
    
    /**
     * Busca mensajes cuyo contenido contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param contenido Texto a buscar en el contenido de los mensajes
     * @return Lista de mensajes que coinciden con la búsqueda
     */
    List<Mensajes> findByContenidoContainingIgnoreCase(String contenido);
    
    /**
     * Obtiene todos los mensajes de alta prioridad que no han sido leídos ordenados por fecha ascendente.
     * 
     * @return Lista de mensajes de alta prioridad no leídos ordenados del más antiguo al más reciente
     */
    @Query("SELECT m FROM Mensajes m WHERE m.prioridad = 'ALTA' AND m.estado = 'NO_LEIDO' ORDER BY m.fechaEnvio ASC")
    List<Mensajes> findMensajesDeAltaPrioridadNoLeidos();
    
    /**
     * Obtiene todos los mensajes enviados en una fecha y hora específicas.
     * 
     * @param fecha Fecha y hora para la cual se desean consultar los mensajes
     * @return Lista de mensajes de esa fecha y hora
     */
    @Query("SELECT m FROM Mensajes m WHERE m.fechaEnvio = :fecha")
    List<Mensajes> findByFechaEnvio(@Param("fecha") LocalDateTime fecha);
    
    /**
     * Obtiene todos los mensajes entre un remitente y destinatario específicos ordenados por fecha descendente.
     * 
     * @param remitente Usuario que envió los mensajes
     * @param destinatario Usuario que recibió los mensajes
     * @return Lista de mensajes entre ambos usuarios ordenados del más reciente al más antiguo
     */
    List<Mensajes> findByRemitenteAndDestinatarioOrderByFechaEnvioDesc(Usuario remitente, Usuario destinatario);
}
