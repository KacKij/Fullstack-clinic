package com.clinic.backend.domain.util;

import com.clinic.backend.exception.InvalidPeselException;
import com.clinic.backend.exception.enums.PeselValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.*;

class PeselUtilTest {


    @Nested
    class ValidateOrThrowTests {

        @Test
        void shouldAcceptValidPesel() {

            String validPesel = "22222222222";

            assertThatCode(() -> PeselUtil.validateOrThrow(validPesel))
                    .doesNotThrowAnyException();
        }

        @Test
        void shouldThrowWrongLengthForNullPesel() {
            assertThatThrownBy(() -> PeselUtil.validateOrThrow(null))
                    .isInstanceOfSatisfying(
                            InvalidPeselException.class,
                            ex -> assertThat(ex.getError())
                                    .isEqualTo(PeselValidationError.WRONG_LENGTH)
                            );
        }

        @Test
        void shouldThrowWrongLengthForTooShortPesel() {

            String shortPesel = "1234567890";

            assertThatThrownBy(() -> PeselUtil.validateOrThrow(shortPesel))
                    .isInstanceOfSatisfying(
                            InvalidPeselException.class,
                            ex -> assertThat(ex.getError())
                                    .isEqualTo(PeselValidationError.WRONG_LENGTH)
                    );
        }

        @Test
        void shouldThrowWrongLengthForTooLongPesel() {

            String longPesel = "123456789012";

            assertThatThrownBy(() -> PeselUtil.validateOrThrow(longPesel))
                    .isInstanceOfSatisfying(
                            InvalidPeselException.class,
                            ex -> assertThat(ex.getError())
                                    .isEqualTo(PeselValidationError.WRONG_LENGTH)
                    );
        }

        @Test
        void shouldThrowNonNumericForLetters() {

            String contaminatedPesel = "123c5s78901";

            assertThatThrownBy(() -> PeselUtil.validateOrThrow(contaminatedPesel))
                    .isInstanceOfSatisfying(
                            InvalidPeselException.class,
                            ex -> assertThat(ex.getError())
                                    .isEqualTo(PeselValidationError.NON_NUMERIC)
                    );
        }

        @Test
        void shouldThrowWrongChecksum() {

            String invalidChecksumPesel = "11111111111";

            assertThatThrownBy(() -> PeselUtil.validateOrThrow(invalidChecksumPesel))
                    .isInstanceOfSatisfying(
                            InvalidPeselException.class,
                            ex -> assertThat(ex.getError())
                                    .isEqualTo(PeselValidationError.WRONG_CHECKSUM)
                    );
        }

        @Test
        void shouldThrowInvalidDate() {

            
        }
    }

    @Nested
    class ExtractDateOfBirthTests {}

    @Nested
    class ExtractGenderTests {}
}
