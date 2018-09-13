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
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Entry point of the car rental application. Definition of primary stage and
 * building of the environment.
 *
 * @author Lukas Raubuch
 */
public class CarTableView extends Application {

	// Has to be static to enable modal dialogs
	@Getter
	private static Stage primaryStage;

	private static BorderPane rootPane;
	private static BorderPane carPane;
	private static BorderPane employeePane;
	private static BorderPane rentalPane;
	private static BorderPane customerPane;

	/**
	 * Entry Point for the main view of the program.
	 *
	 * @throws IOException
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		CarTableView.primaryStage = primaryStage;
		// Load FXML-document for the main view with the needed resource bundle

		initPanes();
		primaryStage.setTitle(I18nComponentsUtil.getStageTitle());
		// Apply styling described in the FXML-document
		rootPane.setCenter(carPane);
		primaryStage.setScene(new Scene(rootPane));
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	/**
	 * Load scenes and add them to the controllers.
	 *
	 * @throws IOException
	 */
	private void initPanes() throws IOException {
		// Root View
		FXMLLoader loaderRootPane = new FXMLLoader(getClass().getResource(I18nStringsUtil.getRootViewFxml()),
				I18nUtil.getResourceBundleComponents());
		rootPane = loaderRootPane.load();

		// Car View
		FXMLLoader loaderCarPane = new FXMLLoader(getClass().getResource(I18nStringsUtil.getCarTableViewFxml()),
				I18nUtil.getResourceBundleComponents());
		carPane = loaderCarPane.load();

		// Employee View
		FXMLLoader loaderEmployeePane = new FXMLLoader(
				getClass().getResource(I18nStringsUtil.getEmployeeTableViewFxml()),
				I18nUtil.getResourceBundleComponents());
		employeePane = loaderEmployeePane.load();

		// Rental View
		FXMLLoader loaderRentalPane = new FXMLLoader(getClass().getResource(I18nStringsUtil.getRentalTableViewFxml()),
				I18nUtil.getResourceBundleComponents());
		rentalPane = loaderRentalPane.load();

		// Customer View
		FXMLLoader loaderCustomerPane = new FXMLLoader(
				getClass().getResource(I18nStringsUtil.getCustomerTableViewFxml()),
				I18nUtil.getResourceBundleComponents());
		customerPane = loaderCustomerPane.load();

		addScenesToController(loaderCarPane);
		addScenesToController(loaderEmployeePane);
		addScenesToController(loaderRentalPane);
		addScenesToController(loaderCustomerPane);
	}

	private void addScenesToController(FXMLLoader loader) {
		GenericTableViewController<?> controller = loader.getController();
		controller.setRootPane(rootPane);
		controller.setCarPane(carPane);
		controller.setCustomerPane(customerPane);
		controller.setEmployeePane(employeePane);
		controller.setRentalPane(rentalPane);
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
	 * @param args
	 *            commandline arguments
	 */
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en"));
		launch(args);
	}
}
