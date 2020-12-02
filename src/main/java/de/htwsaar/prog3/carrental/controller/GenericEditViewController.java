package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.BaseEntity;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    T entity;

    @Setter
    Stage modalStage;

    @Getter
    boolean applyClicked = false;

    /**
     * Handles key presses within a EditView.
     *
     * @param event the event that occurred also containing the button that was pressed
     */
    public void handleKeyEvent(KeyEvent event) {
        KeyCode key = event.getCode();

        if (key == KeyCode.ESCAPE) {
            handleCancelButtonClicked();
        } else if (key == KeyCode.ENTER) {
            handleApplyButtonClicked();
        }
    }

    /**
     * Handle pressing the "Cancel" button.
     */
    public void handleCancelButtonClicked() {
        Alert confirmationDialog =
                DialogUtil.createConfirmationDialog(I18nUtils.getDialogCancelConfirmationText());

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
