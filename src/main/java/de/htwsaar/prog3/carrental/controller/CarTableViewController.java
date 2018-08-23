package de.htwsaar.prog3.carrental.controller;

import java.util.Optional;

import de.htwsaar.prog3.carrental.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

/**
 * This is the Controller for the Main View of the Carrental Application
 * 
 * @author Lukas Raubuch
 */
public class CarTableViewController {

	private CarService service;
	
	@FXML
	private TableView<Car> carTableView;

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
		Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
		confirmationDialog.setTitle(I18nComponentsUtil.getDeleteConfirmationDialogTitleString());
		confirmationDialog.setHeaderText(I18nComponentsUtil.getDeleteConfirmationDialogHeaderString());
		Optional<ButtonType> result = confirmationDialog.showAndWait();
		if (result.get() == ButtonType.OK) {
			service = new CarService();
			service.deleteById(toDelete.getId());
		}
	}
}
