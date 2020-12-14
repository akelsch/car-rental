package de.htwsaar.prog3.carrental.model.generator;

import com.github.javafaker.Faker;
import de.htwsaar.prog3.carrental.model.Customer;

import java.time.ZoneId;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerGenerator implements Generatable<Customer> {
    private final String[] emailSuffixArray = {"@gmail.com", "@yahoo.com", "@hotmail.com", "@gmx.de", "@web.de", "@mail.de", "@freenet.de"};
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public Customer generate() {
        Faker faker = new Faker(Locale.GERMAN);

        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();
        final String emailSuffix = emailSuffixArray[RANDOM.nextInt(emailSuffixArray.length)];

        return Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .email(firstName.toLowerCase() + "." + lastName.toLowerCase() + emailSuffix)
                .phone("+" + GeneratorUtil.generateNumericSequence(RANDOM.nextInt(9,12)))
                .idNumber(GeneratorUtil.generateUpperNumericSequence(9))
                .driverLicenseNumber(GeneratorUtil.generateUpperNumericSequence(11))
                .zipcode(Integer.parseInt(faker.address().zipCode()))
                .city(faker.address().city())
                .street(faker.address().streetAddress())
                .build();
    }
}
