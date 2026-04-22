package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la gestión de notificaciones del sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Notificaciones, incluyendo consultas por mensaje y estado.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Consulta de notificaciones por mensaje
 * - Gestión de estados (leído/no leído)
 */
@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones,Long> {

    /**
     * Busca notificaciones cuyo mensaje contenga el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param mensaje Texto a buscar en los mensajes
     * @return Lista de notificaciones que coinciden con la búsqueda
     */
    List<Notificaciones> findByMensajeContainingIgnoreCase(String mensaje);
    
    /**
     * Obtiene todas las notificaciones con estado leído (true).
     * 
     * @return Lista de notificaciones leídas
     */
    List<Notificaciones> findByLeidoTrue();
    
    /**
     * Obtiene todas las notificaciones con estado no leído (false).
     * 
     * @return Lista de notificaciones no leídas
     */
    List<Notificaciones> findByLeidoFalse();
}
