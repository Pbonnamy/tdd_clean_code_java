package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.validation.DriverSocialSecurityNumberValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DrivingLicenceGenerationServiceTest {

    @Mock
    private InMemoryDatabase database = InMemoryDatabase.getInstance();

    @InjectMocks
    private DrivingLicenceGenerationService service = new DrivingLicenceGenerationService(database, new DriverSocialSecurityNumberValidator());

    @Test
    void should_create() {
        var driver_social_security_number = "123456789012345";
        var actual = service.create(driver_social_security_number);

        assertTrue(database.findById(actual.getId()).isPresent());
    }
}
