package de.htwsaar.prog3.carrental;

import de.htwsaar.prog3.carrental.controller.CarTableViewController;
import de.htwsaar.prog3.carrental.controller.CustomerTableViewController;
import de.htwsaar.prog3.carrental.controller.EmployeeTableViewController;
import de.htwsaar.prog3.carrental.controller.RentalTableViewController;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

/**
 * Entry point of the car rental application. Definition of primary stage and
 * building of the environment.
 *
 * @author Lukas Raubuch, Arthur Kelsch
 */
public class CarRentalApp extends Application {
    private static final Logger logger = LoggerFactory.getLogger(CarRentalApp.class);

    @Getter
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
     * Switches the view to the Car TableView.
     */
    public void showCarTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getCarTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane carTableView = loader.load();
            root.setCenter(carTableView);

            CarTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
        }

        logger.info("Displaying Car TableView");
    }

    /**
     * Switches the view to the Customer TableView.
     */
    public void showCustomerTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getCustomerTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane customerTableView = loader.load();
            root.setCenter(customerTableView);

            CustomerTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
        }

        logger.info("Displaying Customer TableView");
    }

    /**
     * Switches the view to the Employee TableView.
     */
    public void showEmployeeTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getEmployeeTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane employeeTableView = loader.load();
            root.setCenter(employeeTableView);

            EmployeeTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
        }

        logger.info("Displaying Employee TableView");
    }

    /**
     * Switches the view to the Rental TableView.
     */
    public void showRentalTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getRentalTableViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            BorderPane rentalTableView = loader.load();
            root.setCenter(rentalTableView);

            RentalTableViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
        }

        logger.info("Displaying Rental TableView");
    }
}
