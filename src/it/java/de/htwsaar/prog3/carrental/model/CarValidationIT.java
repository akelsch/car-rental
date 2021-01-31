package de.htwsaar.prog3.carrental.model;

import de.htwsaar.prog3.carrental.model.car.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CarValidationIT {

    @Autowired
    private Validator validator;

    private Car testCar;

    @BeforeEach
    void setUp() {
        testCar = Car.builder()
                .brand("BMW")
                .model("M4")
                .type(Type.COUPE)
                .color(Color.WHITE)
                .year(Year.of(2015))
                .mileage(25_000)
                .transmission(Transmission.MANUAL)
                .fuel(Fuel.PETROL)
                .horsepower(333)
                .doors(3)
                .tires(Tire.SUMMER)
                .nextInspection(YearMonth.from(LocalDate.now().plusMonths(14)))
                .dailyRate(255)
                .licenseNumber("SB XY 123")
                .parkingLot("A23H")
                .vin("1234123412341234X")
                .defects("DAMAGE")
                .build();
    }

    @Test
    void testYearNotNull() {
        testCar.setYear(null);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("year", violation.getPropertyPath().toString());
        assertEquals("{validation.car.year.null}", violation.getMessageTemplate());
    }
}
