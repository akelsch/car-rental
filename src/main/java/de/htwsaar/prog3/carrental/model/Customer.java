package de.htwsaar.prog3.carrental.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Customer model for Hibernate.
 *
 * @author Julian Quint
 */
@Entity
@Table
@Data
public class Customer {
    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name = "driver_license_id", nullable = false, unique = true, length = 11)
    private String driverLicenseId;

    @Column(nullable = false)
    private String residence;

    @Column(nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;
}
