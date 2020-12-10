package de.htwsaar.prog3.carrental.util;

import de.htwsaar.prog3.carrental.controller.EditViewController;
import de.htwsaar.prog3.carrental.model.BaseEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Utility class used to handle JavaFX FXML views.
 *
 * @author Arthur Kelsch
 */
@Component
@RequiredArgsConstructor
public class FxmlUtils {

    public static final String FXML_CAR_EDIT = "de.htwsaar.prog3.carrental.fxml.car.edit";
    public static final String FXML_CAR_TABLE = "de.htwsaar.prog3.carrental.fxml.car.table";
    public static final String FXML_CUSTOMER_EDIT = "de.htwsaar.prog3.carrental.fxml.customer.edit";
    public static final String FXML_CUSTOMER_TABLE = "de.htwsaar.prog3.carrental.fxml.customer.table";
    public static final String FXML_EMPLOYEE_EDIT = "de.htwsaar.prog3.carrental.fxml.employee.edit";
    public static final String FXML_EMPLOYEE_TABLE = "de.htwsaar.prog3.carrental.fxml.employee.table";
    public static final String FXML_RENTAL_EDIT = "de.htwsaar.prog3.carrental.fxml.rental.edit";
    public static final String FXML_RENTAL_TABLE = "de.htwsaar.prog3.carrental.fxml.rental.table";

    private final ConfigurableApplicationContext applicationContext;
    private final MessageUtils messageUtils;

    public void setView(Stage stage, String fxml) {
        Parent root = loadView(fxml);
        stage.getScene().setRoot(root);
    }

    @SneakyThrows
    public Parent loadView(String fxml) {
        FXMLLoader fxmlLoader = getFxmlLoader(fxml);
        return fxmlLoader.load();
    }

    @SneakyThrows
    public <T extends BaseEntity> boolean showModalView(Stage stage, String fxml, T entity) {
        FXMLLoader fxmlLoader = getFxmlLoader(fxml);
        Scene scene = new Scene(fxmlLoader.load());
        EditViewController<T> controller = fxmlLoader.getController();

        Stage modalStage = new Stage();
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(stage);
        modalStage.setScene(scene);
        modalStage.setTitle(getStageTitle());
        modalStage.setResizable(false);

        controller.initialize(entity);
        controller.setModalStage(modalStage);

        modalStage.showAndWait();

        return controller.isApplyClicked();
    }

    private FXMLLoader getFxmlLoader(String fxml) throws IOException {
        String file = applicationContext.getEnvironment().getProperty(fxml);
        ClassPathResource resource = new ClassPathResource("fxml/%s".formatted(file));

        FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
        fxmlLoader.setResources(messageUtils.getResourceBundle());
        fxmlLoader.setControllerFactory(applicationContext::getBean);

        return fxmlLoader;
    }

    public String getStageTitle() {
        return messageUtils.getMessage(MessageUtils.STAGE_TITLE);
    }
}
