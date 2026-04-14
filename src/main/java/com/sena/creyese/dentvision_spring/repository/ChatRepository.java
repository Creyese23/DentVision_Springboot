package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Chat;
import com.sena.creyese.dentvision_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de mensajes de chat en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Chat, incluyendo consultas por remitente/destinatario, filtrado por estado
 * de lectura y búsqueda por rangos de fechas.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de mensajes por remitente y destinatario
 * - Obtención de conversaciones entre usuarios
 * - Filtrado por mensajes no leídos
 * - Búsqueda por rangos de fechas
 * - Ordenamiento cronológico de mensajes
 */
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    
    /**
     * Obtiene todos los mensajes enviados por un usuario específico ordenados por fecha descendente.
     * 
     * @param remitente Usuario que envió los mensajes
     * @return Lista de mensajes enviados ordenados del más reciente al más antiguo
     */
    List<Chat> findByRemitenteOrderByFechaChatDesc(Usuario remitente);
    
    /**
     * Obtiene todos los mensajes recibidos por un usuario específico ordenados por fecha descendente.
     * 
     * @param destinatario Usuario que recibió los mensajes
     * @return Lista de mensajes recibidos ordenados del más reciente al más antiguo
     */
    List<Chat> findByDestinatarioOrderByFechaChatDesc(Usuario destinatario);
    
    /**
     * Obtiene la conversación completa entre dos usuarios ordenada por fecha descendente.
     * 
     * @param remitente Usuario remitente de los mensajes
     * @param destinatario Usuario destinatario de los mensajes
     * @return Lista de mensajes entre ambos usuarios ordenados del más reciente al más antiguo
     */
    List<Chat> findByRemitenteOrDestinatarioOrderByFechaChatDesc(Usuario remitente, Usuario destinatario);
    
    /**
     * Obtiene todos los mensajes no leídos de un usuario específico ordenados por fecha descendente.
     * 
     * @param destinatario Usuario destinatario de los mensajes no leídos
     * @return Lista de mensajes no leídos ordenados del más reciente al más antiguo
     */
    List<Chat> findByLeidoFalseAndDestinatarioOrderByFechaChatDesc(Usuario destinatario);
    
    /**
     * Obtiene todos los mensajes dentro de un rango de fechas específico ordenados por fecha descendente.
     * 
     * @param inicio Fecha y hora de inicio del rango
     * @param fin Fecha y hora de fin del rango
     * @return Lista de mensajes en el rango ordenados del más reciente al más antiguo
     */
    List<Chat> findByFechaChatBetweenOrderByFechaChatDesc(LocalDateTime inicio, LocalDateTime fin);
}
