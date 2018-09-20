package de.htwsaar.prog3.carrental.view;

import de.htwsaar.prog3.carrental.controller.RentalEditViewController;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Entry Point of the "Rental Edit" Dialog.
 *
 * @author Michael Bös, Hagen Schackmann
 */
public class RentalEditView {
    private static final Logger logger = LoggerFactory.getLogger(RentalEditView.class);

    /**
     * Start the Rent Edit Dialog in a modal Window.
     *
     * @param parentStage given Stage from RentalTableView in order to guarantee Window Modality
     * @param rental given rental to be edit
     * @return true, if all edited changes are applied to given rental; false it at least one
     *         error occurs
     */
    public boolean start(Stage parentStage, Rental rental) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(getClass().getResource(I18nStringsUtil.getRentalEditViewFxml()),
                            I18nUtil.getResourceBundleComponents());
            Parent page = fxmlLoader.load();

            // create the modal Stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(parentStage);
            Scene scene = new Scene(page);
            modalStage.setScene(scene);
            modalStage.setMaxHeight(760);
            modalStage.setMaxWidth(600);
            modalStage.setResizable(false);

            // set the rental into the controller
            RentalEditViewController controller = fxmlLoader.getController();
            controller.setModalStage(modalStage);
            controller.initialize(rental);

            // show the dialog and wait until the user closes it
            modalStage.showAndWait();

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }
}
