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
        Car car = new Car();

        final String brand = brandsList[RANDOM.nextInt(brandsList.length)];
        final String[] modelsByOneBrand = modelsByBrand.get(brand);
        final String randomLicenceCity = RANDOM.nextBoolean() ? GeneratorUtil.generateUpperSequence(1) : GeneratorUtil.generateUpperSequence(3);
        final String randomLicenceName = RANDOM.nextBoolean() ? GeneratorUtil.generateUpperSequence(1) : GeneratorUtil.generateUpperSequence(2);

        car.setBrand(brand);
        car.setModel(modelsByOneBrand[RANDOM.nextInt(modelsByOneBrand.length)]);
        car.setType(Type.values()[RANDOM.nextInt(Type.values().length)]);
        car.setColor(Color.values()[RANDOM.nextInt(Color.values().length)]);
        car.setYear(Year.of(GeneratorUtil.randomIntBetween(1900, Calendar.getInstance().get(Calendar.YEAR))));
        car.setMileage(GeneratorUtil.randomIntBetween(0, 250) * 1000);
        car.setTransmission(Transmission.values()[RANDOM.nextInt(Transmission.values().length)]);
        car.setHorsepower(GeneratorUtil.randomIntBetween(70, 600));
        car.setFuel(Fuel.values()[RANDOM.nextInt(Fuel.values().length)]);
        car.setDoors(GeneratorUtil.randomIntBetween(1, 5));
        car.setTires(Tire.values()[RANDOM.nextInt(Tire.values().length)]);
        car.setNextInspection(YearMonth.of(GeneratorUtil.randomIntBetween(Calendar.getInstance().get(Calendar.YEAR), 2050), GeneratorUtil.randomIntBetween(1, 12)));
        car.setDailyRate(GeneratorUtil.randomIntBetween(50, 300));
        car.setLicenseNumber(randomLicenceCity + " " + randomLicenceName + " " + GeneratorUtil.randomIntBetween(1, 9999));
        car.setParkingLot(GeneratorUtil.generateUpperNumericSequence(2));
        car.setVin(GeneratorUtil.generateUpperNumericSequence(17));
        return car;
    }
}
