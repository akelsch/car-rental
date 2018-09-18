package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is the Controller for the "Edit Customer View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class CustomerEditViewController {
    @Setter
    private Stage modalStage;

    @Getter
    private boolean applyClicked = false;

    private Customer customerToEdit;
    private CustomerService service = new CustomerService();

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

    /**
     * fills all the text fields with the given information from given customerToEdit.
     *
     * @param customerToEdit given customer to be edit
     */
    public void setCustomer(Customer customerToEdit) {
        this.customerToEdit = customerToEdit;

        firstNameTextField.setText(customerToEdit.getFirstName());
        lastNameTextField.setText(customerToEdit.getLastName());
        emailAddressTextField.setText(customerToEdit.getEmailAddress());
        phoneNumberTextField.setText(customerToEdit.getPhoneNumber());
        dateOfBirthTextField.setText(customerToEdit.getDateOfBirth());
        streetTextField.setText(customerToEdit.getStreet());
        houseNumberTextField.setText(customerToEdit.getHouseNumber());
        cityTextField.setText(customerToEdit.getCity());
        zipCodeTextField.setText(Integer.toString(customerToEdit.getZipCode()));
        idNumberTextField.setText(customerToEdit.getIdNumber());
        driverLicenseIdTextField.setText(customerToEdit.getDriverLicenseId());
    }

    /**
     * Handle Cancel Button clicked.
     */
    public void handleCancelButtonClicked() {
        Alert confirmationDialog = DialogUtil
                .createConfirmationDialog(I18nComponentsUtil.getDialogCancelConfirmationText());

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.orElse(null) == ButtonType.OK) {
            modalStage.close();
        }
    }

    /**
     * Handle Apply Button clicked.
     */
    public void handleApplyButtonClicked() {
        // TODO only update data that has changed?
        if (isInputValid()) {
            customerToEdit.setFirstName(firstNameTextField.getText());
            customerToEdit.setLastName(lastNameTextField.getText());
            customerToEdit.setEmailAddress(emailAddressTextField.getText());
            customerToEdit.setPhoneNumber(phoneNumberTextField.getText());
            customerToEdit.setDateOfBirth(dateOfBirthTextField.getText());
            customerToEdit.setStreet(streetTextField.getText());
            customerToEdit.setHouseNumber(houseNumberTextField.getText());
            customerToEdit.setCity(cityTextField.getText());
            customerToEdit.setZipCode(Integer.parseInt(zipCodeTextField.getText()));
            customerToEdit.setIdNumber(idNumberTextField.getText());
            customerToEdit.setDriverLicenseId(driverLicenseIdTextField.getText());

            applyClicked = true;
            modalStage.close();
        }
    }

    /**
     * Valid Data Check.
     *
     * @return true if every data is valid, false if at least one data is not valid
     */
    private boolean isInputValid() {
        StringBuilder sb = new StringBuilder();
        String errorMessage;

        List<Customer> customers;

        if (firstNameTextField.getText() == null
                || firstNameTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidFirstName());
            sb.append(System.lineSeparator());
        }

        if (lastNameTextField.getText() == null
                || lastNameTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidLastName());
            sb.append(System.lineSeparator());
        }

        if (emailAddressTextField.getText() == null
                || emailAddressTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidEmailAdress());
            sb.append(System.lineSeparator());
        }

        if (phoneNumberTextField.getText() == null
                || phoneNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidPhoneNumber());
            sb.append(System.lineSeparator());
        }

        if (dateOfBirthTextField.getText() == null
                || dateOfBirthTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidDateOfBirth());
            sb.append(System.lineSeparator());
        }

        if (streetTextField.getText() == null || streetTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidStreetName());
            sb.append(System.lineSeparator());
        }

        if (houseNumberTextField.getText() == null
                || houseNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidHouseNumber());
            sb.append(System.lineSeparator());
        }

        if (cityTextField.getText() == null || cityTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidCityName());
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

        if (idNumberTextField.getText() == null
                || idNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidIdNumber());
            sb.append(System.lineSeparator());
        } else {
            customers = service.filter(I18nComponentsUtil.getCustomerIdNumberLabel(), "=", idNumberTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(customerToEdit.getId()))
                    .collect(Collectors.toList());
            if (!customers.isEmpty()) {
                sb.append(I18nComponentsUtil.getCustomerNoValidIdNumberDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        if (driverLicenseIdTextField.getText() == null
                || driverLicenseIdTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCustomerNoValidDriverLicence());
            sb.append(System.lineSeparator());
        } else {
            customers = service.filter(I18nComponentsUtil.getCustomerDriverLicenseIdLabel(),
                    "=", driverLicenseIdTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(customerToEdit.getId()))
                    .collect(Collectors.toList());
            if (!customers.isEmpty()) {
                sb.append(I18nComponentsUtil.getCustomerNoValidDriverLicenceDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        errorMessage = sb.toString();
        if (!errorMessage.isEmpty()) {
            Alert alert = DialogUtil.createErrorDialog(I18nComponentsUtil.getDialogErrorInvalidFieldsTitle(), I18nComponentsUtil.getDialogErrorInvalidFieldsText(), errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }
}