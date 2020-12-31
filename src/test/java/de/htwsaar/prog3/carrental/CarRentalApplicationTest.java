package de.htwsaar.prog3.carrental;

import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.mockStatic;

class CarRentalApplicationTest {

    @Test
    void testMain() {
        try (MockedStatic<Application> mocked = mockStatic(Application.class)) {
            CarRentalApplication.main(new String[]{});
            mocked.verify(() -> Application.launch(CarRentalUiApplication.class));
        }
    }
}
