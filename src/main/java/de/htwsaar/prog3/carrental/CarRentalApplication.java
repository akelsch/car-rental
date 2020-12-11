package de.htwsaar.prog3.carrental;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot application class & application entry point.
 *
 * @author Arthur Kelsch
 */
@SpringBootApplication
public class CarRentalApplication {

    public static void main(String[] args) {
        Application.launch(CarRentalUiApplication.class, args);
    }
}
