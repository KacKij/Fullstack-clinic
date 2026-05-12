package com.clinic.backend.exception;

import com.clinic.backend.exception.enums.PeselValidationError;

public class InvalidPeselException extends RuntimeException {

    private final PeselValidationError error;

    public InvalidPeselException(PeselValidationError error) {
        super(error.toString());
        this.error = error;
    }

    public PeselValidationError getError() {
        return error;
    }
}
