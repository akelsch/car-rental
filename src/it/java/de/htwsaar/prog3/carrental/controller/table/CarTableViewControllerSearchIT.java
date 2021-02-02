package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.TestUiApplication;
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
import org.springframework.test.annotation.DirtiesContext;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@ExtendWith(ApplicationExtension.class)
class CarTableViewControllerSearchIT {
    private static final int EQUAL_OPERATOR_COMBOBOX = 0;
    private static final int NOT_EQUAL_OPERATOR_COMBOBOX = 1;
    private static final int CONTAINS_OPERATOR_COMBOBOX = 2;
    private static final int GREATER_OPERATOR_COMBOBOX = 3;
    private static final int LESS_OPERATOR_COMBOBOX = 4;

    private static final int ID_ATTRIBUTE_COMBOBOX = 0;
    private static final int YEAR_ATTRIBUTE_COMBOBOX = 1;
    private static final int BRAND_ATTRIBUTE_COMBOBOX = 2;
    private static final int MODEL_ATTRIBUTE_COMBOBOX = 3;
    private static final int TYPE_ATTRIBUTE_COMBOBOX = 4;
    private static final int COLOR_ATTRIBUTE_COMBOBOX = 5;
    private static final int DAILY_RATE_ATTRIBUTE_COMBOBOX = 6;
    private static final int DOORS_ATTRIBUTE_COMBOBOX = 7;
    private static final int TRANSMISSION_ATTRIBUTE_COMBOBOX = 8;
    private static final int FUEL_ATTRIBUTE_COMBOBOX = 9;
    private static final int HORSEPOWER_ATTRIBUTE_COMBOBOX = 10;
    private static final int MILEAGE_ATTRIBUTE_COMBOBOX = 11;
    private static final int TIRES_ATTRIBUTE_COMBOBOX = 12;
    private static final int PARKING_LOT_ATTRIBUTE_COMBOBOX = 13;
    private static final int LICENCE_NUMBER_ATTRIBUTE_COMBOBOX = 14;
    private static final int VIN_ATTRIBUTE_COMBOBOX = 15;
    private static final int NEXT_INSPECTION_ATTRIBUTE_COMBOBOX = 16;
    private static final int DEFECTS_ATTRIBUTE_COMBOBOX = 17;

    private static Car knownCar;
    private static Car otherKnownCar;

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

        otherKnownCar = carRepository.save(Car.builder()
                .brand("Tesla")
                .model("Cybertruck")
                .type(Type.SUV)
                .color(Color.BLACK)
                .year(Year.of(2020))
                .mileage(50_000)
                .transmission(Transmission.AUTOMATIC)
                .fuel(Fuel.DIESEL)
                .horsepower(555)
                .doors(5)
                .tires(Tire.ALL_SEASON)
                .nextInspection(YearMonth.from(LocalDate.now().plusMonths(17)))
                .dailyRate(396)
                .licenseNumber("SB CT 9999")
                .parkingLot("CT99")
                .vin("4567456745674567X")
                .defects("Too Fast")
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
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

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

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(YEAR_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getYear().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testBrandEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(BRAND_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getBrand());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testModelEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(MODEL_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getModel());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testTypeEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(TYPE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getType().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testColorEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(COLOR_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getColor().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testDailyRateEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DAILY_RATE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getDailyRate()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testDoorsEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DOORS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getDoors()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testTransmissionEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(TRANSMISSION_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getTransmission().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testFuelEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(FUEL_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getFuel().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testHorsepowerEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(HORSEPOWER_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getHorsepower()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testMileageEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(MILEAGE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getMileage()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testTiresEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(TIRES_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getTires().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testParkingLotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(PARKING_LOT_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getParkingLot());

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

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(LICENCE_NUMBER_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getLicenseNumber());

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

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(VIN_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getVin());

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

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(NEXT_INSPECTION_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getNextInspection().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testDefectsEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DEFECTS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getDefects());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testIdNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getId().toString());

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

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(YEAR_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getYear().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testBrandNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(BRAND_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getBrand());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testModelNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(MODEL_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getModel());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testTypeNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(TYPE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getType().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testColorNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(COLOR_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getColor().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testDailyRateNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DAILY_RATE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getDailyRate()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testDoorsNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DOORS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getDoors()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testTransmissionNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(TRANSMISSION_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getTransmission().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testFuelNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(FUEL_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getFuel().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testHorsepowerNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(HORSEPOWER_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getHorsepower()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testMileageNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(MILEAGE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getMileage()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testTiresNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(TIRES_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getTires().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testParkingLotNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(PARKING_LOT_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getParkingLot());

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

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(LICENCE_NUMBER_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getLicenseNumber());

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

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(VIN_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getVin());

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

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(NEXT_INSPECTION_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getNextInspection().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testDefectsNotEqual(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DEFECTS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getDefects());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
    }

    @Test
    void testBrandContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(BRAND_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getBrand().substring(1, 2));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
        assertFalse(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testModelContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(MODEL_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getModel().charAt(0));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
        assertFalse(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testTypeContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(TYPE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getType().toString().substring(1, 2));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
        assertFalse(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testColorContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(COLOR_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getColor().toString().substring(1, 2));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
        assertFalse(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testTransmissionContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(TRANSMISSION_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getTransmission().toString().substring(0, 3));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
        assertFalse(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testFuelContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(FUEL_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getFuel().toString().substring(1, 4));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
        assertFalse(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testTiresContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(TIRES_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getTires().toString().substring(1, 4));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
        assertFalse(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testParkingLotContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(PARKING_LOT_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getParkingLot().substring(2, 3));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
        assertFalse(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testLicenseNumberContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(LICENCE_NUMBER_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getLicenseNumber().substring(1, 6));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
        assertFalse(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testVinContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(VIN_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getVin().substring(1, 3));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
        assertFalse(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testDefectsContains(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DEFECTS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getDefects().substring(1, 3));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testIdGreaterThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getId().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
        assertTrue(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testYearGreaterThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(YEAR_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getYear().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
        assertTrue(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testDailyRateGreaterThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DAILY_RATE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getDailyRate()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
        assertTrue(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testDoorsGreaterThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DOORS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getDoors()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
        assertTrue(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testHorsepowerGreaterThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(HORSEPOWER_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getHorsepower()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
        assertTrue(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testMileageGreaterThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(MILEAGE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownCar.getMileage()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
        assertTrue(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testNextInspectionGreaterThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(NEXT_INSPECTION_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownCar.getNextInspection().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownCar));
        assertTrue(table.getItems().contains(otherKnownCar));
    }

    @Test
    void testIdLessThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(otherKnownCar.getId().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(otherKnownCar));
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testYearLessThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(YEAR_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(otherKnownCar.getYear().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(otherKnownCar));
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testDailyRateLessThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DAILY_RATE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(otherKnownCar.getDailyRate()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(otherKnownCar));
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testDoorsLessThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(DOORS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(otherKnownCar.getDoors()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(otherKnownCar));
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testHorsepowerLessThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(HORSEPOWER_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(otherKnownCar.getHorsepower()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(otherKnownCar));
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testMileageLessThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(MILEAGE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(otherKnownCar.getMileage()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(otherKnownCar));
        assertTrue(table.getItems().contains(knownCar));
    }

    @Test
    void testNextInspectionLessThan(FxRobot robot) {
        TableView<Car> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(NEXT_INSPECTION_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(otherKnownCar.getNextInspection().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(otherKnownCar));
        assertTrue(table.getItems().contains(knownCar));
    }
}
