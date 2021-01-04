package de.htwsaar.prog3.carrental.application;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.FxmlUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final FxmlUtils fxmlUtils;
    private Stage primaryStage;

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        this.primaryStage = stageReadyEvent.getStage();
        Parent root = fxmlUtils.loadView(FxmlUtils.FXML_CAR_TABLE);
        Scene scene = new Scene(root, 1440, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle(fxmlUtils.getStageTitle());
        primaryStage.show();
    }

    public void switchToCarTableView() {
        fxmlUtils.setView(primaryStage, FxmlUtils.FXML_CAR_TABLE);
    }

    public void switchToCustomerTableView() {
        fxmlUtils.setView(primaryStage, FxmlUtils.FXML_CUSTOMER_TABLE);
    }

    public void switchToEmployeeTableView() {
        fxmlUtils.setView(primaryStage, FxmlUtils.FXML_EMPLOYEE_TABLE);
    }

    public void switchToRentalTableView() {
        fxmlUtils.setView(primaryStage, FxmlUtils.FXML_RENTAL_TABLE);
    }

    public boolean showCarEditView(Car car) {
        return fxmlUtils.showModalView(primaryStage, FxmlUtils.FXML_CAR_EDIT, car);
    }

    public boolean showCustomerEditView(Customer customer) {
        return fxmlUtils.showModalView(primaryStage, FxmlUtils.FXML_CUSTOMER_EDIT, customer);
    }

    public boolean showEmployeeEditView(Employee employee) {
        return fxmlUtils.showModalView(primaryStage, FxmlUtils.FXML_EMPLOYEE_EDIT, employee);
    }

    public boolean showRentalEditView(Rental rental) {
        return fxmlUtils.showModalView(primaryStage, FxmlUtils.FXML_RENTAL_EDIT, rental);
    }
}
