package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Notificaciones;
import com.sena.creyese.dentvision_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de notificaciones del sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Notificaciones, incluyendo consultas por usuario/tipo, gestión de estados,
 * filtrado por prioridades y seguimiento de notificaciones leídas/no leídas.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de notificaciones por usuario y tipo
 * - Gestión de estados (LEIDA, NO_LEIDA)
 * - Filtrado por prioridad y rangos de fechas
 * - Búsqueda por título y mensaje
 * - Conteo de notificaciones no leídas
 * - Control de notificaciones vencidas
 */
@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones,Long> {

    /**
     * Obtiene todas las notificaciones de un usuario específico ordenadas por fecha descendente.
     * 
     * @param usuario Usuario del cual se desean consultar las notificaciones
     * @return Lista de notificaciones ordenadas de la más reciente a la más antigua
     */
    List<Notificaciones> findByUsuarioOrderByFechaCreacionDesc(Usuario usuario);
    
    /**
     * Obtiene todas las notificaciones de un tipo específico ordenadas por fecha descendente.
     * 
     * @param tipo Tipo de notificación (CITA, MENSAJE, FACTURA)
     * @return Lista de notificaciones con ese tipo ordenadas de la más reciente a la más antigua
     */
    List<Notificaciones> findByTipoOrderByFechaCreacionDesc(String tipo);
    
    /**
     * Obtiene todas las notificaciones con un estado específico ordenadas por fecha descendente.
     * 
     * @param estado Estado de las notificaciones a buscar (LEIDA, NO_LEIDA)
     * @return Lista de notificaciones con ese estado ordenadas de la más reciente a la más antigua
     */
    List<Notificaciones> findByEstadoOrderByFechaCreacionDesc(String estado);
    
    /**
     * Obtiene todas las notificaciones dentro de un rango de fechas ordenadas por fecha descendente.
     * 
     * @param inicio Fecha y hora de inicio del rango
     * @param fin Fecha y hora de fin del rango
     * @return Lista de notificaciones en el rango ordenadas de la más reciente a la más antigua
     */
    List<Notificaciones> findByFechaCreacionBetweenOrderByFechaCreacionDesc(LocalDateTime inicio, LocalDateTime fin);
    
    /**
     * Obtiene todas las notificaciones no leídas de un usuario específico ordenadas por fecha ascendente.
     * 
     * @param idUsuario ID del usuario
     * @return Lista de notificaciones no leídas ordenadas de la más antigua a la más reciente
     */
    @Query("SELECT n FROM Notificaciones n WHERE n.estado = 'NO_LEIDA' AND n.usuario.idUsuario = :idUsuario ORDER BY n.fechaCreacion ASC")
    List<Notificaciones> findNotificacionesNoLeidasByUsuario(@Param("idUsuario") Long idUsuario);
    
    /**
     * Obtiene todas las notificaciones con estado LEIDA ordenadas por fecha descendente.
     * 
     * @return Lista de notificaciones leídas ordenadas de la más reciente a la más antigua
     */
    @Query("SELECT n FROM Notificaciones n WHERE n.estado = 'LEIDA' ORDER BY n.fechaCreacion DESC")
    List<Notificaciones> findNotificacionesLeidas();
    
    /**
     * Obtiene todas las notificaciones con estado NO_LEIDA ordenadas por fecha ascendente.
     * 
     * @return Lista de notificaciones no leídas ordenadas de la más antigua a la más reciente
     */
    @Query("SELECT n FROM Notificaciones n WHERE n.estado = 'NO_LEIDA' ORDER BY n.fechaCreacion ASC")
    List<Notificaciones> findNotificacionesNoLeidas();
    
    /**
     * Cuenta el número total de notificaciones no leídas de un usuario específico.
     * 
     * @param idUsuario ID del usuario
     * @return Número de notificaciones no leídas
     */
    @Query("SELECT COUNT(n) FROM Notificaciones n WHERE n.estado = 'NO_LEIDA' AND n.usuario.idUsuario = :idUsuario")
    Long countNotificacionesNoLeidasByUsuario(@Param("idUsuario") Long idUsuario);
    
    /**
     * Cuenta el número total de notificaciones no leídas en todo el sistema.
     * 
     * @return Número total de notificaciones no leídas
     */
    @Query("SELECT COUNT(n) FROM Notificaciones n WHERE n.estado = 'NO_LEIDA'")
    Long countTotalNotificacionesNoLeidas();
    
    /**
     * Busca notificaciones cuyo título contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param titulo Texto a buscar en los títulos
     * @return Lista de notificaciones que coinciden con la búsqueda
     */
    List<Notificaciones> findByTituloContainingIgnoreCase(String titulo);
    
    /**
     * Busca notificaciones cuyo mensaje contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param mensaje Texto a buscar en los mensajes
     * @return Lista de notificaciones que coinciden con la búsqueda
     */
    List<Notificaciones> findByMensajeContainingIgnoreCase(String mensaje);
    
    /**
     * Obtiene todas las notificaciones de alta prioridad que no han sido leídas ordenadas por fecha ascendente.
     * 
     * @return Lista de notificaciones de alta prioridad no leídas ordenadas de la más antigua a la más reciente
     */
    @Query("SELECT n FROM Notificaciones n WHERE n.prioridad = 'ALTA' AND n.estado = 'NO_LEIDA' ORDER BY n.fechaCreacion ASC")
    List<Notificaciones> findNotificacionesDeAltaPrioridadNoLeidas();
    
    /**
     * Obtiene todas las notificaciones creadas en una fecha y hora específicas.
     * 
     * @param fecha Fecha y hora para la cual se desean consultar las notificaciones
     * @return Lista de notificaciones de esa fecha y hora
     */
    @Query("SELECT n FROM Notificaciones n WHERE n.fechaCreacion = :fecha")
    List<Notificaciones> findByFechaCreacion(@Param("fecha") LocalDateTime fecha);
    
    /**
     * Obtiene todas las notificaciones no leídas cuya fecha de creación es anterior a una fecha límite.
     * 
     * @param fechaLimite Fecha límite para considerar una notificación como vencida
     * @return Lista de notificaciones vencidas no leídas
     */
    @Query("SELECT n FROM Notificaciones n WHERE n.fechaCreacion < :fechaLimite AND n.estado = 'NO_LEIDA'")
    List<Notificaciones> findNotificacionesVencidasNoLeidas(@Param("fechaLimite") LocalDateTime fechaLimite);
    
    /**
     * Obtiene todas las notificaciones de un usuario específico y tipo específico ordenadas por fecha descendente.
     * 
     * @param usuario Usuario del cual se desean consultar las notificaciones
     * @param tipo Tipo de notificación a filtrar
     * @return Lista de notificaciones filtradas ordenadas de la más reciente a la más antigua
     */
    List<Notificaciones> findByUsuarioAndTipoOrderByFechaCreacionDesc(Usuario usuario, String tipo);
}
