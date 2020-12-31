package de.htwsaar.prog3.carrental;

import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarRentalUiApplicationIT {

    private TestUiApplication application;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp() throws Exception {
        if (application == null) {
            application = new TestUiApplication(applicationContext);
            FxToolkit.setupApplication(() -> application);
        }
    }

    @Test
    @Order(1)
    void testStart() throws Exception {
        Stage primaryStage = FxToolkit.registerPrimaryStage();
        assertNotNull(primaryStage);
    }

    @Test
    @Order(2)
    void testStop() {
        application.stop();
        verify(application.getTestApplicationContext()).close();
    }

}
