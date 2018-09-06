package de.htwsaar.prog3.carrental.controller;

import java.util.Optional;
import de.htwsaar.prog3.carrental.view.NewCustomerView;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This is the Controller for the "New Customer View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class NewCustomerViewController {

    private CustomerService service = new CustomerService();
    private Customer customer = new Customer();

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailAddressTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField dateOfBirthTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField houseNumberTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField zipCodeTextField;

    @FXML
    private TextField idNumberTextField;

    @FXML
    private TextField driverLicenseIdTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button applyButton;

    /**
     * Handle Cancel Button clicked
     * 
     * @param event
     */
    @FXML
    protected void handleCancelButtonClicked(ActionEvent event) {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog
                .setTitle(I18nComponentsUtil.getCancelCreationConfirmationDialogTitleString());
        confirmationDialog.setHeaderText(
                I18nComponentsUtil.getCancelCreationConfirmationDialogHeaderString());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.get() == ButtonType.OK) {
            NewCustomerView.closeModalWindow();
        }
    }

    /**
     * Handle Apply Button clicked
     * 
     * @param event
     */
    @FXML
    protected void handleApplyButtonClicked(ActionEvent event) {
        // TODO show details + confirmation + valid data check
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog
                .setTitle(I18nComponentsUtil.getApplyCreationConfirmationDialogTitleString());
        confirmationDialog
                .setHeaderText(I18nComponentsUtil.getApplyCreationConfirmationDialogHeaderString());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.get() == ButtonType.OK) {
            customer.setFirstName(firstNameTextField.getText());
            customer.setLastName(lastNameTextField.getText());
            customer.setEmailAddress(emailAddressTextField.getText());
            customer.setPhoneNumber(phoneNumberTextField.getText());
            customer.setDateOfBirth(dateOfBirthTextField.getText());
            customer.setStreet(streetTextField.getText());
            customer.setHouseNumber(houseNumberTextField.getText());
            customer.setCity(cityTextField.getText());
            customer.setZipCode(Integer.parseInt(zipCodeTextField.getText()));
            customer.setIdNumber(idNumberTextField.getText());
            customer.setDriverLicenseId(driverLicenseIdTextField.getText());
            service.persist(customer);
            NewCustomerView.closeModalWindow();
        }
    }
}
