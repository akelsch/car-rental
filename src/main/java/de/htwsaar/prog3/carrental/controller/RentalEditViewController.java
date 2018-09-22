package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.service.EmployeeService;
import de.htwsaar.prog3.carrental.service.RentalService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the Controller for the "Rental Edit View" of the Carrental Application.
 *
 * @author Hagen Schackmann, Michael Bös
 */
public class RentalEditViewController extends GenericEditViewController<Rental> {
    private CustomerService customerService;
    private EmployeeService employeeService;

    @FXML
    private Label carLabel;

    @FXML
    private ChoiceBox<Employee> employeeChoiceBox;

    @FXML
    private Label durationLabel;

    @FXML
    private Label dailyRateLabel;

    @FXML
    private TextField extraCostsTextField;

    @FXML
    private Label sumLabel;

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

    private long duration;

    public RentalEditViewController() {
        service = new RentalService();

        customerService = new CustomerService();
        employeeService = new EmployeeService();
    }

    @Override
    public void initialize(Rental rental) {
        entity = rental;

        Car car = entity.getCar();
        if (car != null) {
            carLabel.setText(car.toString());
            dailyRateLabel.setText(Integer.toString(car.getDailyRate()));
        }

        employeeChoiceBox.setItems(FXCollections.observableArrayList(employeeService.findAll()));
        Employee employee = entity.getEmployee();
        if (employee != null) {
            employeeChoiceBox.setValue(employee);
        }

        fillCustomerTextFields();

        beginDatePicker.setValue(getDatePickerConverter().fromString(entity.getBegin()));
        endDatePicker.setValue(getDatePickerConverter().fromString(entity.getEnd()));
        noteTextArea.setText(entity.getNote());

        driverLicenseIdTextField.requestFocus();
    }

    public void handleConfirmButtonClicked() {
        // calculate Date-to-Date Duration
        LocalDate begin = beginDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();
        duration = (Period.between(begin, end)).plusDays(1).getDays();

        if (isDateInputValid()) {
              durationLabel.setText(duration + " Day(s)");

              // calculate sumLabel
              String stringValue = dailyRateLabel.getText();
              double value = Double.parseDouble(stringValue);
              sumLabel.setText(value * duration + " €");
        }

    }


    public void handleSearchButtonClicked() {
        String driverLicenseId = driverLicenseIdTextField.getText();

        if (driverLicenseId != null && !driverLicenseId.trim().isEmpty()) {
            Customer customer = findCustomerByDriverLicenseId(driverLicenseId);
            entity.setCustomer(customer);

            fillCustomerTextFields();
        }
    }

    @Override
    public void handleApplyButtonClicked() {
        if (isInputValid()) {
            entity.setBegin(getDatePickerConverter().toString(beginDatePicker.getValue()));
            // TODO Case where a new customer is created
            entity.setCustomer(findCustomerByDriverLicenseId(driverLicenseIdTextField.getText()));
            entity.setEmployee(employeeChoiceBox.getValue());
            entity.setEnd(getDatePickerConverter().toString(endDatePicker.getValue()));
            entity.setExtraCosts(Integer.parseInt(extraCostsTextField.getText()));
            entity.setNote(noteTextArea.getText());

            applyClicked = true;
            modalStage.close();
        }
    }

    private boolean isDateInputValid() {
        StringBuilder sb = new StringBuilder();
        String errorMessage;

        if (duration < 1) {
            sb.append(I18nComponentsUtil.getRentalNoValidDateDuration());
            sb.append(System.lineSeparator());
        }

        System.out.println(duration);

        errorMessage = sb.toString();
        if (!errorMessage.isEmpty()) {
            Alert alert = DialogUtil.createErrorDialog(I18nComponentsUtil.getDialogErrorInvalidFieldsTitle(),
                    I18nComponentsUtil.getDialogErrorInvalidFieldsText(), errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }

    @Override
    boolean isInputValid() {
        StringBuilder sb = new StringBuilder();
        String errorMessage;

        List<Customer> customers;



        if (driverLicenseIdTextField.getText() == null || driverLicenseIdTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidDriverLicence());
            sb.append(System.lineSeparator());
        } else {
            customers = customerService.filter(I18nComponentsUtil.getCustomerDriverLicenseIdLabel(),
                    "=", driverLicenseIdTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(entity.getId()))
                    .collect(Collectors.toList());
            if (!customers.isEmpty()) {
                sb.append(I18nComponentsUtil.getCustomerNoValidDriverLicenceDuplicate());
                sb.append(System.lineSeparator());
            }
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
        } else {
            customers = customerService.filter(I18nComponentsUtil.getCustomerIdNumberLabel(), "=", idNumberTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(entity.getId()))
                    .collect(Collectors.toList());
            if (!customers.isEmpty()) {
                sb.append(I18nComponentsUtil.getCustomerNoValidIdNumberDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        if (dateOfBirthTextField.getText() == null || dateOfBirthTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidDateOfBirth());
            sb.append(System.lineSeparator());
        }

        if (zipCodeTextField.getText() == null || zipCodeTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidZipCode());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(zipCodeTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nComponentsUtil.getCustomerNoValidZipCode());
                sb.append(" ");
                sb.append(I18nComponentsUtil.getCustomerNoValidInteger());
                sb.append(System.lineSeparator());
            }
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
        } else {
            try {
                Integer.parseInt(houseNumberTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nComponentsUtil.getCustomerNoValidHouseNumber());
                sb.append(" ");
                sb.append(I18nComponentsUtil.getCustomerNoValidInteger());
                sb.append(System.lineSeparator());
            }
        }

        if (emailTextField.getText() == null || emailTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidEmailAddress());
            sb.append(System.lineSeparator());
        }

        if (phoneNumberTextField.getText() == null || phoneNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidPhoneNumber());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(phoneNumberTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nComponentsUtil.getCustomerNoValidPhoneNumber());
                sb.append(" ");
                sb.append(I18nComponentsUtil.getCustomerNoValidInteger());
                sb.append(System.lineSeparator());
            }
        }

        errorMessage = sb.toString();
        if (!errorMessage.isEmpty()) {
            Alert alert = DialogUtil.createErrorDialog(I18nComponentsUtil.getDialogErrorInvalidFieldsTitle(),
                    I18nComponentsUtil.getDialogErrorInvalidFieldsText(), errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }

    private void fillCustomerTextFields() {
        Customer customer = entity.getCustomer();

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
}

