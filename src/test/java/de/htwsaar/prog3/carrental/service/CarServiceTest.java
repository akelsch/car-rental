package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the {@link CarService} class.
 *
 * @author Arthur Kelsch
 */
class CarServiceTest {
    private CarService carService;

    @BeforeEach
    void setUp() {
        this.carService = new CarService();
    }

    @Test
    void testFindById() {
        // Persist a car
        Car expectedCar = createTestCar1();
        carService.persist(expectedCar);

        // Fetch it from the database
        Car actualCar = carService.findById(expectedCar.getId());

        // Assert that the two cars are identical
        assertThat(actualCar, is(equalTo(expectedCar)));
    }

    @Test
    void testFindAll() {
        // Persist two cars
        Car car1 = createTestCar1();
        carService.persist(car1);

        Car car2 = createTestCar2();
        carService.persist(car2);

        // Add them to a list
        List<Car> expectedCars = new ArrayList<>();
        expectedCars.add(car1);
        expectedCars.add(car2);

        // Fetch them from the database
        List<Car> actualCars = carService.findAll();

        // Assert that the two lists are identical
        assertThat(actualCars, is(equalTo(expectedCars)));
    }

    @Test
    void testUpdate() {
        // Persist a car
        Car expectedCar = createTestCar1();
        carService.persist(expectedCar);

        // Update the persisted car
        Car actualCar = carService.findById(expectedCar.getId());
        actualCar.setColor("White");
        carService.update(actualCar);

        // Fetch it from the database
        Car updatedCar = carService.findById(expectedCar.getId());

        // Assert that the new car got updated
        assertThat(actualCar, is(equalTo(updatedCar)));
    }

    @Test
    void testDelete() {
        // Persist two cars
        Car car1 = createTestCar1();
        carService.persist(car1);

        Car car2 = createTestCar2();
        carService.persist(car2);

        // Fetch them from the database
        List<Car> actualCars = carService.findAll();

        // Assert that there are two cars in the database
        assertThat(actualCars.size(), is(equalTo(2)));

        // Delete one car
        carService.delete(car1);

        // Fetch all cars from the database again
        actualCars = carService.findAll();

        // Assert that only one car is left in the database
        assertThat(actualCars.size(), is(equalTo(1)));
    }

    @Test
    void testDeleteById() {
        // Persist two cars
        Car car1 = createTestCar1();
        carService.persist(car1);

        Car car2 = createTestCar2();
        carService.persist(car2);

        // Fetch them from the database
        List<Car> actualCars = carService.findAll();

        // Assert that there are two cars in the database
        assertThat(actualCars.size(), is(equalTo(2)));

        // Delete one car
        carService.deleteById(car1.getId());

        // Fetch all cars from the database again
        actualCars = carService.findAll();

        // Assert that only one car is left in the database
        assertThat(actualCars.size(), is(equalTo(1)));
    }

    @Test
    void testDeleteAll() {
        // Persist two cars
        Car car1 = createTestCar1();
        carService.persist(car1);

        Car car2 = createTestCar2();
        carService.persist(car2);

        // Fetch them from the database
        List<Car> actualCars = carService.findAll();

        // Assert that there are two cars in the database
        assertThat(actualCars.size(), is(equalTo(2)));

        // Delete all cars
        carService.deleteAll();

        // Fetch all cars from the database again
        actualCars = carService.findAll();

        // Assert that there is no car left in the database
        assertThat(actualCars.size(), is(equalTo(0)));
    }

    private static Car createTestCar1() {
        Car car = new Car();
        car.setDailyRate(120);
        car.setDoorCount(5);
        car.setDrivenDistance(25000);
        car.setHorsepower(303);
        car.setBrand("BMW");
        car.setCategory("Sedan");
        car.setColor("Black");
        car.setConstructionYear("2016");
        car.setFuel("Gasoline");
        car.setGearbox("Manual");
        car.setLicenseNumber("HD GG 1234");
        car.setModel("335i");
        car.setNextInspection("04-2020");
        car.setParkingLot("2A");
        car.setTires("Summer Tires");
        car.setVin("1FTJX35G4RKA95915");

        return car;
    }

    private static Car createTestCar2() {
        Car car = new Car();
        car.setDailyRate(150);
        car.setDoorCount(3);
        car.setDrivenDistance(10000);
        car.setHorsepower(333);
        car.setBrand("Mercedes");
        car.setCategory("Coupe");
        car.setColor("Grey");
        car.setConstructionYear("2017");
        car.setFuel("Gasoline");
        car.setGearbox("Automatic");
        car.setLicenseNumber("SB AB 2018");
        car.setModel("C 400");
        car.setNextInspection("08-2021");
        car.setParkingLot("2C");
        car.setTires("Summer Tires");
        car.setVin("JKAEXEA128A066488");

        return car;
    }
}
