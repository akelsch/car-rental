package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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
    private ComboBox<String> searchOperatorComboBox;
    @FXML
    private TextField searchValueTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filteredEntities = new FilteredList<>(entities);
        SortedList<T> sortedEntities = filteredEntities.sorted();
        sortedEntities.comparatorProperty().bind(entityTable.comparatorProperty());
        entityTable.setItems(sortedEntities);
        postInitialize();
    }

    public abstract void postInitialize();

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

    public void handleKeyEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            handleDeleteClicked();
        }
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
        String operator = searchOperatorComboBox.getValue();
        String value = searchValueTextField.getText();

        PseudoClass error = PseudoClass.getPseudoClass("error");
        searchAttributeComboBox.pseudoClassStateChanged(error, attribute == null);
        searchOperatorComboBox.pseudoClassStateChanged(error, operator == null);
        searchValueTextField.pseudoClassStateChanged(error, StringUtils.isBlank(value));

        if (attribute != null && operator != null && StringUtils.isNotBlank(value)) {
            filteredEntities.setPredicate(entity -> filter(entity, attribute, operator, value));
        }
    }

    private boolean filter(T entity, String attribute, String operator, String value) {
        Optional<?> column = entityTable.getColumns().stream()
                .filter(c -> c.getText().equals(attribute))
                .map(c -> c.getCellObservableValue(entity))
                .map(ObservableValue::getValue)
                .findFirst();

        if (column.isPresent()) {
            Object columnValue = column.get();

            if (columnValue instanceof Integer) {
                int num = (int) columnValue;
                // TODO exception handling
                if ("=".equals(operator)) {
                    return num == Integer.parseInt(value);
                } else if (">".equals(operator)) {
                    return num > Integer.parseInt(value);
                } else if ("<".equals(operator)) {
                    return num < Integer.parseInt(value);
                }
            } else {
                if (operator.equals("="))
                    return StringUtils.equalsIgnoreCase(columnValue.toString(), value);
                else if (operator.equals("LIKE"))
                    return StringUtils.containsIgnoreCase(columnValue.toString(), value);
            }
        }

        return false;
    }

    public void handleClearFilterButtonClicked() {
        PseudoClass error = PseudoClass.getPseudoClass("error");
        searchAttributeComboBox.pseudoClassStateChanged(error, false);
        searchOperatorComboBox.pseudoClassStateChanged(error, false);
        searchValueTextField.pseudoClassStateChanged(error, false);

        filteredEntities.setPredicate(e -> true);
    }
}
