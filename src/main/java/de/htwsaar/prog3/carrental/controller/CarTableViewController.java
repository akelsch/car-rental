package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.RollbackException;
import java.util.Optional;

/**
 * JavaFX controller for the "Car Table" view (main).
 *
 * @author Lukas Raubuch
 */
@Component
@RequiredArgsConstructor
public class CarTableViewController extends GenericTableViewController<Car> {

    private final CarRepository carRepository;
    private final RentalRepository rentalRepository;

    @Override
    void postInitialize() {
        entities.setAll(carRepository.findAll());
    }

    @Override
    public void handleNewClicked() {
        Car car = new Car();

        boolean applyClicked = showCarEditView(car);
        if (applyClicked) {
            carRepository.save(car);
            entities.setAll(carRepository.findAll());
        }
    }

    @Override
    public void handleEditClicked() {
        Car car = entityTable.getSelectionModel().getSelectedItem();

        if (car != null) {
            boolean applyClicked = showCarEditView(car);
            if (applyClicked) {
                carRepository.save(car);
                entities.setAll(carRepository.findAll());
            }
        }
    }

    @Override
    public void handleDeleteClicked() {
        Car car = entityTable.getSelectionModel().getSelectedItem();

        if (null == car) {
            Alert info = DialogUtils.createInformationDialog(I18nUtils.getDialogDeleteNoSelectionText());
            info.show();
            return;
        }

        Alert confirmation = DialogUtils.createConfirmationDialog(I18nUtils.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.orElse(null) == ButtonType.OK) {
            try {
                carRepository.delete(car);
                entities.setAll(carRepository.findAll());
            } catch (RollbackException e) {
                // TODO choose different exception
                // TODO i18n
                Alert error = DialogUtils.createErrorDialog("Invalid Action", "Can't delete this car",
                        "You must first delete the rental");
                error.showAndWait();
            }
        }
    }

    public void handleRentClicked() {
        Car car = entityTable.getSelectionModel().getSelectedItem();

        if (car != null) {
            Rental rental = new Rental();
            rental.setCar(car);

            boolean applyClicked = showRentalEditView(rental);
            if (applyClicked) {
                rentalRepository.save(rental);
                entities.setAll(carRepository.findAll());
            }
        }
    }
}
