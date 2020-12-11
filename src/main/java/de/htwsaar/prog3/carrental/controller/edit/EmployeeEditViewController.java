package de.htwsaar.prog3.carrental.controller.edit;

import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

/**
 * JavaFX controller for the "Edit Employee" view.
 *
 * @author Jens Thewes
 */
@Controller
public class EmployeeEditViewController extends EditViewController<Employee> {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField positionTextField;

    @Override
    public void postInitialize() {
        firstNameTextField.setText(entity.getFirstName());
        lastNameTextField.setText(entity.getLastName());
        positionTextField.setText(entity.getPosition());
    }

    @Override
    public void handleApplyButtonClicked() {
        entity.setFirstName(firstNameTextField.getText());
        entity.setLastName(lastNameTextField.getText());
        entity.setPosition(positionTextField.getText());

        if (isInputValid(entity)) {
            closeModalWithApply();
        }
    }
}
