package fr.esgi.cleancode.validation;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;

public class DrivingLicenceValidator {
    public void validate(DrivingLicence drivingLicence) {
        final var socialSecurityNumber = drivingLicence.getDriverSocialSecurityNumber();

        if (socialSecurityNumber == null) {
            throw new InvalidDriverSocialSecurityNumberException("Driver social security number should not be null");
        }

        if (!socialSecurityNumber.matches("\\d+")) {
            throw new InvalidDriverSocialSecurityNumberException("Driver social security number should only contains numbers");
        }

        if (socialSecurityNumber.length() != 15) {
            throw new InvalidDriverSocialSecurityNumberException("Driver social security number should be 15 characters long");
        }
    }
}
