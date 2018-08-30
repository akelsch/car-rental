package de.htwsaar.prog3.carrental.gui;

import de.htwsaar.prog3.carrental.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.i18n.I18nUtil;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point of the car rental application. Definition of primary stage and
 * building of the environment.
 *
 * @author Lukas Raubuch
 */
public class CarTableView extends Application {

    private Stage primaryStage;

    @Override
    /**
     * Entry Point for the main view of the program
     */
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        // Load FXML-document for the main view with the needed resource bundle
        Parent scene = FXMLLoader.load(getClass().getResource(I18nStringsUtil.getCarTableViewURL()),
                I18nUtil.getResourceBundleComponents());
        primaryStage.setTitle(I18nComponentsUtil.getStageTitleString());
        // Apply styling described in the FXML-document
        primaryStage.setScene(new Scene(scene));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    @Override
    /**
     * This method is used to execute operations when the application is closed by
     * the user. It's primary purpose is to close the database connection
     */
    public void stop() {
        EntityManagerUtil.closeEntityManagerFactory();
    }

    /**
     * Main method of this Application.
     *
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    // GETTER & SETTER Utility
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
