package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.application.StageInitializer;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.model.car.*;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
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
import org.testfx.util.WaitForAsyncUtils;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@ExtendWith(ApplicationExtension.class)
class RentalTableViewControllerSearchIT {

    private static final int EQUAL_OPERATOR_COMBOBOX = 0;
    private static final int NOT_EQUAL_OPERATOR_COMBOBOX = 1;
    private static final int CONTAINS_OPERATOR_COMBOBOX = 2;
    private static final int GREATER_OPERATOR_COMBOBOX = 3;
    private static final int LESS_OPERATOR_COMBOBOX = 4;

    private static final int ID_ATTRIBUTE_COMBOBOX = 0;
    private static final int START_ATTRIBUTE_COMBOBOX = 1;
    private static final int END_ATTRIBUTE_COMBOBOX = 2;
    private static final int CAR_ATTRIBUTE_COMBOBOX = 3;
    private static final int CUSTOMER_ATTRIBUTE_COMBOBOX = 4;
    private static final int EMPLOYEE_ATTRIBUTE_COMBOBOX = 5;
    private static final int EXTRA_COSTS_ATTRIBUTE_COMBOBOX = 6;
    private static final int NOTE_ATTRIBUTE_COMBOBOX = 7;

    private static Rental knownRental;

    @Autowired
    private StageInitializer stageInitializer;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @BeforeAll
    static void beforeAll(@Autowired CarRepository carRepository, @Autowired CustomerRepository customerRepository,
                          @Autowired EmployeeRepository employeeRepository, @Autowired RentalRepository rentalRepository) {
        Car car = carRepository.save(Car.builder()
                .brand("BMW")
                .model("M4")
                .type(Type.COUPE)
                .color(Color.WHITE)
                .year(Year.of(2015))
                .mileage(25_000)
                .transmission(Transmission.MANUAL)
                .fuel(Fuel.PETROL).horsepower(333)
                .doors(3)
                .tires(Tire.SUMMER)
                .nextInspection(YearMonth.from(LocalDate.now().plusMonths(14)))
                .dailyRate(255)
                .licenseNumber("SB XY 123")
                .parkingLot("A23H")
                .vin("1234123412341234X")
                .defects("DAMAGE")
                .build());

        Customer customer = customerRepository.save(Customer.builder()
                .firstName("Bill")
                .lastName("Gates")
                .street("Microsoft, Ave")
                .zipcode(54321)
                .city("Redmond")
                .phone("+12345678900")
                .email("billyg@microsoft.com")
                .dateOfBirth(LocalDate.parse("1955-10-28"))
                .idNumber("ABC123456")
                .driverLicenseNumber("ABCDEFG1234")
                .build());

        Employee employee = employeeRepository.save(Employee.builder()
                .firstName("Björn")
                .lastName("Scherer")
                .position("Manager")
                .build());

        knownRental = rentalRepository.save(Rental.builder()
                .start(LocalDate.now().plusDays(30))
                .end(LocalDate.now().plusDays(37))
                .car(car)
                .customer(customer)
                .employee(employee)
                .extraCosts(300)
                .note("First rental of customer")
                .build());
    }

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.setupApplication(() -> new TestUiApplication(applicationContext));
        WaitForAsyncUtils.asyncFx(() -> stageInitializer.switchToRentalTableView());
        WaitForAsyncUtils.waitForFxEvents();
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    //////////////////////////
    // EQUAL TESTS TO FOLLOW /
    //////////////////////////

    @Test
    void testIdEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getId().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(1, table.getItems().size());
        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testStartEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(START_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getStart().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testEndEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(END_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getEnd().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testCarEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(CAR_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getCar().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testCustomerEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(CUSTOMER_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getCustomer().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testEmployeeEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(EMPLOYEE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getEmployee().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testExtraCostsEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(EXTRA_COSTS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownRental.getExtraCosts()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testNoteEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(NOTE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getNote());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    //////////////////////////////
    // NOT EQUAL TESTS TO FOLLOW /
    //////////////////////////////

    @Test
    void testIdNotEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getId().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownRental));
    }

    @Test
    void testStartNotEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(START_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getStart().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertFalse(table.getItems().contains(knownRental));
    }

    @Test
    void testEndNotEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(END_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getEnd().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertFalse(table.getItems().contains(knownRental));
    }

    @Test
    void testCarNotEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(CAR_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getCar().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertFalse(table.getItems().contains(knownRental));
    }

    @Test
    void testCustomerNotEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(CUSTOMER_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getCustomer().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertFalse(table.getItems().contains(knownRental));
    }

    @Test
    void testEmployeeNotEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(EMPLOYEE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getEmployee().toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertFalse(table.getItems().contains(knownRental));
    }

    @Test
    void testExtraCostsNotEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(EXTRA_COSTS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(String.valueOf(knownRental.getExtraCosts()));

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertFalse(table.getItems().contains(knownRental));
    }

    @Test
    void testNoteNotEqual(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(NOTE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownRental.getNote());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertFalse(table.getItems().contains(knownRental));
    }

    /////////////////////////////
    // CONTAINS TESTS TO FOLLOW /
    /////////////////////////////

    @Test
    void testNoteContains(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(NOTE_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write("First");

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    ////////////////////////////
    // GREATER TESTS TO FOLLOW /
    ////////////////////////////

    @Test
    void testIdGreater(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write("40");

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testStartGreater(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(START_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(LocalDate.now().plusDays(29).toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testEndGreater(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(END_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(LocalDate.now().plusDays(35).toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testExtraCostsGreater(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(EXTRA_COSTS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write("250");

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    /////////////////////////
    // LESS TESTS TO FOLLOW /
    /////////////////////////

    @Test
    void testIdLess(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write("60");

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testStartLess(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(START_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(LocalDate.now().plusDays(36).toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testEndLess(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(END_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(LocalDate.now().plusDays(40).toString());

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }

    @Test
    void testExtraCostsLess(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();

        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(EXTRA_COSTS_ATTRIBUTE_COMBOBOX));

        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write("400");

        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().contains(knownRental));
    }
}
