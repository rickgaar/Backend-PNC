package com.pnc.backend.exceptions;

public class MateriaNotFoundException extends RuntimeException {
    public MateriaNotFoundException(String message) {
        super(message);
    }
}
