package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.view.EditCarView;
import de.htwsaar.prog3.carrental.view.RentOutCarView;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.service.RentalService;
import de.htwsaar.prog3.carrental.service.EmployeeService;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.service.CarService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.time.Duration;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the Controller for the "Rent out Car View" of the Carrental Application.
 *
 * @author Michael Bös
 */

public class RentOutCarViewController {

    private CarService carService = new CarService();

    private CustomerService customerService = new CustomerService();

    private EmployeeService employeeService = new EmployeeService();

    private RentalService rentalService = new RentalService();

    private Car car = EditCarView.getCar();



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

    protected void handleConfirmDatesButtonClicked(ActionEvent event) {

    }

    protected void handleSearchCustomerButtonClicked(ActionEvent event) {

    }

}
