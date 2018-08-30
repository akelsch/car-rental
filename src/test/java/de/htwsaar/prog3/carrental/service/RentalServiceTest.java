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

    private static Car car;
    private static Customer customer;
    private static Employee employee;

    @BeforeEach
    void setUp() {
        CarService carService = new CarService();
        car = CarServiceTest.createTestCar1();
        carService.persist(car);

        CustomerService customerService = new CustomerService();
        customer = CustomerServiceTest.createTestCustomer1();
        customerService.persist(customer);

        EmployeeService employeeService = new EmployeeService();
        employee = EmployeeServiceTest.createTestEmployee1();
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

        Rental actualRental = rentalService.findById(expectedRental.getId());
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

    private static Rental createTestRental1() {
        Rental rental = new Rental();
        rental.setBegin("01.01.1970");
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setEmployee(employee);
        rental.setEnd("19.01.2038");

        return rental;
    }

    private static Rental createTestRental2() {
        Rental rental = new Rental();
        rental.setBegin("05.06.2010");
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setEmployee(employee);
        rental.setEnd("14.10.2011");

        return rental;
    }
}
