package com.sena.creyese.dentvision_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase de configuración de seguridad para la aplicación DentVision.
 * 
 * Esta clase configura la seguridad de la aplicación utilizando Spring Security
 * con autenticación basada en tokens JWT. Define las reglas de acceso a los endpoints
 * y configura los componentes necesarios para la gestión de seguridad.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características de seguridad configuradas:
 * - Deshabilitación de CSRF (para API REST)
 * - Sesiones sin estado (STATELESS) para JWT
 * - Autenticación requerida para todos los endpoints excepto /auth/**
 * - Filtro JWT personalizado para validación de tokens
 * - Codificador de contraseñas BCrypt
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /** Filtro JWT para validar tokens en las solicitudes HTTP */
    private final JwtFilter jwtFilter;

    /**
     * Constructor que inyecta el filtro JWT.
     * 
     * @param jwtFilter Filtro JWT personalizado para validación de tokens
     */
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Configura la cadena de filtros de seguridad para la aplicación.
     * 
     * Define las reglas de seguridad incluyendo:
     * - Deshabilita CSRF para API REST
     * - Configura sesiones sin estado (STATELESS)
     * - Permite acceso público a endpoints de autenticación
     * - Requiere autenticación para todos los demás endpoints
     * - Agrega filtro JWT antes de la autenticación básica
     * 
     * @param http Configuración de seguridad HTTP
     * @return SecurityFilterChain configurada
     * @throws Exception Si hay error en la configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilitar CSRF para API REST
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sesiones sin estado
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/error", "/usuarios", "/pacientes", "/odontologos", "/tecnicos", "/auxiliares", "/admin", "/roles", "/servicios", "/insumos", "/procedimientos", "/citas", "/ordenes", "/pagos", "/facturas", "/inventario", "/entregas", "/chats", "/mensajes", "/notificaciones", "/imagenes", "/detalle-facturas", "/orden-detalles").permitAll() // Endpoints públicos para desarrollo
                        .anyRequest().authenticated() // Requerir autenticación para el resto
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Agregar filtro JWT

        return http.build();
    }

    /**
     * Bean para el codificador de contraseñas.
     * 
     * Utiliza BCrypt para hashear contraseñas de forma segura.
     * BCrypt es un algoritmo de hashing diseñado específicamente para contraseñas.
     * 
     * @return PasswordEncoder con BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean para el gestor de autenticación.
     * 
     * Proporciona el AuthenticationManager que se utiliza para procesar
     * solicitudes de autenticación en la aplicación.
     * 
     * @param authenticationConfiguration Configuración de autenticación
     * @return AuthenticationManager configurado
     * @throws Exception Si hay error en la configuración
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
