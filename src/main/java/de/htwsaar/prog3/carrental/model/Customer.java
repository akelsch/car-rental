package de.htwsaar.prog3.carrental.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Customer object model (JPA entity).
 *
 * @author Julian Quint
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false, unique = true, length = 11)
    private String driverLicenseId;

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String houseNumber;

    @Column(nullable = false, unique = true)
    private String idNumber;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int zipCode;

    @Override
    public String toString() {
        return "%s %s".formatted(firstName, lastName);
    }
}
