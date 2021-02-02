package de.htwsaar.prog3.carrental.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerValidationIT {

    @Autowired
    private Validator validator;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = Customer.builder()
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
    }

    @Test
    void testFirstNameNotBlank() {
        testCustomer.setFirstName(" ");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.stream().findFirst().get();
        assertEquals("firstName", violation.getPropertyPath().toString());
        assertEquals("{validation.customer.first-name}", violation.getMessageTemplate());
    }

    @Test
    void testLastNameNotBlank() {
        testCustomer.setLastName(" ");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.stream().findFirst().get();
        assertEquals("lastName", violation.getPropertyPath().toString());
        assertEquals("{validation.customer.last-name}", violation.getMessageTemplate());
    }

    @Test
    void testStreetNotBlank() {
        testCustomer.setStreet(" ");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.stream().findFirst().get();
        assertEquals("street", violation.getPropertyPath().toString());
        assertEquals("{validation.customer.street}", violation.getMessageTemplate());
    }

    @Test
    void testZipCodeLessFiveDigits() {
        testCustomer.setZipcode(4444);

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.stream().findFirst().get();
        assertEquals("zipcode", violation.getPropertyPath().toString());
        assertEquals("{validation.customer.zipcode}", violation.getMessageTemplate());
    }

    @Test
    void testZipCodeMoreFiveDigits() {
        testCustomer.setZipcode(666666);

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.stream().findFirst().get();
        assertEquals("zipcode", violation.getPropertyPath().toString());
        assertEquals("{validation.customer.zipcode}", violation.getMessageTemplate());
    }


    @Test
    void testCityNotBlank() {
        testCustomer.setCity(" ");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.stream().findFirst().get();
        assertEquals("city", violation.getPropertyPath().toString());
        assertEquals("{validation.customer.city}", violation.getMessageTemplate());
    }

    @Test
    void testPhoneNotBlank() {
        testCustomer.setPhone(" ");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(2, violations.size());

        ArrayList<ConstraintViolation<Customer>> violationsList = new ArrayList<>(violations);
        violationsList.sort(Comparator.comparing(ConstraintViolation::getMessageTemplate));

        ConstraintViolation<Customer> notBlankViolation = violationsList.get(0);
        assertEquals("phone", notBlankViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.phone.blank}", notBlankViolation.getMessageTemplate());

        ConstraintViolation<Customer> patternViolation = violationsList.get(1);
        assertEquals("phone", patternViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.phone.pattern}", patternViolation.getMessageTemplate());
    }

    @Test
    void testPhoneNotPattern() {
        testCustomer.setPhone("9871218426");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.stream().findFirst().get();
        assertEquals("phone", violation.getPropertyPath().toString());
        assertEquals("{validation.customer.phone.pattern}", violation.getMessageTemplate());
    }

    @Test
    void testEmailNotBlank() {
        testCustomer.setEmail(" ");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(2, violations.size());

        ArrayList<ConstraintViolation<Customer>> violationsList = new ArrayList<>(violations);
        violationsList.sort(Comparator.comparing(ConstraintViolation::getMessageTemplate));

        ConstraintViolation<Customer> notBlankViolation = violationsList.get(0);
        assertEquals("email", notBlankViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.email.blank}", notBlankViolation.getMessageTemplate());

        ConstraintViolation<Customer> patternViolation = violationsList.get(1);
        assertEquals("email", patternViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.email.format}", patternViolation.getMessageTemplate());
    }

    @Test
    void testEmailNotEmail() {
        testCustomer.setEmail("abc.de");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.stream().findFirst().get();
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("{validation.customer.email.format}", violation.getMessageTemplate());
    }

    @Test
    void testDateOfBirthNotNull() {
        testCustomer.setDateOfBirth(null);

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(2, violations.size());

        ArrayList<ConstraintViolation<Customer>> violationsList = new ArrayList<>(violations);
        violationsList.sort(Comparator.comparing(ConstraintViolation::getMessageTemplate));

        ConstraintViolation<Customer> notBlankViolation = violationsList.get(0);
        assertEquals("dateOfBirth", notBlankViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.date-of-birth.adult}", notBlankViolation.getMessageTemplate());

        ConstraintViolation<Customer> patternViolation = violationsList.get(1);
        assertEquals("dateOfBirth", patternViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.date-of-birth.null}", patternViolation.getMessageTemplate());
    }

    @Test
    void testDateOfBirthNotMinAge() {
        testCustomer.setDateOfBirth(LocalDate.now().minusYears(17));

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.stream().findFirst().get();
        assertEquals("dateOfBirth", violation.getPropertyPath().toString());
        assertEquals("{validation.customer.date-of-birth.adult}", violation.getMessageTemplate());
    }

    @Test
    void testIdNumberNotBlank() {
        testCustomer.setIdNumber(" ");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(2, violations.size());

        ArrayList<ConstraintViolation<Customer>> violationsList = new ArrayList<>(violations);
        violationsList.sort(Comparator.comparing(ConstraintViolation::getMessageTemplate));

        ConstraintViolation<Customer> notBlankViolation = violationsList.get(0);
        assertEquals("idNumber", notBlankViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.id-number.blank}", notBlankViolation.getMessageTemplate());

        ConstraintViolation<Customer> patternViolation = violationsList.get(1);
        assertEquals("idNumber", patternViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.id-number.pattern}", patternViolation.getMessageTemplate());
    }

    @Test
    void testIdNumberNotPattern() {
        testCustomer.setIdNumber("1AAA5AD454");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ArrayList<ConstraintViolation<Customer>> violationsList = new ArrayList<>(violations);
        violationsList.sort(Comparator.comparing(ConstraintViolation::getMessageTemplate));

        ConstraintViolation<Customer> patternViolation = violationsList.get(0);
        assertEquals("idNumber", patternViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.id-number.pattern}", patternViolation.getMessageTemplate());
    }

    @Test
    void testDriverLicenseNumberNotBlank() {
        testCustomer.setDriverLicenseNumber(" ");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(2, violations.size());

        ArrayList<ConstraintViolation<Customer>> violationsList = new ArrayList<>(violations);
        violationsList.sort(Comparator.comparing(ConstraintViolation::getMessageTemplate));

        ConstraintViolation<Customer> notBlankViolation = violationsList.get(0);
        assertEquals("driverLicenseNumber", notBlankViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.driver-license-number.blank}", notBlankViolation.getMessageTemplate());

        ConstraintViolation<Customer> patternViolation = violationsList.get(1);
        assertEquals("driverLicenseNumber", patternViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.driver-license-number.pattern}", patternViolation.getMessageTemplate());
    }

    @Test
    void testDriverLicenseNumberNotPattern() {
        testCustomer.setDriverLicenseNumber("AD47S75AD");

        Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        assertEquals(1, violations.size());

        ArrayList<ConstraintViolation<Customer>> violationsList = new ArrayList<>(violations);
        violationsList.sort(Comparator.comparing(ConstraintViolation::getMessageTemplate));

        ConstraintViolation<Customer> patternViolation = violationsList.get(0);
        assertEquals("driverLicenseNumber", patternViolation.getPropertyPath().toString());
        assertEquals("{validation.customer.driver-license-number.pattern}", patternViolation.getMessageTemplate());
    }
}
