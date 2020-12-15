package de.htwsaar.prog3.carrental.model.generator;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.car.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public class CarGenerator implements Generatable<Car> {

    private final List<String> brands = List.of("BMW", "Mercedes", "Audi", "VW", "Ford",
            "Toyota", "Mazda", "Nissan", "Hyundai", "Tesla");

    private final Map<String, List<String>> modelsByBrand = Map.of(
            brands.get(0), List.of("2er", "3er", "4er", "5er", "X5"),
            brands.get(1), List.of("A-Klasse", "C-Klasse", "E-Klasse", "S-Klasse", "GLC"),
            brands.get(2), List.of("A4", "A5", "S5", "A7", "A8"),
            brands.get(3), List.of("Golf", "Tiguan", "Passat", "T-Roc", "Polo"),
            brands.get(4), List.of("Focus", "Fiesta", "Kuga", "EcoSport", "Mustang"),
            brands.get(5), List.of("Auris", "Corolla", "GT86", "Prius", "RAV4"),
            brands.get(6), List.of("2", "3", "CX-3", "CX-5", "MX-5"),
            brands.get(7), List.of("350z", "GT-R", "Juke", "Micra", "Qashqai"),
            brands.get(8), List.of("i10", "i20", "i30", "IONIQ", "Santa Fe"),
            brands.get(9), List.of("Model S", "Model 3", "Model X", "Model Y", "Cybertruck"));

    @Override
    public Car generate() {
        final String brand = brands.get(GeneratorUtil.getRandomInt(brands.size()));
        final List<String> modelsByOneBrand = modelsByBrand.get(brand);
        final String randomLicenceCity = GeneratorUtil.generateUpperSequence(GeneratorUtil.getRandomInt(1, 3));
        final String randomLicenceName = GeneratorUtil.generateUpperSequence(GeneratorUtil.getRandomInt(1, 2));

        return Car.builder()
                .brand(brand)
                .model(modelsByOneBrand.get(GeneratorUtil.getRandomInt(modelsByOneBrand.size())))
                .type(Type.values()[GeneratorUtil.getRandomInt(Type.values().length)])
                .color(Color.values()[GeneratorUtil.getRandomInt(Color.values().length)])
                .year(Year.of(GeneratorUtil.getRandomInt(1997, Year.now().getValue())))
                .mileage(GeneratorUtil.getRandomInt(1, 150_000))
                .transmission(Transmission.values()[GeneratorUtil.getRandomInt(Transmission.values().length)])
                .fuel(Fuel.values()[GeneratorUtil.getRandomInt(Fuel.values().length)])
                .horsepower(GeneratorUtil.getRandomInt(80, 600))
                .doors(GeneratorUtil.getRandomInt(2, 7))
                .tires(Tire.values()[GeneratorUtil.getRandomInt(Tire.values().length)])
                .nextInspection(YearMonth.from(LocalDate.now().plusMonths(GeneratorUtil.getRandomInt(0, 48))))
                .dailyRate(GeneratorUtil.getRandomInt(50, 300))
                .licenseNumber(randomLicenceCity + " " + randomLicenceName + " " + GeneratorUtil.getRandomInt(1, 9999))
                .parkingLot(GeneratorUtil.generateUpperNumericSequence(4))
                .vin(GeneratorUtil.generateUpperNumericSequence(17))
                .build();
    }
}
