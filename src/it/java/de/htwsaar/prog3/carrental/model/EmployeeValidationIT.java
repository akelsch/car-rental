package de.htwsaar.prog3.carrental.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmployeeValidationIT {

    @Autowired
    private Validator validator;

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        testEmployee = Employee.builder()
                .firstName("Spike")
                .lastName("Spiegel")
                .position("CEO")
                .build();
    }

    @Test
    void testFirstNameNotBlank() {
        testEmployee.setFirstName(" ");

        Set<ConstraintViolation<Employee>> violations = validator.validate(testEmployee);
        assertEquals(1, violations.size());

        ConstraintViolation<Employee> violation = violations.stream().findFirst().get();
        assertEquals("firstName", violation.getPropertyPath().toString());
        assertEquals("{validation.employee.first-name}", violation.getMessageTemplate());
    }

    @Test
    void testLastNameNotBlank() {
        testEmployee.setLastName(" ");

        Set<ConstraintViolation<Employee>> violations = validator.validate(testEmployee);
        assertEquals(1, violations.size());

        ConstraintViolation<Employee> violation = violations.stream().findFirst().get();
        assertEquals("lastName", violation.getPropertyPath().toString());
        assertEquals("{validation.employee.last-name}", violation.getMessageTemplate());
    }

    @Test
    void testPositionNotBlank() {
        testEmployee.setPosition(" ");

        Set<ConstraintViolation<Employee>> violations = validator.validate(testEmployee);
        assertEquals(1, violations.size());

        ConstraintViolation<Employee> violation = violations.stream().findFirst().get();
        assertEquals("position", violation.getPropertyPath().toString());
        assertEquals("{validation.employee.position}", violation.getMessageTemplate());
    }
}
