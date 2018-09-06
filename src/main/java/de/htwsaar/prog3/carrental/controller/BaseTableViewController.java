package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.util.GUIDialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.view.CarTableView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Setter;

/**
 * Super Controller for all other TableView controllers.
 *
 * @author Lukas Raubuch, Arthur Kelsch
 */
public abstract class BaseTableViewController implements Initializable {
	// Primary stage
	Stage primaryStage = CarTableView.getPrimaryStage();

	// Scenes
	@Setter
	private Scene carScene;

	@Setter
	private Scene customerScene;

	@Setter
	private Scene employeeScene;

	@Setter
	private Scene rentalScene;

	// FXML
	@FXML
	public ComboBox<String> searchComboBoxField;

	@FXML
	public ComboBox<String> searchComboBoxComparator;

	@FXML
	public TextField searchTextField;

	@FXML
	public Button buttonApplyCurrentFilter;

	@FXML
	public Button buttonRemoveCurrentFilter;

	@FXML
	public Button buttonEditSelectedObject;

	@FXML
	public Button buttonDeleteSelectedObject;

	/**
	 * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Car Car}.
	 */
	public void handleCarMenuItemClicked() {
		primaryStage.setScene(carScene);
	}

	/**
	 * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Customer Customer}.
	 */
	public void handleCustomerMenuItemClicked() {
		primaryStage.setScene(customerScene);
	}

	/**
	 * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Employee Employee}.
	 */
	public void handleEmployeeMenuItemClicked() {
		primaryStage.setScene(employeeScene);
	}

	/**
	 * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Rental Rental}.
	 */
	public void handleRentalMenuItemClicked() {
		primaryStage.setScene(rentalScene);
	}

	/**
	 * Closes the application.
	 *
	 * @see CarTableView#stop()
	 */
	public void handleCloseMenuItemClicked() {
		Platform.exit();
	}

	/**
	 * Displays a dialog containing information about this software project.
	 */
	public void handleAboutMenuItemClicked() {
		Alert aboutDialog = GUIDialogUtil.createInformationDialog(I18nComponentsUtil.getDialogInformationHeaderAbout());
		aboutDialog.show();
	}

	/**
	 * Handle pressing the "Search" button.
	 */
	public abstract void handleApplyCurrentFilterButtonClicked();

	/**
	 * Handle pressing the "Clear filter" button.
	 */
	public abstract void handleRemoveCurrentFilterButtonClicked();

	/**
	 * Handle pressing the "New..." button.
	 */
	public abstract void handleNewButtonClicked();

	/**
	 * Handle pressing the "Edit..." button.
	 */
	public abstract void handleEditButtonClicked();

	/**
	 * Handle pressing the "Delete..." button.
	 */
	public abstract void handleDeleteButtonClicked();

	/**
	 * Clears the top two search ComboBox and TextField values.
	 */
	void clearSearchComboBoxAndTextFieldValues() {
		searchComboBoxField.getSelectionModel().clearSelection();
		searchComboBoxComparator.getSelectionModel().clearSelection();
		searchTextField.clear();
	}

	/**
	 * Clears the top two search ComboBox borders.
	 */
	void clearSearchComboBoxBorders() {
		searchComboBoxField.setBorder(null);
		searchComboBoxComparator.setBorder(null);
	}

	/**
	 * Sets the top two search ComboBox borders if they are empty.
	 */
	void setSearchComboBoxBordersIfEmpty() {
		// Border radii taken from modena.css
		Border border = new Border(new BorderStroke(
				Color.RED,
				BorderStrokeStyle.SOLID,
				new CornerRadii(3.0, 3.0, 2.0, 1.0, false),
				BorderWidths.DEFAULT
		));

		if (searchComboBoxField.getSelectionModel().isEmpty()) {
			searchComboBoxField.setBorder(border);
		} else {
			searchComboBoxField.setBorder(null);
		}

		if (searchComboBoxComparator.getSelectionModel().isEmpty()) {
			searchComboBoxComparator.setBorder(border);
		} else {
			searchComboBoxComparator.setBorder(null);
		}
	}
}
