package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.CarRentalApp;
import de.htwsaar.prog3.carrental.model.BaseEntity;
import de.htwsaar.prog3.carrental.service.GenericService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Setter;

/**
 * Super Controller for all other TableView controllers.
 *
 * @author Lukas Raubuch, Arthur Kelsch
 */
public abstract class GenericTableViewController<T extends BaseEntity> {
    // Primary stage
    Stage primaryStage = CarRentalApp.getPrimaryStage();

    // Service and list of entities
    GenericService<T> service;
    ObservableList<T> entities;

    // Scenes
    @Setter
    private Scene carScene;

    @Setter
    private Scene customerScene;

    @Setter
    private Scene employeeScene;

    @Setter
    private Scene rentalScene;

    // FXML
    @FXML
    public ComboBox<String> searchComboBoxField;

    @FXML
    public ComboBox<String> searchComboBoxComparator;

    @FXML
    public TextField searchTextField;

    /**
     * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Car Car}.
     */
    public void handleCarMenuItemClicked() {
        primaryStage.setScene(carScene);
    }

    /**
     * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Customer Customer}.
     */
    public void handleCustomerMenuItemClicked() {
        primaryStage.setScene(customerScene);
    }

    /**
     * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Employee Employee}.
     */
    public void handleEmployeeMenuItemClicked() {
        primaryStage.setScene(employeeScene);
    }

    /**
     * Switch the primary stage scene for {@link de.htwsaar.prog3.carrental.model.Rental Rental}.
     */
    public void handleRentalMenuItemClicked() {
        primaryStage.setScene(rentalScene);
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
