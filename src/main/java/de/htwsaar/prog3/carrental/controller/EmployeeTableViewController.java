package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.RollbackException;
import java.util.Optional;

/**
 * JavaFX controller for the "Employee Table" view.
 *
 * @author Lukas Raubuch
 * @author Jens Thewes
 */
@Component
@RequiredArgsConstructor
public class EmployeeTableViewController extends GenericTableViewController<Employee> {

    private final EmployeeRepository employeeRepository;

    @Override
    void postInitialize() {
        entities.setAll(employeeRepository.findAll());
    }

    @Override
    public void handleNewClicked() {
        Employee employee = new Employee();

        boolean applyClicked = showEmployeeEditView(employee);
        if (applyClicked) {
            employeeRepository.save(employee);
            entities.setAll(employeeRepository.findAll());
        }
    }

    @Override
    public void handleEditClicked() {
        Employee employee = entityTable.getSelectionModel().getSelectedItem();

        if (employee != null) {
            boolean applyClicked = showEmployeeEditView(employee);
            if (applyClicked) {
                employeeRepository.save(employee);
                entities.setAll(employeeRepository.findAll());
            }
        }
    }

    @Override
    public void handleDeleteClicked() {
        Employee employee = entityTable.getSelectionModel().getSelectedItem();

        if (null == employee) {
            Alert info = DialogUtils.createInformationDialog(I18nUtils.getDialogDeleteNoSelectionText());
            info.show();
            return;
        }

        Alert confirmation = DialogUtils.createConfirmationDialog(I18nUtils.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.orElse(null) == ButtonType.OK) {
            try {
                employeeRepository.delete(employee);
                entities.setAll(employeeRepository.findAll());
            } catch (RollbackException e) {
                // TODO i18n
                Alert alert = DialogUtils.createErrorDialog("Invalid Action", "Can't delete this employee",
                        "You must first delete the rental");
                alert.showAndWait();
            }
        }
    }
}
