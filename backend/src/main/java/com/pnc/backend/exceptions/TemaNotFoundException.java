package com.pnc.backend.exceptions;

public class TemaNotFoundException extends RuntimeException {
    public TemaNotFoundException(String message) {
        super(message);
    }
}
