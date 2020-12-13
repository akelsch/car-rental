package de.htwsaar.prog3.carrental.model;

import de.htwsaar.prog3.carrental.model.car.*;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.Year;
import java.time.YearMonth;

/**
 * Car object model (JPA entity).
 *
 * @author Arthur Kelsch
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Car extends BaseEntity {

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private Year year;

    @NotBlank
    @Column(nullable = false)
    private String brand;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Color color;

    @Positive
    @Column(nullable = false)
    private int dailyRate;

    @Positive
    @Column(nullable = false)
    private int doors;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transmission transmission;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Fuel fuel;

    @Positive
    @Column(nullable = false)
    private int horsepower;

    @Positive
    @Column(nullable = false)
    private int mileage;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tire tires;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String parkingLot;

    @NotBlank
    @Pattern(regexp = "\\p{Alpha}{1,3} \\p{Alpha}{1,2} \\p{Digit}{1,4}")
    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @NotBlank
    @Pattern(regexp = "\\p{Alnum}{17}")
    @Column(nullable = false, unique = true)
    private String vin;

    @NotNull
    @FutureOrPresent
    @Column(nullable = false)
    private YearMonth nextInspection;

    @Column
    private String defects;

    @Override
    public String toString() {
        return "%d %s %s".formatted(year.getValue(), brand, model);
    }
}
