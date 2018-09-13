package de.htwsaar.prog3.carrental.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.view.EditCustomerView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for CustomerTableView.
 *
 * @author Lukas Raubuch, Jens Thewes
 */
public class CustomerTableViewController extends GenericTableViewController<Customer>
        implements Initializable {
    @FXML
    private TableView<Customer> customerTableView;
    // TableColumns to associate data with columns
    @FXML
    private TableColumn<Customer, Integer> id;
    @FXML
    private TableColumn<Customer, String> dateOfBirth;
    @FXML
    private TableColumn<Customer, String> driverLicenseId;
    @FXML
    private TableColumn<Customer, String> emailAddress;
    @FXML
    private TableColumn<Customer, String> firstName;
    @FXML
    private TableColumn<Customer, Integer> houseNumber;
    @FXML
    private TableColumn<Customer, String> lastName;
    @FXML
    private TableColumn<Customer, String> phoneNumber;
    @FXML
    private TableColumn<Customer, String> street;
    @FXML
    private TableColumn<Customer, String> city;
    @FXML
    private TableColumn<Customer, String> idNumber;
    @FXML
    private TableColumn<Customer, String> zipCode;

    public CustomerTableViewController() {
        service = new CustomerService();
        entities = FXCollections.observableArrayList(service.findAll());
    }

    @Override
    public void handleNewButtonClicked() {
        Customer newCustomer = new Customer();
        boolean applyClicked = new EditCustomerView().start(primaryStage, newCustomer);
        if (applyClicked) {
            service.persist(newCustomer);
            entities.setAll(service.findAll());
        }
    }

    @Override
    public void handleEditButtonClicked() {
        Customer toEdit = customerTableView.getSelectionModel().getSelectedItem();
        if (toEdit != null) {
            boolean applyClicked = new EditCustomerView().start(primaryStage, toEdit);
            if (applyClicked) {
                service.update(toEdit);
                entities.setAll(service.findAll());
            }
        } else {
            // TODO show Warning
        }
    }

    @Override
    public void handleDeleteButtonClicked() {
        Customer toDelete = customerTableView.getSelectionModel().getSelectedItem();
        if (null == toDelete) {
            Alert informationDialog = DialogUtil
                    .createInformationDialog(I18nComponentsUtil.getDialogDeleteNoSelectionText());
            informationDialog.show();
            return;
        }
        Alert confirmationDialog = DialogUtil
                .createConfirmationDialog(I18nComponentsUtil.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.orElse(null) == ButtonType.OK) {
            service.delete(toDelete);
            entities.setAll(service.findAll());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Associate data with columns
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        driverLicenseId.setCellValueFactory(new PropertyValueFactory<>("DriverLicenseId"));
        emailAddress.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        houseNumber.setCellValueFactory(new PropertyValueFactory<>("HouseNumber"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        street.setCellValueFactory(new PropertyValueFactory<>("Street"));
        city.setCellValueFactory(new PropertyValueFactory<>("City"));
        idNumber.setCellValueFactory(new PropertyValueFactory<>("IdNumber"));
        zipCode.setCellValueFactory(new PropertyValueFactory<>("ZipCode"));

        customerTableView.setItems(entities);
    }
}
