package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.controller.TableViewController;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.CarRepository;
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

    @Override
    public void updateEntities() {
        entities.setAll(carRepository.findAll());
    }

    @Override
    public void handleNewClicked() {
        Car car = new Car();

        boolean applyClicked = showCarEditView(car);
        if (applyClicked) {
            updateEntities();
        }
    }

    @Override
    public void handleEditClicked() {
        Car car = entityTable.getSelectionModel().getSelectedItem();

        if (car != null) {
            boolean applyClicked = showCarEditView(car);
            if (applyClicked) {
                updateEntities();
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
                        updateEntities();
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
            showRentalEditView(rental);
        }
    }
}
