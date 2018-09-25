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
 * Entry point of the "Rental Edit" dialog.
 *
 * @author Michael Bös, Hagen Schackmann
 */
public class RentalEditView {
    private static final Logger logger = LoggerFactory.getLogger(RentalEditView.class);

    /**
     * Starts the "Rental Edit" dialog in a modal window.
     *
     * @param parentStage given stage from RentalTableView in order to guarantee window modality
     * @param rental      given rental to be edit
     * @return true, if all edited changes are applied to given rental; false it at least one error occurs
     */
    public boolean start(Stage parentStage, Rental rental) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getRentalEditViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            Parent page = loader.load();
            RentalEditViewController controller = loader.getController();

            // Create the modal stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(parentStage);
            Scene scene = new Scene(page);
            modalStage.setScene(scene);
            modalStage.setMaxHeight(760);
            modalStage.setMaxWidth(600);
            modalStage.setResizable(false);

            // Put the modal stage and rental into the controller
            controller.setModalStage(modalStage);
            controller.initialize(rental);

            // Show the dialog and wait until the user closes it
            modalStage.showAndWait();

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }
}
