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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This is the Controller for the "Rental Edit View" of the Carrental Application.
 *
 * @author Hagen Schackmann, Michael Bös
 */
public class RentalEditViewController extends GenericEditViewController<Rental> {
    private CustomerService customerService;
    private EmployeeService employeeService;

    private long duration = -1;

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

    public RentalEditViewController() {
        service = new RentalService();

        customerService = new CustomerService();
        employeeService = new EmployeeService();
    }

    @Override
    public void initialize(Rental rental) {
        entity = rental;

        // Car fields
        Car car = entity.getCar();
        if (car != null) {
            carLabel.setText(car.toString());
            dailyRateLabel.setText(Integer.toString(car.getDailyRate()));
        }

        // Customer fields
        fillCustomerTextFields();

        // Employee fields
        employeeChoiceBox.setItems(FXCollections.observableArrayList(employeeService.findAll()));
        Employee employee = entity.getEmployee();
        if (employee != null) {
            employeeChoiceBox.setValue(employee);
        }

        // Rental fields
        LocalDate beginDate = getDatePickerConverter().fromString(entity.getBegin());
        if (beginDate != null) {
            beginDatePicker.setValue(beginDate);
        } else {
            beginDatePicker.setValue(LocalDate.now());
        }

        endDatePicker.setDayCellFactory(getDayCellFactory());
        endDatePicker.setValue(getDatePickerConverter().fromString(entity.getEnd()));

        extraCostsTextField.setText(Integer.toString(rental.getExtraCosts()));
        noteTextArea.setText(entity.getNote());

        // Focus
        driverLicenseIdTextField.requestFocus();
    }

    /**
     * Handle pressing the "Confirm" button.
     */
    public void handleConfirmButtonClicked() {
        setDatePickerBorderIfIsEmpty();

        int dailyRate = Integer.parseInt(dailyRateLabel.getText());

        LocalDate begin = beginDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();

        if (begin != null && end != null) {
            duration = Duration.between(begin.atStartOfDay(), end.atStartOfDay()).toDays();
            durationLabel.setText(String.format("%d %s", duration, I18nComponentsUtil.getRentalDurationLabel()));

            try {
                int extraCosts = Integer.parseInt(extraCostsTextField.getText());
                double sum = (dailyRate * duration) + extraCosts;
                sumLabel.setText(String.format("%.2f %s", sum, I18nComponentsUtil.getRentalCurrencyLabel()));
            } catch (NumberFormatException e) {
                Alert alert = DialogUtil.createErrorDialog(I18nComponentsUtil.getDialogErrorInvalidFieldsTitle(),
                        I18nComponentsUtil.getDialogErrorInvalidFieldsText(),
                        I18nComponentsUtil.getRentalNoValidExtraCosts() + " " + I18nComponentsUtil.getDialogInvalidNumberText());

                alert.showAndWait();
            }
        }
    }

    /**
     * Handle pressing the "Search" button.
     */
    public void handleSearchButtonClicked() {
        String driverLicenseId = driverLicenseIdTextField.getText();

        if (driverLicenseId != null && !driverLicenseId.trim().isEmpty()) {
            Customer customer = findCustomerByDriverLicenseId(driverLicenseId);
            entity.setCustomer(customer);

            fillCustomerTextFields();

            if (customer.getId() != null) {
                firstNameTextField.setDisable(true);
                lastNameTextField.setDisable(true);
                idNumberTextField.setDisable(true);
                dateOfBirthTextField.setDisable(true);
                zipCodeTextField.setDisable(true);
                cityTextField.setDisable(true);
                streetTextField.setDisable(true);
                houseNumberTextField.setDisable(true);
                emailTextField.setDisable(true);
                phoneNumberTextField.setDisable(true);
            } else {
                firstNameTextField.setDisable(false);
                lastNameTextField.setDisable(false);
                idNumberTextField.setDisable(false);
                dateOfBirthTextField.setDisable(false);
                zipCodeTextField.setDisable(false);
                cityTextField.setDisable(false);
                streetTextField.setDisable(false);
                houseNumberTextField.setDisable(false);
                emailTextField.setDisable(false);
                phoneNumberTextField.setDisable(false);
            }
        }
    }

