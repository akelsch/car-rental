package de.htwsaar.prog3.carrental;

import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class CarRentalUiApplicationIT {

    private TestUiApplication application;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp() throws Exception {
        application = new TestUiApplication(applicationContext);
        FxToolkit.setupApplication(() -> application);
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void testStart() throws Exception {
        Stage primaryStage = FxToolkit.registerPrimaryStage();
        assertNotNull(primaryStage);
    }

    @Test
    void testStop() {
        application.stop();
        verify(application.getTestApplicationContext()).close();
    }

}
