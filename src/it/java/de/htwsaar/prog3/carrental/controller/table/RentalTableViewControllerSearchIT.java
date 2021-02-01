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

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class RentalTableViewControllerSearchIT {

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
		knownRental = rentalRepository.save(
				Rental.builder().start(LocalDate.now().plusDays(30)).end(LocalDate.now().plusDays(37)).extraCosts(300)
						.note("").employee(knownEmployee).customer(knownCustomer).car(knownCar).build());
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

}
