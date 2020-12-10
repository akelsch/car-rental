package de.htwsaar.prog3.carrental;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarRentalApplication {

    public static void main(String[] args) {
        Application.launch(CarRentalUiApplication.class, args);
    }
}
