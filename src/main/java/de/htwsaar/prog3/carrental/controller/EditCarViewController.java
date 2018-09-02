package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.gui.EditCarView;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the Controller for the "Car Configuration" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class EditCarViewController implements Initializable {

    private CarService service = new CarService();
    private Car car = EditCarView.getCar();

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
     * Initialize all content fields with the current car configuration.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        brandTextField.setText(car.getBrand());
        modelTextField.setText(car.getModel());
        categoryTextField.setText(car.getCategory());
        colorTextField.setText(car.getColor());
        constructionYearTextField.setText(car.getConstructionYear());
        drivenDistanceTextField.setText(Integer.toString(car.getDrivenDistance()));
        gearBoxChoiceBox.setValue(car.getGearbox());
        horsePowerTextField.setText(Integer.toString(car.getHorsepower()));
        fuelChoiceBox.setValue(car.getFuel());
        doorCountTextField.setText(Integer.toString(car.getDoorCount()));
        tiresTextField.setText(car.getTires());
        nextInspectionTextField.setText(car.getNextInspection());
        vinTextField.setText(car.getVin());
        equipmentTextField.setText(car.getEquipment());
        defectsTextField.setText(car.getDefects());
        licenceNumberTextField.setText(car.getLicenseNumber());
        dailyRateTextField.setText(Integer.toString(car.getDailyRate()));
        parkingLotTextField.setText(car.getParkingLot());
    }

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
            EditCarView.closeModalWindow();
        }
    }

    /**
     * Handle clicking the Apply Button.
     *
     * @param event
     */
    @FXML
    protected void handleApplyButtonClicked(ActionEvent event) {
        // TODO show details + confirmation + valid data check
        // TODO only update data that has changed?
        car.setBrand(brandTextField.getText());
        car.setModel(modelTextField.getText());
        car.setCategory(categoryTextField.getText());
        car.setColor(colorTextField.getText());
        car.setConstructionYear(constructionYearTextField.getText());
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
        service.update(car);
        EditCarView.closeModalWindow();
    }
}
