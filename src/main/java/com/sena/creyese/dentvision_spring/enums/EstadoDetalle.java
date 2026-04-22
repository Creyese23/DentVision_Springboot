package com.sena.creyese.dentvision_spring.enums;

/**
 * Enumeración que define los estados posibles de un usuario en el sistema DentVision.
 * 
 * Esta enumeración representa los dos estados en los que puede encontrarse un usuario
 * dentro del sistema, proporcionando un control más estricto y semánticamente claro
 * que el uso de valores booleanos.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Estados disponibles:
 * - ACTIVO: Usuario habilitado para usar el sistema
 * - INACTIVO: Usuario deshabilitado temporal o permanentemente
 * 
 * Cada estado define claramente la condición del usuario y sus permisos
 * de acceso al sistema.
 */
public enum EstadoDetalle {
    
    /**
     * Estado PENDIENTE del usuario.
     * 
     * Características:
     * - Puede iniciar sesión en el sistema
     * - Tiene acceso a todas las funcionalidades según su rol
     * - Puede realizar operaciones dentro de sus permisos
     * - Visible en búsquedas y listados de usuarios activos
     */
    PENDIENTE,
    
    /**
     * Estado COMPLETADO del usuario.
     * 
     * Características:
     * - No puede iniciar sesión en el sistema
     * - Sus permisos están suspendidos temporal o permanentemente
     * - No aparece en búsquedas de usuarios activos
     * - Puede ser reactivado por un administrador
     */
    COMPLETADO,

    /**
     * Estado COMPLETADO del usuario.
     *
     * Características:
     * - No puede iniciar sesión en el sistema
     * - Sus permisos están suspendidos temporal o permanentemente
     * - No aparece en búsquedas de usuarios activos
     * - Puede ser reactivado por un administrador
     */
    FINALIZADO,

    /**
     * Estado COMPLETADO del usuario.
     *
     * Características:
     * - No puede iniciar sesión en el sistema
     * - Sus permisos están suspendidos temporal o permanentemente
     * - No aparece en búsquedas de usuarios activos
     * - Puede ser reactivado por un administrador
     */
    RECHAZADA
}
