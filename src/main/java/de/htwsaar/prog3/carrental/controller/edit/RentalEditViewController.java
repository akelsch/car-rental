package de.htwsaar.prog3.carrental.controller.edit;

import com.sun.javafx.scene.control.IntegerField;
import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import de.htwsaar.prog3.carrental.util.MessageUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * JavaFX controller for the "Edit Rental" view.
 *
 * @author Hagen Schackmann
 * @author Michael Bös
 */
@Component
@RequiredArgsConstructor
public class RentalEditViewController extends EditViewController<Rental> {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @FXML
    private TextField carTextField;
    @FXML
    private ChoiceBox<Employee> employeeChoiceBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField durationTextField;
    @FXML
    private TextField dailyRateTextField;
    @FXML
    private IntegerField extraCostsIntegerField;
    @FXML
    private TextField sumTextField;

    @FXML
    private TextField driverLicenseIdTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField idNumberTextField;
    @FXML
    private TextField dateOfBirthTextField;
    @FXML
    private IntegerField zipcodeIntegerField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextArea noteTextArea;

    @Override
    public void postInitialize() {
        // Car
        Car car = entity.getCar();
        if (car != null) {
            carTextField.setText(car.toString());
            dailyRateTextField.setText(Integer.toString(car.getDailyRate()));
        }

        // Employee
        employeeChoiceBox.setItems(FXCollections.observableArrayList(employeeRepository.findAll()));
        Employee employee = entity.getEmployee();
        if (employee != null) {
            employeeChoiceBox.setValue(employee);
        }

        // Rental
        initDatePickers();
        extraCostsIntegerField.valueProperty().addListener((x, y, z) -> handleDateAndPriceChanges());
        extraCostsIntegerField.setValue(entity.getExtraCosts());
        noteTextArea.setText(entity.getNote());

        // Customer
        fillCustomerTextFields();
        driverLicenseIdTextField.requestFocus();
    }

    public void handleDateAndPriceChanges() {
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();

        if (start != null && end != null) {
            long duration = start.until(end, ChronoUnit.DAYS);
            int dailyRate = entity.getCar().getDailyRate();
            int extraCosts = extraCostsIntegerField.getValue();
            long sum = (duration * dailyRate) + extraCosts;

            String durationText = "%d %s".formatted(duration, messageUtils.getMessage(MessageUtils.RENTAL_LABEL_DURATION_DAYS));
            String sumText = "%d %s".formatted(sum, messageUtils.getMessage(MessageUtils.RENTAL_LABEL_TOTAL_CURRENCY));
            durationTextField.setText(durationText);
            sumTextField.setText(sumText);
        } else {
            durationTextField.setText(null);
            sumTextField.setText(null);
        }
    }

    /**
     * Handle pressing the "Search" button.
     */
    public void handleSearchButtonClicked() {
        String driverLicenseId = driverLicenseIdTextField.getText();

        if (StringUtils.isNotBlank(driverLicenseId)) {
            Customer customer = findCustomerByDriverLicenseId(driverLicenseId);
            entity.setCustomer(customer);

            fillCustomerTextFields();

            if (customer.getId() != null) {
                firstNameTextField.setDisable(true);
                lastNameTextField.setDisable(true);
                idNumberTextField.setDisable(true);
                dateOfBirthTextField.setDisable(true);
                zipcodeIntegerField.setDisable(true);
                cityTextField.setDisable(true);
                streetTextField.setDisable(true);
                emailTextField.setDisable(true);
                phoneNumberTextField.setDisable(true);
            } else {
                firstNameTextField.setDisable(false);
                lastNameTextField.setDisable(false);
                idNumberTextField.setDisable(false);
                dateOfBirthTextField.setDisable(false);
                zipcodeIntegerField.setDisable(false);
                cityTextField.setDisable(false);
                streetTextField.setDisable(false);
                emailTextField.setDisable(false);
                phoneNumberTextField.setDisable(false);
            }
        }
    }

    @Override
    public void handleApplyButtonClicked() {
        entity.setStart(startDatePicker.getValue());
        entity.setCustomer(findCustomerByDriverLicenseId(driverLicenseIdTextField.getText()));
        entity.setEmployee(employeeChoiceBox.getValue());
        entity.setEnd(endDatePicker.getValue());
        entity.setExtraCosts(extraCostsIntegerField.getValue());
        entity.setNote(noteTextArea.getText());

        if (isInputValid(entity)) {
            if (findCustomerByDriverLicenseId(driverLicenseIdTextField.getText()).getId() == null) {
                Customer customer = new Customer();

                customer.setDriverLicenseNumber(driverLicenseIdTextField.getText());
                customer.setFirstName(firstNameTextField.getText());
                customer.setLastName(lastNameTextField.getText());
                customer.setIdNumber(idNumberTextField.getText());
//                customer.setDateOfBirth(dateOfBirthTextField.getText());
                customer.setZipcode(zipcodeIntegerField.getValue());
                customer.setCity(cityTextField.getText());
                customer.setStreet(streetTextField.getText());
                customer.setEmail(emailTextField.getText());
                customer.setPhone(phoneNumberTextField.getText());

                customerRepository.save(customer);
                entity.setCustomer(customer);
            }
            closeModalWithApply();
        }
    }

    private void initDatePickers() {
        startDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            LocalDate endDate = endDatePicker.getValue();
            if (endDate != null && endDate.isBefore(newValue.plusDays(1))) {
                endDatePicker.setValue(null);
            }
        });
        startDatePicker.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
        endDatePicker.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(startDatePicker.getValue().plusDays(1))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        startDatePicker.setValue(Objects.requireNonNullElseGet(entity.getStart(), LocalDate::now));
        endDatePicker.setValue(entity.getEnd());
        handleDateAndPriceChanges();
    }

    private void fillCustomerTextFields() {
        Customer customer = entity.getCustomer();

        if (customer != null) {
            driverLicenseIdTextField.setText(customer.getDriverLicenseNumber());
            firstNameTextField.setText(customer.getFirstName());
            lastNameTextField.setText(customer.getLastName());
            idNumberTextField.setText(customer.getIdNumber());
            dateOfBirthTextField.setText(customer.getDateOfBirth().toString());
            zipcodeIntegerField.setValue(customer.getZipcode());
            cityTextField.setText(customer.getCity());
            streetTextField.setText(customer.getStreet());
            emailTextField.setText(customer.getEmail());
            phoneNumberTextField.setText(customer.getPhone());
        }
    }

    private Customer findCustomerByDriverLicenseId(String driverLicenseId) {
        return customerRepository.findByDriverLicenseNumber(driverLicenseId).orElseGet(Customer::new);
    }
}
