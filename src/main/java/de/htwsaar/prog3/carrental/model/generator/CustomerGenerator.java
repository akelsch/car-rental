package de.htwsaar.prog3.carrental.model.generator;

import com.github.javafaker.Faker;
import de.htwsaar.prog3.carrental.model.Customer;

import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

public class CustomerGenerator implements Generatable<Customer> {

    private final List<String> emailSuffixes = List.of("@gmail.com", "@yahoo.com", "@hotmail.com",
            "@gmx.de", "@web.de", "@mail.de", "@freenet.de");

    private final Faker faker = new Faker(Locale.GERMAN);

    @Override
    public Customer generate() {
        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();
        final String emailSuffix = emailSuffixes.get(GeneratorUtil.getRandomInt(emailSuffixes.size()));

        return Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .email(firstName.toLowerCase() + "." + lastName.toLowerCase() + emailSuffix)
                .phone("+" + GeneratorUtil.generateNumericSequence(GeneratorUtil.getRandomInt(9, 11)))
                .idNumber(GeneratorUtil.generateUpperNumericSequence(9))
                .driverLicenseNumber(GeneratorUtil.generateUpperNumericSequence(11))
                .zipcode(GeneratorUtil.getRandomInt(10000, 99999))
                .city(faker.address().city())
                .street(faker.address().streetAddress())
                .build();
    }
}
