package de.htwsaar.prog3.carrental.service;


import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Costumer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 *
 * @author Julian Quint
 */
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

        CostumerService costumerService = new CostumerService();
        Costumer costumer = new Costumer();
        costumer.setDateOfBirth("01.01.1970");
        costumer.setDriverLicenseId("31415926535");
        costumer.setEmailAddress("wbraun@htwsaar.de");
        costumer.setHouseNumber("141");
        costumer.setName("Braun");
        costumer.setPhoneNumber("+492718281828");
        costumer.setResidence("Saarbrücken");
        costumer.setStreet("Otto-Hahn Straße");
        costumer.setSurname("Wolfgang");
        costumerService.persist(costumer);

        EmployeeService employeeService = new EmployeeService();
        Employee employee = new Employee();
        employee.setName("Elon");
        employee.setSurname("Musk");
        employee.setPosition("CEO");
        employeeService.persist(employee);

        this.rentalService = new RentalService();
        this.expectedRental = new Rental();

        expectedRental.setCar(rentedCar);
        expectedRental.setCostumer(costumer);
        expectedRental.setEmployee(employee);
        expectedRental.setExtraCosts("42");
        expectedRental.setNote("a");
        expectedRental.setBegin("01.01.1970");
        expectedRental.setEnd("19.01.2038");

        rentalService.persist(expectedRental);
    }

    @AfterEach
    void tearDown() {
        rentalService.remove(expectedRental);
    }

    @Test
    void testRead() {
        Rental actualCar = rentalService.findById(4L);

        assertThat(actualCar, is(equalTo(expectedRental)));
    }
}
