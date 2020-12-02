package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for RentalTableView.
 *
 * @author Lukas Raubuch
 */
@Component
public class RentalTableViewController extends GenericTableViewController<Rental> implements Initializable {

    private final RentalRepository rentalRepository;

    @FXML
    private TableView<Rental> rentalTableView;

    @FXML
    private TableColumn<Rental, Integer> id;
    @FXML
    private TableColumn<Rental, String> begin;
    @FXML
    private TableColumn<Rental, String> car;
    @FXML
    private TableColumn<Rental, String> customer;
    @FXML
    private TableColumn<Rental, String> employee;
    @FXML
    private TableColumn<Rental, String> end;
    @FXML
    private TableColumn<Rental, String> extraCosts;
    @FXML
    private TableColumn<Rental, String> note;

    @Autowired
    public RentalTableViewController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
        entities = FXCollections.observableArrayList(this.rentalRepository.findAll());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        begin.setCellValueFactory(new PropertyValueFactory<>("Begin"));
        car.setCellValueFactory(new PropertyValueFactory<>("Car"));
        customer.setCellValueFactory(new PropertyValueFactory<>("Customer"));
        employee.setCellValueFactory(new PropertyValueFactory<>("Employee"));
        end.setCellValueFactory(new PropertyValueFactory<>("End"));
        extraCosts.setCellValueFactory(new PropertyValueFactory<>("ExtraCosts"));
        note.setCellValueFactory(new PropertyValueFactory<>("Note"));

        rentalTableView.setItems(entities);
    }

    /**
     * Rental view does not use a "New..." button.
     *
     * @see CarTableViewController#handleRentButtonClicked()
     */
    @Override
    public void handleNewButtonClicked() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handleEditButtonClicked() {
        Rental rental = rentalTableView.getSelectionModel().getSelectedItem();

        if (rental != null) {
            boolean applyClicked = app.showRentalEditView(rental);
            if (applyClicked) {
                rentalRepository.save(rental);
                entities.setAll(rentalRepository.findAll());
            }
        }
    }

    @Override
    public void handleDeleteButtonClicked() {
        Rental rental = rentalTableView.getSelectionModel().getSelectedItem();

        if (null == rental) {
            Alert info = DialogUtil.createInformationDialog(I18nComponentsUtil.getDialogDeleteNoSelectionText());
            info.show();
            return;
        }

        Alert confirmation = DialogUtil.createConfirmationDialog(I18nComponentsUtil.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.orElse(null) == ButtonType.OK) {
            rentalRepository.delete(rental);
            entities.setAll(rentalRepository.findAll());
        }
    }
}
