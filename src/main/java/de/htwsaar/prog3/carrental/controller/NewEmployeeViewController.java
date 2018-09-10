package de.htwsaar.prog3.carrental.controller;

import java.util.Optional;
import de.htwsaar.prog3.carrental.view.NewEmployeeView;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.service.EmployeeService;
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
 * This is the Controller for the "New Employee View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class NewEmployeeViewController {

    private EmployeeService service = new EmployeeService();
    private Employee employee = new Employee();

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
     * Handle Cancel Button clicked
     * 
     * @param event
     */
    @FXML
    protected void handleCancelButtonClicked(ActionEvent event) {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog
                .setTitle(I18nComponentsUtil.getDialogConfirmationTitle());
        confirmationDialog.setHeaderText(
                I18nComponentsUtil.getDialogCancelConfirmationText());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.get() == ButtonType.OK) {
            NewEmployeeView.closeModalWindow();
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
                .setTitle(I18nComponentsUtil.getDialogConfirmationTitle());
        confirmationDialog
                .setHeaderText(I18nComponentsUtil.getDialogApplyConfirmationText());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.get() == ButtonType.OK) {
            employee.setFirstName(firstNameTextField.getText());
            employee.setLastName(lastNameTextField.getText());
            employee.setPosition(positionTextField.getText());
            service.persist(employee);
            NewEmployeeView.closeModalWindow();
        }
    }

}
