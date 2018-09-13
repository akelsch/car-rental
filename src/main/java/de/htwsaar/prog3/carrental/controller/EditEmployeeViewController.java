package de.htwsaar.prog3.carrental.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This is the Controller for the "Edit Employee View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class EditEmployeeViewController implements Initializable {

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

    /**
     * Initialize all content fields with the current employee.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

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

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameTextField.getText() == null || firstNameTextField.getText().trim().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        
        if (lastNameTextField.getText() == null || lastNameTextField.getText().trim().length() == 0) {
            errorMessage += "No valid last name!\n";
        }

        if (positionTextField.getText() == null || positionTextField.getText().trim().length() == 0) {
            errorMessage += "No valid positon name!\n";
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