    @Override
    public void handleApplyButtonClicked() {
        if (isInputValid()) {
            if (findCustomerByDriverLicenseId(driverLicenseIdTextField.getText()).getId() == null) {
                Customer customer = new Customer();

                customer.setDriverLicenseId(driverLicenseIdTextField.getText());
                customer.setFirstName(firstNameTextField.getText());
                customer.setLastName(lastNameTextField.getText());
                customer.setIdNumber(idNumberTextField.getText());
                customer.setDateOfBirth(dateOfBirthTextField.getText());
                customer.setZipCode(Integer.parseInt(zipCodeTextField.getText()));
                customer.setCity(cityTextField.getText());
                customer.setStreet(streetTextField.getText());
                customer.setHouseNumber(houseNumberTextField.getText());
                customer.setEmailAddress(emailTextField.getText());
                customer.setPhoneNumber(phoneNumberTextField.getText());

                customerService.persist(customer);
                entity.setCustomer(customer);
            }

            entity.setBegin(getDatePickerConverter().toString(beginDatePicker.getValue()));
            entity.setCustomer(findCustomerByDriverLicenseId(driverLicenseIdTextField.getText()));
            entity.setEmployee(employeeChoiceBox.getValue());
            entity.setEnd(getDatePickerConverter().toString(endDatePicker.getValue()));
            entity.setExtraCosts(Integer.parseInt(extraCostsTextField.getText()));
            entity.setNote(noteTextArea.getText());

            applyClicked = true;
            modalStage.close();
        }
    }

    @Override
    boolean isInputValid() {
        StringBuilder sb = new StringBuilder();

        if (duration < 0.0) {
            sb.append(I18nComponentsUtil.getRentalNoValidDuration());
            sb.append(System.lineSeparator());
        }

        if (employeeChoiceBox.getValue() == null) {
            sb.append(I18nComponentsUtil.getRentalNoValidEmployee());
            sb.append(System.lineSeparator());
        }

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
        } else {
            try {
                Integer.parseInt(zipCodeTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nComponentsUtil.getCustomerNoValidZipCode());
                sb.append(" ");
                sb.append(I18nComponentsUtil.getDialogInvalidNumberText());
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
        }

        if (emailTextField.getText() == null || emailTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidEmailAddress());
            sb.append(System.lineSeparator());
        }

        if (phoneNumberTextField.getText() == null || phoneNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidPhoneNumber());
            sb.append(System.lineSeparator());
        }

        String errorMessage = sb.toString();
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

    private Callback<DatePicker, DateCell> getDayCellFactory() {
        return new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(beginDatePicker.getValue().plusDays(1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
    }

    private void setDatePickerBorderIfIsEmpty() {
        // Border radii taken from modena.css
        Border border = new Border(new BorderStroke(
                Color.RED,
                BorderStrokeStyle.SOLID,
                new CornerRadii(3.0, 3.0, 2.0, 1.0, false),
                BorderWidths.DEFAULT
        ));

        if (beginDatePicker.getValue() == null) {
            beginDatePicker.setBorder(border);
        } else {
            beginDatePicker.setBorder(null);
        }

        if (endDatePicker.getValue() == null) {
            endDatePicker.setBorder(border);
        } else {
            endDatePicker.setBorder(null);
        }

        if (extraCostsTextField.getText().isEmpty()) {
            extraCostsTextField.setBorder(border);
        } else {
            extraCostsTextField.setBorder(null);
        }
    }

    private Customer findCustomerByDriverLicenseId(String driverLicenseId) {
        List<Customer> customers = customerService.filter("driverLicenseId", "=", driverLicenseId);

        if (customers.size() == 1) {
            return customers.get(0);
        }

        return new Customer();
    }
}
