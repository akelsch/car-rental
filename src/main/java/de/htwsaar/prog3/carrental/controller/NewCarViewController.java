package de.htwsaar.prog3.carrental.controller;

import java.util.Optional;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.view.NewCarView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * This is the Controller for the "New Car View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class NewCarViewController {

    private CarService service = new CarService();
    private Car car = new Car();

    @FXML
    private BorderPane rootPane;
    
    @FXML
    private Label titleLabel;
    
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
     * Handle clicking the Cancel Button.
     */
    @FXML
    protected void handleCancelButtonClicked() {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog
                .setTitle(I18nComponentsUtil.getDialogConfirmationTitle());
        confirmationDialog.setHeaderText(
                I18nComponentsUtil.getDialogCancelConfirmationText());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.orElse(null) == ButtonType.OK) {
            NewCarView.closeModalWindow();
        }
    }

    /**
     * Handle clicking the Apply Button.
     */
    @FXML
    protected void handleApplyButtonClicked() {
        // TODO show details + valid data check
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog
                .setTitle(I18nComponentsUtil.getDialogConfirmationTitle());
        confirmationDialog
                .setHeaderText(I18nComponentsUtil.getDialogApplyConfirmationText());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.orElse(null) == ButtonType.OK) {
            car.setBrand(brandTextField.getText());
            car.setModel(modelTextField.getText());
            car.setCategory(categoryTextField.getText());
            car.setColor(colorTextField.getText());
            car.setConstructionYear(Integer.parseInt(constructionYearTextField.getText()));
            car.setDrivenDistance(Integer.parseInt(drivenDistanceTextField.getText()));
            car.setGearbox(gearBoxChoiceBox.getSelectionModel().getSelectedItem());
            car.setHorsepower(Integer.parseInt(horsePowerTextField.getText()));
            car.setFuel(fuelChoiceBox.getSelectionModel().getSelectedItem());
            car.setDoorCount(Integer.parseInt(doorCountTextField.getText()));
            car.setTires(tiresTextField.getText());
            car.setNextInspection(nextInspectionTextField.getText());
            car.setVin(vinTextField.getText());
            car.setEquipment(equipmentTextField.getText());
            car.setDefects(defectsTextField.getText());
            car.setLicenseNumber(licenceNumberTextField.getText());
            car.setDailyRate(Integer.parseInt(dailyRateTextField.getText()));
            car.setParkingLot(parkingLotTextField.getText());
            service.persist(car);
            NewCarView.closeModalWindow();
        }
    }
}
