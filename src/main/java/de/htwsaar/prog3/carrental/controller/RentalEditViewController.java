package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.service.GenericService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * This is the Controller for the "Rental Edit View" of the Carrental Application.
 *
 * @author Hagen Schackmann, Michael Bös
 */

public class RentalEditViewController {

    private GenericService<Customer> customerservice;

    private ObservableList<Customer> entities;

    private Customer customer;

    private Rental rental;

    @Setter
    private Stage modalStage;

    @Getter
    private boolean applyClicked = false;

    @FXML
    private Label carLabel;

    @FXML
    private ChoiceBox employeeChoiceBox;

    @FXML
    private Label durationLabel;

    @FXML
    private Label dailyPriceLabel;

    @FXML
    private TextField dailyPriceTextField;

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


    public void initialize(Rental rental) {
        this.rental = rental;

        beginDatePicker.setValue(getDatePickerConverter().fromString(rental.getBegin()));
        endDatePicker.setValue(getDatePickerConverter().fromString(rental.getEnd()));
        noteTextArea.setText(rental.getNote());
        driverLicenseIdTextField.requestFocus();

        Car car = rental.getCar();
        if (car != null) {
            carLabel.setText(car.toString());
        }

        customer = rental.getCustomer();
        fillCustomerFields();
    }

    public void handleConfirmButtonClicked() {
    }

    public void handleSearchButtonClicked() {

        customer = findByDriverID(driverLicenseIdTextField.getText());
        fillCustomerFields();
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
      rental.setBegin(getDatePickerConverter().toString(beginDatePicker.getValue()));
      rental.setEnd(getDatePickerConverter().toString(endDatePicker.getValue()));
     // rental.setEmployee();
      rental.setCustomer(findByDriverID(driverLicenseIdTextField.getText()));
      rental.setExtraCosts(Integer.parseInt(dailyPriceTextField.getText()));
      rental.setNote(noteTextArea.getText());

      applyClicked = true;
      modalStage.close();
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


    private Customer findByDriverID(String driverID) {
        customerservice = new CustomerService();
        entities = FXCollections.observableArrayList(customerservice.findAll());
        Customer correctCustomer;
        int i;

        for(i = 0; i < entities.size(); i++) {
            if ((entities.get(i).getDriverLicenseId()).contentEquals(driverID)) {
                correctCustomer = entities.get(i);
                System.out.println("FoundFoundFound: " + entities.get(i).getFirstName());
                return correctCustomer;
            }
        } return null;
    }


    private void fillCustomerFields() {
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

    private boolean isInputValid() {
        String errorMessage = "";

        if (driverLicenseIdTextField.getText() == null || driverLicenseIdTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidDriverLicenseId() + "\n";
        }

        if (firstNameTextField.getText() == null || firstNameTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidFirstName() + "\n";
        }

        if (lastNameTextField.getText() == null || lastNameTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidLastName() + "\n";
        }

        if (idNumberTextField.getText() == null || idNumberTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidIdNumber() + "\n";
        }

        if (dateOfBirthTextField.getText() == null
                || dateOfBirthTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidDateOfBirth() + "\n";
        }

        if (zipCodeTextField.getText() == null
                || zipCodeTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidZipCode() + "\n";
        }

        if (cityTextField.getText() == null || cityTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidCityName() + "\n";
        }

        if (streetTextField.getText() == null || streetTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidStreetName() + "\n";
        }

        if (houseNumberTextField.getText() == null || houseNumberTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidHouseNumber() + "\n";
        }

        if (emailTextField.getText() == null || emailTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidEmailAddress() + "\n";
        }

        if (phoneNumberTextField.getText() == null || phoneNumberTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getRentalNoValidPhoneNumber() + "\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(modalStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }
}

