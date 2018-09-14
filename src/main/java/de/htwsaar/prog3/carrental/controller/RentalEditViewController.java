package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.service.EmployeeService;
import de.htwsaar.prog3.carrental.service.RentalService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the Controller for the "Rent out Car View" of the Carrental Application.
 *
 * @author Michael Bös
 */

public class RentalEditViewController {

    private CarService carService = new CarService();

    private CustomerService customerService = new CustomerService();

    private EmployeeService employeeService = new EmployeeService();

    private RentalService rentalService = new RentalService();

    private Car car;

    @FXML
    private Label CarLable;

    @FXML
    private Label DurationLable;

    @FXML
    private Label CostsLable;

    @FXML
    private DatePicker BeginDatePicker;

    @FXML
    private DatePicker EndDatePicker;

    @FXML
    private TextField IDCardTextField;

    @FXML
    private TextField PostcodeTextField;

    @FXML
    private TextField DriverlicenseIDTextField;

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
    private Button SearchForCustomerButton;

    @FXML
    private Button ConfirmRentButton;

    @FXML
    private Button CancelButton;

    @FXML
    private Button ConfirmDatesButton;

    public void initialize(URL location, ResourceBundle resources) {
        CarLable.setText("Car:" + car.getBrand() + " " + car.getModel());
    }

    public void handleConfirmDatesButtonClicked() {

    }

    public void handleSearchCustomerButtonClicked() {

    }
}
