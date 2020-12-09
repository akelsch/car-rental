package de.htwsaar.prog3.carrental.controller.edit;

import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * JavaFX controller for the "Edit Car" view.
 *
 * @author Jens Thewes
 */
@Component
@RequiredArgsConstructor
public class CarEditViewController extends EditViewController<Car> {

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
    private TextField licenseNumberTextField;
    @FXML
    private TextField dailyRateTextField;
    @FXML
    private TextField parkingLotTextField;

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
        licenseNumberTextField.setText(entity.getLicenseNumber());
        dailyRateTextField.setText(Integer.toString(entity.getDailyRate()));
        parkingLotTextField.setText(entity.getParkingLot());
    }

    @Override
    public void handleApplyButtonClicked() {
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
        entity.setLicenseNumber(licenseNumberTextField.getText());
        entity.setDailyRate(Integer.parseInt(dailyRateTextField.getText()));
        entity.setParkingLot(parkingLotTextField.getText());

        // TODO check unique constraints
        if (isInputValid(entity)) {
            closeModalWithApply();
        }
    }
}
