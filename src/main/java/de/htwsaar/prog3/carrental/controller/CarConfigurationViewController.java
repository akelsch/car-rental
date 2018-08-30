package de.htwsaar.prog3.carrental.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import de.htwsaar.prog3.carrental.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.service.CarService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * This is the Controller for the "Car Configuration" of the Carrental Application
 * 
 * @author Jens Thewes
 *
 */
public class CarConfigurationViewController implements Initializable {

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField colorTextField;

    @FXML
    private TextField constructionYearTextField; // ChoiceBox?

    @FXML
    private TextField drivenDistanceTextField;

    @FXML
    private ChoiceBox<String> gearBoxChoiceBox;

    @FXML
    private TextField horsePowerTextField;

    @FXML
    private ChoiceBox<String> fuelChoiceBox;

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
    private TextField defectsTextField; // bigger TextField?

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
     * Initialize all content fields with the current car configuration
     * 
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // get the current car configuration and fill the textfields with content
    }

    /**
     * Handle clicking the Cancel Button
     * 
     * @param event
     */
    @FXML
    protected void handleCancelButtonClicked(ActionEvent event) {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog
                .setTitle(I18nComponentsUtil.getCancelCreationConfirmationDialogTitleString());
        confirmationDialog.setHeaderText(
                I18nComponentsUtil.getCancelCreationConfirmationDialogHeaderString());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.get() == ButtonType.OK) {
            // TODO Implement close modal window
        }
    }

    /**
     * Handle clicking the Apply Button
     * 
     * @param event
     */
    @FXML
    protected void handleApplyButtonClicked(ActionEvent event) {
        // TODO implement show Overview of the edited car and confirmation
    }
}
