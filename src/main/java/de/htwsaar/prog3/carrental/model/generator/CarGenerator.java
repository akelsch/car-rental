package de.htwsaar.prog3.carrental.model.generator;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.car.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

public class CarGenerator implements Generatable<Car> {
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

        final String brand = brandsList[GeneratorUtil.getRandomInt(brandsList.length)];
        final String[] modelsByOneBrand = modelsByBrand.get(brand);
        final String randomLicenceCity = GeneratorUtil.generateUpperSequence(GeneratorUtil.getRandomInt(1,3));
        final String randomLicenceName = GeneratorUtil.generateUpperSequence(GeneratorUtil.getRandomInt(1,2));

        return Car.builder()
                .brand(brand)
                .model(modelsByOneBrand[GeneratorUtil.getRandomInt(modelsByOneBrand.length)])
                .type(Type.values()[GeneratorUtil.getRandomInt(Type.values().length)])
                .color(Color.values()[GeneratorUtil.getRandomInt(Color.values().length)])
                .year(Year.of(GeneratorUtil.getRandomInt(1900, Calendar.getInstance().get(Calendar.YEAR))))
                .mileage(GeneratorUtil.getRandomInt(1, 250000))
                .transmission(Transmission.values()[GeneratorUtil.getRandomInt(Transmission.values().length)])
                .fuel(Fuel.values()[GeneratorUtil.getRandomInt(Fuel.values().length)])
                .horsepower(GeneratorUtil.getRandomInt(70, 600))
                .doors(GeneratorUtil.getRandomInt(1, 5))
                .tires(Tire.values()[GeneratorUtil.getRandomInt(Tire.values().length)])
                .nextInspection(YearMonth.from(LocalDate.now().plusMonths(GeneratorUtil.getRandomInt(0,60))))
                .dailyRate(GeneratorUtil.getRandomInt(50, 300))
                .licenseNumber(randomLicenceCity + " " + randomLicenceName + " " + GeneratorUtil.getRandomInt(1, 9999))
                .parkingLot(GeneratorUtil.generateUpperNumericSequence(4))
                .vin(GeneratorUtil.generateUpperNumericSequence(17))
                .build();
    }
}
