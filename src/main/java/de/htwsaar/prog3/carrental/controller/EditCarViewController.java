package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.view.EditCarView;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the Controller for the "Edit Car View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class EditCarViewController implements Initializable {

    private CarService service = new CarService();
    private Car car = EditCarView.getCar();

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
        constructionYearTextField.setText(Integer.toString(car.getConstructionYear()));
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
            EditCarView.closeModalWindow();
        }
    }

    /**
     * Handle clicking the Apply Button.
     */
    @FXML
    protected void handleApplyButtonClicked() {
        // TODO show details valid data check
        // TODO only update data that has changed?
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
            service.update(car);
            EditCarView.closeModalWindow();
        }
    }
}
