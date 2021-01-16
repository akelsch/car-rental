package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.TestUtils;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.car.*;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.util.filter.Operator;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class CarTableViewControllerSearchIT {

    private static Car knownCar;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @BeforeAll
    static void beforeAll(@Autowired CarRepository carRepository) {
        knownCar = carRepository.save(Car.builder()
                .brand("BMW")
                .model("M4")
                .type(Type.COUPE)
                .color(Color.WHITE)
                .year(Year.of(2015))
                .mileage(25_000)
                .transmission(Transmission.MANUAL)
                .fuel(Fuel.PETROL)
                .horsepower(333)
                .doors(3)
                .tires(Tire.SUMMER)
                .nextInspection(YearMonth.from(LocalDate.now().plusMonths(14)))
                .dailyRate(255)
                .licenseNumber("SB XY 123")
                .parkingLot("A23H")
                .vin("1234123412341234X")
                .build());
    }

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.setupApplication(() -> new TestUiApplication(applicationContext));
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void testIdEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(0)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.type(TestUtils.toKeyCode(knownCar.getId().toString()));

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(1, table.getItems().size());
        assertTrue(table.getItems().contains(knownCar));
    }

    // TODO equal for all other car attributes

    @Test
    void testIdNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(0));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.type(TestUtils.toKeyCode(knownCar.getId().toString()));

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(beforeSize - 1, table.getItems().size());
        assertFalse(table.getItems().contains(knownCar));
    }

    // TODO not equal for all other car attributes & all other operators :D
}
