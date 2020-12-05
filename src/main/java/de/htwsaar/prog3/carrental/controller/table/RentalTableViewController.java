package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

        if (rental != null) {
            dialogUtils.showDeleteConfirmationDialog().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    rentalRepository.delete(rental);
                    entities.setAll(rentalRepository.findAll());
                }
            });
        }
    }
}
