package de.htwsaar.prog3.carrental.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import de.htwsaar.prog3.carrental.gui.EditCustomerView;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class EditCustomerViewController implements Initializable {
    
    private CustomerService service = new CustomerService();
    private Customer customer = EditCustomerView.getCustomer();
    
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
     * Initialize all content fields with the current customer
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameTextField.setText(customer.getFirstName());
        lastNameTextField.setText(customer.getLastName());
        emailAddressTextField.setText(customer.getEmailAddress());
        phoneNumberTextField.setText(customer.getPhoneNumber());
        dateOfBirthTextField.setText(customer.getDateOfBirth());
        streetTextField.setText(customer.getStreet());
        houseNumberTextField.setText(customer.getHouseNumber());
        cityTextField.setText(customer.getCity());
        zipCodeTextField.setText(Integer.toString(customer.getZipCode()));
        idNumberTextField.setText(customer.getIdNumber());
        driverLicenseIdTextField.setText(customer.getDriverLicenseId());
    }

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
            EditCustomerView.closeModalWindow();
        }
    }

    /**
     * Handle Apply Button clicked
     * 
     * @param event
     */
    @FXML
    protected void handleApplyButtonClicked(ActionEvent event) {
        // TODO show details + valid data check
        // TODO only update data that has changed?
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
            service.update(customer);
            EditCustomerView.closeModalWindow();
        }
    }
}
