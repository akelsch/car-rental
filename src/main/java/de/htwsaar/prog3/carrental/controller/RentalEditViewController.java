package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.service.EmployeeService;
import de.htwsaar.prog3.carrental.service.RentalService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the Controller for the "Rental Edit View" of the Carrental Application.
 *
 * @author Hagen Schackmann, Michael Bös
 */

public class RentalEditViewController {
    private CarService carService = new CarService();
    private CustomerService customerService = new CustomerService();
    private EmployeeService employeeService = new EmployeeService();
    private RentalService rentalService = new RentalService();

    private Car car;
    private Customer customer;
    private Employee employee;
    private Rental rental;

    private Stage modalStage;

    private boolean applyClicked = false;

    @FXML
    private Label CarPlaceholderLabel;

    @FXML
    private Label DurationPlaceholderLabel;

    @FXML
    private Label SumCostPlaceholderLabel;

    @FXML
    private TextField IDCardTextField;

    @FXML
    private TextField PostcodeTextField;

    @FXML
    private TextField DriverLicenseIDTextField;

    @FXML
    private TextField DateOfBirthTextField;

    @FXML
    private TextField PhoneNumberTextField;

    @FXML
    private TextField LastNameTextField;

    @FXML
    private TextField StreetTextField;

    @FXML
    private TextField HouseNumberTextField;

    @FXML
    private TextField FirstNameTextField;

    @FXML
    private TextField EMailTextField;

    @FXML
    private TextField CityTextField;

    @FXML
    private TextArea NotesTextArea;

    @FXML
    private TextField searchCustomerField;

    @FXML
    private TextField extraCosts;

    @FXML
    private TextField note;

    @FXML
    private DatePicker beginDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button searchForCustomerButton;

    @FXML
    private Button confirmRentButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmDatesButton;

    public void setModalStage(Stage modalStage) {
        this.modalStage = modalStage;
    }

    public void setRental(Rental rental) {
        this.rental = rental;

        Customer customer = rental.getCustomer();

        if (customer != null) {

            IDCardTextField.setText(customer.getIdNumber());
            PostcodeTextField.setText(Integer.toString(customer.getZipCode()));
            DriverLicenseIDTextField.setText(customer.getDriverLicenseId());
            DateOfBirthTextField.setText(customer.getDateOfBirth());
            PhoneNumberTextField.setText(customer.getPhoneNumber());
            LastNameTextField.setText(customer.getLastName());
            FirstNameTextField.setText(customer.getFirstName());
            StreetTextField.setText(customer.getStreet());
            HouseNumberTextField.setText(customer.getHouseNumber());
            CityTextField.setText(customer.getCity());
            EMailTextField.setText(customer.getEmailAddress());

        }

        NotesTextArea.setText(rental.getNote());
        beginDatePicker.setValue(getDatePickerConverter().fromString(rental.getBegin()));
        endDatePicker.setValue(getDatePickerConverter().fromString(rental.getEnd()));

        if (rental.getCar() != null) {
            CarPlaceholderLabel.setText(" " + rental.getCar().getBrand() + " " + rental.getCar().getModel());
        }
    }

    public boolean isApplyClicked() {
        return applyClicked;
    }

    public void initialize(URL location, ResourceBundle resources) {
        CarPlaceholderLabel.setText("Car:" + car.getBrand() + " " + car.getModel());
    }

    public void handleConfirmDatesButtonClicked() {

    }

    public void handleSearchCustomerButtonClicked() {

    }

    public void handleCancelButtonClicked() {
        Alert confirmationDialog =
                DialogUtil.createConfirmationDialog(I18nComponentsUtil.getDialogCancelConfirmationText());

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.orElse(null) == ButtonType.OK) {
            modalStage.close();
        }
    }

    public void handleConfirmRentButtonClicked() {
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setEmployee(employee);
        // ...
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
}
