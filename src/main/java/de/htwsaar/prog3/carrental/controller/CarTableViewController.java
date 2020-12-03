package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.RollbackException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * JavaFX controller for the "Car Table" view (main).
 *
 * @author Lukas Raubuch
 */
@Component
public class CarTableViewController extends GenericTableViewController<Car> implements Initializable {

    private final CarRepository carRepository;
    private final RentalRepository rentalRepository;

    @FXML
    private TableView<Car> carTableView;
    @FXML
    private TableColumn<Car, Integer> id;
    @FXML
    private TableColumn<Car, String> brand;
    @FXML
    private TableColumn<Car, String> category;
    @FXML
    private TableColumn<Car, String> color;
    @FXML
    private TableColumn<Car, String> constructionYear;
    @FXML
    private TableColumn<Car, Integer> dailyRate;
    @FXML
    private TableColumn<Car, String> defects;
    @FXML
    private TableColumn<Car, Integer> doorCount;
    @FXML
    private TableColumn<Car, Integer> drivenDistance;
    @FXML
    private TableColumn<Car, String> fuel;
    @FXML
    private TableColumn<Car, String> gearbox;
    @FXML
    private TableColumn<Car, Integer> horsepower;
    @FXML
    private TableColumn<Car, String> licenseNumber;
    @FXML
    private TableColumn<Car, String> model;
    @FXML
    private TableColumn<Car, String> nextInspection;
    @FXML
    private TableColumn<Car, String> parkingLot;
    @FXML
    private TableColumn<Car, String> tires;
    @FXML
    private TableColumn<Car, String> vin;

    @Autowired
    public CarTableViewController(CarRepository carRepository, RentalRepository rentalRepository) {
        this.carRepository = carRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        brand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        color.setCellValueFactory(new PropertyValueFactory<>("Color"));
        constructionYear.setCellValueFactory(new PropertyValueFactory<>("ConstructionYear"));
        dailyRate.setCellValueFactory(new PropertyValueFactory<>("DailyRate"));
        defects.setCellValueFactory(new PropertyValueFactory<>("Defects"));
        doorCount.setCellValueFactory(new PropertyValueFactory<>("DoorCount"));
        drivenDistance.setCellValueFactory(new PropertyValueFactory<>("DrivenDistance"));
        fuel.setCellValueFactory(new PropertyValueFactory<>("Fuel"));
        gearbox.setCellValueFactory(new PropertyValueFactory<>("Gearbox"));
        horsepower.setCellValueFactory(new PropertyValueFactory<>("Horsepower"));
        licenseNumber.setCellValueFactory(new PropertyValueFactory<>("LicenseNumber"));
        model.setCellValueFactory(new PropertyValueFactory<>("Model"));
        nextInspection.setCellValueFactory(new PropertyValueFactory<>("NextInspection"));
        parkingLot.setCellValueFactory(new PropertyValueFactory<>("ParkingLot"));
        tires.setCellValueFactory(new PropertyValueFactory<>("Tires"));
        vin.setCellValueFactory(new PropertyValueFactory<>("Vin"));

        entities = FXCollections.observableArrayList(carRepository.findAll());
        carTableView.setItems(entities);
    }

    @Override
    public void handleNewClicked() {
        Car car = new Car();

        boolean applyClicked = application.showCarEditView(car);
        if (applyClicked) {
            carRepository.save(car);
            entities.setAll(carRepository.findAll());
        }
    }

    @Override
    public void handleEditClicked() {
        Car car = carTableView.getSelectionModel().getSelectedItem();

        if (car != null) {
            boolean applyClicked = application.showCarEditView(car);
            if (applyClicked) {
                carRepository.save(car);
                entities.setAll(carRepository.findAll());
            }
        }
    }

    @Override
    public void handleDeleteClicked() {
        Car car = carTableView.getSelectionModel().getSelectedItem();

        if (null == car) {
            Alert info = DialogUtils.createInformationDialog(I18nUtils.getDialogDeleteNoSelectionText());
            info.show();
            return;
        }

        Alert confirmation = DialogUtils.createConfirmationDialog(I18nUtils.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.orElse(null) == ButtonType.OK) {
            try {
                carRepository.delete(car);
                entities.setAll(carRepository.findAll());
            } catch (RollbackException e) {
                // TODO choose different exception
                // TODO i18n
                Alert error = DialogUtils.createErrorDialog("Invalid Action", "Can't delete this car",
                        "You must first delete the rental");
                error.showAndWait();
            }
        }
    }

    public void handleRentClicked() {
        Car car = carTableView.getSelectionModel().getSelectedItem();

        if (car != null) {
            Rental rental = new Rental();
            rental.setCar(car);

            boolean applyClicked = application.showRentalEditView(rental);
            if (applyClicked) {
                rentalRepository.save(rental);
                entities.setAll(carRepository.findAll());
            }
        }
    }
}
