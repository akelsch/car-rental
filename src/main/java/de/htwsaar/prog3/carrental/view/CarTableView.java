package de.htwsaar.prog3.carrental.view;

import java.io.IOException;
import java.util.Locale;

import de.htwsaar.prog3.carrental.controller.GenericTableViewController;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
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

	// Has to be static to enable modal dialogs
	private static Stage primaryStage;
	private static Scene carScene;
	private static Scene employeeScene;
	private static Scene rentalScene;
	private static Scene customerScene;

	/**
	 * Entry Point for the main view of the program.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		setPrimaryStage(primaryStage);
		// Load FXML-document for the main view with the needed resource bundle

		initScenes();
		primaryStage.setTitle(I18nComponentsUtil.getStageTitle());
		// Apply styling described in the FXML-document
		primaryStage.setScene(carScene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	/**
	 * Load scenes and add them to the controllers.
	 *
	 * @throws IOException
	 */
	private void initScenes() throws IOException {
		// Car View
		FXMLLoader loaderCarScene = new FXMLLoader(getClass().getResource(I18nStringsUtil.getCarTableViewFxml()),
				I18nUtil.getResourceBundleComponents());
		Parent carParent = loaderCarScene.load();
		carScene = new Scene(carParent);

		// Employee View
		FXMLLoader loaderEmployeeScene = new FXMLLoader(
				getClass().getResource(I18nStringsUtil.getEmployeeTableViewFxml()),
				I18nUtil.getResourceBundleComponents());
		Parent employeeParent = loaderEmployeeScene.load();
		employeeScene = new Scene(employeeParent);

		// Rental View
		FXMLLoader loaderRentalScene = new FXMLLoader(getClass().getResource(I18nStringsUtil.getRentalTableViewFxml()),
				I18nUtil.getResourceBundleComponents());
		Parent rentalParent = loaderRentalScene.load();
		rentalScene = new Scene(rentalParent);

		// Customer View
		FXMLLoader loaderCustomerScene = new FXMLLoader(
				getClass().getResource(I18nStringsUtil.getCustomerTableViewFxml()),
				I18nUtil.getResourceBundleComponents());
		Parent customerParent = loaderCustomerScene.load();
		customerScene = new Scene(customerParent);

		addScenesToController(loaderCarScene);
		addScenesToController(loaderEmployeeScene);
		addScenesToController(loaderRentalScene);
		addScenesToController(loaderCustomerScene);
	}

	private void addScenesToController(FXMLLoader loader) {
		GenericTableViewController<?> controller = loader.getController();
		controller.setCarScene(carScene);
		controller.setCustomerScene(customerScene);
		controller.setEmployeeScene(employeeScene);
		controller.setRentalScene(rentalScene);
	}

	/**
	 * This method is used to execute operations when the application is closed by
	 * the user. It's primary purpose is to close the database connection
	 */
	@Override
	public void stop() {
		EntityManagerUtil.closeEntityManagerFactory();
	}

	/**
	 * Main method of this Application.
	 *
	 * @param args commandline arguments
	 */
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en"));
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	private void setPrimaryStage(Stage primaryStage) {
		CarTableView.primaryStage = primaryStage;
	}
}
