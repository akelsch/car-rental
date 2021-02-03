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
import java.util.ArrayList;
import java.util.Comparator;
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

    @Test
    void testYearPastOrPresent() {
        testCar.setYear(Year.now().plusYears(1));

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("year", violation.getPropertyPath().toString());
        assertEquals("{validation.car.year.past}", violation.getMessageTemplate());
    }

    @Test
    void testBrandNotBlank() {
        testCar.setBrand(null);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("brand", violation.getPropertyPath().toString());
        assertEquals("{validation.car.brand}", violation.getMessageTemplate());
    }

    @Test
    void testModelNotBlank() {
        testCar.setModel(" ");

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("model", violation.getPropertyPath().toString());
        assertEquals("{validation.car.model}", violation.getMessageTemplate());
    }

    @Test
    void testTypeNotNull() {
        testCar.setType(null);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("type", violation.getPropertyPath().toString());
        assertEquals("{validation.car.type}", violation.getMessageTemplate());
    }

    @Test
    void testColorNotNull() {
        testCar.setColor(null);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("color", violation.getPropertyPath().toString());
        assertEquals("{validation.car.color}", violation.getMessageTemplate());
    }

    @Test
    void testDailyRatePositive() {
        testCar.setDailyRate(0);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("dailyRate", violation.getPropertyPath().toString());
        assertEquals("{validation.car.daily-rate}", violation.getMessageTemplate());
    }

    @Test
    void testDoorsPositive() {
        testCar.setDoors(-1);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("doors", violation.getPropertyPath().toString());
        assertEquals("{validation.car.doors}", violation.getMessageTemplate());
    }

    @Test
    void testTransmissionNotNull() {
        testCar.setTransmission(null);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("transmission", violation.getPropertyPath().toString());
        assertEquals("{validation.car.transmission}", violation.getMessageTemplate());
    }

    @Test
    void testFuelNotNull() {
        testCar.setFuel(null);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("fuel", violation.getPropertyPath().toString());
        assertEquals("{validation.car.fuel}", violation.getMessageTemplate());
    }

    @Test
    void testHorsepowerPositive() {
        testCar.setHorsepower(0);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("horsepower", violation.getPropertyPath().toString());
        assertEquals("{validation.car.horsepower}", violation.getMessageTemplate());
    }

    @Test
    void testMileagePositive() {
        testCar.setMileage(0);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("mileage", violation.getPropertyPath().toString());
        assertEquals("{validation.car.mileage}", violation.getMessageTemplate());
    }

    @Test
    void testTiresNotNull() {
        testCar.setTires(null);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("tires", violation.getPropertyPath().toString());
        assertEquals("{validation.car.tires}", violation.getMessageTemplate());
    }

    @Test
    void testParkingLotNotBlank() {
        testCar.setParkingLot("\t");

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("parkingLot", violation.getPropertyPath().toString());
        assertEquals("{validation.car.parking-lot}", violation.getMessageTemplate());
    }

    @Test
    void testLicenseNumberNotBlankAndPattern() {
        testCar.setLicenseNumber("");

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(2, violations.size());

        ArrayList<ConstraintViolation<Car>> violationsList = new ArrayList<>(violations);
        violationsList.sort(Comparator.comparing(ConstraintViolation::getMessageTemplate));

        ConstraintViolation<Car> notBlankViolation = violationsList.get(0);
        assertEquals("licenseNumber", notBlankViolation.getPropertyPath().toString());
        assertEquals("{validation.car.license-number.blank}", notBlankViolation.getMessageTemplate());

        ConstraintViolation<Car> patternViolation = violationsList.get(1);
        assertEquals("licenseNumber", patternViolation.getPropertyPath().toString());
        assertEquals("{validation.car.license-number.pattern}", patternViolation.getMessageTemplate());
    }

    @Test
    void testVinNotBlankAndPattern() {
        testCar.setVin("");

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(2, violations.size());

        ArrayList<ConstraintViolation<Car>> violationsList = new ArrayList<>(violations);
        violationsList.sort(Comparator.comparing(ConstraintViolation::getMessageTemplate));

        ConstraintViolation<Car> notBlankViolation = violationsList.get(0);
        assertEquals("vin", notBlankViolation.getPropertyPath().toString());
        assertEquals("{validation.car.vin.blank}", notBlankViolation.getMessageTemplate());

        ConstraintViolation<Car> patternViolation = violationsList.get(1);
        assertEquals("vin", patternViolation.getPropertyPath().toString());
        assertEquals("{validation.car.vin.pattern}", patternViolation.getMessageTemplate());
    }

    @Test
    void testNextInspectionNotNull() {
        testCar.setNextInspection(null);

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("nextInspection", violation.getPropertyPath().toString());
        assertEquals("{validation.car.next-inspection.null}", violation.getMessageTemplate());
    }

    @Test
    void testNextInspectionFutureOrPresent() {
        testCar.setNextInspection(YearMonth.now().minusMonths(1));

        Set<ConstraintViolation<Car>> violations = validator.validate(testCar);
        assertEquals(1, violations.size());

        ConstraintViolation<Car> violation = violations.stream().findFirst().get();
        assertEquals("nextInspection", violation.getPropertyPath().toString());
        assertEquals("{validation.car.next-inspection.future}", violation.getMessageTemplate());
    }
}
