package com.sena.creyese.dentvision_spring.config;

import com.sena.creyese.dentvision_spring.enums.TipoRol;
import com.sena.creyese.dentvision_spring.modelo.Roles;
import com.sena.creyese.dentvision_spring.repository.RolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inicializador de datos por defecto para la aplicación DentVision.
 * 
 * Esta clase se encarga de crear los roles básicos del sistema cuando la aplicación
 * se inicia por primera vez. Utiliza CommandLineRunner para ejecutarse después de que
 * Spring haya configurado todos los beans pero antes de que la aplicación empiece
 * a recibir solicitudes.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Iniciando inicialización de datos por defecto...");
        
        // Crear roles por defecto si no existen
        initializeRoles();
        
        logger.info("Inicialización de datos completada.");
    }

    /**
     * Crea los roles básicos del sistema si no existen en la base de datos.
     * 
     * Los roles creados son:
     * - ADMIN: Administrador del sistema
     * - ODONTOLOGO: Profesional dental
     * - TECNICO_DENTAL: Técnico de laboratorio
     * - AUXILIAR_ADMIN: Personal administrativo
     * - PACIENTE: Usuario final
     */
    private void initializeRoles() {
        logger.info("Verificando roles existentes...");
        
        // Verificar y crear cada rol si no existe
        for (TipoRol tipoRol : TipoRol.values()) {
            if (rolRepository.countByNombreRol(tipoRol) == 0) {
                Roles rol = new Roles();
                rol.setNombreRol(tipoRol);
                rolRepository.save(rol);
                logger.info("Rol creado: {}", tipoRol);
            } else {
                logger.debug("Rol ya existe: {}", tipoRol);
            }
        }
        
        logger.info("Inicialización de roles completada.");
    }
}
