package de.htwsaar.prog3.carrental.application;

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class StageInitializerIT {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private StageInitializer stageInitializer;

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.setupApplication(() -> new TestUiApplication(applicationContext));
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void switchToCarTableView(FxRobot robot) throws Exception {
        robot.interact(() -> stageInitializer.switchToCarTableView());
        Stage stage = FxToolkit.registerPrimaryStage();

        BorderPane carTableView = (BorderPane) stage.getScene().getRoot();
        TableView<?> table = (TableView<?>) carTableView.getCenter();
        assertTrue(table.getItems().get(0) instanceof Car);
    }

    @Test
    void switchToCustomerTableView(FxRobot robot) throws Exception {
        robot.interact(() -> stageInitializer.switchToCustomerTableView());
        Stage stage = FxToolkit.registerPrimaryStage();

        BorderPane customerTableView = (BorderPane) stage.getScene().getRoot();
        TableView<?> table = (TableView<?>) customerTableView.getCenter();
        assertTrue(table.getItems().get(0) instanceof Customer);
    }

    @Test
    void switchToEmployeeTableView(FxRobot robot) throws Exception {
        robot.interact(() -> stageInitializer.switchToEmployeeTableView());
        Stage stage = FxToolkit.registerPrimaryStage();

        BorderPane employeeTableView = (BorderPane) stage.getScene().getRoot();
        TableView<?> table = (TableView<?>) employeeTableView.getCenter();
        assertTrue(table.getItems().get(0) instanceof Employee);
    }

    @Test
    void switchToRentalTableView(FxRobot robot) throws Exception {
        robot.interact(() -> stageInitializer.switchToRentalTableView());
        Stage stage = FxToolkit.registerPrimaryStage();

        BorderPane rentalTableView = (BorderPane) stage.getScene().getRoot();
        TableView<?> table = (TableView<?>) rentalTableView.getCenter();
        assertTrue(table.getItems().get(0) instanceof Rental);
    }

    @Test
    void showCarEditView(FxRobot robot) {
        WaitForAsyncUtils.asyncFx(() -> stageInitializer.showCarEditView(new Car()));
        WaitForAsyncUtils.waitForFxEvents();

        Node carEditViewField = robot.lookup("#brandTextField").query();
        assertNotNull(carEditViewField);
    }

    @Test
    void showCustomerEditView(FxRobot robot) {
        WaitForAsyncUtils.asyncFx(() -> stageInitializer.showCustomerEditView(new Customer()));
        WaitForAsyncUtils.waitForFxEvents();

        Node customerEditViewField = robot.lookup("#phoneTextField").query();
        assertNotNull(customerEditViewField);
    }

    @Test
    void showEmployeeEditView(FxRobot robot) {
        WaitForAsyncUtils.asyncFx(() -> stageInitializer.showEmployeeEditView(new Employee()));
        WaitForAsyncUtils.waitForFxEvents();

        Node employeeEditViewField = robot.lookup("#positionTextField").query();
        assertNotNull(employeeEditViewField);
    }

    @Test
    void showRentalEditView(FxRobot robot) {
        WaitForAsyncUtils.asyncFx(() -> stageInitializer.showRentalEditView(new Rental()));
        WaitForAsyncUtils.waitForFxEvents();

        Node rentalEditViewField = robot.lookup("#startDatePicker").query();
        assertNotNull(rentalEditViewField);
    }
}
