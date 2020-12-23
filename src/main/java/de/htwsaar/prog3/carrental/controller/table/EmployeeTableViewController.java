package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.controller.TableViewController;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

/**
 * JavaFX controller for the "Employee Table" view.
 *
 * @author Lukas Raubuch
 * @author Jens Thewes
 */
@Controller
@RequiredArgsConstructor
public class EmployeeTableViewController extends TableViewController<Employee> {

    private final EmployeeRepository employeeRepository;

    @Override
    public void updateEntities() {
        entities.setAll(employeeRepository.findAll());
    }

    @Override
    public void handleNewClicked() {
        Employee employee = new Employee();

        boolean applyClicked = showEmployeeEditView(employee);
        if (applyClicked) {
            updateEntities();
        }
    }

    @Override
    public void handleEditClicked() {
        Employee employee = entityTable.getSelectionModel().getSelectedItem();

        if (employee != null) {
            boolean applyClicked = showEmployeeEditView(employee);
            if (applyClicked) {
                updateEntities();
            }
        }
    }

    @Override
    public void handleDeleteClicked() {
        Employee employee = entityTable.getSelectionModel().getSelectedItem();

        if (employee != null) {
            getDialogUtils().showDeleteConfirmationDialog().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    try {
                        employeeRepository.delete(employee);
                        updateEntities();
                    } catch (DataIntegrityViolationException e) {
                        getDialogUtils().showDeleteErrorDialog();
                    }
                }
            });
        }
    }
}
