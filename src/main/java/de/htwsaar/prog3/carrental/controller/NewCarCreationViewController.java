package de.htwsaar.prog3.carrental.controller;

import java.util.Optional;
import de.htwsaar.prog3.carrental.i18n.I18nComponentsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * This is the Controller for the "New Car Creation View" of the Carrental Application
 * 
 * @author Jens Thewes
 *
 */
public class NewCarCreationViewController {

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField colorTextField;

    @FXML
    private TextField constructionYearTextField; // dropdown?

    @FXML
    private TextField drivenDistanceTextField;

    @FXML
    private TextField gearBoxTextField; // dropdown?

    @FXML
    private TextField horsePowerTextField;

    @FXML
    private TextField fuelTextField; // dropdown?

    @FXML
    private TextField doorCountTextField;

    @FXML
    private TextField tiresTextField;

    @FXML
    private TextField nextInspectionTextField;
    
    @FXML
    private TextField vinTextField;

    @FXML
    private TextField equipmentTextField; // Radio Buttons?

    @FXML
    private TextField defectsTextField; //bigger TextField?

    @FXML
    private TextField licenceNumberTextField; // custom Textfield? [] [] []

    @FXML
    private TextField dailyRateTextField;

    @FXML
    private TextField parkingLotTextField;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Button applyButton;
    
    /**
     * Handle clicking the Cancel Button
     * 
     * @param event
     */
    @FXML
    protected void handleCancelButtonClicked(ActionEvent event) {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog.setTitle(I18nComponentsUtil.getCancelCreationConfirmationDialogTitleString());
        confirmationDialog.setHeaderText(I18nComponentsUtil.getCancelCreationConfirmationDialogHeaderString());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.get() == ButtonType.OK) {
            //TODO Implement close modal window
        }
    }
    
    /**
     * Handle clicking the Apply Button
     * 
     * @param event
     */
    @FXML
    protected void handleApplyButtonClicked(ActionEvent event) {
      //TODO implement show Overview of the newly created car and confirmation 
    }

}
