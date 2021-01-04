package de.htwsaar.prog3.carrental.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

/**
 * Employee object model (JPA entity).
 *
 * @author Julian Quint
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity {

    @NotBlank(message = "{validation.employee.first-name}")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "{validation.employee.last-name}")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "{validation.employee.position}")
    @Column(nullable = false)
    private String position;

    @Override
    public String toString() {
        return "%s %s".formatted(firstName, lastName);
    }
}
