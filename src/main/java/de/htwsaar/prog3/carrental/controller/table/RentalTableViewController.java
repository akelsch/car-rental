package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.controller.TableViewController;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * JavaFX controller for the "Rental Table" view.
 *
 * @author Lukas Raubuch
 */
@Controller
@RequiredArgsConstructor
public class RentalTableViewController extends TableViewController<Rental> {

    private final RentalRepository rentalRepository;

    @Override
    public void postInitialize() {
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
            getDialogUtils().showDeleteConfirmationDialog().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    rentalRepository.delete(rental);
                    entities.setAll(rentalRepository.findAll());
                }
            });
        }
    }
}
