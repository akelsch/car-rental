package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.model.Car;
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
 * Unit tests for the {@link CarService} class.
 *
 * @author Arthur Kelsch
 */
class CarServiceTest {
    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService();
    }

    @AfterEach
    void tearDown() {
        EntityManagerUtil.closeEntityManagerFactory();
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
    	/*
    	 * OLBERTZ Die beiden createTestCar-Methoden sind gut. Ich haette mir noch 
    	 * eine weitere Methode geschrieben, die die Liste mit den erwarteten Autos erzeugt. 
    	 * Dann waere die Testmethode noch etwas entschlackt. Inzwischen schreibe ich mir
    	 * ganz gerne eine TestAPI, die diese ganzen Erzeugungsmethoden von Testdaten
    	 * beinhaltet. Das ist eine Menge von Klassen, die diese ganzen Testdaten erzeugt. 
    	 * Somit ist all das dann noch einmal aus den Testklassen raus und diese werden 
    	 * uebersichtlicher.
    	 */
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

    /*
     * OLBERTZ Hier sollte man unbedingt fuer den Test der Datenbanken ein
     * Framework wie das von mir vorgestellte DBUnit verwenden. Das ermoeglicht
     * ein wesentlich umfangreicheres Testen bei Datenbanken.
     */
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

    @Test
    void testFilter() {
        Car car1 = createTestCar1();
        carService.persist(car1);

        Car car2 = createTestCar2();
        carService.persist(car2);

        List<Car> expectedCars = new ArrayList<>();
        expectedCars.add(car1);

        List<Car> actualCars = carService.filter("brand", "=", "BMW");

        assertThat(actualCars, is(equalTo(expectedCars)));
    }

    static Car createTestCar1() {
        Car car = new Car();
        car.setBrand("BMW");
        car.setCategory("Sedan");
        car.setColor("Black");
        car.setConstructionYear(2016);
        car.setDailyRate(120);
        car.setDoorCount(5);
        car.setDrivenDistance(25000);
        car.setFuel("Gasoline");
        car.setGearbox("Manual");
        car.setHorsepower(303);
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
        car.setBrand("Mercedes");
        car.setCategory("Coupe");
        car.setColor("Grey");
        car.setConstructionYear(2017);
        car.setDailyRate(150);
        car.setDoorCount(3);
        car.setDrivenDistance(10000);
        car.setFuel("Gasoline");
        car.setGearbox("Automatic");
        car.setHorsepower(333);
        car.setLicenseNumber("SB AB 2018");
        car.setModel("C 400");
        car.setNextInspection("08-2021");
        car.setParkingLot("2C");
        car.setTires("Summer Tires");
        car.setVin("JKAEXEA128A066488");

        return car;
    }
}
