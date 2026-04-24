package com.clinic.backend.exception;

public class InvalidPeselException extends RuntimeException {

    public InvalidPeselException() {
        super("Invalid PESEL");
    }

    public InvalidPeselException(String message) {
        super(message);
    }
}
