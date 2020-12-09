package de.htwsaar.prog3.carrental.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;

/**
 * Customer object model (JPA entity).
 *
 * @author Julian Quint
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String city;

    @NotBlank // TODO datum -> 18+
    @Column(nullable = false)
    private String dateOfBirth;

    @Pattern(regexp = "\\p{Alnum}{11}")
    @Column(nullable = false, unique = true)
    private String driverLicenseId;

    @Email
    @Column(nullable = false)
    private String emailAddress;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    // TODO remove
    @Column(nullable = false)
    private String houseNumber;

    @Pattern(regexp = "\\p{Alnum}{9}")
    @Column(nullable = false, unique = true)
    private String idNumber;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Pattern(regexp = "\\+\\p{Digit}+")
    @Column(nullable = false)
    private String phoneNumber;

    @NotBlank
    @Column(nullable = false)
    private String street;

    @Digits(integer = 5, fraction = 0)
    @Column(nullable = false)
    private int zipCode;

    @Override
    public String toString() {
        return "%s %s".formatted(firstName, lastName);
    }
}
