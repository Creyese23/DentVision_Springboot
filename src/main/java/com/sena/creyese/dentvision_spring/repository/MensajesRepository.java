package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Mensajes;
import com.sena.creyese.dentvision_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MensajesRepository extends JpaRepository<Mensajes,Long> {

    List<Mensajes> findByRemitenteOrderByFechaEnvioDesc(Usuario remitente);
    
    List<Mensajes> findByDestinatarioOrderByFechaEnvioDesc(Usuario destinatario);
    
    List<Mensajes> findByAsuntoContainingIgnoreCase(String asunto);
    
    List<Mensajes> findByEstadoOrderByFechaEnvioDesc(String estado);
    
    List<Mensajes> findByFechaEnvioBetweenOrderByFechaEnvioDesc(LocalDateTime inicio, LocalDateTime fin);
    
    @Query("SELECT m FROM Mensajes m WHERE m.estado = 'NO_LEIDO' AND m.destinatario.idUsuario = :idDestinatario ORDER BY m.fechaEnvio ASC")
    List<Mensajes> findMensajesNoLeidosByDestinatario(@Param("idDestinatario") Long idDestinatario);
    
    @Query("SELECT m FROM Mensajes m WHERE m.estado = 'LEIDO' ORDER BY m.fechaEnvio DESC")
    List<Mensajes> findMensajesLeidos();
    
    @Query("SELECT m FROM Mensajes m WHERE m.estado = 'NO_LEIDO' ORDER BY m.fechaEnvio ASC")
    List<Mensajes> findMensajesNoLeidos();
    
    @Query("SELECT COUNT(m) FROM Mensajes m WHERE m.estado = 'NO_LEIDO' AND m.destinatario.idUsuario = :idDestinatario")
    Long countMensajesNoLeidosByDestinatario(@Param("idDestinatario") Long idDestinatario);
    
    @Query("SELECT m FROM Mensajes m WHERE m.remitente.idUsuario = :idRemitente OR m.destinatario.idUsuario = :idUsuario ORDER BY m.fechaEnvio DESC")
    List<Mensajes> findMensajesByUsuario(@Param("idRemitente") Long idRemitente, @Param("idUsuario") Long idUsuario);
    
    List<Mensajes> findByContenidoContainingIgnoreCase(String contenido);
    
    @Query("SELECT m FROM Mensajes m WHERE m.prioridad = 'ALTA' AND m.estado = 'NO_LEIDO' ORDER BY m.fechaEnvio ASC")
    List<Mensajes> findMensajesDeAltaPrioridadNoLeidos();
    
    @Query("SELECT m FROM Mensajes m WHERE m.fechaEnvio = :fecha")
    List<Mensajes> findByFechaEnvio(@Param("fecha") LocalDateTime fecha);
    
    List<Mensajes> findByRemitenteAndDestinatarioOrderByFechaEnvioDesc(Usuario remitente, Usuario destinatario);
}
