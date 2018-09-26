package de.htwsaar.prog3.carrental;

import de.htwsaar.prog3.carrental.controller.*;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

/**
 * Entry point of the car rental application. Definition of primary stage and
 * building of the environment.
 *
 * @author Lukas Raubuch, Arthur Kelsch, Jens Thewes
 */
public class CarRentalApp extends Application {
    private static final Logger logger = LoggerFactory.getLogger(CarRentalApp.class);

    private Stage primaryStage;
    private BorderPane root;

    public static void main(String[] args) {
        // Use English locale by default
        Locale.setDefault(new Locale("en"));

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(I18nComponentsUtil.getStageTitle());

        initializeRoot();

        showCarTableView();
    }

    @Override
    public void stop() {
        EntityManagerUtil.closeEntityManagerFactory();
    }

    /**
     * Initializes the root BorderPane.
     */
    private void initializeRoot() {
        root = new BorderPane();
        root.setPrefSize(1440, 640);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        logger.info("Initialized root BorderPane");
    }

    /**
     * Switches the view to the CarTableView.
     */
    public void showCarTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getCarTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane carTableView = loader.load();
            logger.info("Displaying CarTableView");
            root.setCenter(carTableView);

            CarTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
        }
    }

    /**
     * Opens the CarEditView dialog.
     */
    public boolean showCarEditView(Car car) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getCarEditViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane carEditView = loader.load();
            CarEditViewController controller = loader.getController();

            // Create the modal stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(primaryStage);
            Scene scene = new Scene(carEditView);
            modalStage.setScene(scene);

            // Put the modal stage and car into the controller
            controller.setModalStage(modalStage);
            controller.initialize(car);

            // Show the dialog and wait until the user closes it
            modalStage.setResizable(false);
            logger.info("Displaying CarEditView");
            modalStage.showAndWait();
            logger.info("Closed CarEditView");

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }

    /**
     * Switches the view to the CustomerTableView.
     */
    public void showCustomerTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getCustomerTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane customerTableView = loader.load();
            logger.info("Displaying CustomerTableView");
            root.setCenter(customerTableView);

            CustomerTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
        }
    }

    /**
     * Opens the CustomerEditView dialog.
     */
    public boolean showCustomerEditView(Customer customer) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getCustomerEditViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane customerEditView = loader.load();
            CustomerEditViewController controller = loader.getController();

            // Create the modal stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(primaryStage);
            Scene scene = new Scene(customerEditView);
            modalStage.setScene(scene);

            // Put the modal stage and customer into the controller
            controller.setModalStage(modalStage);
            controller.initialize(customer);

            // Show the dialog and wait until the user closes it
            modalStage.setResizable(false);
            logger.info("Displaying CustomerEditView");
            modalStage.showAndWait();
            logger.info("Closed CustomerEditView");

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }

    /**
     * Switches the view to the EmployeeTableView.
     */
    public void showEmployeeTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getEmployeeTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane employeeTableView = loader.load();
            logger.info("Displaying EmployeeTableView");
            root.setCenter(employeeTableView);

            EmployeeTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
        }
    }

    /**
     * Opens the EmployeeEditView dialog.
     */
    public boolean showEmployeeEditView(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getEmployeeEditViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane employeeEditView = loader.load();
            EmployeeEditViewController controller = loader.getController();

            // Create the modal stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(primaryStage);
            Scene scene = new Scene(employeeEditView);
            modalStage.setScene(scene);

            // Put the modal stage and employee into the controller
            controller.setModalStage(modalStage);
            controller.initialize(employee);

            // Show the dialog and wait until the user closes it
            modalStage.setResizable(false);
            logger.info("Displaying EmployeeEditView");
            modalStage.showAndWait();
            logger.info("Closed EmployeeEditView");

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }

    /**
     * Switches the view to the RentalTableView.
     */
    public void showRentalTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getRentalTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane rentalTableView = loader.load();
            logger.info("Displaying RentalTableView");
            root.setCenter(rentalTableView);

            RentalTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
        }
    }

    /**
     * Opens the RentalEditView dialog.
     */
    public boolean showRentalEditView(Rental rental) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getRentalEditViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            Pane rentalEditView = loader.load();
            RentalEditViewController controller = loader.getController();

            // Create the modal stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(primaryStage);
            Scene scene = new Scene(rentalEditView);
            modalStage.setScene(scene);

            // Put the modal stage and rental into the controller
            controller.setModalStage(modalStage);
            controller.initialize(rental);

            // Show the dialog and wait until the user closes it
            modalStage.setResizable(false);
            logger.info("Displaying RentalEditView");
            modalStage.showAndWait();
            logger.info("Closed RentalEditView");

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }
}
