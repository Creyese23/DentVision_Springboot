package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Chat;
import com.sena.creyese.dentvision_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    
    List<Chat> findByRemitenteOrderByFechaChatDesc(Usuario remitente);
    
    List<Chat> findByDestinatarioOrderByFechaChatDesc(Usuario destinatario);
    
    List<Chat> findByRemitenteOrDestinatarioOrderByFechaChatDesc(Usuario remitente, Usuario destinatario);
    
    List<Chat> findByLeidoFalseAndDestinatarioOrderByFechaChatDesc(Usuario destinatario);
    
    List<Chat> findByFechaChatBetweenOrderByFechaChatDesc(LocalDateTime inicio, LocalDateTime fin);
}
