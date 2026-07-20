package com.ruan.workout.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationPesoException.class)
    public ResponseEntity<String> tratarErroValidacaoPeso(ValidationPesoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationExercicioException.class)
    public ResponseEntity<String> tratarErroValidacaoExercicio(ValidationExercicioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
