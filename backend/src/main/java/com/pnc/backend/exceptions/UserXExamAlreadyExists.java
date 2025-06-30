package com.pnc.backend.exceptions;

public class UserXExamAlreadyExists extends RuntimeException {
    public UserXExamAlreadyExists(String message) {
        super(message);
    }
}
