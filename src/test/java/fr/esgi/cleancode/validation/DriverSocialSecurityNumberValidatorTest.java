package fr.esgi.cleancode.validation;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DriverSocialSecurityNumberValidatorTest {

    @Test
    public void should_not_be_null() {
        final var validator = new DriverSocialSecurityNumberValidator();

        var e = assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> validator.validate(null));
        assertEquals("Driver social security number should not be null", e.getMessage());
    }

    @Test
    public void should_contain_only_numbers() {
        final var validator = new DriverSocialSecurityNumberValidator();

        var e = assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> validator.validate("123abc"));
        assertEquals("Driver social security number should only contains numbers", e.getMessage());
    }

    @Test
    public void should_equal_15_characters() {
        final var validator = new DriverSocialSecurityNumberValidator();

        var e_under_15 = assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> validator.validate("123"));
        assertEquals("Driver social security number should be 15 characters long", e_under_15.getMessage());

        var e_above_15 = assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> validator.validate("123456789101112131415"));
        assertEquals("Driver social security number should be 15 characters long", e_above_15.getMessage());
    }

    @Test
    public void should_be_valid() {
        final var validator = new DriverSocialSecurityNumberValidator();

        assertDoesNotThrow(() -> validator.validate("123456789012345"));
    }
}
