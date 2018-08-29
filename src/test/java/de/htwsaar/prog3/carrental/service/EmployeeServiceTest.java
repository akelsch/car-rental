package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.model.Employee;
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
 * Unit tests for the {@link EmployeeService} class.
 *
 * @author Julian Quint
 */
class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    @AfterEach
    void tearDown() {
        EntityManagerUtil.closeEntityManagerFactory();
    }

    @Test
    void testFindById() {
        Employee expectedEmployee = createTestEmployee1();
        employeeService.persist(expectedEmployee);

        Employee actualEmployee = employeeService.findById(expectedEmployee.getId());

        assertThat(actualEmployee, is(equalTo(expectedEmployee)));
    }

    @Test
    void testFindAll() {
        Employee employee1 = createTestEmployee1();
        employeeService.persist(employee1);

        Employee employee2 = createTestEmployee2();
        employeeService.persist(employee2);

        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(employee1);
        expectedEmployees.add(employee2);

        List<Employee> actualEmployees = employeeService.findAll();

        assertThat(actualEmployees, is(equalTo(expectedEmployees)));
    }

    @Test
    void testUpdate() {
        Employee expectedEmployee = createTestEmployee1();
        employeeService.persist(expectedEmployee);

        expectedEmployee.setPosition("Caretaker");

        Employee actualEmployee = employeeService.findById(expectedEmployee.getId());
        actualEmployee.setPosition("Caretaker");
        employeeService.update(actualEmployee);

        actualEmployee = employeeService.findById(expectedEmployee.getId());

        assertThat(actualEmployee, is(equalTo(expectedEmployee)));
    }

    @Test
    void testDelete() {
        Employee employee1 = createTestEmployee1();
        employeeService.persist(employee1);

        Employee employee2 = createTestEmployee2();
        employeeService.persist(employee2);

        List<Employee> actualEmployees = employeeService.findAll();

        assertThat(actualEmployees.size(), is(equalTo(2)));

        employeeService.delete(employee1);

        actualEmployees = employeeService.findAll();

        assertThat(actualEmployees.size(), is(equalTo(1)));
    }

    @Test
    void testDeleteById() {
        Employee employee1 = createTestEmployee1();
        employeeService.persist(employee1);

        Employee employee2 = createTestEmployee2();
        employeeService.persist(employee2);

        List<Employee> actualEmployees = employeeService.findAll();

        assertThat(actualEmployees.size(), is(equalTo(2)));

        employeeService.deleteById(employee1.getId());

        actualEmployees = employeeService.findAll();

        assertThat(actualEmployees.size(), is(equalTo(1)));
    }

    @Test
    void testDeleteAll() {
        Employee employee1 = createTestEmployee1();
        employeeService.persist(employee1);

        Employee employee2 = createTestEmployee2();
        employeeService.persist(employee2);

        List<Employee> actualEmployees = employeeService.findAll();

        assertThat(actualEmployees.size(), is(equalTo(2)));

        employeeService.deleteAll();

        actualEmployees = employeeService.findAll();

        assertThat(actualEmployees.size(), is(equalTo(0)));
    }

    private static Employee createTestEmployee1() {
        return new Employee("Elon", "Musk", "CEO");
    }

    private static Employee createTestEmployee2() {
        return new Employee("Niklas", "Reinhard", "Caretaker");
    }
}
