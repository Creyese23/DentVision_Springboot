package com.sena.creyese.dentvision_spring.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

/**
 * Filtro JWT para autenticación basada en tokens JSON Web Token.
 * 
 * Este filtro se ejecuta una vez por solicitud y valida los tokens JWT
 * presentes en el header Authorization. Si el token es válido, establece
 * el contexto de autenticación en Spring Security.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Extracción de token del header HTTP
 * - Validación de firma HMAC-SHA256
 * - Verificación de expiración
 * - Validación del issuer
 * - Establecimiento del contexto de seguridad
 * - Manejo seguro de errores
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    /** ObjectMapper para procesar JSON del payload JWT */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /** Secreto para firmar tokens JWT (configurable en application.properties) */
    @Value("${security.jwt.secret:dentvision-secret-key-change-me}")
    private String jwtSecret;

    /** Nombre del header HTTP que contiene el token JWT */
    @Value("${security.jwt.header:Authorization}")
    private String authHeader;

    /** Prefijo del token (generalmente "Bearer ") */
    @Value("${security.jwt.prefix:Bearer }")
    private String tokenPrefix;

    /** Issuer esperado en el token JWT */
    @Value("${security.jwt.issuer:dentvision}")
    private String expectedIssuer;

    /**
     * Método principal del filtro que procesa cada solicitud HTTP.
     * 
     * Este método:
     * 1. Extrae el token del header Authorization
     * 2. Si no hay token válido, continúa con la cadena de filtros
     * 3. Si hay token, lo valida y establece el contexto de autenticación
     * 4. Maneja excepciones de seguridad limpiando el contexto
     * 
     * @param request Solicitud HTTP entrante
     * @param response Respuesta HTTP saliente
     * @param filterChain Cadena de filtros a continuar
     * @throws ServletException Si hay error de servlet
     * @throws IOException Si hay error de E/S
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Extraer el header de autorización
        String header = request.getHeader(authHeader);

        // Si no hay header o no tiene el prefijo esperado, continuar sin autenticación
        if (header == null || !header.startsWith(tokenPrefix)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraer el token eliminando el prefijo "Bearer "
        String token = header.substring(tokenPrefix.length()).trim();

        try {
            // Validar y parsear el token JWT
            Map<String, Object> claims = parseAndValidateToken(token);
            String username = (String) claims.get("sub");

            // Si hay username y no hay autenticación actual, establecerla
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            // Si hay error en la validación, limpiar el contexto de seguridad
            SecurityContextHolder.clearContext();
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    /**
     * Parsea y valida un token JWT.
     * 
     * Este método realiza las siguientes validaciones:
     * 1. Verifica que el token tenga las 3 partes (header, payload, signature)
     * 2. Valida la firma HMAC-SHA256
     * 3. Verifica que el token no haya expirado
     * 4. Valida el issuer del token
     * 
     * @param token Token JWT a validar
     * @return Map con los claims del payload del token
     * @throws Exception Si el token es inválido o expiró
     */
    private Map<String, Object> parseAndValidateToken(String token) throws Exception {
        // Dividir el token en sus 3 partes
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Token JWT invalido");
        }

        // Construir el input para la firma (header + payload)
        String signingInput = parts[0] + "." + parts[1];
        // Calcular la firma esperada
        String expectedSignature = sign(signingInput, jwtSecret);

        // Validar la firma usando comparación de tiempo constante
        if (!constantTimeEquals(expectedSignature, parts[2])) {
            throw new IllegalArgumentException("Firma JWT invalida");
        }

        // Decodificar y parsear el payload
        Map<String, Object> payload = OBJECT_MAPPER.readValue(
                Base64.getUrlDecoder().decode(parts[1]),
                new TypeReference<Map<String, Object>>() {}
        );

        // Validar expiración del token
        Object exp = payload.get("exp");
        if (exp instanceof Number expNumber) {
            long expSeconds = expNumber.longValue();
            if (Instant.now().getEpochSecond() >= expSeconds) {
                throw new IllegalArgumentException("Token expirado");
            }
        }

        // Validar el issuer del token
        Object iss = payload.get("iss");
        if (iss instanceof String issuer && expectedIssuer != null && !expectedIssuer.isBlank()) {
            if (!expectedIssuer.equals(issuer)) {
                throw new IllegalArgumentException("Issuer JWT invalido");
            }
        }

        return payload;
    }

    /**
     * Firma datos usando HMAC-SHA256.
     * 
     * @param data Datos a firmar
     * @param secret Secreto para la firma
     * @return Firma codificada en Base64 URL
     * @throws Exception Si hay error en la firma
     */
    private String sign(String data, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(keySpec);
        byte[] signature = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(signature);
    }

    /**
     * Compara dos strings en tiempo constante para prevenir ataques de timing.
     * 
     * @param a Primer string a comparar
     * @param b Segundo string a comparar
     * @return true si los strings son iguales, false en caso contrario
     */
    private boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            result |= a.charAt(i) ^ b.charAt(i);
        }
        return result == 0;
    }
}
