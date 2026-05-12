package com.clinic.backend.exception.enums;

public enum PeselValidationError {
    WRONG_LENGTH,
    NON_NUMERIC,
    WRONG_CHECKSUM,
    INVALID_DATE,
    INVALID_MONTH_CODE
}
