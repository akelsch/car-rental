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
        Rental rental = new Rental();
        Faker faker = new Faker(Locale.GERMAN);

        final Date startDate = faker.date().between(new Date(System.currentTimeMillis() - 2419200L), new Date(System.currentTimeMillis() + 2419200L));
        final LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        final Date endDate = faker.date().between(startDate, new Date(System.currentTimeMillis() + 2419200L));
        final LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        rental.setCar(new CarGenerator().generate());
        rental.setCustomer(new CustomerGenerator().generate());
        rental.setEmployee(new EmployeeGenerator().generate());
        rental.setStart(startLocalDate);
        rental.setEnd(endLocalDate);
        rental.setExtraCosts(GeneratorUtil.randomIntBetween(0, 500));
        rental.setNote(noteList[RANDOM.nextInt(noteList.length)]);
        return rental;
    }
}

