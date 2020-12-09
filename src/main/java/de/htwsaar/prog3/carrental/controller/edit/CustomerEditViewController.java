package de.htwsaar.prog3.carrental.controller.edit;

import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * JavaFX controller for the "Edit Customer" view.
 *
 * @author Jens Thewes
 */
@Component
@RequiredArgsConstructor
public class CustomerEditViewController extends EditViewController<Customer> {

    private final CustomerRepository customerRepository;

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

    @Override
    public void initialize(Customer customer) {
        entity = customer;

        firstNameTextField.setText(entity.getFirstName());
        lastNameTextField.setText(entity.getLastName());
        emailAddressTextField.setText(entity.getEmailAddress());
        phoneNumberTextField.setText(entity.getPhoneNumber());
        dateOfBirthTextField.setText(entity.getDateOfBirth());
        streetTextField.setText(entity.getStreet());
        houseNumberTextField.setText(entity.getHouseNumber());
        cityTextField.setText(entity.getCity());
        zipCodeTextField.setText(Integer.toString(entity.getZipCode()));
        idNumberTextField.setText(entity.getIdNumber());
        driverLicenseIdTextField.setText(entity.getDriverLicenseId());
    }

    @Override
    public void handleApplyButtonClicked() {
        entity.setFirstName(firstNameTextField.getText());
        entity.setLastName(lastNameTextField.getText());
        entity.setEmailAddress(emailAddressTextField.getText());
        entity.setPhoneNumber(phoneNumberTextField.getText());
        entity.setDateOfBirth(dateOfBirthTextField.getText());
        entity.setStreet(streetTextField.getText());
        entity.setHouseNumber(houseNumberTextField.getText());
        entity.setCity(cityTextField.getText());
        entity.setZipCode(Integer.parseInt(zipCodeTextField.getText()));
        entity.setIdNumber(idNumberTextField.getText());
        entity.setDriverLicenseId(driverLicenseIdTextField.getText());

        // TODO check unique constraints
        if (isInputValid(entity)) {
            closeModalWithApply();
        }
    }
}
