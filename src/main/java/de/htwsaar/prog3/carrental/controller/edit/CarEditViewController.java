package de.htwsaar.prog3.carrental.controller.edit;

import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.car.*;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.util.DateUtils;
import de.htwsaar.prog3.carrental.util.MessageUtils;
import de.htwsaar.prog3.carrental.util.fx.IntegerField;
import de.htwsaar.prog3.carrental.util.fx.LabelableStringConverter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.Year;
import java.time.YearMonth;
import java.util.Objects;

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
    private ComboBox<Type> typeComboBox;
    @FXML
    private ComboBox<Color> colorComboBox;
    @FXML
    private IntegerField doorsIntegerField;
    @FXML
    private ComboBox<Transmission> transmissionComboBox;
    @FXML
    private ComboBox<Fuel> fuelComboBox;
    @FXML
    private IntegerField horsepowerIntegerField;
    @FXML
    private IntegerField mileageIntegerField;
    @FXML
    private ComboBox<Tire> tiresComboBox;
    @FXML
    private TextField parkingLotTextField;
    @FXML
    private TextField licenseNumberTextField;
    @FXML
    private TextField vinTextField;
    @FXML
    private ComboBox<YearMonth> nextInspectionComboBox;
    @FXML
    private TextField defectsTextField;

    @Override
    public void postInitialize() {
        typeComboBox.setItems(FXCollections.observableArrayList(Type.class.getEnumConstants()));
        colorComboBox.setItems(FXCollections.observableArrayList(Color.class.getEnumConstants()));
        transmissionComboBox.setItems(FXCollections.observableArrayList(Transmission.class.getEnumConstants()));
        fuelComboBox.setItems(FXCollections.observableArrayList(Fuel.class.getEnumConstants()));
        tiresComboBox.setItems(FXCollections.observableArrayList(Tire.class.getEnumConstants()));
        nextInspectionComboBox.setItems(FXCollections.observableArrayList(DateUtils.createNextInspectionDates()));

        typeComboBox.setConverter(new LabelableStringConverter<>(getMessageUtils()));
        colorComboBox.setConverter(new LabelableStringConverter<>(getMessageUtils()));
        transmissionComboBox.setConverter(new LabelableStringConverter<>(getMessageUtils()));
        fuelComboBox.setConverter(new LabelableStringConverter<>(getMessageUtils()));
        tiresComboBox.setConverter(new LabelableStringConverter<>(getMessageUtils()));

        if (entity.getYear() != null) {
            yearIntegerField.setValue(entity.getYear().getValue());
        }
        brandTextField.setText(entity.getBrand());
        modelTextField.setText(entity.getModel());
        dailyRateIntegerField.setValue(entity.getDailyRate());
        typeComboBox.setValue(entity.getType());
        colorComboBox.setValue(entity.getColor());
        doorsIntegerField.setValue(entity.getDoors());
        transmissionComboBox.setValue(entity.getTransmission());
        fuelComboBox.setValue(entity.getFuel());
        horsepowerIntegerField.setValue(entity.getHorsepower());
        mileageIntegerField.setValue(entity.getMileage());
        tiresComboBox.setValue(entity.getTires());
        parkingLotTextField.setText(entity.getParkingLot());
        licenseNumberTextField.setText(entity.getLicenseNumber());
        vinTextField.setText(entity.getVin());
        nextInspectionComboBox.setValue(entity.getNextInspection());
        defectsTextField.setText(entity.getDefects());
    }

    @Override
    public void handleApplyButtonClicked() {
        entity.setYear(Year.of(yearIntegerField.getValue()));
        entity.setBrand(brandTextField.getText());
        entity.setModel(modelTextField.getText());
        entity.setDailyRate(dailyRateIntegerField.getValue());
        entity.setType(typeComboBox.getValue());
        entity.setColor(colorComboBox.getValue());
        entity.setDoors(doorsIntegerField.getValue());
        entity.setTransmission(transmissionComboBox.getValue());
        entity.setFuel(fuelComboBox.getValue());
        entity.setHorsepower(horsepowerIntegerField.getValue());
        entity.setMileage(mileageIntegerField.getValue());
        entity.setTires(tiresComboBox.getValue());
        entity.setParkingLot(parkingLotTextField.getText());
        entity.setLicenseNumber(licenseNumberTextField.getText());
        entity.setVin(vinTextField.getText());
        entity.setNextInspection(nextInspectionComboBox.getValue());
        entity.setDefects(defectsTextField.getText());

        if (isInputValid(entity) && areFieldsUnique(entity)) {
            carRepository.save(entity);
            closeModalWithApply();
        }
    }

    private boolean areFieldsUnique(Car car) {
        StringBuilder sb = new StringBuilder();

        Long id = Objects.requireNonNullElse(car.getId(), 0L);

        if (carRepository.existsByIdNotAndParkingLot(id, car.getParkingLot())) {
            sb.append(getMessageUtils().getMessage(MessageUtils.VALIDATION_CAR_PARKING_LOT_DUPLICATE));
            sb.append(System.lineSeparator());
        }

        if (carRepository.existsByIdNotAndLicenseNumber(id, car.getLicenseNumber())) {
            sb.append(getMessageUtils().getMessage(MessageUtils.VALIDATION_CAR_LICENSE_NUMBER_DUPLICATE));
            sb.append(System.lineSeparator());
        }

        if (carRepository.existsByIdNotAndVin(id, car.getVin())) {
            sb.append(getMessageUtils().getMessage(MessageUtils.VALIDATION_CAR_VIN_DUPLICATE));
            sb.append(System.lineSeparator());
        }

        if (sb.isEmpty()) {
            return true;
        }

        getDialogUtils().showValidationErrorDialog(sb.toString());

        return false;
    }
}
