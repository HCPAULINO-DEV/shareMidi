package com.projects.my.shareMidi_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<ExceptionDto> handleCategoriaNaoEncontrada(CategoriaNaoEncontradaException e) {
        var exception = new ExceptionDto(
                HttpStatus.NOT_FOUND.value(),  // Código numérico 404
                "NOT_FOUND",                   // Tipo de erro
                e.getMessage()                 // Mensagem da exceção
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    @ExceptionHandler(CategoriaJaExistenteException.class)
    public ResponseEntity<ExceptionDto> handleCategoriaJaExistente(CategoriaJaExistenteException e) {
        var exception = new ExceptionDto(
                HttpStatus.BAD_REQUEST.value(),  // Código numérico 400
                "BAD_REQUEST",                   // Tipo de erro
                e.getMessage()                   // Mensagem da exceção
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    // Catch-all handler for other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGeneralException(Exception e) {
        var exception = new ExceptionDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),  // Código numérico 500
                "INTERNAL_SERVER_ERROR",                    // Tipo de erro
                "Ocorreu um erro interno no servidor."      // Mensagem genérica para erro interno
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
    }

    public record ExceptionDto(int errorCode, String error, String message) {}
}
