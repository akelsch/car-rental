package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Julian Quint
 */
class EmployeeServiceTest {
    private EmployeeService employeeService;
    private Employee expectedEmployee;

    @BeforeEach
    void setUp() {
        this.employeeService = new EmployeeService();

        this.expectedEmployee = new Employee();
        expectedEmployee.setName("Elon");
        expectedEmployee.setSurname("Musk");
        expectedEmployee.setPosition("CEO");

        employeeService.persist(expectedEmployee);
    }

    @Test
    void testRead() {
        Employee actualEmployee = employeeService.findById(1L);

        assertThat(actualEmployee, is(equalTo(expectedEmployee)));
    }
}
