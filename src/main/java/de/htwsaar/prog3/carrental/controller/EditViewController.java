package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.BaseEntity;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Super controller for all other EditView controllers.
 *
 * @author Jens Thewes
 * @author Arthur Kelsch
 */
public abstract class EditViewController<T extends BaseEntity> extends BaseController {

    public T entity;

    @Setter
    private Stage modalStage;

    @Getter
    private boolean applyClicked;

    public void initialize(T entity) {
        this.entity = entity;
        postInitialize();
    }

    public abstract void postInitialize();

    /**
     * Handles key presses within a EditView.
     *
     * @param event the event that occurred also containing the button that was pressed
     */
    public void handleKeyEvent(KeyEvent event) {
        switch (event.getCode()) {
            case ESCAPE -> handleCancelButtonClicked();
            case ENTER -> handleApplyButtonClicked();
        }
    }

    /**
     * Handle pressing the "Cancel" button.
     */
    public void handleCancelButtonClicked() {
        getDialogUtils().showCancelDialog().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                applyClicked = false;
                modalStage.close();
            }
        });
    }

    /**
     * Handle pressing the "Apply" button.
     */
    public abstract void handleApplyButtonClicked();

    /**
     * Checks if all the input fields contain valid data.
     *
     * @return true if all inputs are valid, else false
     */
    public boolean isInputValid(T entity) {
        Set<ConstraintViolation<T>> violations = getValidator().validate(entity);

        if (violations.isEmpty()) {
            return true;
        }

        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<T> violation : violations) {
            sb.append(violation.getMessage());
            sb.append(System.lineSeparator());
        }

        getDialogUtils().showValidationErrorDialog(sb.toString());

        return false;
    }

    public void closeModalWithApply() {
        applyClicked = true;
        modalStage.close();
    }
}
