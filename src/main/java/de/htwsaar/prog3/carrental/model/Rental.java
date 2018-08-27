package de.htwsaar.prog3.carrental.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Rental model for Hibernate.
 *
 * @author Julian Quint
 */
@Entity
@Table
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Rental extends BaseEntity {
    @Column(nullable = false)
    private String begin;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = false)
    private String end;

    @Column(name = "extra_costs", nullable = false)
    private String extraCosts;

    @Column
    private String note;
}
