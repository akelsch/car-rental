package de.htwsaar.prog3.carrental.controller.edit;

import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import de.htwsaar.prog3.carrental.util.DateUtils;
import de.htwsaar.prog3.carrental.util.MessageUtils;
import de.htwsaar.prog3.carrental.util.fx.IntegerField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * JavaFX controller for the "Edit Rental" view.
 *
 * @author Hagen Schackmann
 * @author Michael Bös
 */
@Controller
@RequiredArgsConstructor
public class RentalEditViewController extends EditViewController<Rental> {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @FXML
    private ComboBox<Customer> customerComboBox;
    @FXML
    private ComboBox<Employee> employeeComboBox;
    @FXML
    private TextField carTextField;
    @FXML
    private TextField dailyRateTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField durationTextField;
    @FXML
    private IntegerField extraCostsIntegerField;
    @FXML
    private TextField sumTextField;
    @FXML
    private TextArea noteTextArea;

    private Predicate<LocalDate> startDatePickerPredicate = date -> date.isBefore(LocalDate.now());
    private Predicate<LocalDate> endDatePickerPredicate = date -> date.isBefore(startDatePicker.getValue().plusDays(1));

    @Override
    public void postInitialize() {
        // Customer
        customerComboBox.setItems(FXCollections.observableArrayList(customerRepository.findAll()));
        Customer customer = entity.getCustomer();
        if (customer != null) {
            customerComboBox.setValue(customer);
        }

        // Employee
        employeeComboBox.setItems(FXCollections.observableArrayList(employeeRepository.findAll()));
        Employee employee = entity.getEmployee();
        if (employee != null) {
            employeeComboBox.setValue(employee);
        }

        // Car
        Car car = entity.getCar();
        if (car != null) {
            carTextField.setText(car.toString());
            dailyRateTextField.setText(Integer.toString(car.getDailyRate()));
            startDatePickerPredicate = startDatePickerPredicate.or(excludeRentedDurations(car));
            endDatePickerPredicate = endDatePickerPredicate.or(excludeFirstRentedDurationAfterStart(car));
        }

        // Rental
        initDatePickers();
        handleDateAndPriceChanges();
        extraCostsIntegerField.valueProperty().addListener((x, y, z) -> handleDateAndPriceChanges());
        extraCostsIntegerField.setValue(entity.getExtraCosts());
        noteTextArea.setText(entity.getNote());
    }

    public void handleDateAndPriceChanges() {
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();

        if (start != null && end != null) {
            long duration = start.until(end, ChronoUnit.DAYS);
            int dailyRate = entity.getCar().getDailyRate();
            int extraCosts = extraCostsIntegerField.getValue();
            long sum = (duration * dailyRate) + extraCosts;

            String durationText = "%d %s".formatted(duration, getMessageUtils().getMessage(MessageUtils.RENTAL_LABEL_DURATION_DAYS));
            String sumText = "%d %s".formatted(sum, getMessageUtils().getMessage(MessageUtils.RENTAL_LABEL_TOTAL_CURRENCY));
            durationTextField.setText(durationText);
            sumTextField.setText(sumText);
        } else {
            durationTextField.setText(null);
            sumTextField.setText(null);
        }
    }

    @Override
    public void handleApplyButtonClicked() {
        entity.setStart(startDatePicker.getValue());
        entity.setEnd(endDatePicker.getValue());
        entity.setCustomer(customerComboBox.getValue());
        entity.setEmployee(employeeComboBox.getValue());
        entity.setExtraCosts(extraCostsIntegerField.getValue());
        entity.setNote(noteTextArea.getText());

        if (isInputValid(entity)) {
            rentalRepository.save(entity);
            closeModalWithApply();
        }
    }

    private Predicate<LocalDate> excludeRentedDurations(Car car) {
        return date -> {
            for (Rental rental : rentalRepository.findAllByCarId(car.getId())) {
                if (date.isAfter(rental.getStart().minusDays(1))
                        && date.isBefore(rental.getEnd().plusDays(1))) {
                    return true;
                }
            }
            return false;
        };
    }

    private Predicate<LocalDate> excludeFirstRentedDurationAfterStart(Car car) {
        return date -> {
            Rental earliestRental = rentalRepository.findFirstByCarIdOrderByStart(car.getId());
            if (earliestRental != null) {
                return date.isAfter(earliestRental.getStart().minusDays(1))
                        && earliestRental.getStart().isAfter(startDatePicker.getValue());
            }
            return false;
        };
    }

    private void initDatePickers() {
        startDatePicker.setDayCellFactory(DateUtils.createDayCellFactory(startDatePickerPredicate));
        endDatePicker.setDayCellFactory(DateUtils.createDayCellFactory(endDatePickerPredicate));

        startDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            LocalDate endDate = endDatePicker.getValue();
            if (endDate != null && endDate.isBefore(newValue.plusDays(1))) {
                endDatePicker.setValue(null);
            }
        });

        startDatePicker.setValue(Objects.requireNonNullElseGet(entity.getStart(), LocalDate::now));
        endDatePicker.setValue(entity.getEnd());
    }
}
