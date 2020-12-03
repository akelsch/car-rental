package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import de.htwsaar.prog3.carrental.util.I18nUtils;
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

import javax.persistence.RollbackException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * JavaFX controller for the "Customer Table" view.
 *
 * @author Lukas Raubuch
 * @author Jens Thewes
 */
@Component
public class CustomerTableViewController extends GenericTableViewController<Customer> implements Initializable {

    private final CustomerRepository customerRepository;

    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, Integer> id;
    @FXML
    private TableColumn<Customer, String> city;
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
    private TableColumn<Customer, String> idNumber;
    @FXML
    private TableColumn<Customer, String> lastName;
    @FXML
    private TableColumn<Customer, String> phoneNumber;
    @FXML
    private TableColumn<Customer, String> street;
    @FXML
    private TableColumn<Customer, String> zipCode;

    @Autowired
    public CustomerTableViewController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        city.setCellValueFactory(new PropertyValueFactory<>("City"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        driverLicenseId.setCellValueFactory(new PropertyValueFactory<>("DriverLicenseId"));
        emailAddress.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        houseNumber.setCellValueFactory(new PropertyValueFactory<>("HouseNumber"));
        idNumber.setCellValueFactory(new PropertyValueFactory<>("IdNumber"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        street.setCellValueFactory(new PropertyValueFactory<>("Street"));
        zipCode.setCellValueFactory(new PropertyValueFactory<>("ZipCode"));

        entities = FXCollections.observableArrayList(customerRepository.findAll());
        customerTableView.setItems(entities);
    }

    @Override
    public void handleNewClicked() {
        Customer customer = new Customer();

        boolean applyClicked = application.showCustomerEditView(customer);
        if (applyClicked) {
            customerRepository.save(customer);
            entities.setAll(customerRepository.findAll());
        }
    }

    @Override
    public void handleEditClicked() {
        Customer customer = customerTableView.getSelectionModel().getSelectedItem();

        if (customer != null) {
            boolean applyClicked = application.showCustomerEditView(customer);
            if (applyClicked) {
                customerRepository.save(customer);
                entities.setAll(customerRepository.findAll());
            }
        }
    }

    @Override
    public void handleDeleteClicked() {
        Customer customer = customerTableView.getSelectionModel().getSelectedItem();

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
