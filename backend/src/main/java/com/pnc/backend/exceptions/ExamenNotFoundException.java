package com.pnc.backend.exceptions;

public class ExamenNotFoundException extends RuntimeException {
    public ExamenNotFoundException(String message) {
        super(message);
    }
}
