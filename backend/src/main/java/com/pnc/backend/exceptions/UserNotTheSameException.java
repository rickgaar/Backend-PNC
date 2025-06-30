package com.pnc.backend.exceptions;

public class UserNotTheSameException extends RuntimeException {
    public UserNotTheSameException(String message) {
        super(message);
    }
}
