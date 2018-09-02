package de.htwsaar.prog3.carrental.model.producer;

import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.service.EmployeeService;
import de.htwsaar.prog3.carrental.service.RentalService;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;

import java.util.ArrayList;
import java.util.List;

public class RentalProducer {
    private static CarService carService = new CarService();
    private static CustomerService customerService = new CustomerService();
    private static EmployeeService employeeService = new EmployeeService();
    private static RentalService rentalService = new RentalService();

    private static void produce() {
        List<Rental> list = new ArrayList<>();

        list.add(new Rental("02.08.2018", carService.findById(1L), customerService.findById(1L),
                employeeService.findById(2L), "02.09.2018", 0, ""));
        list.add(new Rental("16.04.2018", carService.findById(7L), customerService.findById(3L),
                employeeService.findById(2L), "25.04.2018", 200, "Damaged two tires"));
        list.add(new Rental("10.06.2017", carService.findById(5L), customerService.findById(2L),
                employeeService.findById(3L), "01.06.2017", 0, ""));
        list.add(new Rental("25.10.2017", carService.findById(10L), customerService.findById(5L),
                employeeService.findById(4L), "12.11.2017", 0, ""));
        list.add(new Rental("18.02.2018", carService.findById(8L), customerService.findById(4L),
                employeeService.findById(5L), "20.03.2018", 0, ""));
        list.add(new Rental("13.03.2018", carService.findById(2L), customerService.findById(6L),
                employeeService.findById(7L), "25.03.2018", 0, ""));
        list.add(new Rental("20.10.2017", carService.findById(11L), customerService.findById(7L),
                employeeService.findById(6L), "01.11.2017", 0, ""));
        list.add(new Rental("03.05.2018", carService.findById(6L), customerService.findById(10L),
                employeeService.findById(3L), "25.03.2018", 0, ""));
        list.add(new Rental("10.08.2017", carService.findById(4L), customerService.findById(9L),
                employeeService.findById(1L), "20.08.2017", 300, "Broke left wing mirror"));
        list.add(new Rental("06.07.2018", carService.findById(3L), customerService.findById(8L),
                employeeService.findById(6L), "10.08.2018", 0, ""));


        for (Rental rental : list) {
            rentalService.persist(rental);
        }
    }

    public static void main(String[] args) {
        produce();
        EntityManagerUtil.closeEntityManagerFactory();
    }
}
