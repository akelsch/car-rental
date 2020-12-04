package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.RollbackException;
import java.util.Optional;

/**
 * JavaFX controller for the "Customer Table" view.
 *
 * @author Lukas Raubuch
 * @author Jens Thewes
 */
@Component
@RequiredArgsConstructor
public class CustomerTableViewController extends GenericTableViewController<Customer> {

    private final CustomerRepository customerRepository;

    @Override
    void postInitialize() {
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

        if (null == customer) {
            Alert info = DialogUtils.createInformationDialog(I18nUtils.getDialogDeleteNoSelectionText());
            info.show();
            return;
        }

        Alert confirmation = DialogUtils.createConfirmationDialog(I18nUtils.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.orElse(null) == ButtonType.OK) {
            try {
                customerRepository.delete(customer);
                entities.setAll(customerRepository.findAll());
            } catch (RollbackException e) {
                // TODO i18n
                Alert error = DialogUtils.createErrorDialog("Invalid Action", "Can't delete this customer",
                        "You must first delete the rental");
                error.showAndWait();
            }
        }
    }
}
