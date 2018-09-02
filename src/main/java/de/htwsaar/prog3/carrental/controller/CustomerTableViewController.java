package de.htwsaar.prog3.carrental.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import de.htwsaar.prog3.carrental.view.EditCustomerView;
import de.htwsaar.prog3.carrental.view.NewCustomerView;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.util.GUIDialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for CustomerTableView
 * 
 * @author Lukas Raubuch, Jens Thewes
 */
public class CustomerTableViewController extends TableViewController {

    private CustomerService service = new CustomerService();
    private ObservableList<Customer> customers =
            FXCollections.observableArrayList(service.findAll());

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

    @FXML
    private Button buttonCreateNewCustomer;

    @Override
    protected void handleDeleteButtonClicked() {
        Customer toDelete = customerTableView.getSelectionModel().getSelectedItem();
        if (null == toDelete) {
            Alert informationDialog = GUIDialogUtil.createInformationDialog(
                    I18nComponentsUtil.getInformationDialogHeaderNoObjectSelected());
            informationDialog.show();
            return;
        }
        Alert confirmationDialog = GUIDialogUtil
                .createConfirmationDialog(I18nComponentsUtil.getConfirmationDialogHeaderDelete());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.get() == ButtonType.OK) {
            service.delete(toDelete);
            customers.remove(toDelete);
        }
    }

    /**
     * Handle Clicking the Edit Button.
     *
     */
    @Override
    protected void handleEditButtonClicked() {
        Customer toEdit = customerTableView.getSelectionModel().getSelectedItem();
        try {
            new EditCustomerView().start(primaryStage, toEdit);
            customerTableView.setItems(FXCollections.observableArrayList(service.findAll()));
        } catch (Exception e) {
            // TODO Logger?
        }
    }

    /**
     * Handle Clicking the New Button.
     */
    @FXML
    private void handleNewButtonClicked() {
        try {
            new NewCustomerView().start(primaryStage);
            customerTableView.setItems(FXCollections.observableArrayList(service.findAll()));
        } catch (Exception e) {
            // TODO Logger?
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        customerTableView.setItems(customers);
    }

    @Override
    protected void handleRemoveCurrentFilterButtonClicked() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void handleApplyCurrentFilterButtonClicked() {
        // TODO Auto-generated method stub

    }

}