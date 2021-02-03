package de.htwsaar.prog3.carrental.util;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class FxmlUtilsIT {

    @Autowired
    private FxmlUtils fxmlUtils;

    @Autowired
    private MessageUtils messageUtils;

    @Test
    void setView(FxRobot robot) throws Exception {
        Stage stage = FxToolkit.setupStage(s -> s.setScene(new Scene(new StackPane())));
        FxToolkit.showStage();

        robot.interact(() -> fxmlUtils.setView(stage, FxmlUtils.FXML_CUSTOMER_TABLE));

        BorderPane customerTableView = (BorderPane) stage.getScene().getRoot();
        TableView<?> table = (TableView<?>) customerTableView.getCenter();
        assertTrue(table.getItems().get(0) instanceof Customer);
    }

    @Test
    void loadView() {
        BorderPane carEditView = (BorderPane) fxmlUtils.loadView(FxmlUtils.FXML_CAR_EDIT);
        Label label = (Label) carEditView.getTop();

        assertEquals(messageUtils.getMessage("car.label.edit-title"), label.getText());
    }

    @Test
    void loadViewWithException() {
        assertThrows(IOException.class, () -> fxmlUtils.loadView("some-non-existing-fxml-file"));
    }

    @Test
    void showModalView(FxRobot robot) throws Exception {
        Future<Boolean> isApplyClicked = WaitForAsyncUtils.asyncFx(() ->
                fxmlUtils.showModalView(new Stage(), FxmlUtils.FXML_EMPLOYEE_EDIT, new Employee()));
        WaitForAsyncUtils.waitForFxEvents();

        BorderPane employeeEditView = robot.fromAll().queryAs(BorderPane.class);
        Stage stage = (Stage) employeeEditView.getScene().getWindow();
        Label label = (Label) employeeEditView.getTop();

        assertEquals(Modality.WINDOW_MODAL, stage.getModality());
        assertFalse(stage.isResizable());
        assertEquals(messageUtils.getMessage("employee.label.edit-title"), label.getText());

        robot.press(KeyCode.ESCAPE);
        robot.clickOn(robot.lookup(".alert").queryAs(DialogPane.class).lookupButton(ButtonType.OK));

        assertFalse(isApplyClicked.get());
    }

    @Test
    void showModalViewWithException() throws Exception {
        Stage stage = FxToolkit.registerPrimaryStage();
        assertThrows(IOException.class, () -> fxmlUtils.showModalView(stage, "some-non-existing-fxml-file", new Car()));
    }
}
