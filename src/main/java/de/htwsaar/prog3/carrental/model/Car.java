package de.htwsaar.prog3.carrental.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Car model for Hibernate.
 *
 * @author Arthur Kelsch
 */
@Entity
@Table
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String color;

    @Column(name = "construction_year", nullable = false)
    private String constructionYear;

    @Column(name = "daily_rate", nullable = false)
    private int dailyRate;

    @Column
    private String defects;

    @Column(name = "door_count", nullable = false)
    private int doorCount;

    @Column(name = "driven_distance", nullable = false)
    private int drivenDistance;

    @Column
    private String equipment;

    @Column(nullable = false)
    private String fuel;

    @Column(nullable = false)
    private String gearbox;

    @Column(nullable = false)
    private int horsepower;

    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false)
    private String model;

    @Column(name = "next_inspection", nullable = false)
    private String nextInspection;

    @Column(name = "parking_lot", nullable = false, unique = true)
    private String parkingLot;

    @Column(nullable = false)
    private String tires;

    @Column(nullable = false, unique = true)
    private String vin;
}
