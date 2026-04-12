package com.sena.creyese.dentvision_spring.exception;

/**
 * Clase que representa una respuesta de error estandarizada para el sistema DentVision.
 * 
 * Esta clase encapsula la información de error que se retorna al cliente cuando
 * ocurre una excepción en la API REST. Proporciona un formato consistente
 * para todas las respuestas de error, incluyendo mensaje descriptivo, código
 * de estado HTTP y timestamp del momento en que ocurrió el error.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Estructura de respuesta:
 * - mensaje: Descripción detallada del error ocurrido
 * - status: Código de estado HTTP correspondiente al error
 * - timestamp: Fecha y hora en que ocurrió el error (formato ISO 8601)
 * 
 * Ejemplo de uso:
 * <pre>
 * ErrorResponse error = new ErrorResponse("Recurso no encontrado", 404);
 * </pre>
 */
public class ErrorResponse {
    
    /** Mensaje descriptivo del error */
    private String mensaje;
    
    /** Código de estado HTTP del error */
    private int status;
    
    /** Timestamp del momento en que ocurrió el error */
    private String timestamp;
    
    /**
     * Constructor que inicializa una respuesta de error.
     * 
     * @param mensaje Mensaje descriptivo del error
     * @param status Código de estado HTTP del error
     */
    public ErrorResponse(String mensaje, int status) {
        this.mensaje = mensaje;
        this.status = status;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    /**
     * Obtiene el mensaje descriptivo del error.
     * 
     * @return Mensaje del error
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje descriptivo del error.
     * 
     * @param mensaje Mensaje del error a establecer
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el código de estado HTTP del error.
     * 
     * @return Código de estado HTTP
     */
    public int getStatus() {
        return status;
    }

    /**
     * Establece el código de estado HTTP del error.
     * 
     * @param status Código de estado HTTP a establecer
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Obtiene el timestamp del momento en que ocurrió el error.
     * 
     * @return Timestamp en formato String
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Establece el timestamp del momento en que ocurrió el error.
     * 
     * @param timestamp Timestamp a establecer
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
