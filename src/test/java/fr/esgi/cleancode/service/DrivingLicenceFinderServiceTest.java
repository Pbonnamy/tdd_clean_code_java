package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceFinderServiceTest {

    @InjectMocks
    private DrivingLicenceFinderService service;

    @Mock
    private InMemoryDatabase database;

    @Test
    void should_find() {

    }

    @Test
    void should_not_find() {
        var id = new DrivingLicenceIdGenerationService().generateNewDrivingLicenceId();
        var actual = service.findById(id);

        assertTrue(actual.isEmpty());
    }
}