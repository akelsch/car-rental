package de.htwsaar.prog3.carrental.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import de.htwsaar.prog3.carrental.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.util.GUIDialogUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This is the Controller for the Main View of the Carrental Application
 * 
 * @author Lukas Raubuch
 */
public class CarTableViewController implements Initializable {

	private CarService service = new CarService();
	private ObservableList<Car> data = FXCollections.observableArrayList(service.findAll());

	@FXML
	private TableView<Car> carTableView;
	// TableColumns to associate Data with columns
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

	@FXML
	private Button editSelectedCarButton;

	@FXML
	private Button createNewCarButton;

	@FXML
	private Button deleteSelectedCarButton;

	@FXML
	/**
	 * Handles Clicking on the TableView
	 * 
	 * @param event
	 */
	protected void handleTableViewClickedAction(ActionEvent event) {
		// TODO: Implement
	}

	@FXML
	/**
	 * Handle Clicking the Edit Button
	 * 
	 * @param event
	 */
	protected void handleEditButtonCllicked(ActionEvent event) {
		// TODO: Implement
	}

	@FXML
	/**
	 * Handle Clicking the New Button
	 * 
	 * @param event
	 */
	protected void handleNewButtonCllicked(ActionEvent event) {
		// TODO: Implement
	}

	@FXML
	/**
	 * Handle Clicking the Delete Button
	 * 
	 * @param event
	 */
	protected void handleDeleteButtonCllicked(ActionEvent event) {
		Car toDelete = carTableView.getSelectionModel().getSelectedItem();
		if (null == toDelete) {
			Alert informationDialog = GUIDialogUtil
					.createInformationDialog(I18nComponentsUtil.getInformationDialogHeaderNoCarSelected());
			informationDialog.show();
			return;
		}
		Alert confirmationDialog = GUIDialogUtil
				.createConfirmationDialog(I18nComponentsUtil.getConfirmationDialogHeaderDelete());
		Optional<ButtonType> result = confirmationDialog.showAndWait();
		if (result.get() == ButtonType.OK) {
			service = new CarService();
			service.delete(toDelete);
			data.remove(toDelete);
		}
	}

	@Override
	/**
	 * Initialize the CarTableView with data from the database
	 */
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

		carTableView.setItems(data);

	}
}
