package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrivingLicencePointRemoverServiceTest {
    @InjectMocks
    private DrivingLicencePointRemoverService service;

    @Mock
    private InMemoryDatabase database;

    @Test
    void should_throw_exception_if_not_found() {
        var drivingLicence = DrivingLicence.builder().build();

        when(database.findById(drivingLicence.getId())).thenReturn(Optional.empty());

        var e = assertThrows(
                ResourceNotFoundException.class,
                () -> service.removePoints(drivingLicence.getId(), 1)
        );

        assertEquals("Driving Licence not found", e.getMessage());
        verify(database).findById(drivingLicence.getId());
        verifyNoMoreInteractions(database);
    }

    @Test
    void should_remove_points() {
        var drivingLicenceId = UUID.randomUUID();
        var drivingLicence = DrivingLicence.builder().id(drivingLicenceId).build();
        var modifiedDrivingLicence = drivingLicence.withAvailablePoints(10);

        when(database.findById(drivingLicenceId)).thenReturn(Optional.of(drivingLicence));
        when(database.save(drivingLicenceId, modifiedDrivingLicence)).thenReturn(modifiedDrivingLicence);

        var actual = service.removePoints(drivingLicenceId, 2);

        assertEquals(10, actual.getAvailablePoints());
        verify(database).findById(drivingLicenceId);
        verify(database).save(drivingLicenceId, modifiedDrivingLicence);
        verifyNoMoreInteractions(database);
    }
}
