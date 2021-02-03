package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.application.StageInitializer;
import de.htwsaar.prog3.carrental.model.Employee;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SuppressWarnings("unchecked")
class EmployeeTableViewControllerIT {

    private static final int NEW_BUTTON = 0;
    private static final int EDIT_BUTTON = 1;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private StageInitializer stageInitializer;

    private Application application;

    private static Employee knownEmployee;

    @BeforeAll
    static void beforeAll() {
        knownEmployee = Employee.builder()
                .firstName("Björn")
                .lastName("Scherer")
                .position("Manager")
                .build();
    }

    @BeforeEach
    void setUp() throws Exception {
        if (application == null) {
            // FxToolkit does not work with static @BeforeAll, hence @BeforeEach
            application = FxToolkit.setupApplication(() -> new TestUiApplication(applicationContext));
            WaitForAsyncUtils.asyncFx(() -> stageInitializer.switchToEmployeeTableView());
            WaitForAsyncUtils.waitForFxEvents();
        }
    }

    @AfterEach
    void tearDown() {
        // do not cleanup stages but reuse stage in the assigned test order
    }

    @Test
    @Order(1)
    void handleNewClicked(FxRobot robot) {
        BorderPane employeeTableView = (BorderPane) robot.window(0).getScene().getRoot();

        HBox bottom = (HBox) employeeTableView.getBottom();
        ObservableList<Node> buttons = bottom.getChildrenUnmodifiable();
        Button newButton = robot.from(buttons.get(NEW_BUTTON)).queryButton();
        robot.clickOn(newButton);

        TextField firstNameTextField = robot.lookup("#firstNameTextField").queryAs(TextField.class);
        TextField lastNameTextField = robot.lookup("#lastNameTextField").queryAs(TextField.class);
        TextField positionTextField = robot.lookup("#positionTextField").queryAs(TextField.class);
        robot.clickOn(firstNameTextField).write(knownEmployee.getFirstName());
        robot.clickOn(lastNameTextField).write(knownEmployee.getLastName());
        robot.clickOn(positionTextField).write(knownEmployee.getPosition());
        robot.press(KeyCode.ENTER);

        TableView<Employee> table = (TableView<Employee>) employeeTableView.getCenter();
        table.getSelectionModel().selectLast();
        Employee employee = table.getSelectionModel().getSelectedItem();
        assertNotNull(employee.getId());
        assertEquals(knownEmployee.getFirstName(), employee.getFirstName());
        assertEquals(knownEmployee.getLastName(), employee.getLastName());
        assertEquals(knownEmployee.getPosition(), employee.getPosition());
    }

    @Test
    @Order(2)
    void handleEditClicked(FxRobot robot) {
        BorderPane employeeTableView = (BorderPane) robot.window(0).getScene().getRoot();
        TableView<Employee> table = (TableView<Employee>) employeeTableView.getCenter();
        table.getSelectionModel().selectLast();

        HBox bottom = (HBox) employeeTableView.getBottom();
        ObservableList<Node> buttons = bottom.getChildrenUnmodifiable();
        Button editButton = robot.from(buttons.get(EDIT_BUTTON)).queryButton();
        robot.clickOn(editButton);

        TextField lastNameTextField = robot.lookup("#positionTextField").queryAs(TextField.class);
        robot.doubleClickOn(lastNameTextField).write("Customer Advisor");
        robot.press(KeyCode.ENTER);

        Employee employee = table.getSelectionModel().getSelectedItem();
        assertEquals("Customer Advisor", employee.getPosition());
    }

    @Test
    @Order(3)
    void handleDeleteClicked(FxRobot robot) {
        BorderPane employeeTableView = (BorderPane) robot.window(0).getScene().getRoot();
        TableView<Employee> table = (TableView<Employee>) employeeTableView.getCenter();
        table.getSelectionModel().selectLast();

        robot.press(KeyCode.DELETE);
        robot.clickOn(robot.lookup(".alert").queryAs(DialogPane.class).lookupButton(ButtonType.OK));

        table.getSelectionModel().selectLast();
        Employee employee = table.getSelectionModel().getSelectedItem();
        assertNotEquals(knownEmployee.getId(), employee.getId());
    }
}
