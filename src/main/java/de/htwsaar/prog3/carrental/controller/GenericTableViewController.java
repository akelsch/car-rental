package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.CarRentalUiApplication;
import de.htwsaar.prog3.carrental.model.BaseEntity;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Super controller for all other TableView controllers.
 *
 * @author Lukas Raubuch
 * @author Arthur Kelsch
 */
public abstract class GenericTableViewController<T extends BaseEntity> {

    ObservableList<T> entities;

    CarRentalUiApplication application;

    @FXML
    public ComboBox<String> searchComboBoxField;
    @FXML
    public ComboBox<String> searchComboBoxComparator;
    @FXML
    public TextField searchTextField;

    @Autowired
    public final void setApplication(CarRentalUiApplication application) {
        this.application = application;
    }

    /**
     * Handles key presses within a TableView.
     *
     * @param event the event that occurred also containing the button that was pressed
     */
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
        DialogUtils.createInformationDialog(I18nUtils.getDialogAboutText()).show();
    }

    /**
     * Applies the selected filters to the current entity list.
     */
    public void handleApplyCurrentFilterButtonClicked() {
        setSearchComboBoxAndTextFieldBordersIfEmpty();

        String field = searchComboBoxField.getValue();
        String comparator = searchComboBoxComparator.getValue();
        String value = searchTextField.getText();

        if (field != null && comparator != null && !value.isEmpty()) {
            // TODO filter
//            entities.setAll(service.filter(field, comparator, value));
        }
    }

    /**
     * Removes the selected filters of the current entity list.
     */
    public void handleRemoveCurrentFilterButtonClicked() {
        clearSearchComboBoxAndTextFieldBorders();
        clearSearchComboBoxAndTextFieldValues();

        // TODO filter
//        entities.setAll(service.findAll());
    }

    /**
     * Clears the top two search ComboBox and TextField values.
     */
    private void clearSearchComboBoxAndTextFieldValues() {
        searchComboBoxField.getSelectionModel().clearSelection();
        searchComboBoxComparator.getSelectionModel().clearSelection();
        searchTextField.clear();
    }

    /**
     * Clears the top two search ComboBox and TextField borders.
     */
    private void clearSearchComboBoxAndTextFieldBorders() {
        searchComboBoxField.setBorder(null);
        searchComboBoxComparator.setBorder(null);
        searchTextField.setBorder(null);
    }

    /**
     * Sets the top two search ComboBox and TextField borders if they are empty.
     */
    private void setSearchComboBoxAndTextFieldBordersIfEmpty() {
        // Border radii taken from modena.css
        Border border = new Border(new BorderStroke(
                Color.RED,
                BorderStrokeStyle.SOLID,
                new CornerRadii(3.0, 3.0, 2.0, 1.0, false),
                BorderWidths.DEFAULT
        ));

        if (searchComboBoxField.getSelectionModel().isEmpty()) {
            searchComboBoxField.setBorder(border);
        } else {
            searchComboBoxField.setBorder(null);
        }

        if (searchComboBoxComparator.getSelectionModel().isEmpty()) {
            searchComboBoxComparator.setBorder(border);
        } else {
            searchComboBoxComparator.setBorder(null);
        }

        if (searchTextField.getText().isEmpty()) {
            searchTextField.setBorder(border);
        } else {
            searchTextField.setBorder(null);
        }
    }
}
