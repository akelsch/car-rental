package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.gui.CarConfigurationView;
import de.htwsaar.prog3.carrental.gui.CarTableView;
import de.htwsaar.prog3.carrental.gui.NewCarCreationView;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;
import de.htwsaar.prog3.carrental.util.GUIDialogUtil;
import de.htwsaar.prog3.carrental.util.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the Controller for the Main View of the Carrental Application.
 *
 * @author Lukas Raubuch
 * @see CarTableView
 */
public class CarTableViewController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(CarTableViewController.class);

	private CarService service = new CarService();
	private ObservableList<Car> cars = FXCollections.observableArrayList(service.findAll());

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
	private ComboBox<String> searchComboBoxField;

	@FXML
	private ComboBox<String> searchComboBoxComparator;

	@FXML
	private TextField searchTextField;

	@FXML
	private Button buttonApplyCurrentFilter;

	@FXML
	private Button buttonRemoveCurrentFilter;

	@FXML
	private Button editSelectedCarButton;

	@FXML
	private Button createNewCarButton;

	@FXML
	private Button deleteSelectedCarButton;

	@FXML
	private Button rentSelectedCarButton;

	/**
	 * Handle Clicking the Edit Button.
	 *
	 * @param event
	 */
	@FXML
	protected void handleEditButtonClicked(ActionEvent event) {
		Car toEdit = carTableView.getSelectionModel().getSelectedItem();
		try {
			new CarConfigurationView().start(CarTableView.getPrimaryStage(), toEdit);
		} catch (Exception e) {
			logger.error("Error while editing selected car");
		}
	}

	/**
	 * Handle Clicking the New Button.
	 *
	 * @param event
	 */
	@FXML
	protected void handleNewButtonClicked(ActionEvent event) {
		try {
			new NewCarCreationView().start(CarTableView.getPrimaryStage());
			carTableView.setItems(FXCollections.observableArrayList(service.findAll()));
		} catch (Exception e) {
			logger.error("Error while creating a new Car");
		}
	}

	/**
	 * Handle Clicking the Delete Button.
	 *
	 * @param event
	 */
	@FXML
	protected void handleDeleteButtonClicked(ActionEvent event) {
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
			logger.info("OK Button pressed. Deleting Car...");
			service.delete(toDelete);
			cars.remove(toDelete);
		}
	}

	/**
	 * Initialize the CarTableView with data from the database.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Associate data with Columns
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

		carTableView.setItems(cars);
	}

	@FXML
	public void handleApplyCurrentFilterButtonClicked() {
		// TODO: Implement with Arthur and Julian
	}

	/**
	 * Remove the filtering to display all cars again and not only those matching
	 * the filter. Just set all cars list in the {@code carTableView}
	 */
	@FXML
	public void handleRemoveCurrentFilterButtonClicked() {
		carTableView.setItems(cars);
	}

	/**
	 * Closes the application.
	 *
	 * @see CarTableView#stop()
	 */
	@FXML
	public void closeApplication() {
		logger.info("Closing EntityManagerFactory");
		EntityManagerUtil.closeEntityManagerFactory();
		logger.info("Shutting Down");
		System.exit(0);
	}

	@FXML
	public void handleRentButtonClicked() {
		// TODO: Implement with Michael
	}

	/**
	 * Displays a dialog containing information about this software project.
	 */
	@FXML
	public void handleAboutButtonClicked() {
		Alert aboutDialog = GUIDialogUtil.createInformationDialog(I18nComponentsUtil.getDialogInformationHeaderAbout());
		aboutDialog.show();
	}

	/**
	 * Load FXML for employees view and set the scene with the loaded FXML
	 * @throws IOException 
	 */
	@FXML
	public void switchToEmployeesView() throws IOException {
		CarTableView.setEmployeeView();
	}

	/**
	 * Load FXML for rentals view and set the scene with the loaded FXML
	 */
	@FXML
	public void switchToRentalsView() {
	}

	/**
	 * Load FXML for customers view and set the scene with the loaded FXML
	 */
	@FXML
	public void switchToCustomersView() {
	}

	/**
	 * Already in cars view. Nothing to do
	 */
	@FXML
	public void switchToCarsView() {
	}
}
