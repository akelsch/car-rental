package de.htwsaar.prog3.carrental.application;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.model.car.*;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceSchemaCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements ApplicationListener<DataSourceSchemaCreatedEvent> {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final RentalRepository rentalRepository;

    @Override
    public void onApplicationEvent(DataSourceSchemaCreatedEvent event) {
        // TODO Jens: add generator for all entities

        Car car = Car.builder()
                .year(Year.of(2017))
                .brand("BMW")
                .model("M4")
                .type(Type.COUPE)
                .color(Color.WHITE)
                .dailyRate(120)
                .doors(2)
                .transmission(Transmission.MANUAL)
                .fuel(Fuel.PETROL)
                .horsepower(480)
                .mileage(25_000)
                .tires(Tire.SUMMER)
                .parkingLot("2A")
                .licenseNumber("SB M 4")
                .vin("M4BMW123412341234")
                .nextInspection(YearMonth.of(2021, 6))
                .build();

        Customer customer = Customer.builder()
                .firstName("Peter")
                .lastName("Pan")
                .street("Otto-Hahn-Straße 42")
                .zipcode(66111)
                .city("Saarbrücken")
                .phone("+49123456789")
                .email("peter@pan.de")
                .dateOfBirth(LocalDate.now().minusYears(20))
                .idNumber("T22000129")
                .driverLicenseNumber("B072RRE2I55")
                .build();

        Employee employee = Employee.builder()
                .firstName("Spongebob")
                .lastName("Schwammkopf")
                .position("Burgerbrater")
                .build();

        carRepository.save(car);
        customerRepository.save(customer);
        employeeRepository.save(employee);

        Rental rental = Rental.builder()
                .start(LocalDate.now())
                .end(LocalDate.now().plusDays(5))
                .car(car)
                .customer(customer)
                .employee(employee)
                .build();

        rentalRepository.save(rental);
    }
}
