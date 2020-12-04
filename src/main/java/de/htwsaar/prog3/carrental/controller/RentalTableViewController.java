package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * JavaFX controller for the "Rental Table" view.
 *
 * @author Lukas Raubuch
 */
@Component
@RequiredArgsConstructor
public class RentalTableViewController extends GenericTableViewController<Rental> {

    private final RentalRepository rentalRepository;

    @Override
    void postInitialize() {
        entities.setAll(rentalRepository.findAll());
    }

    /**
     * @see CarTableViewController#handleRentClicked()
     */
    @Override
    public void handleNewClicked() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handleEditClicked() {
        Rental rental = entityTable.getSelectionModel().getSelectedItem();

        if (rental != null) {
            boolean applyClicked = showRentalEditView(rental);
            if (applyClicked) {
                rentalRepository.save(rental);
                entities.setAll(rentalRepository.findAll());
            }
        }
    }

    @Override
    public void handleDeleteClicked() {
        Rental rental = entityTable.getSelectionModel().getSelectedItem();

        if (null == rental) {
            Alert info = DialogUtils.createInformationDialog(I18nUtils.getDialogDeleteNoSelectionText());
            info.show();
            return;
        }

        Alert confirmation = DialogUtils.createConfirmationDialog(I18nUtils.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.orElse(null) == ButtonType.OK) {
            rentalRepository.delete(rental);
            entities.setAll(rentalRepository.findAll());
        }
    }
}
