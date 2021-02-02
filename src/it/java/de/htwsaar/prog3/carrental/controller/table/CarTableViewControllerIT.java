package de.htwsaar.prog3.carrental.controller.table;

import de.htwsaar.prog3.carrental.TestUiApplication;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.model.car.*;
import de.htwsaar.prog3.carrental.repository.RentalRepository;
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

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SuppressWarnings("unchecked")
class CarTableViewControllerIT {

    private static final int COUPE_TYPE_COMBOBOX = 1;
    private static final int WHITE_COLOR_COMBOBOX = 8;
    private static final int MANUAL_TRANSMISSION_COMBOBOX = 0;
    private static final int PETROL_FUEL_COMBOBOX = 0;
    private static final int SUMMER_TIRES_COMBOBOX = 0;
    private static final int WINTER_TIRES_COMBOBOX = 1;
    private static final int TWO_YEAR_NEXT_INSPECTION_COMBOBOX = 14;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    private Application application;

    @BeforeEach
    void setUp() throws Exception {
        if (application == null) {
            // FxToolkit does not work with static @BeforeAll, hence @BeforeEach
            application = FxToolkit.setupApplication(() -> new TestUiApplication(applicationContext));
        }
    }

    @AfterEach
    void tearDown() {
        // do not cleanup stages but reuse stage in the assigned test order
    }

    @Test
    @Order(1)
    void handleNewClicked(FxRobot robot) {
        BorderPane carTableView = (BorderPane) robot.window(0).getScene().getRoot();

        HBox bottom = (HBox) carTableView.getBottom();
        ObservableList<Node> buttons = bottom.getChildrenUnmodifiable();
        Button newButton = robot.from(buttons.get(0)).queryButton();
        robot.clickOn(newButton);

        IntegerField yearIntegerField = robot.lookup("#yearIntegerField").queryAs(IntegerField.class);
        TextField brandTextField = robot.lookup("#brandTextField").queryAs(TextField.class);
        TextField modelTextField = robot.lookup("#modelTextField").queryAs(TextField.class);
        ComboBox<Type> typeComboBox = robot.lookup("#typeComboBox").queryAs(ComboBox.class);
        ComboBox<Color> colorComboBox = robot.lookup("#colorComboBox").queryAs(ComboBox.class);
        IntegerField doorsIntegerField = robot.lookup("#doorsIntegerField").queryAs(IntegerField.class);
        ComboBox<Transmission> transmissionComboBox = robot.lookup("#transmissionComboBox").queryAs(ComboBox.class);
        ComboBox<Fuel> fuelComboBox = robot.lookup("#fuelComboBox").queryAs(ComboBox.class);
        IntegerField horsepowerIntegerField = robot.lookup("#horsepowerIntegerField").queryAs(IntegerField.class);
        IntegerField mileageIntegerField = robot.lookup("#mileageIntegerField").queryAs(IntegerField.class);
        ComboBox<Tire> tiresComboBox = robot.lookup("#tiresComboBox").queryAs(ComboBox.class);
        ComboBox<YearMonth> nextInspectionComboBox = robot.lookup("#nextInspectionComboBox").queryAs(ComboBox.class);
        TextField defectsTextField = robot.lookup("#defectsTextField").queryAs(TextField.class);
        IntegerField dailyRateIntegerField = robot.lookup("#dailyRateIntegerField").queryAs(IntegerField.class);
        TextField parkingLotTextField = robot.lookup("#parkingLotTextField").queryAs(TextField.class);
        TextField licenseNumberTextField = robot.lookup("#licenseNumberTextField").queryAs(TextField.class);
        TextField vinTextField = robot.lookup("#vinTextField").queryAs(TextField.class);
        robot.clickOn(yearIntegerField).write("2015");
        robot.clickOn(brandTextField).write("BMW");
        robot.clickOn(modelTextField).write("M4");
        robot.interact(() -> typeComboBox.getSelectionModel().select(COUPE_TYPE_COMBOBOX));
        robot.interact(() -> colorComboBox.getSelectionModel().select(WHITE_COLOR_COMBOBOX));
        robot.clickOn(doorsIntegerField).write("3");
        robot.interact(() -> transmissionComboBox.getSelectionModel().select(MANUAL_TRANSMISSION_COMBOBOX));
        robot.interact(() -> fuelComboBox.getSelectionModel().select(PETROL_FUEL_COMBOBOX));
        robot.clickOn(horsepowerIntegerField).write("333");
        robot.clickOn(mileageIntegerField).write("25000");
        robot.interact(() -> tiresComboBox.getSelectionModel().select(SUMMER_TIRES_COMBOBOX));
        robot.interact(() -> nextInspectionComboBox.getSelectionModel().select(TWO_YEAR_NEXT_INSPECTION_COMBOBOX));
        robot.clickOn(defectsTextField).write("DAMAGE");
        robot.clickOn(dailyRateIntegerField).write("255");
        robot.clickOn(parkingLotTextField).write("A23H");
        robot.clickOn(licenseNumberTextField).write("SB XY 123");
        robot.clickOn(vinTextField).write("1234123412341234X");
        robot.press(KeyCode.ENTER);

        TableView<Car> table = (TableView<Car>) carTableView.getCenter();
        table.getSelectionModel().selectLast();
        Car car = table.getSelectionModel().getSelectedItem();
        assertNotNull(car.getId());
        assertEquals("SB XY 123", car.getLicenseNumber());
    }

