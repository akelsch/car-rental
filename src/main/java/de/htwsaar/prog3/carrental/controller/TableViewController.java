package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.*;
import de.htwsaar.prog3.carrental.util.filter.FilterPredicate;
import de.htwsaar.prog3.carrental.util.filter.Operator;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Super controller for all other TableView controllers.
 *
 * @author Lukas Raubuch
 * @author Arthur Kelsch
 */
public abstract class TableViewController<T extends BaseEntity> extends BaseController implements Initializable {

    public final ObservableList<T> entities = FXCollections.observableArrayList();
    private FilteredList<T> filteredEntities;

    @FXML
    public TableView<T> entityTable;
    @FXML
    private ComboBox<String> searchAttributeComboBox;
    @FXML
    private ComboBox<Operator> searchOperatorComboBox;
    @FXML
    private TextField searchValueTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Table
        filteredEntities = new FilteredList<>(entities);
        SortedList<T> sortedEntities = filteredEntities.sorted();
        sortedEntities.comparatorProperty().bind(entityTable.comparatorProperty());
        entityTable.setItems(sortedEntities);
        postInitialize();

        // Search
        searchAttributeComboBox.setItems(entityTable.getColumns().stream()
                .map(TableColumnBase::getText)
                .collect(Collectors.collectingAndThen(Collectors.toList(), FXCollections::observableArrayList)));
        searchOperatorComboBox.setItems(FXCollections.observableArrayList(Operator.class.getEnumConstants()));
    }

    public abstract void postInitialize();

    public void handleKeyEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            handleDeleteClicked();
        }
    }

    public boolean showCarEditView(Car car) {
        return application.showCarEditView(car);
    }

    public boolean showCustomerEditView(Customer customer) {
        return application.showCustomerEditView(customer);
    }

    public boolean showEmployeeEditView(Employee employee) {
        return application.showEmployeeEditView(employee);
    }

    public boolean showRentalEditView(Rental rental) {
        return application.showRentalEditView(rental);
    }

    public void handleCarMenuItemClicked() {
        application.switchToCarTableView();
    }

    public void handleCustomerMenuItemClicked() {
        application.switchToCustomerTableView();
    }

    public void handleEmployeeMenuItemClicked() {
        application.switchToEmployeeTableView();
    }

    public void handleRentalMenuItemClicked() {
        application.switchToRentalTableView();
    }

    public void handleCloseMenuItemClicked() {
        application.stop();
    }

    public abstract void handleNewClicked();

    public abstract void handleEditClicked();

    public abstract void handleDeleteClicked();

    public void handleAboutMenuItemClicked() {
        dialogUtils.showAboutDialog();
    }

    public void handleSearchButtonClicked() {
        String attribute = searchAttributeComboBox.getValue();
        Operator operator = searchOperatorComboBox.getValue();
        String value = searchValueTextField.getText();

        PseudoClass error = PseudoClass.getPseudoClass("error");
        searchAttributeComboBox.pseudoClassStateChanged(error, attribute == null);
        searchOperatorComboBox.pseudoClassStateChanged(error, operator == null);
        searchValueTextField.pseudoClassStateChanged(error, StringUtils.isBlank(value));

        if (attribute != null && operator != null && StringUtils.isNotBlank(value)) {
            filteredEntities.setPredicate(entity -> filter(entity, attribute, operator, value));
        }
    }

    private boolean filter(T entity, String attribute, Operator operator, String value) {
        return entityTable.getColumns().stream()
                .filter(c -> c.getText().equals(attribute))
                .map(c -> c.getCellObservableValue(entity))
                .map(ObservableValue::getValue)
                .findFirst()
                .filter(FilterPredicate.of(operator, value))
                .isPresent();
    }

    public void handleClearFilterButtonClicked() {
        PseudoClass error = PseudoClass.getPseudoClass("error");
        searchAttributeComboBox.pseudoClassStateChanged(error, false);
        searchOperatorComboBox.pseudoClassStateChanged(error, false);
        searchValueTextField.pseudoClassStateChanged(error, false);

        filteredEntities.setPredicate(e -> true);
    }
}
