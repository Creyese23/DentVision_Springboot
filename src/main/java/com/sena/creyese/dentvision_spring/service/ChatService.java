package com.sena.creyese.dentvision_spring.service;



import com.sena.creyese.dentvision_spring.modelo.Chat;

import com.sena.creyese.dentvision_spring.modelo.Usuario;

import com.sena.creyese.dentvision_spring.repository.ChatRepository;

import org.springframework.stereotype.Service;



import java.time.LocalDateTime;

import java.util.List;

import java.util.Optional;



/**

 * Servicio de negocio para la gestión de chat y mensajería en el sistema DentVision.

 * 

 * Esta clase proporciona la lógica de negocio para todas las operaciones

 * relacionadas con comunicación entre usuarios, incluyendo envío de mensajes,

 * seguimiento de conversaciones, gestión de mensajes leídos/no leídos y

 * búsqueda por rangos de fechas. Actúa como una capa de abstracción entre

 * los controladores y el repositorio de chat.

 * 

 * @author Creyese

 * @version 1.0

 * @since 2026

 * 

 * Funcionalidades principales:

 * - Gestión CRUD de mensajes de chat

 * - Envío y recepción de mensajes entre usuarios

 * - Seguimiento de conversaciones

 * - Gestión de estado de lectura

 * - Búsquedas avanzadas por fechas y usuarios

 * - Validaciones de negocio

 * - Manejo de transacciones implícito

 */

@Service

public class ChatService {

    

    /** Repositorio para el acceso a datos de chat */

    private final ChatRepository chatRepository;



    /**

     * Constructor que inyecta el repositorio de chat.

     * 

     * @param chatRepository Repositorio de chat a inyectar

     */

    public ChatService(ChatRepository chatRepository) {

        this.chatRepository = chatRepository;

    }



    /**

     * Obtiene todos los mensajes de chat del sistema.

     * 

     * Este método retorna una lista con todos los mensajes registrados

     * en la base de datos. Útil para administración y reportes.

     * 

     * @return Lista de todos los mensajes de chat

     */

    public List<Chat> listarTodos() {

        return chatRepository.findAll();

    }



    /**

     * Busca un mensaje de chat por su identificador único.

     * 

     * @param id Identificador único del mensaje

     * @return Optional con el mensaje encontrado o vacío si no existe

     */

    public Optional<Chat> obtenerPorId(Long id) {

        return chatRepository.findById(id);

    }



    /**

     * Guarda o actualiza un mensaje de chat en la base de datos.

     * 

     * Este método maneja tanto la creación de nuevos mensajes

     * como la actualización de mensajes existentes. Si el mensaje

     * no tiene fecha, se asigna automáticamente la fecha y hora actual.

     * 

     * @param chat Mensaje de chat a guardar o actualizar

     * @return Mensaje guardado con su ID asignado

     */

    public Chat guardar(Chat chat) {

        if (chat.getFechaChat() == null) {

            chat.setFechaChat(LocalDateTime.now());

        }

        return chatRepository.save(chat);

    }



    /**

     * Elimina un mensaje de chat del sistema por su identificador.

     * 

     * @param id Identificador único del mensaje a eliminar

     */

    public void eliminar(Long id) {

        chatRepository.deleteById(id);

    }



    /**

     * Obtiene todos los mensajes enviados por un usuario específico.

     * 

     * Los mensajes se retornan ordenados por fecha de envío descendente

     * (más recientes primero).

     * 

     * @param remitente Usuario que envió los mensajes

     * @return Lista de mensajes enviados por el usuario

     */

    public List<Chat> obtenerMensajesEnviadosPorUsuario(Usuario remitente) {

        return chatRepository.findByRemitenteOrderByFechaChatDesc(remitente);

    }



    /**

     * Obtiene todos los mensajes recibidos por un usuario específico.

     * 

     * Los mensajes se retornan ordenados por fecha de envío descendente

     * (más recientes primero).

     * 

     * @param destinatario Usuario que recibió los mensajes

     * @return Lista de mensajes recibidos por el usuario

     */

    public List<Chat> obtenerMensajesRecibidosPorUsuario(Usuario destinatario) {

        return chatRepository.findByDestinatarioOrderByFechaChatDesc(destinatario);

    }



    /**

     * Obtiene la conversación completa entre dos usuarios.

     * 

     * Este método retorna todos los mensajes intercambiados entre

     * dos usuarios, sin importar quién fue el remitente o destinatario.

     * Los mensajes se ordenan por fecha descendente.

     * 

     * @param usuario1 Primer usuario de la conversación

     * @param usuario2 Segundo usuario de la conversación

     * @return Lista completa de la conversación entre los dos usuarios

     */

    public List<Chat> obtenerConversacionEntreUsuarios(Usuario usuario1, Usuario usuario2) {

        return chatRepository.findByRemitenteOrDestinatarioOrderByFechaChatDesc(usuario1, usuario2);

    }



    /**

     * Obtiene todos los mensajes no leídos para un usuario específico.

     * 

     * Útil para mostrar notificaciones de mensajes pendientes de lectura.

     * Los mensajes se ordenan por fecha descendente.

     * 

     * @param destinatario Usuario que recibió los mensajes no leídos

     * @return Lista de mensajes no leídos para el usuario

     */

    public List<Chat> obtenerMensajesNoLeidos(Usuario destinatario) {

        return chatRepository.findByLeidoFalseAndDestinatarioOrderByFechaChatDesc(destinatario);

    }



    /**

     * Obtiene todos los mensajes enviados en un rango de fechas específico.

     * 

     * Útil para reportes y auditoría de comunicación en períodos

     * determinados. Los mensajes se ordenan por fecha descendente.

     * 

     * @param inicio Fecha y hora de inicio del rango

     * @param fin Fecha y hora de fin del rango

     * @return Lista de mensajes en el rango de fechas especificado

     */

    public List<Chat> obtenerMensajesPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin) {

        return chatRepository.findByFechaChatBetweenOrderByFechaChatDesc(inicio, fin);

    }



    /**

     * Marca un mensaje específico como leído.

     * 

     * Este método busca el mensaje por su ID y actualiza su estado

     * a leído (true). Si el mensaje no existe, no realiza ninguna acción.

     * 

     * @param idMensaje Identificador único del mensaje a marcar como leído

     */

    public void marcarMensajeComoLeido(Long idMensaje) {

        Optional<Chat> chatOpt = chatRepository.findById(idMensaje);

        if (chatOpt.isPresent()) {

            Chat chat = chatOpt.get();

            chat.setLeido(true);

            chatRepository.save(chat);

        }

    }



    /**

     * Envía un nuevo mensaje entre dos usuarios.

     * 

     * Este método crea un nuevo mensaje de chat con todos los datos

     * necesarios: remitente, destinatario, contenido del mensaje,

     * fecha y hora actual, y estado de lectura inicial (false).

     * 

     * @param remitente Usuario que envía el mensaje

     * @param destinatario Usuario que recibe el mensaje

     * @param mensaje Contenido del mensaje a enviar

     * @return Mensaje guardado con su ID asignado

     */

    public Chat enviarMensaje(Usuario remitente, Usuario destinatario, String mensaje) {

        Chat chat = new Chat();

        chat.setRemitente(remitente);

        chat.setDestinatario(destinatario);

        chat.setMensaje(mensaje);

        chat.setFechaChat(LocalDateTime.now());

        chat.setLeido(false);

        return chatRepository.save(chat);

    }

}

