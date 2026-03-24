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
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Value("${security.jwt.secret:dentvision-secret-key-change-me}")
    private String jwtSecret;

    @Value("${security.jwt.header:Authorization}")
    private String authHeader;

    @Value("${security.jwt.prefix:Bearer }")
    private String tokenPrefix;

    @Value("${security.jwt.issuer:dentvision}")
    private String expectedIssuer;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(authHeader);

        if (header == null || !header.startsWith(tokenPrefix)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(tokenPrefix.length()).trim();

        try {
            Map<String, Object> claims = parseAndValidateToken(token);
            String username = (String) claims.get("sub");

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private Map<String, Object> parseAndValidateToken(String token) throws Exception {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Token JWT invalido");
        }

        String signingInput = parts[0] + "." + parts[1];
        String expectedSignature = sign(signingInput, jwtSecret);

        if (!constantTimeEquals(expectedSignature, parts[2])) {
            throw new IllegalArgumentException("Firma JWT invalida");
        }

        Map<String, Object> payload = OBJECT_MAPPER.readValue(
                Base64.getUrlDecoder().decode(parts[1]),
                new TypeReference<Map<String, Object>>() {}
        );

        Object exp = payload.get("exp");
        if (exp instanceof Number expNumber) {
            long expSeconds = expNumber.longValue();
            if (Instant.now().getEpochSecond() >= expSeconds) {
                throw new IllegalArgumentException("Token expirado");
            }
        }

        Object iss = payload.get("iss");
        if (iss instanceof String issuer && expectedIssuer != null && !expectedIssuer.isBlank()) {
            if (!expectedIssuer.equals(issuer)) {
                throw new IllegalArgumentException("Issuer JWT invalido");
            }
        }

        return payload;
    }

    private String sign(String data, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(keySpec);
        byte[] signature = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(signature);
    }

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
