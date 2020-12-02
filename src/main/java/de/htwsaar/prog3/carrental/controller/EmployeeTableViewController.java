package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.RollbackException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for EmployeeTableView.
 *
 * @author Lukas Raubuch, Jens Thewes
 */
@Component
public class EmployeeTableViewController extends GenericTableViewController<Employee> implements Initializable {

    private final EmployeeRepository employeeRepository;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, Integer> id;
    @FXML
    private TableColumn<Employee, String> firstName;
    @FXML
    private TableColumn<Employee, String> lastName;
    @FXML
    private TableColumn<Employee, String> position;

    @Autowired
    public EmployeeTableViewController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        entities = FXCollections.observableArrayList(this.employeeRepository.findAll());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        position.setCellValueFactory(new PropertyValueFactory<>("Position"));

        employeeTableView.setItems(entities);
    }

    @Override
    public void handleNewButtonClicked() {
        Employee employee = new Employee();

        boolean applyClicked = application.showEmployeeEditView(employee);
        if (applyClicked) {
            employeeRepository.save(employee);
            entities.setAll(employeeRepository.findAll());
        }
    }

    @Override
    public void handleEditButtonClicked() {
        Employee employee = employeeTableView.getSelectionModel().getSelectedItem();

        if (employee != null) {
            boolean applyClicked = application.showEmployeeEditView(employee);
            if (applyClicked) {
                employeeRepository.save(employee);
                entities.setAll(employeeRepository.findAll());
            }
        }
    }

    @Override
    public void handleDeleteButtonClicked() {
        Employee employee = employeeTableView.getSelectionModel().getSelectedItem();

        if (null == employee) {
            Alert info = DialogUtil.createInformationDialog(I18nUtils.getDialogDeleteNoSelectionText());
            info.show();
            return;
        }

        Alert confirmation = DialogUtil.createConfirmationDialog(I18nUtils.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.orElse(null) == ButtonType.OK) {
            try {
                employeeRepository.delete(employee);
                entities.setAll(employeeRepository.findAll());
            } catch (RollbackException e) {
                // TODO i18n
                Alert alert = DialogUtil.createErrorDialog("Invalid Action", "Can't delete this employee",
                        "You must first delete the rental");
                alert.showAndWait();
            }
        }
    }
}
