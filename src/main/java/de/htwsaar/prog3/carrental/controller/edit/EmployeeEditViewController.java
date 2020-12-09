package de.htwsaar.prog3.carrental.controller.edit;

import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * JavaFX controller for the "Edit Employee" view.
 *
 * @author Jens Thewes
 */
@Component
public class EmployeeEditViewController extends EditViewController<Employee> {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField positionTextField;

    @Override
    public void initialize(Employee employee) {
        entity = employee;

        firstNameTextField.setText(entity.getFirstName());
        lastNameTextField.setText(entity.getLastName());
        positionTextField.setText(entity.getPosition());
    }

    @Override
    public void handleApplyButtonClicked() {
        if (isInputValid()) {
            entity.setFirstName(firstNameTextField.getText());
            entity.setLastName(lastNameTextField.getText());
            entity.setPosition(positionTextField.getText());
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

        if (StringUtils.isBlank(positionTextField.getText())) {
            sb.append("TODO");
            sb.append(System.lineSeparator());
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
