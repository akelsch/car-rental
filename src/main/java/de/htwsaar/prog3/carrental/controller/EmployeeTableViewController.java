package de.htwsaar.prog3.carrental.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import de.htwsaar.prog3.carrental.view.EditEmployeeView;
import de.htwsaar.prog3.carrental.view.NewEmployeeView;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.service.EmployeeService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for EmployeeTableView.
 * 
 * @author Lukas Raubuch, Jens Thewes
 */
public class EmployeeTableViewController extends GenericTableViewController<Employee> {
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

    public EmployeeTableViewController() {
        service = new EmployeeService();
        entities = FXCollections.observableArrayList(service.findAll());
    }

    @Override
    public void handleNewButtonClicked() {
        new NewEmployeeView().start(primaryStage);
        employeeTableView.setItems(FXCollections.observableArrayList(service.findAll()));
    }

    @Override
    public void handleEditButtonClicked() {
        Employee toEdit = employeeTableView.getSelectionModel().getSelectedItem();
        new EditEmployeeView().start(primaryStage, toEdit);
        employeeTableView.setItems(FXCollections.observableArrayList(service.findAll()));
    }

    @Override
    public void handleDeleteButtonClicked() {
        Employee toDelete = employeeTableView.getSelectionModel().getSelectedItem();
        if (null == toDelete) {
            Alert informationDialog = DialogUtil.createInformationDialog(
                    I18nComponentsUtil.getDialogDeleteNoSelectionText());
            informationDialog.show();
            return;
        }
        Alert confirmationDialog = DialogUtil
                .createConfirmationDialog(I18nComponentsUtil.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            service.delete(toDelete);
            entities.remove(toDelete);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Associate data with columns
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        position.setCellValueFactory(new PropertyValueFactory<>("Position"));

        employeeTableView.setItems(entities);
    }
}
