package de.htwsaar.prog3.carrental.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

/**
 * Rental object model (JPA entity).
 *
 * @author Julian Quint
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Rental extends BaseEntity {

    // TODO datum -> @FutureOrPresent
    @Column(nullable = false)
    private String begin;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // TODO datum -> @FutureOrPresent
    // TODO check after begin
    @Column(nullable = false)
    private String end;

    @Min(0)
    @Column
    private int extraCosts;

    @Column
    private String note;
}
