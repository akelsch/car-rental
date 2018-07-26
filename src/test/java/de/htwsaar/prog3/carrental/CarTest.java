package de.htwsaar.prog3.carrental;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarTest.class);

    private Car testCar;

    @BeforeEach
    void setUp() {
        LOGGER.info("Hi from setUp!");

        testCar = new Car(Color.BLACK, Manufacturer.BMW);
    }

    @AfterEach
    void tearDown() {
        LOGGER.info("Hi from tearDown!");
    }

    @Test
    void testCarColor() {
        LOGGER.info("Hi from testCarColor!");

        assertEquals(Color.BLACK, testCar.getColor());
    }

    @Test
    void testCarManufacturer() {
        LOGGER.info("Hi from testCarManufacturer!");

        assertEquals(Manufacturer.BMW, testCar.getManufacturer());
    }
}
