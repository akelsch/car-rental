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
                .defects("DAMAGE")
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
        robot.write(knownCar.getId().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(1, table.getItems().size());
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testYearEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(1)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getYear().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testBrandEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(2)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getBrand());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testModelEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(3)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getModel());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testTypeEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(4)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getType().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testColorEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(5)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getColor().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testDailyRateEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(6)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getDailyRate()));

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testDoorsEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(7)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getDoors()));

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testTransmissionEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(8)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getTransmission().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testFuelEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(9)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getFuel().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testHorsepowerEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(10)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getHorsepower()));

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testMileageEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(11)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getMileage()));

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testTiresEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(12)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getTires().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testParkingLotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(13)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getParkingLot());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(1, table.getItems().size());
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testLicenseNumberEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(14)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getLicenseNumber());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(1, table.getItems().size());
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testVinEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(15)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getVin());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(1, table.getItems().size());
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testNextInspectionEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(16)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getNextInspection().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testDefectsEqual(FxRobot robot) {
        // TODO test for defects
    }

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
        robot.write(knownCar.getId().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(beforeSize - 1, table.getItems().size());
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testYearNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(1)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getYear().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testBrandNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(2)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getBrand());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testModelNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(3)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getModel());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testTypeNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(4)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getType().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testColorNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(5)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getColor().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testDailyRateNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(6)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getDailyRate()));

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testDoorsNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(7)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getDoors()));

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testTransmissionNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(8)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getTransmission().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testFuelNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(9)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getFuel().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testHorsepowerNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(10)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getHorsepower()));

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testMileageNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(11)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getMileage()));

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testTiresNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(12)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getTires().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testParkingLotNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(13)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getParkingLot());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(beforeSize - 1, table.getItems().size());
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testLicenseNumberNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(14)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getLicenseNumber());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(beforeSize - 1, table.getItems().size());
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testVinNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(15)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getVin());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(beforeSize - 1, table.getItems().size());
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testNextInspectionNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(16)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(1));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getNextInspection().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testDefectsNotEqual(FxRobot robot) {
        // TODO test for defects
    }

    // TODO not equal for all other car attributes & all other operators :D
}
