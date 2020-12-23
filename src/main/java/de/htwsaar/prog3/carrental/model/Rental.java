package de.htwsaar.prog3.carrental.model;

import de.htwsaar.prog3.carrental.model.validation.AfterDefault;
import de.htwsaar.prog3.carrental.model.validation.ValidRental;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

/**
 * Rental object model (JPA entity).
 *
 * @author Julian Quint
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ValidRental(groups = AfterDefault.class)
public class Rental extends BaseEntity {

    @NotNull
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate start;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate end;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "car_fk", nullable = false)
    private Car car;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_fk", nullable = false)
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_fk", nullable = false)
    private Employee employee;

    @PositiveOrZero
    @Column
    private int extraCosts;

    @Column
    private String note;
}
