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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;
import java.net.URL;
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
    private Label carLable;

    @FXML
    private Label durationLable;

    @FXML
    private Label costsLable;

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
    }

    public boolean isApplyClicked() {
        return applyClicked;
    }

    public void initialize(URL location, ResourceBundle resources) {
        carLable.setText("Car:" + car.getBrand() + " " + car.getModel());
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
}
