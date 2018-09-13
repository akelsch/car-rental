package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * This is the Controller for the "Edit Employee View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class EditEmployeeViewController {

    private Stage modalStage;
    private Employee employeeToEdit;
    private boolean applyClicked = false;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField positionTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button applyButton;

    public void setModalStage(Stage modalStage) {
        this.modalStage = modalStage;
    }

    public void setEmployee(Employee employeeToEdit) {
        this.employeeToEdit = employeeToEdit;

        firstNameTextField.setText(employeeToEdit.getFirstName());
        lastNameTextField.setText(employeeToEdit.getLastName());
        positionTextField.setText(employeeToEdit.getPosition());
    }

    public boolean isApplyClicked() {
        return applyClicked;
    }

    /**
     * Handle Cancel Button clicked.
     */
    @FXML
    protected void handleCancelButtonClicked() {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog.setTitle(I18nComponentsUtil.getDialogConfirmationTitle());
        confirmationDialog.setHeaderText(I18nComponentsUtil.getDialogCancelConfirmationText());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.orElse(null) == ButtonType.OK) {
            modalStage.close();
        }
    }

    /**
     * Handle Apply Button clicked.
     */
    @FXML
    protected void handleApplyButtonClicked() {
        // TODO only update data that has changed?
        if (isInputValid()) {
            employeeToEdit.setFirstName(firstNameTextField.getText());
            employeeToEdit.setLastName(lastNameTextField.getText());
            employeeToEdit.setPosition(positionTextField.getText());

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
        String errorMessage = "";

        if (firstNameTextField.getText() == null || firstNameTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getEmployeeNoValidFirstName() + "\n";
        }

        if (lastNameTextField.getText() == null || lastNameTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getEmployeeNoValidLastName() + "\n";
        }

        if (positionTextField.getText() == null || positionTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getEmployeeNoValidPosition() + "\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(modalStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }
}
