package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import fr.esgi.cleancode.validation.DriverSocialSecurityNumberValidator;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceGenerationService {

    private final InMemoryDatabase database;
    private final DriverSocialSecurityNumberValidator validator;

    public void create(UUID drivingLicenceId, String driverSocialSecurityNumber) {

        validator.validate(driverSocialSecurityNumber);

        final var drivingLicence = DrivingLicence.builder()
                .id(drivingLicenceId)
                .driverSocialSecurityNumber(driverSocialSecurityNumber)
                .build();

        database.save(drivingLicenceId, drivingLicence);
    }
}