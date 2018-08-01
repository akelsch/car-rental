package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Julian Quint
 */
class CustomerServiceTest {
    private CustomerService customerService;
    private Customer expectedCustomer;

    @BeforeEach
    void setUp() {
        this.customerService = new CustomerService();

        this.expectedCustomer = new Customer();
        expectedCustomer.setDateOfBirth("01.01.1970");
        expectedCustomer.setDriverLicenseId("31415926535");
        expectedCustomer.setEmailAddress("wbraun@htwsaar.de");
        expectedCustomer.setHouseNumber("141");
        expectedCustomer.setName("Braun");
        expectedCustomer.setPhoneNumber("+492718281828");
        expectedCustomer.setResidence("Saarbrücken");
        expectedCustomer.setStreet("Otto-Hahn Straße");
        expectedCustomer.setSurname("Wolfgang");

        customerService.persist(expectedCustomer);
    }

    @Test
    void testRead() {
        Customer actualCustomer = customerService.findById(1L);

        assertThat(actualCustomer, is(equalTo(expectedCustomer)));
    }
}
