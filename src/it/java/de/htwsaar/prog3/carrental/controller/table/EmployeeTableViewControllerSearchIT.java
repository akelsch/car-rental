package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.application.StageInitializer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import de.htwsaar.prog3.carrental.util.filter.Operator;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@ExtendWith(ApplicationExtension.class)
class EmployeeTableViewControllerSearchIT {

    private static final int EQUAL_OPERATOR_COMBOBOX = 0;
    private static final int NOT_EQUAL_OPERATOR_COMBOBOX = 1;
    private static final int CONTAINS_OPERATOR_COMBOBOX = 2;
    private static final int GREATER_OPERATOR_COMBOBOX = 3;
    private static final int LESS_OPERATOR_COMBOBOX = 4;

    private static final int ID_ATTRIBUTE_COMBOBOX = 0;
    private static final int FIRST_NAME_ATTRIBUTE_COMBOBOX = 1;
    private static final int LAST_NAME_ATTRIBUTE_COMBOBOX = 2;
    private static final int POSITION_ATTRIBUTE_COMBOBOX = 3;

    private static Employee knownEmployee;

    @Autowired
    private StageInitializer stageInitializer;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @BeforeAll
    static void beforeAll(@Autowired EmployeeRepository employeeRepository) {
        knownEmployee = employeeRepository.save(Employee.builder()
                .firstName("Björn")
                .lastName("Scherer")
                .position("Manager")
                .build());
    }

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.setupApplication(() -> new TestUiApplication(applicationContext));
        WaitForAsyncUtils.asyncFx(() -> stageInitializer.switchToEmployeeTableView());
        WaitForAsyncUtils.waitForFxEvents();
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void testIdEqual(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownEmployee.getId().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertEquals(1, table.getItems().size());
        assertTrue(table.getItems().contains(knownEmployee));
    }

    @Test
    void testFirstNameEqual(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(FIRST_NAME_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownEmployee.getFirstName());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownEmployee));
    }

    @Test
    void testLastNameEqual(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(LAST_NAME_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownEmployee.getLastName());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownEmployee));
    }

    @Test
    void testPositionEqual(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(POSITION_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(EQUAL_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownEmployee.getPosition());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownEmployee));
    }

    @Test
    void testIdNotEqual(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownEmployee.getId().toString());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownEmployee));
    }

    @Test
    void testFirstNameNotEqual(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(FIRST_NAME_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownEmployee.getFirstName());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownEmployee));
    }

    @Test
    void testLastNameNotEqual(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(LAST_NAME_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownEmployee.getLastName());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownEmployee));
    }

    @Test
    void testPositionNotEqual(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(POSITION_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(NOT_EQUAL_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write(knownEmployee.getPosition());

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownEmployee));
    }

    @Test
    void testFirstNameContains(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(FIRST_NAME_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write("jör");

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownEmployee));
    }

    @Test
    void testLastNameContains(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(LAST_NAME_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write("chere");

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownEmployee));
    }

    @Test
    void testPositionContains(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(POSITION_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(CONTAINS_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write("nager");

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownEmployee));
    }

    @Test
    void testIdGreater(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(GREATER_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write("100");

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertTrue(table.getItems().contains(knownEmployee));
    }

    @Test
    void testIdLess(FxRobot robot) {
        TableView<Employee> table = robot.lookup("#entityTable").query();
        int beforeSize = table.getItems().size();

        // Attribute
        ComboBox<String> searchAttributeComboBox = robot.lookup("#searchAttributeComboBox").query();
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(ID_ATTRIBUTE_COMBOBOX));

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(LESS_OPERATOR_COMBOBOX));

        // Search string
        TextField searchValueTextField = robot.lookup("#searchValueTextField").query();
        robot.clickOn(searchValueTextField);
        robot.write("100");

        // Search via button
        Button searchButton = robot.from(searchAttributeComboBox.getParent().getChildrenUnmodifiable()).nth(3).queryButton();
        robot.clickOn(searchButton);

        assertTrue(table.getItems().size() < beforeSize);
        assertFalse(table.getItems().contains(knownEmployee));
    }
}
