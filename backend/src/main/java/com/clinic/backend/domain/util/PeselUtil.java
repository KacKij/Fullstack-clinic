package com.clinic.backend.domain.util;

import com.clinic.backend.domain.enums.Gender;
import com.clinic.backend.exception.InvalidPeselException;
import com.clinic.backend.exception.enums.PeselValidationError;

import java.time.DateTimeException;
import java.time.LocalDate;

public final class PeselUtil {

    private PeselUtil() {
    }

    public static void validateOrThrow(String pesel) {
        if (pesel == null || pesel.length() != 11) {
            throw new InvalidPeselException(PeselValidationError.WRONG_LENGTH);
        }

        if (!pesel.matches("\\d{11}")) {
            throw new InvalidPeselException(PeselValidationError.NON_NUMERIC);
        }

        if (!hasValidChecksum(pesel)) {
            throw new InvalidPeselException(PeselValidationError.WRONG_CHECKSUM);
        }

        extractDateOfBirth(pesel);
    }

    public static LocalDate extractDateOfBirth(String pesel) {

        try {
            int year = Integer.parseInt(pesel.substring(0, 2));
            int month = Integer.parseInt(pesel.substring(2, 4));
            int day = Integer.parseInt(pesel.substring(4, 6));

            int century;

            if (month >= 1 && month <= 12) {
                century = 1900;
            } else if (month >= 21 && month <= 32) {
                century = 2000;
                month -= 20;
            } else if (month >= 41 && month <= 52) {
                century = 2100;
                month -= 40;
            } else if (month >= 61 && month <= 72) {
                century = 2200;
                month -= 60;
            } else if (month >= 81 && month <= 92) {
                century = 1800;
                month -= 80;
            } else {
                throw new InvalidPeselException(PeselValidationError.INVALID_DATE);
            }

            return LocalDate.of(century + year, month, day);

        } catch (DateTimeException | NumberFormatException ex) {
            throw new InvalidPeselException(PeselValidationError.INVALID_DATE);
        }
    }

    public static Gender extractGender(String pesel) {
        int digit = Character.getNumericValue(pesel.charAt(9));
        return digit % 2 == 0 ? Gender.FEMALE : Gender.MALE;
    }

    public static boolean hasValidChecksum(String pesel) {
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

        int sum = 0;

        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(pesel.charAt(i));
            sum += digit * weights[i];
        }

        int checksum = (10 - (sum % 10)) % 10;
        int controlDigit = Character.getNumericValue(pesel.charAt(10));

        return checksum == controlDigit;
    }
}
