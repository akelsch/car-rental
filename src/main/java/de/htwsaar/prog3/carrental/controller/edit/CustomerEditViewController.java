package de.htwsaar.prog3.carrental.controller.edit;

import com.sun.javafx.scene.control.IntegerField;
import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * JavaFX controller for the "Edit Customer" view.
 *
 * @author Jens Thewes
 */
@Controller
@RequiredArgsConstructor
public class CustomerEditViewController extends EditViewController<Customer> {

    private final CustomerRepository customerRepository;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private IntegerField zipcodeIntegerField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField dateOfBirthTextField; // TODO DatePicker?
    @FXML
    private TextField idNumberTextField;
    @FXML
    private TextField driverLicenseNumberTextField;

    @Override
    public void postInitialize() {
        firstNameTextField.setText(entity.getFirstName());
        lastNameTextField.setText(entity.getLastName());
        streetTextField.setText(entity.getStreet());
        zipcodeIntegerField.setValue(entity.getZipcode());
        cityTextField.setText(entity.getCity());
        phoneTextField.setText(entity.getPhone());
        emailTextField.setText(entity.getEmail());
        if (entity.getDateOfBirth() != null) {
            dateOfBirthTextField.setText(entity.getDateOfBirth().toString());
        }
        idNumberTextField.setText(entity.getIdNumber());
        driverLicenseNumberTextField.setText(entity.getDriverLicenseNumber());
    }

    @Override
    public void handleApplyButtonClicked() {
        entity.setFirstName(firstNameTextField.getText());
        entity.setLastName(lastNameTextField.getText());
        entity.setStreet(streetTextField.getText());
        entity.setZipcode(zipcodeIntegerField.getValue());
        entity.setCity(cityTextField.getText());
        entity.setPhone(phoneTextField.getText());
        entity.setEmail(emailTextField.getText());
//        entity.setDateOfBirth(dateOfBirthTextField.getText());
        entity.setIdNumber(idNumberTextField.getText());
        entity.setDriverLicenseNumber(driverLicenseNumberTextField.getText());

        // TODO check unique constraints
        if (isInputValid(entity)) {
            closeModalWithApply();
        }
    }
}
