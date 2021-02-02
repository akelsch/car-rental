package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.application.StageInitializer;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.fx.IntegerField;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
        WaitForAsyncUtils.asyncFx(() -> stageInitializer.switchToRentalTableView());
        WaitForAsyncUtils.waitForFxEvents();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void handleEditClicked(FxRobot robot) {
        BorderPane rentalTableView = (BorderPane) robot.window(0).getScene().getRoot();
        TableView<Rental> table = (TableView<Rental>) rentalTableView.getCenter();
        table.getSelectionModel().selectLast();

        HBox bottom = (HBox) rentalTableView.getBottom();
        ObservableList<Node> buttons = bottom.getChildrenUnmodifiable();
        Button editButton = robot.from(buttons.get(0)).queryButton();
        robot.clickOn(editButton);

        IntegerField extraCostsIntegerField = robot.lookup("#extraCostsIntegerField").queryAs(IntegerField.class);
        robot.doubleClickOn(extraCostsIntegerField).write("1337");
        robot.press(KeyCode.ENTER);

        Rental rental = table.getSelectionModel().getSelectedItem();
        assertEquals(1337, rental.getExtraCosts());
    }

    @Test
    @Order(2)
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
    @Order(3)
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
