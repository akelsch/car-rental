package de.htwsaar.prog3.carrental.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Utility class used to create JavaFX dialogs.
 *
 * @author Lukas Raubuch
 * @author Julian Quint
 */
@Component
@RequiredArgsConstructor
public class DialogUtils {

    private final MessageUtils messageUtils;

    private static Alert createInformationDialog(String headerText) {
        Alert informationDialog = new Alert(AlertType.INFORMATION);
        informationDialog.setHeaderText(headerText);
        return informationDialog;
    }

    private static Alert createConfirmationDialog(String headerText) {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog.setHeaderText(headerText);
        return confirmationDialog;
    }

    private static Alert createErrorDialog(String headerText, String details) {
        Alert errorDialog = new Alert(AlertType.ERROR);
        errorDialog.setHeaderText(headerText);
        errorDialog.setContentText(details);
        return errorDialog;
    }

    public void showAboutDialog() {
        String message = messageUtils.getMessage(MessageUtils.DIALOG_INFORMATION_ABOUT);
        Alert informationDialog = DialogUtils.createInformationDialog(message);
        informationDialog.show();
    }

    public Optional<ButtonType> showCancelDialog() {
        String message = messageUtils.getMessage(MessageUtils.DIALOG_CONFIRMATION_CANCEL);
        Alert confirmationDialog = DialogUtils.createConfirmationDialog(message);
        return confirmationDialog.showAndWait();
    }

    public Optional<ButtonType> showDeleteConfirmationDialog() {
        String message = messageUtils.getMessage(MessageUtils.DIALOG_CONFIRMATION_DELETE);
        Alert confirmationDialog = DialogUtils.createConfirmationDialog(message);
        return confirmationDialog.showAndWait();
    }

    public void showDeleteErrorDialog() {
        String message = messageUtils.getMessage(MessageUtils.DIALOG_ERROR_DELETE);
        String details = messageUtils.getMessage(MessageUtils.DIALOG_ERROR_DELETE_DETAILS);
        Alert errorDialog = DialogUtils.createErrorDialog(message, details);
        errorDialog.show();
    }

    public void showDeleteRentalErrorDialog() {
        String message = messageUtils.getMessage(MessageUtils.DIALOG_ERROR_DELETE);
        String details = messageUtils.getMessage(MessageUtils.DIALOG_ERROR_DELETE_RENTAL);
        Alert errorDialog = DialogUtils.createErrorDialog(message, details);
        errorDialog.show();
    }

    public void showValidationErrorDialog(String details) {
        String message = messageUtils.getMessage(MessageUtils.DIALOG_ERROR_VALIDATION);
        Alert errorDialog = DialogUtils.createErrorDialog(message, details);
        errorDialog.show();
    }
}
