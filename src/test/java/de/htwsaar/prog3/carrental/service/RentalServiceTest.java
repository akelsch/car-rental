package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the {@link RentalService} class.
 *
 * @author Julian Quint
 */
class RentalServiceTest {
    private RentalService rentalService;
    private static Car rentedCar;
    private static Customer customer;
    private static Employee employee;

    @BeforeEach
    void setUp() {
        CarService carService = new CarService();
        rentedCar = createCar();
        carService.persist(rentedCar);

        CustomerService customerService = new CustomerService();
        customer = createCustomer();
        customerService.persist(customer);

        EmployeeService employeeService = new EmployeeService();
        employee = createEmployee();
        employeeService.persist(employee);

        rentalService = new RentalService();
    }

    @AfterEach
    void tearDown() {
        EntityManagerUtil.closeEntityManagerFactory();
    }

    @Test
    void testFindById() {
        Rental expectedRental = createTestRental1();
        rentalService.persist(expectedRental);

        Rental actualRental = rentalService.findById(expectedRental.getId());

        assertThat(actualRental, is(equalTo(expectedRental)));
    }

    @Test
    void testFindAll() {
        Rental rental1 = createTestRental1();
        rentalService.persist(rental1);

        Rental rental2 = createTestRental2();
        rentalService.persist(rental2);

        List<Rental> expectedRentals = new ArrayList<>();
        expectedRentals.add(rental1);
        expectedRentals.add(rental2);

        List<Rental> actualRentals = rentalService.findAll();

        assertThat(actualRentals, is(equalTo(expectedRentals)));
    }

    @Test
    void testUpdate() {
        Rental expectedRental = createTestRental1();
        rentalService.persist(expectedRental);

        expectedRental.setExtraCosts("24");

        Rental actualRental = rentalService.findById(expectedRental.getId());
        actualRental.setExtraCosts("24");
        rentalService.update(actualRental);

        actualRental = rentalService.findById(expectedRental.getId());

        assertThat(actualRental, is(equalTo(expectedRental)));
    }

    @Test
    void testDelete() {
        Rental rental1 = createTestRental1();
        rentalService.persist(rental1);

        Rental rental2 = createTestRental2();
        rentalService.persist(rental2);

        List<Rental> actualRentals = rentalService.findAll();

        assertThat(actualRentals.size(), is(equalTo(2)));

        rentalService.delete(rental1);

        actualRentals = rentalService.findAll();

        assertThat(actualRentals.size(), is(equalTo(1)));
    }

    @Test
    void testDeleteById() {
        Rental rental1 = createTestRental1();
        rentalService.persist(rental1);

        Rental rental2 = createTestRental2();
        rentalService.persist(rental2);

        List<Rental> actualRentals = rentalService.findAll();

        assertThat(actualRentals.size(), is(equalTo(2)));

        rentalService.deleteById(rental1.getId());

        actualRentals = rentalService.findAll();

        assertThat(actualRentals.size(), is(equalTo(1)));
    }

    @Test
    void testDeleteAll() {
        Rental rental1 = createTestRental1();
        rentalService.persist(rental1);

        Rental rental2 = createTestRental2();
        rentalService.persist(rental2);

        List<Rental> actualRentals = rentalService.findAll();

        assertThat(actualRentals.size(), is(equalTo(2)));

        rentalService.deleteAll();

        actualRentals = rentalService.findAll();

        assertThat(actualRentals.size(), is(equalTo(0)));
    }

    private static Car createCar() {
        return new Car("Nissan", "Coupe", "Blue", "2017", 250, null, 3, 6000, null, "Gasoline", "Automatic", 570,
                "N ISM 0", "GT-R", "10-2019", "2C", "Summer Tires", "1G2ZF57B584174326");
    }

    private static Customer createCustomer() {
        return new Customer("Saarbrücken", "01.01.1970", "31415926535", "wbraun@htwsaar.de", "Wolfgang", "141",
                "4077722104D580209241090826", "Braun", "+492718281828", "Otto-Hahn Straße", 66111);
    }

    private static Employee createEmployee() {
        return new Employee("Elon", "Musk", "CEO");
    }

    private static Rental createTestRental1() {
        return new Rental("01.01.1970", rentedCar, customer, employee, "19.01.2038", "42", null);
    }

    private static Rental createTestRental2() {
        return new Rental("05.06.2010", rentedCar, customer, employee, "14.10.2011", "42", null);
    }
}
