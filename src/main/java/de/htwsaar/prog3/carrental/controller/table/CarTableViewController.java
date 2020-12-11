package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.controller.TableViewController;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

/**
 * JavaFX controller for the "Car Table" view (main).
 *
 * @author Lukas Raubuch
 */
@Controller
@RequiredArgsConstructor
public class CarTableViewController extends TableViewController<Car> {

    private final CarRepository carRepository;
    private final RentalRepository rentalRepository;

    @Override
    public void postInitialize() {
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

        if (car != null) {
            getDialogUtils().showDeleteConfirmationDialog().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    try {
                        carRepository.delete(car);
                        entities.setAll(carRepository.findAll());
                    } catch (DataIntegrityViolationException e) {
                        getDialogUtils().showDeleteErrorDialog();
                    }
                }
            });
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
