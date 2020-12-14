package de.htwsaar.prog3.carrental.model.generator;

import com.github.javafaker.Faker;
import de.htwsaar.prog3.carrental.model.Employee;

import java.util.Locale;

public class EmployeeGenerator implements Generatable<Employee> {
    private final String[] positionList = {"CEO", "Customer Advisor"};

    @Override
    public Employee generate() {
        Faker faker = new Faker(Locale.GERMAN);

        return Employee.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .position(positionList[GeneratorUtil.getRandomInt(positionList.length)])
                .build();
    }
}
