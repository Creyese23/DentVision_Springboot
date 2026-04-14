package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Chat;
import com.sena.creyese.dentvision_spring.modelo.Usuario;
import com.sena.creyese.dentvision_spring.service.ChatService;
import com.sena.creyese.dentvision_spring.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de mensajes de chat en el sistema DentVision.
 * 
 * Este controlador proporciona endpoints completos para el sistema de mensajería interna,
 * permitiendo la comunicación entre usuarios del sistema (odontólogos, pacientes,
 * técnicos, auxiliares y administradores). Facilita el envío de mensajes, consulta
 * de conversaciones y gestión del estado de lectura de los mensajes.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características principales:
 * - Envío y recepción de mensajes entre usuarios
 * - Consulta de conversaciones por usuario
 * - Filtrado por estado de lectura (leídos/no leídos)
 * - Búsqueda por rangos de fechas
 * - Marcado de mensajes como leídos
 * - Gestión completa de CRUD para mensajes
 * 
 * Endpoints disponibles:
 * - GET /api/chats - Listar todos los mensajes
 * - GET /api/chats/{id} - Obtener mensaje por ID
 * - POST /api/chats - Crear nuevo mensaje
 * - POST /api/chats/enviar - Enviar mensaje entre usuarios
 * - GET /api/chats/usuario/{id}/enviados - Mensajes enviados por usuario
 * - GET /api/chats/usuario/{id}/recibidos - Mensajes recibidos por usuario
 * - GET /api/chats/usuario/{id}/no-leidos - Mensajes no leídos de usuario
 * - GET /api/chats/conversacion/{id1}/{id2} - Conversación entre dos usuarios
 * - GET /api/chats/rango-fechas - Mensajes por rango de fechas
 * - PUT /api/chats/{id}/marcar-leido - Marcar mensaje como leído
 * - PUT /api/chats/{id} - Actualizar mensaje
 * - DELETE /api/chats/{id} - Eliminar mensaje
 */
