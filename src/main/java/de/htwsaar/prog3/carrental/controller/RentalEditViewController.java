package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.service.EmployeeService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * This is the Controller for the "Rental Edit View" of the Carrental Application.
 *
 * @author Hagen Schackmann, Michael Bös
 */

public class RentalEditViewController {
    private Rental rental;

    private CustomerService customerService;
    private EmployeeService employeeService;

    @Setter
    private Stage modalStage;

    @Getter
    private boolean applyClicked = false;

    @FXML
    private Label carLabel;

    @FXML
    private ChoiceBox<Employee> employeeChoiceBox;

    @FXML
    private Label durationLabel;
    // TODO set durationLabel after making date selection

    @FXML
    private Label dailyRateLabel;

    @FXML
    private TextField extraCostsTextField;

    @FXML
    private Label sumLabel;
    // TODO calculate sumLabel

    @FXML
    private DatePicker beginDatePicker;

    @FXML
    private DatePicker endDatePicker;

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
    private TextField zipCodeTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField houseNumberTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextArea noteTextArea;

    public RentalEditViewController() {
        customerService = new CustomerService();
        employeeService = new EmployeeService();
    }

    public void initialize(Rental rental) {
        this.rental = rental;

        Car car = this.rental.getCar();
        if (car != null) {
            carLabel.setText(car.toString());
            dailyRateLabel.setText(Integer.toString(car.getDailyRate()));
        }

        employeeChoiceBox.setItems(FXCollections.observableArrayList(employeeService.findAll()));
        Employee employee = this.rental.getEmployee();
        if (employee != null) {
            employeeChoiceBox.setValue(employee);
        }

        fillCustomerTextFields();

        beginDatePicker.setValue(getDatePickerConverter().fromString(this.rental.getBegin()));
        endDatePicker.setValue(getDatePickerConverter().fromString(this.rental.getEnd()));
        noteTextArea.setText(this.rental.getNote());

        driverLicenseIdTextField.requestFocus();
    }

    public void handleConfirmButtonClicked() {
        // TODO implement
    }

    public void handleSearchButtonClicked() {
        String driverLicenseId = driverLicenseIdTextField.getText();

        if (driverLicenseId != null && !driverLicenseId.trim().isEmpty()) {
            Customer customer = findCustomerByDriverLicenseId(driverLicenseId);
            rental.setCustomer(customer);

            fillCustomerTextFields();
        }
    }

    public void handleCancelButtonClicked() {
        Alert confirmationDialog =
                DialogUtil.createConfirmationDialog(I18nComponentsUtil.getDialogCancelConfirmationText());

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.orElse(null) == ButtonType.OK) {
            modalStage.close();
        }
    }

    public void handleApplyButtonClicked() {
        if (isInputValid()) {
            rental.setBegin(getDatePickerConverter().toString(beginDatePicker.getValue()));
            // TODO Case where a new customer is created
            rental.setCustomer(findCustomerByDriverLicenseId(driverLicenseIdTextField.getText()));
            rental.setEmployee(employeeChoiceBox.getValue());
            rental.setEnd(getDatePickerConverter().toString(endDatePicker.getValue()));
            rental.setExtraCosts(Integer.parseInt(extraCostsTextField.getText()));
            rental.setNote(noteTextArea.getText());

            applyClicked = true;
            modalStage.close();
        }
    }

    private void fillCustomerTextFields() {
        Customer customer = this.rental.getCustomer();

        if (customer != null) {
            driverLicenseIdTextField.setText(customer.getDriverLicenseId());
            firstNameTextField.setText(customer.getFirstName());
            lastNameTextField.setText(customer.getLastName());
            idNumberTextField.setText(customer.getIdNumber());
            dateOfBirthTextField.setText(customer.getDateOfBirth());
            zipCodeTextField.setText(Integer.toString(customer.getZipCode()));
            cityTextField.setText(customer.getCity());
            streetTextField.setText(customer.getStreet());
            houseNumberTextField.setText(customer.getHouseNumber());
            emailTextField.setText(customer.getEmailAddress());
            phoneNumberTextField.setText(customer.getPhoneNumber());
        }
    }

    private StringConverter<LocalDate> getDatePickerConverter() {
        String pattern = "dd.MM.yyyy";

        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        beginDatePicker.setConverter(converter);
        beginDatePicker.setPromptText(pattern);

        endDatePicker.setConverter(converter);
        endDatePicker.setPromptText(pattern);

        return converter;
    }

    private Customer findCustomerByDriverLicenseId(String driverLicenseId) {
        List<Customer> customers = customerService.filter("driverLicenseId", "=", driverLicenseId);

        if (customers.size() == 1) {
            return customers.get(0);
        }

        return new Customer();
    }

    private boolean isInputValid() {
        StringBuilder sb = new StringBuilder();
        String errorMessage;

        // TODO add missing checks

        if (driverLicenseIdTextField.getText() == null || driverLicenseIdTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidDriverLicence());
            sb.append(System.lineSeparator());
        }

        if (firstNameTextField.getText() == null || firstNameTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidFirstName());
            sb.append(System.lineSeparator());
        }

        if (lastNameTextField.getText() == null || lastNameTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidLastName());
            sb.append(System.lineSeparator());
        }

        if (idNumberTextField.getText() == null || idNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidIdNumber());
            sb.append(System.lineSeparator());
        }

        if (dateOfBirthTextField.getText() == null || dateOfBirthTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidDateOfBirth());
            sb.append(System.lineSeparator());
        }

        if (zipCodeTextField.getText() == null || zipCodeTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidZipCode());
            sb.append(System.lineSeparator());
        }

        if (cityTextField.getText() == null || cityTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidCityName());
            sb.append(System.lineSeparator());
        }

        if (streetTextField.getText() == null || streetTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidStreetName());
            sb.append(System.lineSeparator());
        }

        if (houseNumberTextField.getText() == null || houseNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidHouseNumber());
            sb.append(System.lineSeparator());
        }

        if (emailTextField.getText() == null || emailTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidEmailAdress());
            sb.append(System.lineSeparator());
        }

        if (phoneNumberTextField.getText() == null || phoneNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidPhoneNumber());
            sb.append(System.lineSeparator());
        }

        errorMessage = sb.toString();
        if (!errorMessage.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(modalStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }
}
