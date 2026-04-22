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
 * sobre la entidad Chat, incluyendo consultas por remitente/destinatario y búsqueda por contenido.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de mensajes por remitente y destinatario
 * - Obtención de conversaciones entre usuarios
 * - Búsqueda por contenido de mensajes
 */
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    
    /**
     * Obtiene todos los mensajes enviados por un usuario específico.
     * 
     * @param remitente Usuario que envió los mensajes
     * @return Lista de mensajes enviados
     */
    List<Chat> findByRemitente(Usuario remitente);
    
    /**
     * Obtiene todos los mensajes recibidos por un usuario específico.
     * 
     * @param destinatario Usuario que recibió los mensajes
     * @return Lista de mensajes recibidos
     */
    List<Chat> findByDestinatario(Usuario destinatario);
    
    /**
     * Obtiene la conversación completa entre dos usuarios.
     * 
     * @param remitente Usuario remitente de los mensajes
     * @param destinatario Usuario destinatario de los mensajes
     * @return Lista de mensajes entre ambos usuarios
     */
    List<Chat> findByRemitenteOrDestinatario(Usuario remitente, Usuario destinatario);
    
    /**
     * Busca mensajes cuyo contenido contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param mensaje Texto a buscar en el contenido de los mensajes
     * @return Lista de mensajes que coinciden con la búsqueda
     */
    List<Chat> findByMensajeContainingIgnoreCase(String mensaje);
    
    /**
     * Obtiene todos los mensajes enviados por un usuario específico ordenados por fecha descendente.
     * 
     * @param remitente Usuario que envió los mensajes
     * @return Lista de mensajes enviados ordenados por fecha descendente
     */
    List<Chat> findByRemitenteOrderByFechaChatDesc(Usuario remitente);
    
    /**
     * Obtiene todos los mensajes recibidos por un usuario específico ordenados por fecha descendente.
     * 
     * @param destinatario Usuario que recibió los mensajes
     * @return Lista de mensajes recibidos ordenados por fecha descendente
     */
    List<Chat> findByDestinatarioOrderByFechaChatDesc(Usuario destinatario);
    
    /**
     * Obtiene la conversación completa entre dos usuarios ordenada por fecha descendente.
     * 
     * @param usuario1 Primer usuario de la conversación
     * @param usuario2 Segundo usuario de la conversación
     * @return Lista de mensajes entre ambos usuarios ordenados por fecha descendente
     */
    List<Chat> findByRemitenteOrDestinatarioOrderByFechaChatDesc(Usuario usuario1, Usuario usuario2);
    
    /**
     * Obtiene todos los mensajes no leídos para un usuario específico ordenados por fecha descendente.
     * 
     * @param destinatario Usuario que recibió los mensajes no leídos
     * @return Lista de mensajes no leídos ordenados por fecha descendente
     */
    List<Chat> findByLeidoFalseAndDestinatarioOrderByFechaChatDesc(Usuario destinatario);
    
    /**
     * Obtiene todos los mensajes enviados en un rango de fechas específico ordenados por fecha descendente.
     * 
     * @param inicio Fecha y hora de inicio del rango
     * @param fin Fecha y hora de fin del rango
     * @return Lista de mensajes en el rango de fechas ordenados por fecha descendente
     */
    List<Chat> findByFechaChatBetweenOrderByFechaChatDesc(LocalDateTime inicio, LocalDateTime fin);
}
