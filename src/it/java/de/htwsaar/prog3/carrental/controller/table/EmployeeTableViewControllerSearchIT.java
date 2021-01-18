package de.htwsaar.prog3.carrental.controller.table;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.application.StageInitializer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.repository.EmployeeRepository;
import de.htwsaar.prog3.carrental.util.filter.Operator;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class EmployeeTableViewControllerSearchIT {

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
        stageInitializer.switchToEmployeeTableView();
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
        robot.interact(() -> searchAttributeComboBox.getSelectionModel().select(0)); // FxRobot has no select :(

        // Operator
        ComboBox<Operator> searchOperatorComboBox = robot.lookup("#searchOperatorComboBox").query();
        robot.interact(() -> searchOperatorComboBox.getSelectionModel().select(0));

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

}
