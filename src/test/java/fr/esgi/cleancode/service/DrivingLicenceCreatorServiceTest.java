package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceCreatorServiceTest {

    @Mock private InMemoryDatabase database;

    @InjectMocks private DrivingLicenceCreatorService service;

    @Test
    void should_create() {
        var drivingLicence = DrivingLicence.builder().driverSocialSecurityNumber("123456789012345").build();

        when(database.save(drivingLicence.getId() ,drivingLicence)).thenReturn(drivingLicence);

        var actual = service.create(drivingLicence);

        assertThat(actual).isEqualTo(drivingLicence);
        verify(database).save(drivingLicence.getId() ,drivingLicence);
        verifyNoMoreInteractions(database);
    }

    @Test
    void should_not_create() {
        var drivingLicence = DrivingLicence.builder().driverSocialSecurityNumber(null).build();

        assertThrows(
                InvalidDriverSocialSecurityNumberException.class,
                () -> service.create(drivingLicence)
        );

        verifyNoInteractions(database);
    }
}
