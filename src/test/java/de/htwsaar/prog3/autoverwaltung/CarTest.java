package de.htwsaar.prog3.autoverwaltung;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCarColorAndManufacturer() {
        Car car = new Car(Color.BLACK, Manufacturer.BMW);

        assertEquals(Color.BLACK, car.getColor());
        assertEquals(Manufacturer.BMW, car.getManufacturer());
    }
}
