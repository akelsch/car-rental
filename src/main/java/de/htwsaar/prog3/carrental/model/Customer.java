package de.htwsaar.prog3.carrental.model;

import de.htwsaar.prog3.carrental.model.validation.MinAge;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Customer object model (JPA entity).
 *
 * @author Julian Quint
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    @NotBlank(message = "{validation.customer.first-name}")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "{validation.customer.last-name}")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "{validation.customer.street}")
    @Column(nullable = false)
    private String street;

    @Min(value = 10000, message = "{validation.customer.zipcode}")
    @Max(value = 99999, message = "{validation.customer.zipcode}")
    @Column(nullable = false)
    private int zipcode;

    @NotBlank(message = "{validation.customer.city}")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "{validation.customer.phone.blank}")
    @Pattern(regexp = "\\+\\p{Digit}+", message = "{validation.customer.phone.pattern}")
    @Column(nullable = false)
    private String phone;

    @NotBlank(message = "{validation.customer.email.blank}")
    @Email(message = "{validation.customer.email.format}")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "{validation.customer.date-of-birth.null}")
    @MinAge(value = 18, message = "{validation.customer.date-of-birth.adult}")
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank(message = "{validation.customer.id-number.blank}")
    @Pattern(regexp = "\\p{Alnum}{9}", message = "{validation.customer.id-number.pattern}")
    @Column(nullable = false, unique = true)
    private String idNumber;

    @NotBlank(message = "{validation.customer.driver-license-number.blank}")
    @Pattern(regexp = "\\p{Alnum}{11}", message = "{validation.customer.driver-license-number.pattern}")
    @Column(nullable = false, unique = true)
    private String driverLicenseNumber;

    @Override
    public String toString() {
        return "%s %s".formatted(firstName, lastName);
    }
}
