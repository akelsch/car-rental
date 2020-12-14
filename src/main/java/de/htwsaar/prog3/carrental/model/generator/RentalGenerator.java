package de.htwsaar.prog3.carrental.model.generator;

import de.htwsaar.prog3.carrental.model.Rental;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RentalGenerator implements Generatable<Rental> {
    private final String[] noteList = {"", "Damaged two tires", "Damaged one tire", "Broke left wing mirror", "Broke right wing mirror"};
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public Rental generate() {

        final LocalDate startDate = LocalDate.now().plusDays(RANDOM.nextInt(0,101));
        final LocalDate endDate = startDate.plusDays(RANDOM.nextInt(1,101));

        return Rental.builder()
                .start(startDate)
                .end(endDate)
                .car(new CarGenerator().generate())
                .customer(new CustomerGenerator().generate())
                .employee(new EmployeeGenerator().generate())
                .extraCosts(RANDOM.nextInt(0, 501))
                .note(noteList[RANDOM.nextInt(noteList.length)])
                .build();
    }
}

