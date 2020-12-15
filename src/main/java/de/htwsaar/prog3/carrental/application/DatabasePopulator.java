package de.htwsaar.prog3.carrental.application;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.model.generator.CarGenerator;
import de.htwsaar.prog3.carrental.model.generator.CustomerGenerator;
import de.htwsaar.prog3.carrental.model.generator.EmployeeGenerator;
import de.htwsaar.prog3.carrental.model.generator.RentalGenerator;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceSchemaCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements ApplicationListener<DataSourceSchemaCreatedEvent> {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final RentalRepository rentalRepository;

    @Override
    public void onApplicationEvent(DataSourceSchemaCreatedEvent event) {
        List<Car> cars = new CarGenerator().generate(50);
        carRepository.saveAll(cars);

        List<Customer> customers = new CustomerGenerator().generate(50);
        customerRepository.saveAll(customers);

        List<Employee> employees = new EmployeeGenerator().generate(50);
        employeeRepository.saveAll(employees);

        List<Rental> rentals = new RentalGenerator().generate(50);
        for (Rental rental : rentals) {
            carRepository.save(rental.getCar());
            customerRepository.save(rental.getCustomer());
            employeeRepository.save(rental.getEmployee());
            rentalRepository.save(rental);
        }
    }
}
