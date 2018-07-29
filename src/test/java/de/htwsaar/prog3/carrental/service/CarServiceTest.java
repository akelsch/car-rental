package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarServiceTest {
    private CarService carService;

    @BeforeEach
    void setUp() {
        this.carService = new CarService();
    }

    @Test
    void testCreateAndRead() {
        Car expectedCar = new Car();
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
        expectedCar.setNextInspection("04-2010");
        expectedCar.setParkingLot("2A");
        expectedCar.setTires("Summer Tires");
        expectedCar.setVin("1FTJX35G4RKA95915");

        carService.persist(expectedCar);
        Car actualCar = carService.findById(1L);

        assertEquals(expectedCar, actualCar);
    }
}
