package de.htwsaar.prog3.carrental.view;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwsaar.prog3.carrental.controller.CarEditViewController;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Entry Point of the "Edit Car" Dialog.
 *
 * @author Jens Thewes
 */
public class CarEditView {
    private static final Logger logger = LoggerFactory.getLogger(CarEditView.class);

    /**
     * Start the Edit Car Dialog in a modal Window.
     *
     * @param parentStage given Stage from CarTableView in order to guarantee Window Modality
     * @param car given car to be edit
     * @return true, if all edited changes are applied to given car; false it at least one error
     *         occurs
     */
    public boolean start(Stage parentStage, Car car) {
        // Load FXML document for the car configuration view wit the needed resource bundle
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(getClass().getResource(I18nStringsUtil.getCarEditViewFxml()),
                            I18nUtil.getResourceBundleComponents());
            Parent page = fxmlLoader.load();

            // create the modal Stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(parentStage);
            Scene scene = new Scene(page);
            modalStage.setScene(scene);
            modalStage.setMaxHeight(600);
            modalStage.setMaxWidth(900);
            modalStage.setResizable(false);

            // set the car into the controller
            CarEditViewController controller = fxmlLoader.getController();
            controller.setModalStage(modalStage);
            controller.initialize(car);

            // add handler to the modal Stage
            modalStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
                if (KeyCode.ENTER == event.getCode()) {
                    controller.handleApplyButtonClicked();
                } else if (KeyCode.ESCAPE == event.getCode()) {
                    controller.handleCancelButtonClicked();
                }
            });

            // show the dialog and wait until the user closes it
            modalStage.showAndWait();

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }
}
