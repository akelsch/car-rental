package de.htwsaar.prog3.carrental;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.FxmlUtils;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Entry point of the car rental application. Definition of primary stage and
 * building of the environment.
 *
 * @author Lukas Raubuch, Arthur Kelsch, Jens Thewes
 */
public class CarRentalUiApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private FxmlUtils fxmlUtils;
    private Stage primaryStage;

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer =
                context -> context.registerBean(Application.class, () -> CarRentalUiApplication.this);

        applicationContext = new SpringApplicationBuilder()
                .sources(CarRentalApplication.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));

        fxmlUtils = new FxmlUtils(applicationContext);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Parent root = fxmlUtils.loadView(I18nStringsUtil.getCarTableViewFxml());
        Scene scene = new Scene(root, 1440, 810);
        primaryStage.setScene(scene);
        primaryStage.setTitle(I18nComponentsUtil.getStageTitle());
        primaryStage.show();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    public void switchToCarTableView() {
        fxmlUtils.setView(primaryStage, I18nStringsUtil.getCarTableViewFxml());
    }

    public void switchToCustomerTableView() {
        fxmlUtils.setView(primaryStage, I18nStringsUtil.getCustomerTableViewFxml());
    }

    public void switchToEmployeeTableView() {
        fxmlUtils.setView(primaryStage, I18nStringsUtil.getEmployeeTableViewFxml());
    }

    public void switchToRentalTableView() {
        fxmlUtils.setView(primaryStage, I18nStringsUtil.getRentalTableViewFxml());
    }

    public boolean showCarEditView(Car car) {
        return fxmlUtils.showModalView(primaryStage, I18nStringsUtil.getCarEditViewFxml(), car);
    }

    public boolean showCustomerEditView(Customer customer) {
        return fxmlUtils.showModalView(primaryStage, I18nStringsUtil.getCustomerEditViewFxml(), customer);
    }

    public boolean showEmployeeEditView(Employee employee) {
        return fxmlUtils.showModalView(primaryStage, I18nStringsUtil.getEmployeeEditViewFxml(), employee);
    }

    public boolean showRentalEditView(Rental rental) {
        return fxmlUtils.showModalView(primaryStage, I18nStringsUtil.getRentalEditViewFxml(), rental);
    }

}
