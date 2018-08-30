package de.htwsaar.prog3.carrental.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Customer model for Hibernate.
 *
 * @author Julian Quint
 */
@Entity
@Table
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {
    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "driver_license_id", nullable = false, unique = true, length = 11)
    private String driverLicenseId;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "id_number", nullable = false)
    private String idNumber;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String residence;

    @Column(nullable = false)
    private String street;
}
