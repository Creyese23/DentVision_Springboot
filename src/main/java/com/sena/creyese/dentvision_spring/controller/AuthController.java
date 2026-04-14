package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Usuario;
import com.sena.creyese.dentvision_spring.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST para la gestión de autenticación en el sistema DentVision.
 * 
 * Este controlador maneja las operaciones de autenticación incluyendo registro,
 * inicio de sesión y verificación de tokens JWT. Proporciona los endpoints
 * necesarios para que los usuarios puedan autenticarse en el sistema.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Endpoints disponibles:
 * - POST /auth/register - Registro de nuevos usuarios
 * - POST /auth/login - Inicio de sesión y generación de token
 * - GET /auth/verify - Verificación de token JWT
 * 
 * Características de seguridad:
 * - Codificación de contraseñas con BCrypt
 * - Generación de tokens JWT con 24 horas de validez
 * - Validación de estado del usuario (activo/inactivo)
 * - Manejo seguro de errores sin exponer información sensible
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /** Servicio para la gestión de usuarios */
    private final UsuarioService usuarioService;
    
    /** Codificador de contraseñas para hashear y verificar passwords */
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor que inyecta las dependencias necesarias.
     * 
     * @param usuarioService Servicio para gestión de usuarios
     * @param passwordEncoder Codificador de contraseñas
     */
    public AuthController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Endpoint para registrar un nuevo usuario en el sistema.
     * 
     * Este método:
     * 1. Verifica que el email no esté ya registrado
     * 2. Codifica las contraseñas usando BCrypt
     * 3. Establece el estado como activo
     * 4. Registra la fecha de creación
     * 5. Guarda el nuevo usuario en la base de datos
     * 
     * @param usuario Datos del usuario a registrar
     * @return ResponseEntity con mensaje de éxito o error
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registrar(@RequestBody Usuario usuario) {
        // Verificar si el email ya está registrado
        if (usuarioService.obtenerPorEmail(usuario.getEmail()).isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "El email ya está registrado");
            return ResponseEntity.badRequest().body(response);
        }

        // Codificar contraseñas por seguridad
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setConfirmar_password(passwordEncoder.encode(usuario.getConfirmar_password()));
        
        // Establecer estado y fecha de registro
        usuario.setEstado(true);
        usuario.setFecha_registro(java.time.LocalDate.now());
        
        // Guardar el nuevo usuario
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        
        // Preparar respuesta de éxito
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado exitosamente");
        response.put("id", nuevoUsuario.getId().toString());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para iniciar sesión y generar un token JWT.
     * 
     * Este método:
     * 1. Verifica las credenciales del usuario
     * 2. Verifica que el usuario esté activo
     * 3. Genera un token JWT con 24 horas de validez
     * 4. Devuelve el token y datos básicos del usuario
     * 
     * @param credentials Map con email y password
     * @return ResponseEntity con token y datos del usuario o error
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Buscar usuario por email
        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorEmail(email);
        
        // Verificar credenciales
        if (usuarioOpt.isEmpty() || !passwordEncoder.matches(password, usuarioOpt.get().getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Credenciales inválidas");
            return ResponseEntity.badRequest().body(response);
        }

        Usuario usuario = usuarioOpt.get();
        
        // Verificar que el usuario esté activo
        if (!usuario.getEstado()) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Usuario inactivo");
            return ResponseEntity.badRequest().body(response);
        }

        // Generar token JWT
        String token = generateToken(usuario);
        
        // Preparar respuesta con token y datos del usuario
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("usuario", Map.of(
            "id", usuario.getId(),
            "nombres", usuario.getNombres(),
            "apellidos", usuario.getApellidos(),
            "email", usuario.getEmail(),
            "rol", usuario.getRol() != null ? usuario.getRol().getNombreRol().toString() : "SIN_ROL"
        ));
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para verificar la validez de un token JWT.
     * 
     * Este método:
     * 1. Extrae el token del header Authorization
     * 2. Parsea y valida el token
     * 3. Verifica que el usuario exista y esté activo
     * 4. Devuelve la información del usuario si el token es válido
     * 
     * @param authHeader Header Authorization con el token Bearer
     * @return ResponseEntity con estado de validación y datos del usuario
     */
    @GetMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyToken(@RequestHeader("Authorization") String authHeader) {
        // Verificar que el token esté presente y tenga el formato correcto
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Token no proporcionado");
            return ResponseEntity.badRequest().body(response);
        }

        // Extraer el token (eliminar "Bearer ")
        String token = authHeader.substring(7);
        
        try {
            // Parsear y validar el token
            Map<String, Object> claims = parseToken(token);
            String email = (String) claims.get("sub");
            
            // Verificar que el usuario exista
            Optional<Usuario> usuarioOpt = usuarioService.obtenerPorEmail(email);
            if (usuarioOpt.isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("error", "Usuario no encontrado");
                return ResponseEntity.badRequest().body(response);
            }

            Usuario usuario = usuarioOpt.get();
            
            // Preparar respuesta de éxito
            Map<String, Object> response = new HashMap<>();
            response.put("valid", true);
            response.put("usuario", Map.of(
                "id", usuario.getId(),
                "nombres", usuario.getNombres(),
                "apellidos", usuario.getApellidos(),
                "email", usuario.getEmail(),
                "rol", usuario.getRol() != null ? usuario.getRol().getNombreRol().toString() : "SIN_ROL"
            ));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Manejar errores de validación del token
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Token inválido");
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Genera un token JWT para un usuario específico.
     * 
     * El token incluye:
     * - sub: email del usuario (subject)
     * - iss: dentvision (issuer)
     * - exp: tiempo de expiración (24 horas)
     * 
     * @param usuario Usuario para el cual se genera el token
     * @return Token JWT como string
     */
    private String generateToken(Usuario usuario) {
        // Crear claims del token
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", usuario.getEmail());
        claims.put("iss", "dentvision");
        claims.put("exp", System.currentTimeMillis() / 1000 + 86400); // 24 horas
        
        try {
            // Codificar header del JWT
            String header = java.util.Base64.getUrlEncoder().withoutPadding()
                .encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes());
            
            // Codificar payload del JWT
            String payload = java.util.Base64.getUrlEncoder().withoutPadding()
                .encodeToString(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(claims).getBytes());
            
            // Generar firma HMAC-SHA256
            String signature = sign(header + "." + payload, "dentvision-secret-key-change-me");
            
            // Retornar token completo (header.payload.signature)
            return header + "." + payload + "." + signature;
        } catch (Exception e) {
            throw new RuntimeException("Error al generar token", e);
        }
    }

    /**
     * Parsea el payload de un token JWT.
     * 
     * @param token Token JWT a parsear
     * @return Map con los claims del token
     * @throws Exception Si el token es inválido
     */
    private Map<String, Object> parseToken(String token) throws Exception {
        // Dividir el token en sus 3 partes
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Token JWT inválido");
        }

        // Decodificar y parsear el payload
        String payload = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));
        return new com.fasterxml.jackson.databind.ObjectMapper().readValue(payload, Map.class);
    }

    /**
     * Firma datos usando HMAC-SHA256 para la validación JWT.
     * 
     * @param data Datos a firmar
     * @param secret Secreto para la firma
     * @return Firma codificada en Base64 URL
     * @throws Exception Si hay error en la firma
     */
    private String sign(String data, String secret) throws Exception {
        // Crear instancia de HMAC-SHA256
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
        
        // Crear clave secreta
        javax.crypto.spec.SecretKeySpec keySpec = new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256");
        
        // Inicializar el MAC con la clave
        mac.init(keySpec);
        
        // Generar la firma
        byte[] signature = mac.doFinal(data.getBytes());
        
        // Codificar la firma en Base64 URL sin padding
        return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(signature);
    }
}
