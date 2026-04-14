package com.sena.creyese.dentvision_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación DentVision Spring Boot.
 * 
 * Esta clase es el punto de entrada de la aplicación de gestión dental.
 * Contiene el método main que inicia el contexto de Spring Boot.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características principales:
 * - Sistema de gestión dental completo
 * - Autenticación con JWT
 * - Gestión de pacientes, citas, facturación
 * - Sistema de chat y notificaciones
 * - Gestión de inventario y órdenes de trabajo
 */
@SpringBootApplication
public class DentVisionSpringApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     * 
     * @param args Argumentos de línea de comandos pasados a la aplicación
     */
    public static void main(String[] args) {
        SpringApplication.run(DentVisionSpringApplication.class, args);
    }

}
