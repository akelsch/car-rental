package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.view.EditCarView;
import de.htwsaar.prog3.carrental.view.CarTableView;
import de.htwsaar.prog3.carrental.view.NewCarView;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.util.GUIDialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the Controller for the Main View of the Carrental Application.
 *
 * @author Lukas Raubuch
 * @see CarTableView
 */
public class CarTableViewController extends GenericTableViewController<Car> {
	private static final Logger logger = LoggerFactory.getLogger(CarTableViewController.class);

	@FXML
	private TableView<Car> carTableView;
	// TableColumns to associate data with columns
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
	}

	@Override
	public void handleNewButtonClicked() {
		try {
			new NewCarView().start(primaryStage);
			carTableView.setItems(FXCollections.observableArrayList(service.findAll()));
		} catch (Exception e) {
			logger.error("Error while creating a new Car");
		}
	}

	@Override
	public void handleEditButtonClicked() {
		Car toEdit = carTableView.getSelectionModel().getSelectedItem();
		try {
			new EditCarView().start(primaryStage, toEdit);
			carTableView.setItems(FXCollections.observableArrayList(service.findAll()));
		} catch (Exception e) {
			logger.error("Error while editing selected car");
		}
	}

	@Override
	public void handleDeleteButtonClicked() {
		Car toDelete = carTableView.getSelectionModel().getSelectedItem();
		if (null == toDelete) {
			Alert informationDialog = GUIDialogUtil
					.createInformationDialog(I18nComponentsUtil.getDialogDeleteNoSelectionText());
			informationDialog.show();
			return;
		}
		Alert confirmationDialog = GUIDialogUtil
				.createConfirmationDialog(I18nComponentsUtil.getDialogDeleteConfirmationText());
		Optional<ButtonType> result = confirmationDialog.showAndWait();
		if (result.get() == ButtonType.OK) {
			logger.info("OK Button pressed. Deleting Car...");
			service.delete(toDelete);
			entities.remove(toDelete);
		}
	}

	/**
	 * Handle pressing the "Rent..." button.
	 */
	public void handleRentButtonClicked() {
		// TODO: Implement with Michael
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Associate data with columns
		logger.info("Associating TableColumns with model data");
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
}
