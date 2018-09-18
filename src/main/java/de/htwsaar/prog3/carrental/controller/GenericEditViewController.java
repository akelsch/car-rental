package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.BaseEntity;
import de.htwsaar.prog3.carrental.service.GenericService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * Super Controller for all other EditView controllers.
 *
 * @author Jens Thewes, Arthur Kelsch
 */
public abstract class GenericEditViewController<T extends BaseEntity> {
    // Backend service
    GenericService<T> service;

    // Entity
    T entity;

    // Stage
    @Setter
    Stage modalStage;

    @Getter
    boolean applyClicked = false;

    /**
     * Handle pressing the "Cancel" button.
     */
    public void handleCancelButtonClicked() {
        Alert confirmationDialog =
                DialogUtil.createConfirmationDialog(I18nComponentsUtil.getDialogCancelConfirmationText());

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.orElse(null) == ButtonType.OK) {
            modalStage.close();
        }
    }

    /**
     * Fills all the text fields with the given entity fields.
     *
     * @param entity given entity of type {@link T}
     */
    public abstract void initialize(T entity);

    /**
     * Handle pressing the "Apply" button.
     */
    public abstract void handleApplyButtonClicked();

    /**
     * Checks if all the input fields contain valid data.
     *
     * @return true if all inputs are valid, else false
     */
    abstract boolean isInputValid();
}
