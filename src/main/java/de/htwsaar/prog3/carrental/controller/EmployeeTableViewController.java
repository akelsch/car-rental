package de.htwsaar.prog3.carrental.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.service.EmployeeService;
import de.htwsaar.prog3.carrental.util.GUIDialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for EmployeeTableView
 * @author Lukas Raubuch
 */
public class EmployeeTableViewController extends TableViewController{
	
	private EmployeeService service = new EmployeeService();
	private ObservableList<Employee> employees = FXCollections.observableArrayList(service.findAll());
	
	@FXML
	private TableView<Employee> employeeTableView;
	// TableColumns to associate data with columns
	@FXML
	private TableColumn<Employee, Integer> id;
	@FXML
	private TableColumn<Employee, String> firstName;
	@FXML
	private TableColumn<Employee, String> lastName;
	@FXML
	private TableColumn<Employee, String> position;
	
	
	@Override
	protected void handleDeleteButtonClicked() {
		Employee toDelete = employeeTableView.getSelectionModel().getSelectedItem();
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
			employees.remove(toDelete);
		}
	}
	
	@Override
	protected void handleEditButtonClicked() {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void handleNewButtonClicked() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Initialize the EmployeeTableView with data from the database.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Associate data with columns
		id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		position.setCellValueFactory(new PropertyValueFactory<>("Position"));
		
		employeeTableView.setItems(employees);
		
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
