package com.sena.creyese.dentvision_spring.enums;

/**
 * Enumeración que define los tipos de roles disponibles en el sistema DentVision.
 * 
 * Esta enumeración representa los diferentes roles que pueden tener los usuarios
 * en el sistema de gestión dental. Cada rol define los permisos y accesos
 * que tendrá el usuario dentro de la aplicación.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Roles disponibles:
 * - ADMIN: Administrador del sistema con acceso completo
 * - ODONTOLOGO: Profesional dental con acceso a pacientes y tratamientos
 * - TECNICO_DENTAL: Técnico especializado en laboratorio dental
 * - AUXILIAR_ADMIN: Personal administrativo de apoyo
 * - PACIENTE: Usuario final que recibe los servicios dentales
 * 
 * Cada rol está diseñado para proporcionar el nivel adecuado de acceso
 * según las responsabilidades del usuario en el sistema.
 */
public enum TipoRol {
    
    /**
     * Rol de Administrador del sistema.
     * 
     * Permisos:
     * - Acceso completo a todas las funcionalidades
     * - Gestión de usuarios y roles
     * - Configuración del sistema
     * - Reportes y estadísticas completas
     * - Gestión de clínicas y sucursales
     */
    ADMIN,
    
    /**
     * Rol de Odontólogo.
     * 
     * Permisos:
     * - Gestión de pacientes asignados
     * - Creación y edición de tratamientos
     * - Consulta de historial clínico
     * - Programación de citas
     * - Prescripción de medicamentos
     * - Generación de órdenes de trabajo
     */
    ODONTOLOGO,
    
    /**
     * Rol de Técnico Dental.
     * 
     * Permisos:
     * - Gestión de órdenes de trabajo asignadas
     * - Actualización de estados de trabajos
     * - Gestión de inventario de laboratorio
     * - Subida de imágenes de diseños
     * - Registro de entregas
     * - Consulta de especificaciones técnicas
     */
    TECNICO_DENTAL,
    
    /**
     * Rol de Auxiliar Administrativo.
     * 
     * Permisos:
     * - Gestión de citas y agenda
     * - Registro de pacientes básico
     * - Facturación y pagos
     * - Comunicación con pacientes
     * - Gestión de notificaciones
     * - Reportes básicos de operaciones
     */
    AUXILIAR_ADMIN,
    
    /**
     * Rol de Paciente.
     * 
     * Permisos:
     * - Consulta de información personal
     * - Visualización de historial clínico
     * - Programación de citas propias
     * - Consulta de facturas y pagos
     * - Comunicación con la clínica
     * - Acceso a portal de paciente
     */
    PACIENTE
}
