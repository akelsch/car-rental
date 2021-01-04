package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.controller.TableViewController;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.CarRepository;
import de.htwsaar.prog3.carrental.util.fx.Labelable;
import de.htwsaar.prog3.carrental.util.fx.LabelableCellFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JavaFX controller for the "Car Table" view (main).
 *
 * @author Lukas Raubuch
 */
@Controller
@RequiredArgsConstructor
public class CarTableViewController extends TableViewController<Car> {

    private final CarRepository carRepository;

    @SuppressWarnings("unchecked")
    @Override
    public void postInitialize() {
        List<TableColumn<Car, Labelable>> enumColumns = entityTable.getColumns().stream()
                .filter(c -> c.getStyleClass().contains("labelable-enum"))
                .map(c -> (TableColumn<Car, Labelable>) c)
                .collect(Collectors.toList());

        enumColumns.forEach(c -> c.setCellFactory(new LabelableCellFactory<>(getMessageUtils())));
    }

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