    @Test
    @Order(2)
    void handleEditClicked(FxRobot robot) {
        BorderPane carTableView = (BorderPane) robot.window(0).getScene().getRoot();
        TableView<Car> table = (TableView<Car>) carTableView.getCenter();
        table.getSelectionModel().selectLast();

        HBox bottom = (HBox) carTableView.getBottom();
        ObservableList<Node> buttons = bottom.getChildrenUnmodifiable();
        Button editButton = robot.from(buttons.get(1)).queryButton();
        robot.clickOn(editButton);

        ComboBox<Tire> tiresComboBox = robot.lookup("#tiresComboBox").queryAs(ComboBox.class);
        robot.interact(() -> tiresComboBox.getSelectionModel().select(WINTER_TIRES_COMBOBOX));
        robot.press(KeyCode.ENTER);

        Car car = table.getSelectionModel().getSelectedItem();
        assertEquals(Tire.WINTER, car.getTires());
    }

    @Test
    @Order(3)
    void handleDeleteClicked(FxRobot robot) {
        BorderPane carTableView = (BorderPane) robot.window(0).getScene().getRoot();
        TableView<Car> table = (TableView<Car>) carTableView.getCenter();
        table.getSelectionModel().selectLast();

        robot.press(KeyCode.DELETE);
        robot.clickOn(robot.lookup(".alert").queryAs(DialogPane.class).lookupButton(ButtonType.OK));

        table.getSelectionModel().selectLast();
        Car car = table.getSelectionModel().getSelectedItem();
        assertNotEquals("SB XY 123", car.getLicenseNumber());
    }

    @Test
    void handleRentClicked(FxRobot robot) {
        BorderPane carTableView = (BorderPane) robot.window(0).getScene().getRoot();
        TableView<Car> table = (TableView<Car>) carTableView.getCenter();
        table.getSelectionModel().selectFirst();

        HBox bottom = (HBox) carTableView.getBottom();
        ObservableList<Node> buttons = bottom.getChildrenUnmodifiable();
        Button rentButton = robot.from(buttons.get(3)).queryButton();
        robot.clickOn(rentButton);

        ComboBox<Customer> customerComboBox = robot.lookup("#customerComboBox").queryAs(ComboBox.class);
        ComboBox<Employee> employeeComboBox = robot.lookup("#employeeComboBox").queryAs(ComboBox.class);
        DatePicker endDatePicker = robot.lookup("#endDatePicker").queryAs(DatePicker.class);
        TextArea noteTextArea = robot.lookup("#noteTextArea").queryAs(TextArea.class);
        TextField sumTextField = robot.lookup("#sumTextField").queryAs(TextField.class);
        robot.interact(() -> customerComboBox.getSelectionModel().selectFirst());
        robot.interact(() -> employeeComboBox.getSelectionModel().selectFirst());
        robot.interact(() -> endDatePicker.setValue(LocalDate.now().plusDays(3)));
        robot.clickOn(noteTextArea).write("TestFX bad");
        robot.clickOn(sumTextField).press(KeyCode.ENTER); // de-select text area first as it captures ENTER key

        Car car = table.getSelectionModel().getSelectedItem();
        List<Rental> rentals = rentalRepository.findAllByCarId(car.getId());
        assertEquals(1, rentals.size());
        assertEquals(car, rentals.get(0).getCar());
    }
}
