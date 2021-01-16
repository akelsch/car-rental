package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.application.StageInitializer;
import de.htwsaar.prog3.carrental.model.Rental;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class RentalTableViewControllerIT {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private StageInitializer stageInitializer;

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.setupApplication(() -> new TestUiApplication(applicationContext));
        stageInitializer.switchToRentalTableView();
        WaitForAsyncUtils.waitForFxEvents();
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void testRegularDelete(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        TableRow<Rental> row = robot.lookup(".table-row-cell").nth(0).query();
        robot.clickOn(row);
        robot.press(KeyCode.DELETE);
        robot.clickOn(robot.lookup(".alert").queryAs(DialogPane.class).lookupButton(ButtonType.OK));

        int afterSize = table.getItems().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    void testActiveDelete(FxRobot robot) {
        TableView<Rental> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        Rental firstRental = table.getItems().get(0);
        firstRental.setStart(LocalDate.now()); // will not update UI

        TableRow<Rental> row = robot.lookup(".table-row-cell").nth(0).query();
        robot.clickOn(row);
        robot.press(KeyCode.DELETE);

        robot.clickOn(robot.lookup(".alert").queryAs(DialogPane.class).lookupButton(ButtonType.OK));

        int afterSize = table.getItems().size();
        assertEquals(beforeSize, afterSize);
    }
}
