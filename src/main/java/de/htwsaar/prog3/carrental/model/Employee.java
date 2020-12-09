package de.htwsaar.prog3.carrental.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * Employee object model (JPA entity).
 *
 * @author Julian Quint
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    private String position;

    @Override
    public String toString() {
        return "%s %s".formatted(firstName, lastName);
    }
}
