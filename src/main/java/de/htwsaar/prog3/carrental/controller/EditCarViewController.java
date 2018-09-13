package de.htwsaar.prog3.carrental.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This is the Controller for the "Edit Car View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class EditCarViewController implements Initializable {

    private Stage modalStage;
    private Car carToEdit;
    private boolean applyClicked = false;

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
    private TextField constructionYearTextField;

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
    private TextField equipmentTextField;

    @FXML
    private TextField defectsTextField;

    @FXML
    private TextField licenceNumberTextField;

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
    public void initialize(URL location, ResourceBundle resources) {}

    public void setModalStage(Stage modalStage) {
        this.modalStage = modalStage;
    }

    public void setCar(Car carToEdit) {
        this.carToEdit = carToEdit;

        brandTextField.setText(carToEdit.getBrand());
        modelTextField.setText(carToEdit.getModel());
        categoryTextField.setText(carToEdit.getCategory());
        colorTextField.setText(carToEdit.getColor());
        constructionYearTextField.setText(Integer.toString(carToEdit.getConstructionYear()));
        drivenDistanceTextField.setText(Integer.toString(carToEdit.getDrivenDistance()));
        gearBoxChoiceBox.setValue(carToEdit.getGearbox());
        horsePowerTextField.setText(Integer.toString(carToEdit.getHorsepower()));
        fuelChoiceBox.setValue(carToEdit.getFuel());
        doorCountTextField.setText(Integer.toString(carToEdit.getDoorCount()));
        tiresTextField.setText(carToEdit.getTires());
        nextInspectionTextField.setText(carToEdit.getNextInspection());
        vinTextField.setText(carToEdit.getVin());
        equipmentTextField.setText(carToEdit.getEquipment());
        defectsTextField.setText(carToEdit.getDefects());
        licenceNumberTextField.setText(carToEdit.getLicenseNumber());
        dailyRateTextField.setText(Integer.toString(carToEdit.getDailyRate()));
        parkingLotTextField.setText(carToEdit.getParkingLot());
    }

    public boolean isApplyClicked() {
        return applyClicked;
    }

    /**
     * Handle clicking the Cancel Button.
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
     * Handle clicking the Apply Button.
     * 
     * @return
     */
    @FXML
    protected void handleApplyButtonClicked() {
        // TODO only update data that has changed?
        if (isInputValid()) {
            carToEdit.setBrand(brandTextField.getText());
            carToEdit.setModel(modelTextField.getText());
            carToEdit.setCategory(categoryTextField.getText());
            carToEdit.setColor(colorTextField.getText());
            carToEdit.setConstructionYear(Integer.parseInt(constructionYearTextField.getText()));
            carToEdit.setDrivenDistance(Integer.parseInt(drivenDistanceTextField.getText()));
            carToEdit.setGearbox(gearBoxChoiceBox.getSelectionModel().getSelectedItem());
            carToEdit.setHorsepower(Integer.parseInt(horsePowerTextField.getText()));
            carToEdit.setFuel(fuelChoiceBox.getSelectionModel().getSelectedItem());
            carToEdit.setDoorCount(Integer.parseInt(doorCountTextField.getText()));
            carToEdit.setTires(tiresTextField.getText());
            carToEdit.setNextInspection(nextInspectionTextField.getText());
            carToEdit.setVin(vinTextField.getText());
            carToEdit.setEquipment(equipmentTextField.getText());
            carToEdit.setDefects(defectsTextField.getText());
            carToEdit.setLicenseNumber(licenceNumberTextField.getText());
            carToEdit.setDailyRate(Integer.parseInt(dailyRateTextField.getText()));
            carToEdit.setParkingLot(parkingLotTextField.getText());

            applyClicked = true;
            modalStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        // TODO valid check

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
