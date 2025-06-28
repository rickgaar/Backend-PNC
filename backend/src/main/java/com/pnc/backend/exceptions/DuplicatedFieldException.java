package com.pnc.backend.exceptions;

public class DuplicatedFieldException extends RuntimeException {
    public DuplicatedFieldException(String message) {
        super(message);
    }
}
