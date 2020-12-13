package de.htwsaar.prog3.carrental.model.generator;

import com.github.javafaker.Faker;
import de.htwsaar.prog3.carrental.model.Employee;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class EmployeeGenerator extends Generator<Employee> {
    private final String[] positionList = {"CEO", "Customer Advisor"};
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public Employee generate() {
        Employee employee = new Employee();
        Faker faker = new Faker(Locale.GERMAN);

        employee.setFirstName(faker.name().firstName());
        employee.setLastName(faker.name().lastName());
        employee.setPosition(positionList[RANDOM.nextInt(positionList.length)]);
        return employee;
    }
}
