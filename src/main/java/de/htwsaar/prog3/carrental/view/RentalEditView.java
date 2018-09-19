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
    private static Stage modalStage;

    /**
     * Start the Rent Edit Dialog in a modal Window.
     *
     * @param parentStage
     * @param rental
     * @throws Exception
     */
    public boolean start(Stage parentStage, Rental rental) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(getClass().getResource(I18nStringsUtil.getRentalEditViewFxml()),
                            I18nUtil.getResourceBundleComponents());
            Parent scene = fxmlLoader.load();

            modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            // Apply styling described in the FXML document
            modalStage.setScene(new Scene(scene));
            modalStage.setMaxHeight(715);
            modalStage.setMaxWidth(600);
            // set the Owner of the modal window and the Modality of the new Stage (Modal Window)
            modalStage.initOwner(parentStage);
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.show();

            RentalEditViewController controller = fxmlLoader.getController();
            controller.setModalStage(modalStage);
            controller.setRental(rental);

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }

        /**
         * close the modal window
         */
        public static void closeModalWindow() {
            modalStage.close();
        }

}
