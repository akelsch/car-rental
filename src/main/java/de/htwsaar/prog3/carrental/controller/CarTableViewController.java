package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.service.RentalService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.RollbackException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the Controller for the Main View of the Carrental Application.
 *
 * @author Lukas Raubuch
 */
public class CarTableViewController extends GenericTableViewController<Car> implements Initializable {
    private RentalService rentalService;

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

    public CarTableViewController() {
        service = new CarService();
        entities = FXCollections.observableArrayList(service.findAll());

        rentalService = new RentalService();
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

        carTableView.setItems(entities);
    }

    @Override
    public void handleNewButtonClicked() {
        Car car = new Car();

        boolean applyClicked = app.showCarEditView(car);
        if (applyClicked) {
            service.persist(car);
            entities.setAll(service.findAll());
        }
    }

    @Override
    public void handleEditButtonClicked() {
        Car car = carTableView.getSelectionModel().getSelectedItem();

        if (car != null) {
            boolean applyClicked = app.showCarEditView(car);
            if (applyClicked) {
                service.update(car);
                entities.setAll(service.findAll());
            }
        }
    }

    @Override
    public void handleDeleteButtonClicked() {
        Car car = carTableView.getSelectionModel().getSelectedItem();

        if (null == car) {
            Alert info = DialogUtil.createInformationDialog(I18nComponentsUtil.getDialogDeleteNoSelectionText());
            info.show();
            return;
        }

        Alert confirmation = DialogUtil.createConfirmationDialog(I18nComponentsUtil.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.orElse(null) == ButtonType.OK) {
            try {
                service.delete(car);
                entities.setAll(service.findAll());
            } catch (RollbackException e) {
                // TODO i18n
                Alert error = DialogUtil.createErrorDialog("Invalid Action", "Can't delete this car",
                        "You must first delete the rental");
                error.showAndWait();
            }
        }
    }

    /**
     * Handle pressing the "Rent..." button.
     */
    public void handleRentButtonClicked() {
        Car car = carTableView.getSelectionModel().getSelectedItem();

        if (car != null) {
            Rental rental = new Rental();
            rental.setCar(car);

            boolean applyClicked = app.showRentalEditView(rental);
            if (applyClicked) {
                rentalService.persist(rental);
                entities.setAll(service.findAll());
            }
        }
    }
}
