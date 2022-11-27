package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import fr.esgi.cleancode.validation.DrivingLicenceValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrivingLicenceCreatorService {

    private final InMemoryDatabase database;
    private final DrivingLicenceValidator validator = new DrivingLicenceValidator();

    public DrivingLicence create(DrivingLicence drivingLicence) {

        validator.validate(drivingLicence);

        return database.save(drivingLicence.getId(), drivingLicence);
    }
}
