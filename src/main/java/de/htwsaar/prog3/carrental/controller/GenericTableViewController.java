package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.CarRentalApp;
import de.htwsaar.prog3.carrental.model.BaseEntity;
import de.htwsaar.prog3.carrental.service.GenericService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.Setter;

/**
 * Super Controller for all other TableView controllers.
 *
 * @author Lukas Raubuch, Arthur Kelsch
 */
public abstract class GenericTableViewController<T extends BaseEntity> {
    // Backend service
    GenericService<T> service;

    // Entity list
    ObservableList<T> entities;

    // JavaFX application
    @Setter
    CarRentalApp app;

    @FXML
    public ComboBox<String> searchComboBoxField;

    @FXML
    public ComboBox<String> searchComboBoxComparator;

    @FXML
    public TextField searchTextField;

    /**
     * Handles key presses within a TableView.
     *
     * @param event the event that occurred also containing the button that was pressed
     */
    public void handleKeyEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            handleDeleteButtonClicked();
        }
    }

    /**
     * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Car Car}.
     */
    public void handleCarMenuItemClicked() {
        app.showCarTableView();
    }

    /**
     * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Customer Customer}.
     */
    public void handleCustomerMenuItemClicked() {
        app.showCustomerTableView();
    }

    /**
     * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Employee Employee}.
     */
    public void handleEmployeeMenuItemClicked() {
        app.showEmployeeTableView();
    }

    /**
     * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Rental Rental}.
     */
    public void handleRentalMenuItemClicked() {
        app.showRentalTableView();
    }

    /**
     * Closes the application.
     *
     * @see CarRentalApp#stop()
     */
    public void handleCloseMenuItemClicked() {
        Platform.exit();
    }

    /**
     * Displays a dialog containing information about this software project.
     */
    public void handleAboutMenuItemClicked() {
        Alert aboutDialog = DialogUtil.createInformationDialog(I18nComponentsUtil.getDialogAboutText());
        aboutDialog.show();
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
            entities.setAll(service.filter(field, comparator, value));
        }
    }

    /**
     * Removes the selected filters of the current entity list.
     */
    public void handleRemoveCurrentFilterButtonClicked() {
        clearSearchComboBoxAndTextFieldBorders();
        clearSearchComboBoxAndTextFieldValues();

        entities.setAll(service.findAll());
    }

    /**
     * Handle pressing the "New..." button.
     */
    public abstract void handleNewButtonClicked();

    /**
     * Handle pressing the "Edit..." button.
     */
    public abstract void handleEditButtonClicked();

    /**
     * Handle pressing the "Delete..." button.
     */
    public abstract void handleDeleteButtonClicked();

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
