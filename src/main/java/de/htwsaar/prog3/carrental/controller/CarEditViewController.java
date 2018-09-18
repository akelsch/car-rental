package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the Controller for the "Edit Car View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class CarEditViewController extends GenericEditViewController<Car> {
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

    public CarEditViewController() {
        service = new CarService();
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
        // TODO only update data that has changed?
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

            applyClicked = true;
            modalStage.close();
        }
    }

    @Override
    boolean isInputValid() {
        StringBuilder sb = new StringBuilder();
        String errorMessage;
        List<Car> cars;

        if (brandTextField.getText() == null || brandTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidBrand());
            sb.append(System.lineSeparator());
        }

        if (modelTextField.getText() == null || modelTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidModel());
            sb.append(System.lineSeparator());
        }

        if (categoryTextField.getText() == null || categoryTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidCategory());
            sb.append(System.lineSeparator());
        }

        if (colorTextField.getText() == null || colorTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidColor());
            sb.append(System.lineSeparator());
        }

        if (constructionYearTextField.getText() == null
                || constructionYearTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidConstructionYear());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(constructionYearTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nComponentsUtil.getCarNoValidConstructionYear());
                sb.append(" ");
                sb.append(I18nComponentsUtil.getCarNoValidInteger());
                sb.append(System.lineSeparator());
            }
        }

        if (drivenDistanceTextField.getText() == null
                || drivenDistanceTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidDrivenDistance());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(constructionYearTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nComponentsUtil.getCarNoValidDrivenDistance());
                sb.append(" ");
                sb.append(I18nComponentsUtil.getCarNoValidInteger());
                sb.append(System.lineSeparator());
            }
        }

        if (gearBoxChoiceBox.getSelectionModel().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidGearbox());
            sb.append(System.lineSeparator());
        }

        if (horsePowerTextField.getText() == null || horsePowerTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidHorsepower());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(horsePowerTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nComponentsUtil.getCarNoValidHorsepower());
                sb.append(" ");
                sb.append(I18nComponentsUtil.getCarNoValidInteger());
                sb.append(System.lineSeparator());
            }
        }

        if (fuelChoiceBox.getSelectionModel().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidFuel());
            sb.append(System.lineSeparator());
        }

        if (doorCountTextField.getText() == null || doorCountTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidDoorCount());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(doorCountTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nComponentsUtil.getCarNoValidDoorCount());
                sb.append(" ");
                sb.append(I18nComponentsUtil.getCarNoValidInteger());
                sb.append(System.lineSeparator());
            }
        }

        if (tiresTextField.getText() == null || tiresTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidTires());
            sb.append(System.lineSeparator());
        }

        if (nextInspectionTextField.getText() == null
                || nextInspectionTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidNextInspection());
            sb.append(System.lineSeparator());
        }

        if (vinTextField.getText() == null || vinTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidVin());
            sb.append(System.lineSeparator());
        } else {
            cars = service.filter(I18nComponentsUtil.getCarVinLabel(), "=", vinTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(entity.getId()))
                    .collect(Collectors.toList());
            if (!cars.isEmpty()) {
                sb.append(I18nComponentsUtil.getCarNoValidVinDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        if (licenceNumberTextField.getText() == null
                || licenceNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidLicenceNumber());
            sb.append(System.lineSeparator());
        } else {
            cars = service.filter(I18nComponentsUtil.getCarLicenceNumberLabel(), "=", licenceNumberTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(entity.getId()))
                    .collect(Collectors.toList());
            if (!cars.isEmpty()) {
                sb.append(I18nComponentsUtil.getCarNoValidLicenceNumberDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        if (dailyRateTextField.getText() == null || dailyRateTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidDailyRate());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(dailyRateTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nComponentsUtil.getCarNoValidDailyRate());
                sb.append(" ");
                sb.append(I18nComponentsUtil.getCarNoValidInteger());
                sb.append(System.lineSeparator());
            }
        }

        if (parkingLotTextField.getText() == null || parkingLotTextField.getText().trim().isEmpty()) {
            sb.append(I18nComponentsUtil.getCarNoValidParkingLot());
            sb.append(System.lineSeparator());
        } else {
            cars = service.filter(I18nComponentsUtil.getCarParkingLotLabel(), "=", parkingLotTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(entity.getId()))
                    .collect(Collectors.toList());
            if (!cars.isEmpty()) {
                sb.append(I18nComponentsUtil.getCarNoValidParkingLotDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        errorMessage = sb.toString();
        if (!errorMessage.isEmpty()) {
            Alert alert = DialogUtil.createErrorDialog(I18nComponentsUtil.getDialogErrorInvalidFieldsTitle(), I18nComponentsUtil.getDialogErrorInvalidFieldsText(), errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }
}
