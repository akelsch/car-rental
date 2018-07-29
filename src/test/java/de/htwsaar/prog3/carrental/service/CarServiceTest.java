package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

class CarServiceTest {
    private CarService carService;
    private Car expectedCar;

    @BeforeEach
    void setUp() {
        this.carService = new CarService();

        this.expectedCar = new Car();
        expectedCar.setDailyRate(120);
        expectedCar.setDoorCount(5);
        expectedCar.setDrivenDistance(25000);
        expectedCar.setHorsepower(303);
        expectedCar.setBrand("BMW");
        expectedCar.setCategory("Sedan");
        expectedCar.setColor("Black");
        expectedCar.setConstructionYear("2016");
        expectedCar.setFuel("Gasoline");
        expectedCar.setGearbox("Manual");
        expectedCar.setLicenseNumber("HD GG 1234");
        expectedCar.setModel("335i");
        expectedCar.setNextInspection("04-2020");
        expectedCar.setParkingLot("2A");
        expectedCar.setTires("Summer Tires");
        expectedCar.setVin("1FTJX35G4RKA95915");

        carService.persist(expectedCar);
    }

    @AfterEach
    void tearDown() {
        carService.remove(expectedCar);
    }

    @Test
    void testRead() {
        Car actualCar = carService.findById(1L);

        assertThat(actualCar, is(equalTo(expectedCar)));
    }

    @Test
    @Disabled("Not working together with testRead(), only on its own")
    void testUpdate() {
        Car actualCar = carService.findById(1L);
        actualCar.setColor("White");
        carService.update(actualCar);

        Car updatedCar = carService.findById(1L);

        assertThat(actualCar, is(equalTo(updatedCar)));
    }
}
