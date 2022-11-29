package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicencePointRemoverService {

    private final InMemoryDatabase database;

    public DrivingLicence removePoints(UUID drivingLicenceId, int pointsToRemove) {
        final var drivingLicence = database.findById(drivingLicenceId);

        if (drivingLicence.isEmpty()) {
            throw new ResourceNotFoundException("Driving Licence not found");
        } else {
            final var updatedDrivingLicence = drivingLicence.get().withAvailablePoints(drivingLicence.get().getAvailablePoints() - pointsToRemove);
            return database.save(drivingLicenceId, updatedDrivingLicence);
        }
    }
}
