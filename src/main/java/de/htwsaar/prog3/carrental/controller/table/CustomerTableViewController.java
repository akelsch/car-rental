package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.controller.TableViewController;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

/**
 * JavaFX controller for the "Customer Table" view.
 *
 * @author Lukas Raubuch
 * @author Jens Thewes
 */
@Component
@RequiredArgsConstructor
public class CustomerTableViewController extends TableViewController<Customer> {

    private final CustomerRepository customerRepository;

    @Override
    public void postInitialize() {
        entities.setAll(customerRepository.findAll());
    }

    @Override
    public void handleNewClicked() {
        Customer customer = new Customer();

        boolean applyClicked = showCustomerEditView(customer);
        if (applyClicked) {
            customerRepository.save(customer);
            entities.setAll(customerRepository.findAll());
        }
    }

    @Override
    public void handleEditClicked() {
        Customer customer = entityTable.getSelectionModel().getSelectedItem();

        if (customer != null) {
            boolean applyClicked = showCustomerEditView(customer);
            if (applyClicked) {
                customerRepository.save(customer);
                entities.setAll(customerRepository.findAll());
            }
        }
    }

    @Override
    public void handleDeleteClicked() {
        Customer customer = entityTable.getSelectionModel().getSelectedItem();

        if (customer != null) {
            dialogUtils.showDeleteConfirmationDialog().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    try {
                        customerRepository.delete(customer);
                        entities.setAll(customerRepository.findAll());
                    } catch (DataIntegrityViolationException e) {
                        dialogUtils.showDeleteErrorDialog();
                    }
                }
            });
        }
    }
}
