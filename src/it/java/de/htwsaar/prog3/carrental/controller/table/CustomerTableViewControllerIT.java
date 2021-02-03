package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.application.StageInitializer;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.util.fx.IntegerField;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SuppressWarnings("unchecked")
class CustomerTableViewControllerIT {

    private static final int NEW_BUTTON = 0;
    private static final int EDIT_BUTTON = 1;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private StageInitializer stageInitializer;

    private Application application;

    private static Customer knownCustomer;

    @BeforeAll
    static void beforeAll() {
        knownCustomer = Customer.builder()
                .firstName("Rainer")
                .lastName("Korn")
                .dateOfBirth(LocalDate.of(1990, 5, 7))
                .email("rainer.zufall@mail.de")
                .phone("+49420694242")
                .idNumber("12EAS6789")
                .driverLicenseNumber("1O9876SAE21")
                .zipcode(52431)
                .city("Zufallsberg")
                .street("Kornstrasse")
                .build();
    }

    @BeforeEach
    void setUp() throws Exception {
        if (application == null) {
            // FxToolkit does not work with static @BeforeAll, hence @BeforeEach
            application = FxToolkit.setupApplication(() -> new TestUiApplication(applicationContext));
            WaitForAsyncUtils.asyncFx(() -> stageInitializer.switchToCustomerTableView());
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
        BorderPane customerTableView = (BorderPane) robot.window(0).getScene().getRoot();

        HBox bottom = (HBox) customerTableView.getBottom();
        ObservableList<Node> buttons = bottom.getChildrenUnmodifiable();
        Button newButton = robot.from(buttons.get(NEW_BUTTON)).queryButton();
        robot.clickOn(newButton);

        TextField firstNameTextField = robot.lookup("#firstNameTextField").queryAs(TextField.class);
        TextField lastNameTextField = robot.lookup("#lastNameTextField").queryAs(TextField.class);
        TextField streetTextField = robot.lookup("#streetTextField").queryAs(TextField.class);
        IntegerField zipcodeIntegerField = robot.lookup("#zipcodeIntegerField").queryAs(IntegerField.class);
        TextField cityTextField = robot.lookup("#cityTextField").queryAs(TextField.class);
        TextField phoneTextField = robot.lookup("#phoneTextField").queryAs(TextField.class);
        TextField emailTextField = robot.lookup("#emailTextField").queryAs(TextField.class);
        DatePicker dateOfBirthDatePicker = robot.lookup("#dateOfBirthDatePicker").queryAs(DatePicker.class);
        TextField idNumberTextField = robot.lookup("#idNumberTextField").queryAs(TextField.class);
        TextField driverLicenseNumberTextField = robot.lookup("#driverLicenseNumberTextField").queryAs(TextField.class);

        robot.clickOn(firstNameTextField).write(knownCustomer.getFirstName());
        robot.clickOn(lastNameTextField).write(knownCustomer.getLastName());
        robot.clickOn(streetTextField).write(knownCustomer.getStreet());
        robot.clickOn(zipcodeIntegerField).write(String.valueOf(knownCustomer.getZipcode()));
        robot.clickOn(cityTextField).write(knownCustomer.getCity());
        robot.clickOn(phoneTextField).write(knownCustomer.getPhone());
        robot.clickOn(emailTextField).write(knownCustomer.getEmail());
        robot.interact(() -> dateOfBirthDatePicker.setValue(knownCustomer.getDateOfBirth()));
        robot.clickOn(idNumberTextField).write(knownCustomer.getIdNumber());
        robot.clickOn(driverLicenseNumberTextField).write(knownCustomer.getDriverLicenseNumber());
        robot.press(KeyCode.ENTER);

        TableView<Customer> table = (TableView<Customer>) customerTableView.getCenter();
        table.getSelectionModel().selectLast();
        Customer customer = table.getSelectionModel().getSelectedItem();
        assertNotNull(customer.getId());
        assertEquals(knownCustomer.getFirstName(), customer.getFirstName());
        assertEquals(knownCustomer.getLastName(), customer.getLastName());
        assertEquals(knownCustomer.getZipcode(), customer.getZipcode());
        assertEquals(knownCustomer.getCity(), customer.getCity());
        assertEquals(knownCustomer.getPhone(), customer.getPhone());
        assertEquals(knownCustomer.getEmail(), customer.getEmail());
        assertEquals(knownCustomer.getDateOfBirth(), customer.getDateOfBirth());
        assertEquals(knownCustomer.getIdNumber(), customer.getIdNumber());
        assertEquals(knownCustomer.getDriverLicenseNumber(), customer.getDriverLicenseNumber());
    }

    @Test
    @Order(2)
    void handleEditClicked(FxRobot robot) {
        BorderPane customerTableView = (BorderPane) robot.window(0).getScene().getRoot();
        TableView<Customer> table = (TableView<Customer>) customerTableView.getCenter();
        table.getSelectionModel().selectLast();

        HBox bottom = (HBox) customerTableView.getBottom();
        ObservableList<Node> buttons = bottom.getChildrenUnmodifiable();
        Button editButton = robot.from(buttons.get(EDIT_BUTTON)).queryButton();
        robot.clickOn(editButton);

        TextField streetTextField = robot.lookup("#streetTextField").queryAs(TextField.class);
        robot.doubleClickOn(streetTextField).write("Kornweg");
        robot.press(KeyCode.ENTER);

        Customer customer = table.getSelectionModel().getSelectedItem();
        assertEquals("Kornweg", customer.getStreet());
    }

    @Test
    @Order(3)
    void handleDeleteClicked(FxRobot robot) {
        BorderPane employeeTableView = (BorderPane) robot.window(0).getScene().getRoot();
        TableView<Customer> table = (TableView<Customer>) employeeTableView.getCenter();
        table.getSelectionModel().selectLast();

        robot.press(KeyCode.DELETE);
        robot.clickOn(robot.lookup(".alert").queryAs(DialogPane.class).lookupButton(ButtonType.OK));

        table.getSelectionModel().selectLast();
        Customer customer = table.getSelectionModel().getSelectedItem();
        assertNotEquals(knownCustomer.getId(), customer.getId());
    }
}
