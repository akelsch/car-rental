package de.htwsaar.prog3.carrental.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Car object model (JPA entity).
 *
 * @author Arthur Kelsch
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Car extends BaseEntity {

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int constructionYear;

    @Column(nullable = false)
    private int dailyRate;

    @Column
    private String defects;

    @Column(nullable = false)
    private int doorCount;

    @Column(nullable = false)
    private int drivenDistance;

    @Column
    private String equipment;

    @Column(nullable = false)
    private String fuel;

    @Column(nullable = false)
    private String gearbox;

    @Column(nullable = false)
    private int horsepower;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String nextInspection;

    @Column(nullable = false, unique = true)
    private String parkingLot;

    @Column(nullable = false)
    private String tires;

    @Column(nullable = false, unique = true)
    private String vin;

    @Override
    public String toString() {
        return "%d %s %s".formatted(constructionYear, brand, model);
    }
}
