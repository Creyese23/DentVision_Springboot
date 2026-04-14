package com.sena.creyese.dentvision_spring.exception;

/**
 * Excepción personalizada para indicar que un recurso solicitado no fue encontrado.
 * 
 * Esta clase representa una excepción de tipo RuntimeException que se lanza
 * cuando un recurso específico (paciente, odontólogo, cita, etc.) no existe
 * en la base de datos. Es capturada por el GlobalExceptionHandler para
 * retornar una respuesta HTTP 404 (Not Found) con un mensaje descriptivo.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Características:
 * - Hereda de RuntimeException para no requerir manejo obligatorio
 * - Proporciona mensajes descriptivos de recursos no encontrados
 * - Es manejada automáticamente por el GlobalExceptionHandler
 * - Genera respuestas HTTP 404 consistentes
 * 
 * Ejemplo de uso:
 * <pre>
 * if (paciente.isEmpty()) {
 *     throw new RecursoNoEncontrado("Paciente con ID " + id + " no encontrado");
 * }
 * </pre>
 */
public class RecursoNoEncontrado extends RuntimeException {
    
    /**
     * Constructor que inicializa la excepción con un mensaje descriptivo.
     * 
     * @param mensaje Mensaje descriptivo del recurso no encontrado
     */
    public RecursoNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
