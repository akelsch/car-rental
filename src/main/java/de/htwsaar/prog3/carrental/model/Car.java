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

    @NotNull(message = "{validation.car.year.null}")
    @PastOrPresent(message = "{validation.car.year.past}")
    @Column(nullable = false)
    private Year year;

    @NotBlank(message = "{validation.car.brand}")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "{validation.car.model}")
    @Column(nullable = false)
    private String model;

    @NotNull(message = "{validation.car.type}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @NotNull(message = "{validation.car.color}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Color color;

    @Positive(message = "{validation.car.daily-rate}")
    @Column(nullable = false)
    private int dailyRate;

    @Positive(message = "{validation.car.doors}")
    @Column(nullable = false)
    private int doors;

    @NotNull(message = "{validation.car.transmission}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transmission transmission;

    @NotNull(message = "{validation.car.fuel}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Fuel fuel;

    @Positive(message = "{validation.car.horsepower}")
    @Column(nullable = false)
    private int horsepower;

    @Positive(message = "{validation.car.mileage}")
    @Column(nullable = false)
    private int mileage;

    @NotNull(message = "{validation.car.tires}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tire tires;

    @NotBlank(message = "{validation.car.parking-lot}")
    @Column(nullable = false, unique = true)
    private String parkingLot;

    @NotBlank(message = "{validation.car.license-number.blank}")
    @Pattern(regexp = "\\p{Alpha}{1,3} \\p{Alpha}{1,2} \\p{Digit}{1,4}", message = "{validation.car.license-number.pattern}")
    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @NotBlank(message = "{validation.car.vin.blank}")
    @Pattern(regexp = "\\p{Alnum}{17}", message = "{validation.car.vin.pattern}")
    @Column(nullable = false, unique = true)
    private String vin;

    @NotNull(message = "{validation.car.next-inspection.null}")
    @FutureOrPresent(message = "{validation.car.next-inspection.future}")
    @Column(nullable = false)
    private YearMonth nextInspection;

    @Column
    private String defects;

    @Override
    public String toString() {
        return "%d %s %s".formatted(year.getValue(), brand, model);
    }
}
