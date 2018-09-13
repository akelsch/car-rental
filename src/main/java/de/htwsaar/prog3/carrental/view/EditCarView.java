package de.htwsaar.prog3.carrental.view;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwsaar.prog3.carrental.controller.EditCarViewController;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Entry Point of the "Edit Car" Dialog.
 *
 * @author Jens Thewes
 */
public class EditCarView {
    private static final Logger logger = LoggerFactory.getLogger(EditCarView.class);

    /**
     * Start the Car Configuration View Dialog in a modal Window in order to edit a existing car.
     *
     * @param parentStage
     * @param car
     */
    public boolean start(Stage parentStage, Car carToEdit) {
        // Load FXML document for the car configuration view wit the needed resource bundle
        try {
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource(I18nStringsUtil.getEditCarViewFxml()),
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
        EditCarViewController controller = fxmlLoader.getController();
        controller.setModalStage(modalStage);
        controller.setCar(carToEdit);

        // show the dialog and wait until the user closes it
        modalStage.showAndWait();

        return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }
}
