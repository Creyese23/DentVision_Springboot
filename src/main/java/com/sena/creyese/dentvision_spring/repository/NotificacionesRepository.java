package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Notificaciones;
import com.sena.creyese.dentvision_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones,Long> {

    List<Notificaciones> findByUsuarioOrderByFechaCreacionDesc(Usuario usuario);
    
    List<Notificaciones> findByTipoOrderByFechaCreacionDesc(String tipo);
    
    List<Notificaciones> findByEstadoOrderByFechaCreacionDesc(String estado);
    
    List<Notificaciones> findByFechaCreacionBetweenOrderByFechaCreacionDesc(LocalDateTime inicio, LocalDateTime fin);
    
    @Query("SELECT n FROM Notificaciones n WHERE n.estado = 'NO_LEIDA' AND n.usuario.idUsuario = :idUsuario ORDER BY n.fechaCreacion ASC")
    List<Notificaciones> findNotificacionesNoLeidasByUsuario(@Param("idUsuario") Long idUsuario);
    
    @Query("SELECT n FROM Notificaciones n WHERE n.estado = 'LEIDA' ORDER BY n.fechaCreacion DESC")
    List<Notificaciones> findNotificacionesLeidas();
    
    @Query("SELECT n FROM Notificaciones n WHERE n.estado = 'NO_LEIDA' ORDER BY n.fechaCreacion ASC")
    List<Notificaciones> findNotificacionesNoLeidas();
    
    @Query("SELECT COUNT(n) FROM Notificaciones n WHERE n.estado = 'NO_LEIDA' AND n.usuario.idUsuario = :idUsuario")
    Long countNotificacionesNoLeidasByUsuario(@Param("idUsuario") Long idUsuario);
    
    @Query("SELECT COUNT(n) FROM Notificaciones n WHERE n.estado = 'NO_LEIDA'")
    Long countTotalNotificacionesNoLeidas();
    
    List<Notificaciones> findByTituloContainingIgnoreCase(String titulo);
    
    List<Notificaciones> findByMensajeContainingIgnoreCase(String mensaje);
    
    @Query("SELECT n FROM Notificaciones n WHERE n.prioridad = 'ALTA' AND n.estado = 'NO_LEIDA' ORDER BY n.fechaCreacion ASC")
    List<Notificaciones> findNotificacionesDeAltaPrioridadNoLeidas();
    
    @Query("SELECT n FROM Notificaciones n WHERE n.fechaCreacion = :fecha")
    List<Notificaciones> findByFechaCreacion(@Param("fecha") LocalDateTime fecha);
    
    @Query("SELECT n FROM Notificaciones n WHERE n.fechaCreacion < :fechaLimite AND n.estado = 'NO_LEIDA'")
    List<Notificaciones> findNotificacionesVencidasNoLeidas(@Param("fechaLimite") LocalDateTime fechaLimite);
    
    List<Notificaciones> findByUsuarioAndTipoOrderByFechaCreacionDesc(Usuario usuario, String tipo);
}
