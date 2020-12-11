package de.htwsaar.prog3.carrental;

import de.htwsaar.prog3.carrental.application.StageReadyEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * JavaFX application class, builds the Spring application context.
 *
 * @author Lukas Raubuch
 * @author Arthur Kelsch
 * @author Jens Thewes
 * @see CarRentalApplication
 */
public class CarRentalUiApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder()
                .sources(CarRentalApplication.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) {
        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        applicationContext.close();
    }
}
