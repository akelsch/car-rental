package de.htwsaar.prog3.carrental.service;


import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Julian Quint
 */
@Disabled
class RentalServiceTest {
    private RentalService rentalService;
    private Rental expectedRental;

    @BeforeEach
    void setUp() {
        CarService carService = new CarService();
        Car rentedCar = new Car();
        rentedCar.setDailyRate(120);
        rentedCar.setDoorCount(5);
        rentedCar.setDrivenDistance(25000);
        rentedCar.setHorsepower(303);
        rentedCar.setBrand("BMW");
        rentedCar.setCategory("Sedan");
        rentedCar.setColor("Black");
        rentedCar.setConstructionYear("2016");
        rentedCar.setFuel("Gasoline");
        rentedCar.setGearbox("Manual");
        rentedCar.setLicenseNumber("HD GG 1234");
        rentedCar.setModel("335i");
        rentedCar.setNextInspection("04-2020");
        rentedCar.setParkingLot("2A");
        rentedCar.setTires("Summer Tires");
        rentedCar.setVin("1FTJX35G4RKA95915");
        carService.persist(rentedCar);

        CustomerService customerService = new CustomerService();
        Customer customer = new Customer();
        customer.setDateOfBirth("01.01.1970");
        customer.setDriverLicenseId("31415926535");
        customer.setEmailAddress("wbraun@htwsaar.de");
        customer.setHouseNumber("141");
        customer.setFirstName("Wolfgang");
        customer.setPhoneNumber("+492718281828");
        customer.setResidence("Saarbrücken");
        customer.setStreet("Otto-Hahn Straße");
        customer.setLastName("Braun");
        customerService.persist(customer);

        EmployeeService employeeService = new EmployeeService();
        Employee employee = new Employee();
        employee.setFirstName("Elon");
        employee.setLastName("Musk");
        employee.setPosition("CEO");
        employeeService.persist(employee);

        this.rentalService = new RentalService();
        this.expectedRental = new Rental();

        expectedRental.setCar(rentedCar);
        expectedRental.setCustomer(customer);
        expectedRental.setEmployee(employee);
        expectedRental.setExtraCosts("42");
        expectedRental.setNote("a");
        expectedRental.setBegin("01.01.1970");
        expectedRental.setEnd("19.01.2038");

        rentalService.persist(expectedRental);
    }

    @Test
    void testRead() {
        Rental actualRental = rentalService.findById(1L);

        assertThat(actualRental, is(equalTo(expectedRental)));
    }
}
