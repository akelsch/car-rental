package de.htwsaar.prog3.carrental.gui;

import de.htwsaar.prog3.carrental.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.i18n.I18nUtil;
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
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Load FXML-document for the main view with the needed resource bundle 
		Parent scene = FXMLLoader.load(getClass().getResource(I18nStringsUtil.getCarTableViewURL()),
				I18nUtil.getResourceBundleComponents());
		primaryStage.setTitle(I18nComponentsUtil.getStageTitleString());
		// Apply styling described in the FXML-document
		primaryStage.setScene(new Scene(scene));
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	/**
	 * Main method of this Application.
	 *
	 * @param args
	 *            commandline arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
