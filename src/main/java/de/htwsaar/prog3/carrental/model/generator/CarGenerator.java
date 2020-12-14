package de.htwsaar.prog3.carrental.model.generator;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.car.*;

import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CarGenerator implements Generatable<Car> {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private final String[] brandsList = {"BMW", "Audi", "Toyota", "Chevy", "Ford", "Dodge", "Lincoln", "Buick", "Honda", "Nissan"};
    private final HashMap<String, String[]> modelsByBrand = new HashMap<>();

    private void init(){
        modelsByBrand.put("BMW", new String[]{"328i", "M3", "M5", "X1", "X3", "X5"});
        modelsByBrand.put("Audi", new String[]{"A4", "A5", "S5", "A7", "A8"});
        modelsByBrand.put("Toyota", new String[]{"Prius", "Camry", "Corolla"});
        modelsByBrand.put("Chevy", new String[]{"Camero", "Silverado", "Malibu"});
        modelsByBrand.put("Ford", new String[]{"Mustang", "F150", "Focus", "Fiesta"});
        modelsByBrand.put("Dodge", new String[]{"Ram", "Challenger", "Charger", "Durango"});
        modelsByBrand.put("Lincoln", new String[]{"Navigator", "MKZ", "MKX", "MKS"});
        modelsByBrand.put("Buick", new String[]{"Enclave", "Regal", "LaCrosse", "Verano", "Encore", "Riveria"});
        modelsByBrand.put("Honda", new String[]{"Accord", "Civic", "CR-V", "Odyssey"});
        modelsByBrand.put("Nissan", new String[]{"Rogue", "Juke", "Cube", "Pathfiner", "Versa", "Altima"});
    }

    @Override
    public Car generate() {
        init();

        final String brand = brandsList[RANDOM.nextInt(brandsList.length)];
        final String[] modelsByOneBrand = modelsByBrand.get(brand);
        final String randomLicenceCity = RANDOM.nextBoolean() ? GeneratorUtil.generateUpperSequence(1) : GeneratorUtil.generateUpperSequence(3);
        final String randomLicenceName = RANDOM.nextBoolean() ? GeneratorUtil.generateUpperSequence(1) : GeneratorUtil.generateUpperSequence(2);

        return Car.builder()
                .brand(brand)
                .model(modelsByOneBrand[RANDOM.nextInt(modelsByOneBrand.length)])
                .type(Type.values()[RANDOM.nextInt(Type.values().length)])
                .color(Color.values()[RANDOM.nextInt(Color.values().length)])
                .year(Year.of(GeneratorUtil.randomIntBetween(1900, Calendar.getInstance().get(Calendar.YEAR))))
                .mileage(GeneratorUtil.randomIntBetween(0, 250) * 1000)
                .transmission(Transmission.values()[RANDOM.nextInt(Transmission.values().length)])
                .fuel(Fuel.values()[RANDOM.nextInt(Fuel.values().length)])
                .horsepower(GeneratorUtil.randomIntBetween(70, 600))
                .doors(GeneratorUtil.randomIntBetween(1, 5))
                .tires(Tire.values()[RANDOM.nextInt(Tire.values().length)])
                .nextInspection(YearMonth.of(GeneratorUtil.randomIntBetween(Calendar.getInstance().get(Calendar.YEAR), 2050), GeneratorUtil.randomIntBetween(1, 12)))
                .dailyRate(GeneratorUtil.randomIntBetween(50, 300))
                .licenseNumber(randomLicenceCity + " " + randomLicenceName + " " + GeneratorUtil.randomIntBetween(1, 9999))
                .parkingLot(GeneratorUtil.generateUpperNumericSequence(2))
                .vin(GeneratorUtil.generateUpperNumericSequence(17))
                .build();
    }
}
