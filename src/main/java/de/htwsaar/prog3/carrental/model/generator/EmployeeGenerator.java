package de.htwsaar.prog3.carrental.model.generator;

import com.github.javafaker.Faker;
import de.htwsaar.prog3.carrental.model.Employee;

import java.util.List;
import java.util.Locale;

public class EmployeeGenerator implements Generatable<Employee> {

    private final List<String> positions = List.of("CEO", "Manager", "Customer Advisor");

    private final Faker faker = new Faker(Locale.GERMAN);

    @Override
    public Employee generate() {
        return Employee.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .position(positions.get(GeneratorUtil.getRandomInt(positions.size())))
                .build();
    }
}
