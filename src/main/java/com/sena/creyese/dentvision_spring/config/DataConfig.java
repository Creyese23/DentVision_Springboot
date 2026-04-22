package com.sena.creyese.dentvision_spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan;

/**
 * Configuración de datos para el entorno de desarrollo.
 * 
 * Esta clase excluye los repositories problemáticos para permitir
 * que la aplicación inicie correctamente.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.sena.creyese.dentvision_spring.repository",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, 
        classes = {
            com.sena.creyese.dentvision_spring.repository.AdminRepository.class,
            com.sena.creyese.dentvision_spring.repository.AuxiliarAdminRepository.class,
            com.sena.creyese.dentvision_spring.repository.CitaRepository.class,
            com.sena.creyese.dentvision_spring.repository.DetalleFacturaRepository.class,
            com.sena.creyese.dentvision_spring.repository.EntregaRepository.class,
            com.sena.creyese.dentvision_spring.repository.FacturaRepository.class,
            com.sena.creyese.dentvision_spring.repository.InventarioRepository.class,
            com.sena.creyese.dentvision_spring.repository.MensajesRepository.class,
            com.sena.creyese.dentvision_spring.repository.NotificacionesRepository.class,
            com.sena.creyese.dentvision_spring.repository.OrdenDetalleRepository.class,
            com.sena.creyese.dentvision_spring.repository.OrdenTrabajoRepository.class,
            com.sena.creyese.dentvision_spring.repository.PagosRepository.class,
            com.sena.creyese.dentvision_spring.repository.PacienteRepository.class,
            com.sena.creyese.dentvision_spring.repository.ProcedimientoRepository.class,
            com.sena.creyese.dentvision_spring.repository.RolRepository.class,
            com.sena.creyese.dentvision_spring.repository.ServiciosRepository.class,
            com.sena.creyese.dentvision_spring.repository.TecnicoDentalRepository.class,
            com.sena.creyese.dentvision_spring.repository.UsuarioRepository.class
        }))
public class DataConfig {
}
