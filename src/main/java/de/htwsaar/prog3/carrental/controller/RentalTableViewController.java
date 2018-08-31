package de.htwsaar.prog3.carrental.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.service.RentalService;
import de.htwsaar.prog3.carrental.util.GUIDialogUtil;
import de.htwsaar.prog3.carrental.util.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for RentalTableView
 * @author Lukas Raubuch
 */
public class RentalTableViewController extends TableViewController {

	private RentalService service = new RentalService();
	private ObservableList<Rental> rentals = FXCollections.observableArrayList(service.findAll());

	@FXML
	private TableView<Rental> rentalTableView;
	// TableColumns to associate data with columns
	@FXML
	private TableColumn<Rental, Integer> id;
	@FXML
	private TableColumn<Rental, String> begin;
	@FXML
	private TableColumn<Rental, String> car;
	@FXML
	private TableColumn<Rental, String> customer;
	@FXML
	private TableColumn<Rental, String> employee;
	@FXML
	private TableColumn<Rental, String> end;
	@FXML
	private TableColumn<Rental, String> extraCosts;
	@FXML
	private TableColumn<Rental, String> note;

	@Override
	protected void handleDeleteButtonClicked() {
		Rental toDelete = rentalTableView.getSelectionModel().getSelectedItem();
		if (null == toDelete) {
			Alert informationDialog = GUIDialogUtil
					.createInformationDialog(I18nComponentsUtil.getInformationDialogHeaderNoObjectSelected());
			informationDialog.show();
			return;
		}
		Alert confirmationDialog = GUIDialogUtil
				.createConfirmationDialog(I18nComponentsUtil.getConfirmationDialogHeaderDelete());
		Optional<ButtonType> result = confirmationDialog.showAndWait();
		if (result.get() == ButtonType.OK) {
			service.delete(toDelete);
			rentals.remove(toDelete);
		}
	}

	@Override
	protected void handleEditButtonClicked() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Initialize the RentalTableView with data from the database.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		begin.setCellValueFactory(new PropertyValueFactory<>("Begin"));
		car.setCellValueFactory(new PropertyValueFactory<>("Car"));
		customer.setCellValueFactory(new PropertyValueFactory<>("Customer"));
		employee.setCellValueFactory(new PropertyValueFactory<>("Employee"));
		end.setCellValueFactory(new PropertyValueFactory<>("End"));
		extraCosts.setCellValueFactory(new PropertyValueFactory<>("ExtraCosts"));
		note.setCellValueFactory(new PropertyValueFactory<>("Note"));
		
		rentalTableView.setItems(rentals);
	}

	@Override
	protected void handleRemoveCurrentFilterButtonClicked() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void handleApplyCurrentFilterButtonClicked() {
		// TODO Auto-generated method stub

	}

}
