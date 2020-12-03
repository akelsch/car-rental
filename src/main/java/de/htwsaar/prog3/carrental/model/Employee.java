package de.htwsaar.prog3.carrental.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Employee object model (JPA entity).
 *
 * @author Julian Quint
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String position;

    @Override
    public String toString() {
        return "%s %s".formatted(firstName, lastName);
    }
}
