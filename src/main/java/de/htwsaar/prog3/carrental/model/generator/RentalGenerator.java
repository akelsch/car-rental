package de.htwsaar.prog3.carrental.model.generator;

import com.github.javafaker.Faker;
import de.htwsaar.prog3.carrental.model.Rental;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class RentalGenerator implements Generatable<Rental> {
    private final String[] noteList = {"", "Damaged two tires", "Damaged one tire", "Broke left wing mirror", "Broke right wing mirror"};
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public Rental generate() {
        Faker faker = new Faker(Locale.GERMAN);

        final Date startDate = faker.date().between(new Date(System.currentTimeMillis() - 2419200L), new Date(System.currentTimeMillis() + 2419200L));
        final LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        final Date endDate = faker.date().between(startDate, new Date(System.currentTimeMillis() + 2419200L));
        final LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return Rental.builder()
                .start(startLocalDate)
                .end(endLocalDate)
                .car(new CarGenerator().generate())
                .customer(new CustomerGenerator().generate())
                .employee(new EmployeeGenerator().generate())
                .extraCosts(GeneratorUtil.randomIntBetween(0, 500))
                .note(noteList[RANDOM.nextInt(noteList.length)])
                .build();
    }
}

