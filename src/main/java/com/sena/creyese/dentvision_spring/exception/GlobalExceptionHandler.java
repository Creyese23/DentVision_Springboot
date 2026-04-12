package com.sena.creyese.dentvision_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manejador global de excepciones para el sistema DentVision.
 * 
 * Esta clase centraliza el manejo de excepciones en toda la aplicación,
 * proporcionando respuestas HTTP consistentes y estructuradas para diferentes
 * tipos de errores. Utiliza la anotación @ControllerAdvice para interceptar
 * excepciones en todos los controladores de la aplicación.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Manejo centralizado de excepciones
 * - Respuestas HTTP estructuradas
 * - Validación de datos de entrada
 * - Manejo de recursos no encontrados
 * - Formato consistente de errores
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo RecursoNoEncontrado.
     * 
     * Este método captura excepciones lanzadas cuando un recurso solicitado
     * no existe en el sistema y retorna una respuesta HTTP 404 (Not Found)
     * con un mensaje de error descriptivo.
     * 
     * @param ex Excepción de recurso no encontrado
     * @return ResponseEntity con código 404 y detalles del error
     */
    @ExceptionHandler(RecursoNoEncontrado.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            RecursoNoEncontrado ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    /**
     * Maneja excepciones de validación de datos de entrada.
     * 
     * Este método captura excepciones lanzadas cuando los datos de entrada
     * en las peticiones HTTP no cumplen con las reglas de validación
     * definidas con anotaciones @Valid. Retorna una respuesta HTTP 400
     * (Bad Request) con el primer mensaje de error encontrado.
     * 
     * @param ex Excepción de validación de método
     * @return ResponseEntity con código 400 y detalles del error de validación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getAllErrors()
                .get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(msg, 400));
    }
}
