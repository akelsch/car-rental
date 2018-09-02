package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.view.CarTableView;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;
import de.htwsaar.prog3.carrental.util.GUIDialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Super Controller that is used to keep the remaining controllers small enough
 * to be easy to read
 *
 * @author Lukas Raubuch
 */
public abstract class TableViewController implements Initializable {
	private Scene carScene;
	private Scene employeeScene;
	private Scene rentalScene;
	private Scene customerScene;
	protected final Stage primaryStage = CarTableView.getPrimaryStage();

	@FXML
	protected Button buttonApplyCurrentFilter;

	@FXML
	protected Button buttonRemoveCurrentFilter;

	@FXML
	protected Button buttonEditSelectedObject;

	@FXML
	protected Button buttonDeleteSelectedObject;

	@FXML
	protected TextField searchTextField;

	@FXML
	protected ComboBox<String> searchComboBoxField;

	@FXML
	protected ComboBox<String> searchComboBoxComparator;

	/**
	 * Handle Clicking the Edit Button.
	 */
	@FXML
	protected abstract void handleEditButtonClicked();

	/**
	 * Handle Clicking the Delete Button.
	 */
	@FXML
	protected abstract void handleDeleteButtonClicked();

	/**
	 * Handle Clicking the Clear Filter Button
	 */
	@FXML
	protected abstract void handleRemoveCurrentFilterButtonClicked();

	/**
	 * Handle Clicking the Apply Filter Button
	 */
	@FXML
	protected abstract void handleApplyCurrentFilterButtonClicked();

	// Menubar Operations

	/**
	 * Displays a dialog containing information about this software project.
	 */
	@FXML
	public void handleAboutButtonClicked() {
		Alert aboutDialog = GUIDialogUtil.createInformationDialog(I18nComponentsUtil.getDialogInformationHeaderAbout());
		aboutDialog.show();
	}

	/**
	 * Closes the application.
	 *
	 * @see CarTableView#stop()
	 */
	@FXML
	public void closeApplication() {
		Platform.exit();
	}

	// Switching between Scenes

	/**
	 * Load FXML for employees view and set the scene with the loaded FXML
	 */
	@FXML
	public void switchToEmployeesView(ActionEvent event) {
		primaryStage.setScene(employeeScene);
	}

	/**
	 * Load FXML for rentals view and set the scene with the loaded FXML
	 */
	@FXML
	public void switchToRentalsView() {
		primaryStage.setScene(rentalScene);
	}

	/**
	 * Load FXML for customers view and set the scene with the loaded FXML
	 */
	@FXML
	public void switchToCustomersView() {
		primaryStage.setScene(customerScene);
	}

	/**
	 * Load FXML for cars view and set the scene with the loaded FXML
	 */
	@FXML
	public void switchToCarsView() {
		primaryStage.setScene(carScene);
	}

	public void setCarScene(Scene scene) {
		carScene = scene;
	}

	public void setEmployeeScene(Scene scene) {
		employeeScene = scene;
	}

	public void setCustomerScene(Scene scene) {
		customerScene = scene;
	}

	public void setRentalScene(Scene scene) {
		rentalScene = scene;
	}
}
