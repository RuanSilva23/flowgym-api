package com.ruan.workout.infra.exception;

public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}
