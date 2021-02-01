package de.htwsaar.prog3.carrental.controller.table;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

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
import org.testfx.util.WaitForAsyncUtils;

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.application.StageInitializer;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.model.car.Color;
import de.htwsaar.prog3.carrental.model.car.Fuel;
import de.htwsaar.prog3.carrental.model.car.Tire;
import de.htwsaar.prog3.carrental.model.car.Transmission;
import de.htwsaar.prog3.carrental.model.car.Type;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import de.htwsaar.prog3.carrental.util.filter.Operator;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@SpringBootTest
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
	private static final int EMPLOYEE_ATTRIBUTE_COMBOBOX = 4;
	private static final int CUSTOMER_ATTRIBUTE_COMBOBOX = 5;
	private static final int EXTRA_COST_ATTRIBUTE_COMBOBOX = 6;
	private static final int NOTE_ATTRIBUTE_COMBOBOX = 7;

	private static Rental knownRental;
	private static Car knownCar;
	private static Employee knownEmployee;
	private static Customer knownCustomer;

	@Autowired
	private StageInitializer stageInitializer;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@BeforeAll
	static void beforeAll(@Autowired RentalRepository rentalRepository, @Autowired CarRepository carRepository,
			@Autowired EmployeeRepository employeeRepository, @Autowired CustomerRepository customerRepository) {
		knownEmployee = employeeRepository
				.save(Employee.builder().firstName("Björn").lastName("Scherer").position("Manager").build());
		knownCustomer = customerRepository.save(Customer.builder().firstName("Bill").lastName("Gates")
				.street("Microsoft, Ave").zipcode(54321).city("Redmond").phone("+12345678900")
				.email("billyg@microsoft.com").dateOfBirth(LocalDate.parse("1955-10-28")).idNumber("ABC123456")
				.driverLicenseNumber("ABCDEFG1234").build());
		knownCar = carRepository.save(Car.builder().brand("BMW").model("M4").type(Type.COUPE).color(Color.WHITE)
				.year(Year.of(2015)).mileage(25_000).transmission(Transmission.MANUAL).fuel(Fuel.PETROL).horsepower(333)
				.doors(3).tires(Tire.SUMMER).nextInspection(YearMonth.from(LocalDate.now().plusMonths(14)))
				.dailyRate(255).licenseNumber("SB XY 123").parkingLot("A23H").vin("1234123412341234X").defects("DAMAGE")
				.build());
		knownRental = rentalRepository.save(Rental.builder().start(LocalDate.now().plusDays(30))
				.end(LocalDate.now().plusDays(37)).car(knownCar).customer(knownCustomer).employee(knownEmployee)
				.extraCosts(300).note("First rental of customer").build());
	}

	@BeforeEach
	void setUp() throws Exception {
		FxToolkit.setupApplication(() -> new TestUiApplication(applicationContext));
		stageInitializer.switchToRentalTableView();
		WaitForAsyncUtils.waitForFxEvents();
	}

	@AfterEach
	void tearDown() throws Exception {
		FxToolkit.cleanupStages();
	}

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
}
