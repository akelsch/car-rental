package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.view.NewCarView;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;

/**
 * This is the Controller for the "New Car View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class NewCarViewController {

    private CarService service = new CarService();
    private Car car = new Car();

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
            NewCarView.closeModalWindow();
        }
    }

    /**
     * Handle clicking the Apply Button.
     *
     * @param event
     */
    @FXML
    protected void handleApplyButtonClicked(ActionEvent event) {
        // TODO show details + valid data check
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog
                .setTitle(I18nComponentsUtil.getApplyCreationConfirmationDialogTitleString());
        confirmationDialog
                .setHeaderText(I18nComponentsUtil.getApplyCreationConfirmationDialogHeaderString());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.get() == ButtonType.OK) {
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
