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
class RentalValidationIT {

    @Autowired
    private Validator validator;

    private Rental testRental;

    @BeforeEach
    void setUp() {
        Customer testCustomer = Customer.builder()
                .firstName("Rainer")
                .lastName("Korn")
                .dateOfBirth(LocalDate.of(1990, 5, 7))
                .email("rainer.zufall@mail.de")
                .phone("+49420694242")
                .idNumber("12EAS6789")
                .driverLicenseNumber("1O9876SAE21")
                .zipcode(52431)
                .city("Zufallsberg")
                .street("Kornstrasse")
                .build();

        Car testCar = Car.builder()
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

        Employee testEmployee = Employee.builder()
                .firstName("Spike")
                .lastName("Spiegel")
                .position("CEO")
                .build();

        testRental = Rental.builder()
                .start(LocalDate.now().plusDays(1))
                .end(LocalDate.now().plusDays(3))
                .car(testCar)
                .customer(testCustomer)
                .employee(testEmployee)
                .extraCosts(0)
                .build();
    }

    @Test
    void testStartFutureOrPresent() {
        testRental.setStart(LocalDate.now().minusDays(1));

        Set<ConstraintViolation<Rental>> violations = validator.validate(testRental);
        assertEquals(1, violations.size());

        ConstraintViolation<Rental> violation = violations.stream().findFirst().get();
        assertEquals("start", violation.getPropertyPath().toString());
        assertEquals("{validation.rental.start.future}", violation.getMessageTemplate());
    }

    @Test
    void testStartNotNull() {
        testRental.setStart(null);

        Set<ConstraintViolation<Rental>> violations = validator.validate(testRental);
        assertEquals(1, violations.size());

        ConstraintViolation<Rental> violation = violations.stream().findFirst().get();
        assertEquals("start", violation.getPropertyPath().toString());
        assertEquals("{validation.rental.start.null}", violation.getMessageTemplate());
    }

    @Test
    void testEndFuture() {
        testRental.setEnd(LocalDate.now());

        Set<ConstraintViolation<Rental>> violations = validator.validate(testRental);
        assertEquals(1, violations.size());

        ConstraintViolation<Rental> violation = violations.stream().findFirst().get();
        assertEquals("end", violation.getPropertyPath().toString());
        assertEquals("{validation.rental.end.future}", violation.getMessageTemplate());
    }

    @Test
    void testEndNotNull() {
        testRental.setEnd(null);

        Set<ConstraintViolation<Rental>> violations = validator.validate(testRental);
        assertEquals(1, violations.size());

        ConstraintViolation<Rental> violation = violations.stream().findFirst().get();
        assertEquals("end", violation.getPropertyPath().toString());
        assertEquals("{validation.rental.end.null}", violation.getMessageTemplate());
    }

    @Test
    void testCarNotNull() {
        testRental.setCar(null);

        Set<ConstraintViolation<Rental>> violations = validator.validate(testRental);
        assertEquals(1, violations.size());

        ConstraintViolation<Rental> violation = violations.stream().findFirst().get();
        assertEquals("car", violation.getPropertyPath().toString());
        assertEquals("{validation.rental.car}", violation.getMessageTemplate());
    }

    @Test
    void testCustomerNotNull() {
        testRental.setCustomer(null);

        Set<ConstraintViolation<Rental>> violations = validator.validate(testRental);
        assertEquals(1, violations.size());

        ConstraintViolation<Rental> violation = violations.stream().findFirst().get();
        assertEquals("customer", violation.getPropertyPath().toString());
        assertEquals("{validation.rental.customer}", violation.getMessageTemplate());
    }

    @Test
    void testEmployeeNotNull() {
        testRental.setEmployee(null);

        Set<ConstraintViolation<Rental>> violations = validator.validate(testRental);
        assertEquals(1, violations.size());

        ConstraintViolation<Rental> violation = violations.stream().findFirst().get();
        assertEquals("employee", violation.getPropertyPath().toString());
        assertEquals("{validation.rental.employee}", violation.getMessageTemplate());
    }

    @Test
    void testExtraCostsPositiveOrZero() {
        testRental.setExtraCosts(-1);

        Set<ConstraintViolation<Rental>> violations = validator.validate(testRental);
        assertEquals(1, violations.size());

        ConstraintViolation<Rental> violation = violations.stream().findFirst().get();
        assertEquals("extraCosts", violation.getPropertyPath().toString());
        assertEquals("{validation.rental.extra-costs}", violation.getMessageTemplate());
    }
}
