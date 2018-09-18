package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is the Controller for the "Edit Car View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class CarEditViewController {

    private Stage modalStage;
    private Car carToEdit;
    private boolean applyClicked = false;
    private CarService service = new CarService();

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

    /**
     * sets the modalStage in order to use it locally.
     *
     * @param modalStage given modalStage
     */
    public void setModalStage(Stage modalStage) {
        this.modalStage = modalStage;
    }

    /**
     * fills all the text fields with the given information from given carToEdit.
     *
     * @param carToEdit given car to be edit
     */
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

    /**
     * Has applyButton been clicked?
     *
     * @return true, if applyButton has been clicked; false if not
     */
    public boolean isApplyClicked() {
        return applyClicked;
    }

    /**
     * Handle clicking the Cancel Button.
     */
    public void handleCancelButtonClicked() {
        Alert confirmationDialog = DialogUtil
                .createConfirmationDialog(I18nComponentsUtil.getDialogCancelConfirmationText());

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.orElse(null) == ButtonType.OK) {
            modalStage.close();
        }
    }

    /**
     * Handle clicking the Apply Button.
     */
    public void handleApplyButtonClicked() {
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

    /**
     * Valid Data Check.
     *
     * @return true if every data is valid, false if at least one data is not valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        List<Car> cars;

        if (brandTextField.getText() == null || brandTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidBrand() + "\n";
        }

        if (modelTextField.getText() == null || modelTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidModel() + "\n";
        }

        if (categoryTextField.getText() == null || categoryTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidCategory() + "\n";
        }

        if (colorTextField.getText() == null || colorTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidColor() + "\n";
        }

        if (constructionYearTextField.getText() == null
                || constructionYearTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidConstructionYear() + "\n";
        } else {
            try {
                Integer.parseInt(constructionYearTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += I18nComponentsUtil.getCarNoValidConstructionYear() + " "
                        + I18nComponentsUtil.getCarNoValidInteger() + "\n";
            }
        }

        if (drivenDistanceTextField.getText() == null
                || drivenDistanceTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidDrivenDistance() + "\n";
        } else {
            try {
                Integer.parseInt(constructionYearTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += I18nComponentsUtil.getCarNoValidDrivenDistance() + " "
                        + I18nComponentsUtil.getCarNoValidInteger() + "\n";
            }
        }

        if (gearBoxChoiceBox.getSelectionModel().isEmpty()) {
            errorMessage += I18nComponentsUtil.getCarNoValidGearbox() + "\n";
        }

        if (horsePowerTextField.getText() == null || horsePowerTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidHorsepower() + "\n";
        } else {
            try {
                Integer.parseInt(horsePowerTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += I18nComponentsUtil.getCarNoValidHorsepower() + " "
                        + I18nComponentsUtil.getCarNoValidInteger() + "\n";
            }
        }

        if (fuelChoiceBox.getSelectionModel().isEmpty()) {
            errorMessage += I18nComponentsUtil.getCarNoValidFuel() + "\n";
        }

        if (doorCountTextField.getText() == null || doorCountTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidDoorCount() + "\n";
        } else {
            try {
                Integer.parseInt(doorCountTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += I18nComponentsUtil.getCarNoValidDoorCount() + " "
                        + I18nComponentsUtil.getCarNoValidInteger() + "\n";
            }
        }

        if (tiresTextField.getText() == null || tiresTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidTires() + "\n";
        }

        if (nextInspectionTextField.getText() == null
                || nextInspectionTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidNextInspection() + "\n";
        }

        if (vinTextField.getText() == null || vinTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidVin() + "\n";
        } else {
            cars = service.filter(I18nComponentsUtil.getCarVinLabel(), "=", vinTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(carToEdit.getId()))
                    .collect(Collectors.toList());
            if (!cars.isEmpty()) {
                errorMessage += "There is already a car with this vin \n";
            }
        }

        if (licenceNumberTextField.getText() == null
                || licenceNumberTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidLicenceNumber() + "\n";
        } else {
            cars = service.filter(I18nComponentsUtil.getCarLicenceNumberLabel(), "=", licenceNumberTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(carToEdit.getId()))
                    .collect(Collectors.toList());
            if (!cars.isEmpty()) {
                errorMessage += "There is already a car with this license number \n";
            }
        }

        if (dailyRateTextField.getText() == null || dailyRateTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidDailyRate() + "\n";
        } else {
            try {
                Integer.parseInt(dailyRateTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += I18nComponentsUtil.getCarNoValidDailyRate() + " "
                        + I18nComponentsUtil.getCarNoValidInteger() + "\n";
            }
        }

        if (parkingLotTextField.getText() == null || parkingLotTextField.getText().length() == 0) {
            errorMessage += I18nComponentsUtil.getCarNoValidParkingLot() + "\n";
        } else {
            cars = service.filter(I18nComponentsUtil.getCarParkingLotLabel(), "=", parkingLotTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(carToEdit.getId()))
                    .collect(Collectors.toList());
            if (!cars.isEmpty()) {
                errorMessage += "There is already a car with this parking lot \n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = DialogUtil.createErrorDialog("Invalid Fields",
                    "Please correct invalid fields", errorMessage);
            alert.showAndWait();

            return false;
        }
    }
}
