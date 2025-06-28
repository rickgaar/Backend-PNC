package com.pnc.backend.exceptions;

public class RespuestaNotFoundException extends RuntimeException {
    public RespuestaNotFoundException(String message) {
        super(message);
    }
}
