package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the {@link CustomerService} class.
 *
 * @author Julian Quint
 */
class CustomerServiceTest {
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        this.customerService = new CustomerService();
    }

    @AfterEach
    void tearDown() {
        EntityManagerUtil.closeEntityManagerFactory();
    }

    @Test
    void testFindById() {
        Customer expectedCustomer = createTestCustomer1();
        customerService.persist(expectedCustomer);

        Customer actualCustomer = customerService.findById(expectedCustomer.getId());

        assertThat(actualCustomer, is(equalTo(expectedCustomer)));
    }

    @Test
    void testFindAll() {
        Customer customer1 = createTestCustomer1();
        customerService.persist(customer1);

        Customer customer2 = createTestCustomer2();
        customerService.persist(customer2);

        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(customer1);
        expectedCustomers.add(customer2);

        List<Customer> actualCustomers = customerService.findAll();

        assertThat(actualCustomers, is(equalTo(expectedCustomers)));
    }

    @Test
    void testUpdate() {
        Customer expectedCustomer = createTestCustomer1();
        customerService.persist(expectedCustomer);

        expectedCustomer.setEmailAddress("stevejobs@mac.os");

        Customer actualCostumer = customerService.findById(expectedCustomer.getId());
        actualCostumer.setEmailAddress("stevejobs@mac.os");
        customerService.update(actualCostumer);

        actualCostumer = customerService.findById(expectedCustomer.getId());

        assertThat(actualCostumer, is(equalTo(expectedCustomer)));
    }

    @Test
    void testDelete() {
        Customer customer1 = createTestCustomer1();
        customerService.persist(customer1);

        Customer customer2 = createTestCustomer2();
        customerService.persist(customer2);

        List<Customer> actualCustomers = customerService.findAll();

        assertThat(actualCustomers.size(), is(equalTo(2)));

        customerService.delete(customer1);

        actualCustomers = customerService.findAll();

        assertThat(actualCustomers.size(), is(equalTo(1)));
    }

    @Test
    void testDeleteById() {
        Customer customer1 = createTestCustomer1();
        customerService.persist(customer1);

        Customer customer2 = createTestCustomer2();
        customerService.persist(customer2);

        List<Customer> actualCustomers = customerService.findAll();

        assertThat(actualCustomers.size(), is(equalTo(2)));

        customerService.deleteById(customer1.getId());

        actualCustomers = customerService.findAll();

        assertThat(actualCustomers.size(), is(equalTo(1)));
    }

    @Test
    void testDeleteAll() {
        Customer customer1 = createTestCustomer1();
        customerService.persist(customer1);

        Customer customer2 = createTestCustomer2();
        customerService.persist(customer2);

        List<Customer> actualCustomers = customerService.findAll();

        assertThat(actualCustomers.size(), is(equalTo(2)));

        customerService.deleteAll();

        actualCustomers = customerService.findAll();

        assertThat(actualCustomers.size(), is(equalTo(0)));
    }

    private static Customer createTestCustomer1() {
        return new Customer("01.01.1970", "31415926535", "wbraun@htwsaar.de", "Wolfgang", "141", "Braun",
                "+492718281828", "Saarbrücken", "Otto-Hahn Straße");
    }

    private static Customer createTestCustomer2() {
        return new Customer("24.02.1955", "14142135623", "sjobs@mac.os", "Steve", "42", "Jobs",
                "+4918686302002", "Saarbrücken", "Otto-Hahn Straße");
    }
}
