package de.htwsaar.prog3.carrental.model.generator;

import com.github.javafaker.Faker;
import de.htwsaar.prog3.carrental.model.Customer;

import java.time.ZoneId;
import java.util.Locale;

public class CustomerGenerator implements Generatable<Customer> {
    private final String[] emailSuffixArray = {"@gmail.com", "@yahoo.com", "@hotmail.com", "@gmx.de", "@web.de", "@mail.de", "@freenet.de"};

    @Override
    public Customer generate() {
        Faker faker = new Faker(Locale.GERMAN);

        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();
        final String emailSuffix = emailSuffixArray[GeneratorUtil.getRandomInt(emailSuffixArray.length)];

        return Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .email(firstName.toLowerCase() + "." + lastName.toLowerCase() + emailSuffix)
                .phone("+" + GeneratorUtil.generateNumericSequence(GeneratorUtil.getRandomInt(9,11)))
                .idNumber(GeneratorUtil.generateUpperNumericSequence(9))
                .driverLicenseNumber(GeneratorUtil.generateUpperNumericSequence(11))
                .zipcode(Integer.parseInt(faker.address().zipCode()))
                .city(faker.address().city())
                .street(faker.address().streetAddress())
                .build();
    }
}
