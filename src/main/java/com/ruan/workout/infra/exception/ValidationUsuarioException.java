package com.ruan.workout.infra.exception;

public class ValidationUsuarioException extends RuntimeException{
    public ValidationUsuarioException(String message) {
        super(message);
    }
}
