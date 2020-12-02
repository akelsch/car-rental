package de.htwsaar.prog3.carrental;

import de.htwsaar.prog3.carrental.application.StageReadyEvent;
import de.htwsaar.prog3.carrental.controller.*;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * Entry point of the car rental application. Definition of primary stage and
 * building of the environment.
 *
 * @author Lukas Raubuch, Arthur Kelsch, Jens Thewes
 */
@Slf4j
public class CarRentalUiApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    private Stage primaryStage;
    private BorderPane root;

    @Override
    public void init() {
        applicationContext = SpringApplication.run(CarRentalApplication.class);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(I18nComponentsUtil.getStageTitle());

        initializeRoot();
        showCarTableView();

        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
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

        log.info("Initialized root BorderPane");
    }

    /**
     * Switches the view to the CarTableView.
     */
    public void showCarTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
            loader.setLocation(getClass().getResource(I18nStringsUtil.getCarTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane carTableView = loader.load();
            log.info("Displaying CarTableView");
            root.setCenter(carTableView);

            CarTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            log.error("Failed loading FXML!", e);
        }
    }

    /**
     * Opens the CarEditView dialog.
     *
     * @param car car that we want to edit
     * @return true if the changes were applied successfully, else false
     */
    public boolean showCarEditView(Car car) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
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
            log.info("Displaying CarEditView");
            modalStage.showAndWait();
            log.info("Closed CarEditView");

            return controller.isApplyClicked();
        } catch (IOException e) {
            log.error("Failed loading FXML!", e);
            return false;
        }
    }

    /**
     * Switches the view to the CustomerTableView.
     */
    public void showCustomerTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
            loader.setLocation(getClass().getResource(I18nStringsUtil.getCustomerTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane customerTableView = loader.load();
            log.info("Displaying CustomerTableView");
            root.setCenter(customerTableView);

            CustomerTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            log.error("Failed loading FXML!", e);
        }
    }

    /**
     * Opens the CustomerEditView dialog.
     *
     * @param customer customer that we want to edit
     * @return true if the changes were applied successfully, else false
     */
    public boolean showCustomerEditView(Customer customer) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
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
            log.info("Displaying CustomerEditView");
            modalStage.showAndWait();
            log.info("Closed CustomerEditView");

            return controller.isApplyClicked();
        } catch (IOException e) {
            log.error("Failed loading FXML!", e);
            return false;
        }
    }

    /**
     * Switches the view to the EmployeeTableView.
     */
    public void showEmployeeTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
            loader.setLocation(getClass().getResource(I18nStringsUtil.getEmployeeTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane employeeTableView = loader.load();
            log.info("Displaying EmployeeTableView");
            root.setCenter(employeeTableView);

            EmployeeTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            log.error("Failed loading FXML!", e);
        }
    }

    /**
     * Opens the EmployeeEditView dialog.
     *
     * @param employee employee that we want to edit
     * @return true if the changes were applied successfully, else false
     */
    public boolean showEmployeeEditView(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
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
            log.info("Displaying EmployeeEditView");
            modalStage.showAndWait();
            log.info("Closed EmployeeEditView");

            return controller.isApplyClicked();
        } catch (IOException e) {
            log.error("Failed loading FXML!", e);
            return false;
        }
    }

    /**
     * Switches the view to the RentalTableView.
     */
    public void showRentalTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
            loader.setLocation(getClass().getResource(I18nStringsUtil.getRentalTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane rentalTableView = loader.load();
            log.info("Displaying RentalTableView");
            root.setCenter(rentalTableView);

            RentalTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            log.error("Failed loading FXML!", e);
        }
    }

    /**
     * Opens the RentalEditView dialog.
     *
     * @param rental rental that we want to edit
     * @return true if the changes were applied successfully, else false
     */
    public boolean showRentalEditView(Rental rental) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
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
            log.info("Displaying RentalEditView");
            modalStage.showAndWait();
            log.info("Closed RentalEditView");

            return controller.isApplyClicked();
        } catch (IOException e) {
            log.error("Failed loading FXML!", e);
            return false;
        }
    }

}
