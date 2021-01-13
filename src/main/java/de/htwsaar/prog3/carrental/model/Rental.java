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

    @NotNull(message = "{validation.rental.start.null}")
    @FutureOrPresent(message = "{validation.rental.start.future}")
    @Column(nullable = false)
    private LocalDate start;

    @NotNull(message = "{validation.rental.end.null}")
    @Future(message = "{validation.rental.end.future}")
    @Column(nullable = false)
    private LocalDate end;

    @NotNull(message = "{validation.rental.car}")
    @ManyToOne
    @JoinColumn(name = "car_fk", nullable = false)
    private Car car;

    @NotNull(message = "{validation.rental.customer}")
    @ManyToOne
    @JoinColumn(name = "customer_fk", nullable = false)
    private Customer customer;

    @NotNull(message = "{validation.rental.employee}")
    @ManyToOne
    @JoinColumn(name = "employee_fk", nullable = false)
    private Employee employee;

    @PositiveOrZero(message = "{validation.rental.extra-costs}")
    @Column
    private int extraCosts;

    @Column
    private String note;

    public boolean isActive() {
        LocalDate now = LocalDate.now();
        return this.getStart().isBefore(now.plusDays(1))
                && this.getEnd().isAfter(now);
    }
}
