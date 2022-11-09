package fr.esgi.cleancode.validation;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;

public class DriverSocialSecurityNumberValidator {
    public void validate(String driverSocialSecurityNumber) {
        if (driverSocialSecurityNumber == null) {
            throw new InvalidDriverSocialSecurityNumberException("Driver social security number should not be null");
        }

        if (!driverSocialSecurityNumber.matches("\\d+")) {
            throw new InvalidDriverSocialSecurityNumberException("Driver social security number should only contains numbers");
        }

        if (driverSocialSecurityNumber.length() != 15) {
            throw new InvalidDriverSocialSecurityNumberException("Driver social security number should be 15 characters long");
        }
    }
}
