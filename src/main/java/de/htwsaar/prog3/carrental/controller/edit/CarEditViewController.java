package de.htwsaar.prog3.carrental.controller.edit;

import com.sun.javafx.scene.control.IntegerField;
import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.car.*;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.Year;
import java.time.YearMonth;

/**
 * JavaFX controller for the "Edit Car" view.
 *
 * @author Jens Thewes
 */
@Controller
@RequiredArgsConstructor
public class CarEditViewController extends EditViewController<Car> {

    private final CarRepository carRepository;

    @FXML
    private IntegerField yearIntegerField;
    @FXML
    private TextField brandTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private IntegerField dailyRateIntegerField;
    @FXML
    private ChoiceBox<Type> typeChoiceBox;
    @FXML
    private ChoiceBox<Color> colorChoiceBox;
    @FXML
    private IntegerField doorsIntegerField;
    @FXML
    private ChoiceBox<Transmission> transmissionChoiceBox;
    @FXML
    private ChoiceBox<Fuel> fuelChoiceBox;
    @FXML
    private IntegerField horsepowerIntegerField;
    @FXML
    private IntegerField mileageIntegerField;
    @FXML
    private ChoiceBox<Tire> tiresChoiceBox;
    @FXML
    private TextField parkingLotTextField;
    @FXML
    private TextField licenseNumberTextField;
    @FXML
    private TextField vinTextField;
    @FXML
    private ChoiceBox<YearMonth> nextInspectionChoiceBox;
    @FXML
    private TextField defectsTextField;

    @Override
    public void postInitialize() {
        // TODO fill choice boxes
        if (entity.getYear() != null) {
            yearIntegerField.setValue(entity.getYear().getValue());
        }
        brandTextField.setText(entity.getBrand());
        modelTextField.setText(entity.getModel());
        dailyRateIntegerField.setValue(entity.getDailyRate());
        typeChoiceBox.setValue(entity.getType());
        colorChoiceBox.setValue(entity.getColor());
        doorsIntegerField.setValue(entity.getDoors());
        transmissionChoiceBox.setValue(entity.getTransmission());
        fuelChoiceBox.setValue(entity.getFuel());
        horsepowerIntegerField.setValue(entity.getHorsepower());
        mileageIntegerField.setValue(entity.getMileage());
        tiresChoiceBox.setValue(entity.getTires());
        parkingLotTextField.setText(entity.getParkingLot());
        licenseNumberTextField.setText(entity.getLicenseNumber());
        vinTextField.setText(entity.getVin());
        nextInspectionChoiceBox.setValue(entity.getNextInspection());
        defectsTextField.setText(entity.getDefects());
    }

    @Override
    public void handleApplyButtonClicked() {
        entity.setYear(Year.of(yearIntegerField.getValue()));
        entity.setBrand(brandTextField.getText());
        entity.setModel(modelTextField.getText());
        entity.setDailyRate(dailyRateIntegerField.getValue());
        entity.setType(typeChoiceBox.getValue());
        entity.setColor(colorChoiceBox.getValue());
        entity.setDoors(doorsIntegerField.getValue());
        entity.setTransmission(transmissionChoiceBox.getValue());
        entity.setFuel(fuelChoiceBox.getValue());
        entity.setHorsepower(horsepowerIntegerField.getValue());
        entity.setMileage(mileageIntegerField.getValue());
        entity.setTires(tiresChoiceBox.getValue());
        entity.setParkingLot(parkingLotTextField.getText());
        entity.setLicenseNumber(licenseNumberTextField.getText());
        entity.setVin(vinTextField.getText());
        entity.setNextInspection(nextInspectionChoiceBox.getValue());
        entity.setDefects(defectsTextField.getText());

        // TODO check unique constraints
        if (isInputValid(entity)) {
            closeModalWithApply();
        }
    }
}