@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;
    private final UsuarioService usuarioService;

    /**
     * Constructor del controlador con inyección de dependencias.
     * 
     * @param chatService Servicio para la gestión de mensajes de chat
     * @param usuarioService Servicio para la gestión de usuarios
     */
    public ChatController(ChatService chatService, UsuarioService usuarioService) {
        this.chatService = chatService;
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para listar todos los mensajes del sistema.
     * 
     * @return Lista completa de todos los mensajes de chat
     */
    @GetMapping
    public List<Chat> listarTodos() {
        return chatService.listarTodos();
    }

    /**
     * Endpoint para obtener un mensaje específico por su ID.
     * 
     * @param id Identificador único del mensaje
     * @return ResponseEntity con el mensaje encontrado o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Chat> obtenerPorId(@PathVariable Long id) {
        return chatService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un nuevo mensaje de chat.
     * 
     * @param chat Objeto Chat con la información del mensaje a crear
     * @return ResponseEntity con el mensaje creado
     */
    @PostMapping
    public ResponseEntity<Chat> crear(@Valid @RequestBody Chat chat) {
        Chat nuevo = chatService.guardar(chat);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * Endpoint para enviar un mensaje entre dos usuarios.
     * 
     * @param payload Mapa que contiene idRemitente, idDestinatario y mensaje
     * @return ResponseEntity con el mensaje enviado
     * @throws RuntimeException si el remitente o destinatario no existen
     */
    @PostMapping("/enviar")
    public ResponseEntity<Chat> enviarMensaje(@RequestBody Map<String, Object> payload) {
        Long idRemitente = Long.valueOf(payload.get("idRemitente").toString());
        Long idDestinatario = Long.valueOf(payload.get("idDestinatario").toString());
        String mensaje = payload.get("mensaje").toString();

        Usuario remitente = usuarioService.obtenerPorId(idRemitente)
                .orElseThrow(() -> new RuntimeException("Remitente no encontrado"));
        Usuario destinatario = usuarioService.obtenerPorId(idDestinatario)
                .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));

        Chat nuevoMensaje = chatService.enviarMensaje(remitente, destinatario, mensaje);
        return ResponseEntity.ok(nuevoMensaje);
    }

    /**
     * Endpoint para obtener todos los mensajes enviados por un usuario específico.
     * 
     * @param idUsuario Identificador único del usuario remitente
     * @return ResponseEntity con la lista de mensajes enviados
     * @throws RuntimeException si el usuario no existe
     */
    @GetMapping("/usuario/{idUsuario}/enviados")
    public ResponseEntity<List<Chat>> obtenerMensajesEnviados(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.obtenerPorId(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Chat> mensajes = chatService.obtenerMensajesEnviadosPorUsuario(usuario);
        return ResponseEntity.ok(mensajes);
    }

    /**
     * Endpoint para obtener todos los mensajes recibidos por un usuario específico.
     * 
     * @param idUsuario Identificador único del usuario destinatario
     * @return ResponseEntity con la lista de mensajes recibidos
     * @throws RuntimeException si el usuario no existe
     */
    @GetMapping("/usuario/{idUsuario}/recibidos")
    public ResponseEntity<List<Chat>> obtenerMensajesRecibidos(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.obtenerPorId(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Chat> mensajes = chatService.obtenerMensajesRecibidosPorUsuario(usuario);
        return ResponseEntity.ok(mensajes);
    }

    /**
     * Endpoint para obtener todos los mensajes no leídos de un usuario específico.
     * 
     * @param idUsuario Identificador único del usuario
     * @return ResponseEntity con la lista de mensajes no leídos
     * @throws RuntimeException si el usuario no existe
     */
    @GetMapping("/usuario/{idUsuario}/no-leidos")
    public ResponseEntity<List<Chat>> obtenerMensajesNoLeidos(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.obtenerPorId(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Chat> mensajes = chatService.obtenerMensajesNoLeidos(usuario);
        return ResponseEntity.ok(mensajes);
    }

    /**
     * Endpoint para obtener la conversación completa entre dos usuarios específicos.
     * 
     * @param idUsuario1 Identificador único del primer usuario
     * @param idUsuario2 Identificador único del segundo usuario
     * @return ResponseEntity con la lista de mensajes de la conversación
     * @throws RuntimeException si alguno de los usuarios no existe
     */
    @GetMapping("/conversacion/{idUsuario1}/{idUsuario2}")
    public ResponseEntity<List<Chat>> obtenerConversacion(@PathVariable Long idUsuario1, @PathVariable Long idUsuario2) {
        Usuario usuario1 = usuarioService.obtenerPorId(idUsuario1)
                .orElseThrow(() -> new RuntimeException("Usuario 1 no encontrado"));
        Usuario usuario2 = usuarioService.obtenerPorId(idUsuario2)
                .orElseThrow(() -> new RuntimeException("Usuario 2 no encontrado"));
        List<Chat> conversacion = chatService.obtenerConversacionEntreUsuarios(usuario1, usuario2);
        return ResponseEntity.ok(conversacion);
    }

    /**
     * Endpoint para obtener mensajes dentro de un rango de fechas específico.
     * 
     * @param inicio Fecha y hora de inicio del rango (formato ISO)
     * @param fin Fecha y hora de fin del rango (formato ISO)
     * @return ResponseEntity con la lista de mensajes en el rango especificado
     */
    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Chat>> obtenerMensajesPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<Chat> mensajes = chatService.obtenerMensajesPorRangoDeFechas(inicio, fin);
        return ResponseEntity.ok(mensajes);
    }

    /**
     * Endpoint para marcar un mensaje específico como leído.
     * 
     * @param id Identificador único del mensaje a marcar como leído
     * @return ResponseEntity con estado 200 si la operación fue exitosa
     */
    @PutMapping("/{id}/marcar-leido")
    public ResponseEntity<Void> marcarComoLeido(@PathVariable Long id) {
        chatService.marcarMensajeComoLeido(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para actualizar un mensaje existente.
     * 
     * @param id Identificador único del mensaje a actualizar
     * @param chat Objeto Chat con los datos actualizados
     * @return ResponseEntity con el mensaje actualizado o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Chat> actualizar(@PathVariable Long id, @Valid @RequestBody Chat chat) {
        return chatService.obtenerPorId(id)
                .map(existente -> {
                    existente.setMensaje(chat.getMensaje());
                    existente.setLeido(chat.isLeido());
                    Chat actualizado = chatService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar un mensaje específico del sistema.
     * 
     * @param id Identificador único del mensaje a eliminar
     * @return ResponseEntity con estado 204 si se eliminó correctamente o 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (chatService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        chatService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
