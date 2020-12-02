package de.htwsaar.prog3.carrental.util;

import de.htwsaar.prog3.carrental.controller.GenericEditViewController;
import de.htwsaar.prog3.carrental.model.BaseEntity;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class FxmlUtils {

    private final ConfigurableApplicationContext applicationContext;

    public FxmlUtils(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SneakyThrows
    public Parent loadView(String resource) {
        FXMLLoader fxmlLoader = getFxmlLoader(new ClassPathResource(resource));
        return fxmlLoader.load();
    }

    public void setView(Stage stage, String resource) {
        Parent view = loadView(resource);
        stage.getScene().setRoot(view);
    }

    @SneakyThrows
    public <T extends BaseEntity> boolean showModalView(Stage stage, String resource, T entity) {
        FXMLLoader fxmlLoader = getFxmlLoader(new ClassPathResource(resource));
        Scene scene = new Scene(fxmlLoader.load());
        GenericEditViewController<T> controller = fxmlLoader.getController();

        Stage modalStage = new Stage();
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(stage);
        modalStage.setScene(scene);
        modalStage.setTitle(I18nComponentsUtil.getStageTitle());
        modalStage.setResizable(false);

        controller.setModalStage(modalStage);
        controller.initialize(entity);

        modalStage.showAndWait();

        return controller.isApplyClicked();
    }

    private FXMLLoader getFxmlLoader(ClassPathResource fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(fxml.getURL());
        fxmlLoader.setResources(I18nUtil.getResourceBundleComponents());
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader;
    }
}
