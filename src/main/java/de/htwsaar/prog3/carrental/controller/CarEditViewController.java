package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is the Controller for the "Edit Car View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
@Component
public class CarEditViewController extends GenericEditViewController<Car> {

    private final CarRepository carRepository;

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

    @Autowired
    public CarEditViewController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void initialize(Car car) {
        entity = car;

        brandTextField.setText(entity.getBrand());
        modelTextField.setText(entity.getModel());
        categoryTextField.setText(entity.getCategory());
        colorTextField.setText(entity.getColor());
        constructionYearTextField.setText(Integer.toString(entity.getConstructionYear()));
        drivenDistanceTextField.setText(Integer.toString(entity.getDrivenDistance()));
        gearBoxChoiceBox.setValue(entity.getGearbox());
        horsePowerTextField.setText(Integer.toString(entity.getHorsepower()));
        fuelChoiceBox.setValue(entity.getFuel());
        doorCountTextField.setText(Integer.toString(entity.getDoorCount()));
        tiresTextField.setText(entity.getTires());
        nextInspectionTextField.setText(entity.getNextInspection());
        vinTextField.setText(entity.getVin());
        equipmentTextField.setText(entity.getEquipment());
        defectsTextField.setText(entity.getDefects());
        licenceNumberTextField.setText(entity.getLicenseNumber());
        dailyRateTextField.setText(Integer.toString(entity.getDailyRate()));
        parkingLotTextField.setText(entity.getParkingLot());
    }

    @Override
    public void handleApplyButtonClicked() {
        if (isInputValid()) {
            entity.setBrand(brandTextField.getText());
            entity.setModel(modelTextField.getText());
            entity.setCategory(categoryTextField.getText());
            entity.setColor(colorTextField.getText());
            entity.setConstructionYear(Integer.parseInt(constructionYearTextField.getText()));
            entity.setDrivenDistance(Integer.parseInt(drivenDistanceTextField.getText()));
            entity.setGearbox(gearBoxChoiceBox.getSelectionModel().getSelectedItem());
            entity.setHorsepower(Integer.parseInt(horsePowerTextField.getText()));
            entity.setFuel(fuelChoiceBox.getSelectionModel().getSelectedItem());
            entity.setDoorCount(Integer.parseInt(doorCountTextField.getText()));
            entity.setTires(tiresTextField.getText());
            entity.setNextInspection(nextInspectionTextField.getText());
            entity.setVin(vinTextField.getText());
            entity.setEquipment(equipmentTextField.getText());
            entity.setDefects(defectsTextField.getText());
            entity.setLicenseNumber(licenceNumberTextField.getText());
            entity.setDailyRate(Integer.parseInt(dailyRateTextField.getText()));
            entity.setParkingLot(parkingLotTextField.getText());
            closeModalWithApply();
        }
    }

    @Override
    boolean isInputValid() {
        StringBuilder sb = new StringBuilder();

        if (brandTextField.getText() == null || brandTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidBrand());
            sb.append(System.lineSeparator());
        }

        if (modelTextField.getText() == null || modelTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidModel());
            sb.append(System.lineSeparator());
        }

        if (categoryTextField.getText() == null || categoryTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidCategory());
            sb.append(System.lineSeparator());
        }

        if (colorTextField.getText() == null || colorTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidColor());
            sb.append(System.lineSeparator());
        }

        if (constructionYearTextField.getText() == null || constructionYearTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidConstructionYear());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(constructionYearTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nUtils.getCarNoValidConstructionYear());
                sb.append(" ");
                sb.append(I18nUtils.getDialogInvalidNumberText());
                sb.append(System.lineSeparator());
            }
        }

        if (drivenDistanceTextField.getText() == null || drivenDistanceTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidDrivenDistance());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(constructionYearTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nUtils.getCarNoValidDrivenDistance());
                sb.append(" ");
                sb.append(I18nUtils.getDialogInvalidNumberText());
                sb.append(System.lineSeparator());
            }
        }

        if (gearBoxChoiceBox.getSelectionModel().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidGearbox());
            sb.append(System.lineSeparator());
        }

        if (horsePowerTextField.getText() == null || horsePowerTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidHorsepower());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(horsePowerTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nUtils.getCarNoValidHorsepower());
                sb.append(" ");
                sb.append(I18nUtils.getDialogInvalidNumberText());
                sb.append(System.lineSeparator());
            }
        }

        if (fuelChoiceBox.getSelectionModel().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidFuel());
            sb.append(System.lineSeparator());
        }

        if (doorCountTextField.getText() == null || doorCountTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidDoorCount());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(doorCountTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nUtils.getCarNoValidDoorCount());
                sb.append(" ");
                sb.append(I18nUtils.getDialogInvalidNumberText());
                sb.append(System.lineSeparator());
            }
        }

        if (tiresTextField.getText() == null || tiresTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidTires());
            sb.append(System.lineSeparator());
        }

        if (nextInspectionTextField.getText() == null || nextInspectionTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidNextInspection());
            sb.append(System.lineSeparator());
        }

        if (vinTextField.getText() == null || vinTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidVin());
            sb.append(System.lineSeparator());
        } else {
            if (carRepository.existsByIdNotAndVin(entity.getId(), vinTextField.getText())) {
                sb.append(I18nUtils.getCarNoValidVinDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        if (licenceNumberTextField.getText() == null || licenceNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidLicenceNumber());
            sb.append(System.lineSeparator());
        } else {
            if (carRepository.existsByIdNotAndLicenseNumber(entity.getId(), licenceNumberTextField.getText())) {
                sb.append(I18nUtils.getCarNoValidLicenceNumberDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        if (dailyRateTextField.getText() == null || dailyRateTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidDailyRate());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(dailyRateTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nUtils.getCarNoValidDailyRate());
                sb.append(" ");
                sb.append(I18nUtils.getDialogInvalidNumberText());
                sb.append(System.lineSeparator());
            }
        }

        if (parkingLotTextField.getText() == null || parkingLotTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCarNoValidParkingLot());
            sb.append(System.lineSeparator());
        } else {
            if (carRepository.existsByIdNotAndParkingLot(entity.getId(), parkingLotTextField.getText())) {
                sb.append(I18nUtils.getCarNoValidParkingLotDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        String errorMessage = sb.toString();
        if (!errorMessage.isEmpty()) {
            Alert alert = DialogUtil.createErrorDialog(I18nUtils.getDialogErrorInvalidFieldsTitle(),
                    I18nUtils.getDialogErrorInvalidFieldsText(), errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }
}
