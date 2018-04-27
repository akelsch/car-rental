package de.htwsaar.prog3.autoverwaltung;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTests {
    private Car car;

    @BeforeAll
    void setUp() {
    }

    @AfterAll
    void tearDown() {
    }

    @Test
    void testCarColorManufacturer() {
        car = new Car(Color.BLACK, Manufacturer.BMW);

        assertEquals(Color.BLACK, car.getColor());
        assertEquals(Manufacturer.BMW, car.getManufacturer());
    }
}
