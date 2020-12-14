package de.htwsaar.prog3.carrental.model.generator;

import de.htwsaar.prog3.carrental.model.Rental;

import java.time.LocalDate;

public class RentalGenerator implements Generatable<Rental> {
    private final String[] noteList = {"", "Damaged two tires", "Damaged one tire", "Broke left wing mirror", "Broke right wing mirror"};

    @Override
    public Rental generate() {

        final LocalDate startDate = LocalDate.now().plusDays(GeneratorUtil.getRandomInt(0,100));
        final LocalDate endDate = startDate.plusDays(GeneratorUtil.getRandomInt(1,100));

        return Rental.builder()
                .start(startDate)
                .end(endDate)
                .car(new CarGenerator().generate())
                .customer(new CustomerGenerator().generate())
                .employee(new EmployeeGenerator().generate())
                .extraCosts(GeneratorUtil.getRandomInt(0, 501))
                .note(noteList[GeneratorUtil.getRandomInt(noteList.length)])
                .build();
    }
}

