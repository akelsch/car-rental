package de.htwsaar.prog3.carrental.controller.edit;

import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * JavaFX controller for the "Edit Customer" view.
 *
 * @author Jens Thewes
 */
@Component
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

    @Autowired
    public CustomerEditViewController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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
        if (isInputValid()) {
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
            closeModalWithApply();
        }
    }

    @Override
    public boolean isInputValid() {
        StringBuilder sb = new StringBuilder();

        if (StringUtils.isBlank(firstNameTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        }

        if (StringUtils.isBlank(lastNameTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        }

        if (StringUtils.isBlank(emailAddressTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        }

        if (StringUtils.isBlank(phoneNumberTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        }

        if (StringUtils.isBlank(dateOfBirthTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        }

        if (StringUtils.isBlank(streetTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        }

        if (StringUtils.isBlank(houseNumberTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        }

        if (StringUtils.isBlank(cityTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        }

        if (StringUtils.isBlank(zipCodeTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(zipCodeTextField.getText());
            } catch (NumberFormatException e) {
                sb.append("TODO");
                sb.append(" ");
                sb.append("TODO");
                sb.append(System.lineSeparator());
            }
        }

        if (StringUtils.isBlank(idNumberTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        } else {
            if (customerRepository.existsByIdNotAndIdNumber(entity.getId(), idNumberTextField.getText())) {
                sb.append("TODO");
                sb.append(System.lineSeparator());
            }
        }

        if (StringUtils.isBlank(driverLicenseIdTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
        } else {
            if (customerRepository.existsByIdNotAndDriverLicenseId(entity.getId(), driverLicenseIdTextField.getText())) {
                sb.append("TODO");
                sb.append(System.lineSeparator());
            }
        }

        String errorMessage = sb.toString();
        if (!errorMessage.isEmpty()) {
            Alert alert = DialogUtils.createErrorDialog(
                    "TODO", errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }
}
