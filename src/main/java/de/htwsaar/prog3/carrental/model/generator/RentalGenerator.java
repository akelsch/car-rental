package de.htwsaar.prog3.carrental.model.generator;

import de.htwsaar.prog3.carrental.model.Rental;

import java.time.LocalDate;

public class RentalGenerator implements Generatable<Rental> {
    private final String[] noteList = {"", "Damaged two tires", "Damaged one tire", "Broke left wing mirror", "Broke right wing mirror"};

    private final CarGenerator carGenerator = new CarGenerator();
    private final CustomerGenerator customerGenerator = new CustomerGenerator();
    private final EmployeeGenerator employeeGenerator = new EmployeeGenerator();

    @Override
    public Rental generate() {
        final LocalDate startDate = LocalDate.now().plusDays(GeneratorUtil.getRandomInt(0, 100));
        final LocalDate endDate = startDate.plusDays(GeneratorUtil.getRandomInt(1, 100));

        return Rental.builder()
                .start(startDate)
                .end(endDate)
                .car(carGenerator.generate())
                .customer(customerGenerator.generate())
                .employee(employeeGenerator.generate())
                .extraCosts(GeneratorUtil.getRandomInt(0, 501))
                .note(noteList[GeneratorUtil.getRandomInt(noteList.length)])
                .build();
    }
}

