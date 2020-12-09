package de.htwsaar.prog3.carrental.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;

/**
 * Car object model (JPA entity).
 *
 * @author Arthur Kelsch
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Car extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String brand;

    @NotBlank
    @Column(nullable = false)
    private String category;

    @NotBlank
    @Column(nullable = false)
    private String color;

    @Min(0)
    @Max(2020)
    // TODO java.time.Year -> @PastOrPresent
    @Column(nullable = false)
    private int constructionYear;

    @Min(0)
    @Column(nullable = false)
    private int dailyRate;

    @Column
    private String defects;

    @Min(1)
    @Max(10)
    @Column(nullable = false)
    private int doorCount;

    @Min(0)
    @Column(nullable = false)
    private int drivenDistance;

    // TODO remove
    @Column
    private String equipment;

    @NotBlank // TODO i18n -> Enum
    @Column(nullable = false)
    private String fuel;

    @NotBlank // TODO i18n -> Enum
    @Column(nullable = false)
    private String gearbox;

    @Min(0)
    @Column(nullable = false)
    private int horsepower;

    @Pattern(regexp = "\\p{Alpha}{1,3} \\p{Alpha}{1,2} \\p{Digit}{1,4}") // TODO regex korrekt?
    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @NotBlank // TODO Datum
    @Column(nullable = false)
    private String nextInspection;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String parkingLot;

    @NotBlank // TODO i18n -> Enum
    @Column(nullable = false)
    private String tires;

    @Pattern(regexp = "\\p{Alnum}{17}")
    @Column(nullable = false, unique = true)
    private String vin;

    @Override
    public String toString() {
        return "%d %s %s".formatted(constructionYear, brand, model);
    }
}
