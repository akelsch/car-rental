package de.htwsaar.prog3.carrental.util;

import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.Stop;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class DialogUtilsIT {

    @Autowired
    private DialogUtils dialogUtils;

    @Autowired
    private MessageUtils messageUtils;

    @Start
    void start(Stage stage) {
        // we do not need to show the stage as we test dialog pop-ups only
    }

    @Stop
    void stop() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void showAboutDialog(FxRobot robot) {
        robot.interact(() -> dialogUtils.showAboutDialog());

        DialogPane dialogPane = robot.lookup(".alert").queryAs(DialogPane.class);
        assertTrue(dialogPane.getStyleClass().contains("information"));
        assertEquals(messageUtils.getMessage(MessageUtils.DIALOG_INFORMATION_ABOUT), dialogPane.getHeaderText());
    }

    @Test
    void showCancelDialog(FxRobot robot) {
        WaitForAsyncUtils.asyncFx(() -> dialogUtils.showCancelDialog());
        WaitForAsyncUtils.waitForFxEvents();

        DialogPane dialogPane = robot.lookup(".alert").queryAs(DialogPane.class);
        assertTrue(dialogPane.getStyleClass().contains("confirmation"));
        assertEquals(messageUtils.getMessage(MessageUtils.DIALOG_CONFIRMATION_CANCEL), dialogPane.getHeaderText());
    }

    @Test
    void showDeleteConfirmationDialog(FxRobot robot) {
        WaitForAsyncUtils.asyncFx(() -> dialogUtils.showDeleteConfirmationDialog());
        WaitForAsyncUtils.waitForFxEvents();

        DialogPane dialogPane = robot.lookup(".alert").queryAs(DialogPane.class);
        assertTrue(dialogPane.getStyleClass().contains("confirmation"));
        assertEquals(messageUtils.getMessage(MessageUtils.DIALOG_CONFIRMATION_DELETE), dialogPane.getHeaderText());
    }

    @Test
    void showDeleteErrorDialog(FxRobot robot) {
        robot.interact(() -> dialogUtils.showDeleteErrorDialog());

        DialogPane dialogPane = robot.lookup(".alert").queryAs(DialogPane.class);
        assertTrue(dialogPane.getStyleClass().contains("error"));
        assertEquals(messageUtils.getMessage(MessageUtils.DIALOG_ERROR_DELETE), dialogPane.getHeaderText());
        assertEquals(messageUtils.getMessage(MessageUtils.DIALOG_ERROR_DELETE_DETAILS), dialogPane.getContentText());
    }

    @Test
    void showValidationErrorDialog(FxRobot robot) {
        robot.interact(() -> dialogUtils.showValidationErrorDialog("some details"));

        DialogPane dialogPane = robot.lookup(".alert").queryAs(DialogPane.class);
        assertTrue(dialogPane.getStyleClass().contains("error"));
        assertEquals(messageUtils.getMessage(MessageUtils.DIALOG_ERROR_VALIDATION), dialogPane.getHeaderText());
        assertEquals("some details", dialogPane.getContentText());
    }
}
