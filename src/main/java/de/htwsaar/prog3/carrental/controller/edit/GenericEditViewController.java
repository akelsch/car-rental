package de.htwsaar.prog3.carrental.controller.edit;

import de.htwsaar.prog3.carrental.controller.BaseController;
import de.htwsaar.prog3.carrental.model.BaseEntity;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * Super controller for all other EditView controllers.
 *
 * @author Jens Thewes
 * @author Arthur Kelsch
 */
public abstract class GenericEditViewController<T extends BaseEntity> extends BaseController {

    T entity;

    @Setter
    private Stage modalStage;

    @Getter
    private boolean applyClicked = false;

    /**
     * Fills all the text fields with the given entity fields.
     *
     * @param entity given entity of type {@link T}
     */
    public abstract void initialize(T entity);

    /**
     * Handles key presses within a EditView.
     *
     * @param event the event that occurred also containing the button that was pressed
     */
    public void handleKeyEvent(KeyEvent event) {
        // TODO https://riptutorial.com/javafx/example/28054/default-and-cancel-buttons
        switch (event.getCode()) {
            case ESCAPE -> handleCancelButtonClicked();
            case ENTER -> handleApplyButtonClicked();
        }
    }

    /**
     * Handle pressing the "Cancel" button.
     */
    public void handleCancelButtonClicked() {
        dialogUtils.showCancelDialog().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
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
    abstract boolean isInputValid();

    void closeModalWithApply() {
        applyClicked = true;
        modalStage.close();
    }
}
