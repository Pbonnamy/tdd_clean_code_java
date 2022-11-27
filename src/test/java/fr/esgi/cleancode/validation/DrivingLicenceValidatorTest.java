package fr.esgi.cleancode.validation;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrivingLicenceValidatorTest {

    @Test
    void should_not_be_null() {
        final var validator = new DrivingLicenceValidator();

        var e = assertThrows(
                InvalidDriverSocialSecurityNumberException.class,
                () -> validator.validate(DrivingLicence.builder().driverSocialSecurityNumber(null).build())
        );

        assertEquals("Driver social security number should not be null", e.getMessage());
    }

    @Test
    void should_contain_only_numbers() {
        final var validator = new DrivingLicenceValidator();

        var e = assertThrows(
                InvalidDriverSocialSecurityNumberException.class,
                () -> validator.validate(DrivingLicence.builder().driverSocialSecurityNumber("123abc").build())
        );
        assertEquals("Driver social security number should only contains numbers", e.getMessage());
    }

    @Test
    void should_equal_15_characters() {
        final var validator = new DrivingLicenceValidator();

        var e_under_15 = assertThrows(
                InvalidDriverSocialSecurityNumberException.class,
                () -> validator.validate(DrivingLicence.builder().driverSocialSecurityNumber("123").build())
        );
        assertEquals("Driver social security number should be 15 characters long", e_under_15.getMessage());

        var e_above_15 = assertThrows(
                InvalidDriverSocialSecurityNumberException.class,
                () -> validator.validate(DrivingLicence.builder().driverSocialSecurityNumber("123456789101112131415").build())
        );
        assertEquals("Driver social security number should be 15 characters long", e_above_15.getMessage());
    }

    @Test
    void should_be_valid() {
        final var validator = new DrivingLicenceValidator();

        assertDoesNotThrow(() -> validator.validate(DrivingLicence.builder().driverSocialSecurityNumber("123456789012345").build()));
    }
}
