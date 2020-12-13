package de.htwsaar.prog3.carrental.model.generator;

import com.github.javafaker.Faker;
import de.htwsaar.prog3.carrental.model.Customer;

import java.time.ZoneId;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerGenerator extends Generator<Customer> {
    private final String[] emailSuffix = {"@gmail.com", "@yahoo.com", "@hotmail.com", "@gmx.de", "@web.de", "@mail.de", "@freenet.de"};
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public Customer generate() {
        Customer customer = new Customer();
        Faker faker = new Faker(Locale.GERMAN);

        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setDateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        customer.setEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() + emailSuffix[RANDOM.nextInt(emailSuffix.length)]);
        customer.setPhone(GeneratorUtil.generateNumericSequence(GeneratorUtil.randomIntBetween(9,11)));
        customer.setIdNumber(GeneratorUtil.generateUpperNumericSequence(9));
        customer.setDriverLicenseNumber(GeneratorUtil.generateUpperNumericSequence(11));
        customer.setZipcode(Integer.parseInt(faker.address().zipCode()));
        customer.setCity(faker.address().city());
        customer.setStreet(faker.address().streetAddress());
        return customer;
    }
}
