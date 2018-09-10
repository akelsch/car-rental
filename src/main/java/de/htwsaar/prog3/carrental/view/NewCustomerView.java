package de.htwsaar.prog3.carrental.view;

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
 * Entry Point of the "New Customer" Dialog.
 *
 * @author Jens Thewes
 */
public class NewCustomerView {
    private static final Logger logger = LoggerFactory.getLogger(NewCustomerView.class);

    private static Stage modalWindow;

    /**
     * Start the New Employee Dialog in a modal Window.
     *
     * @param parentStage
     */
    public void start(Stage parentStage) {
        modalWindow = new Stage();

        // Load FXML document for the new customer view wit the needed resource bundle
        Parent scene = null;
        try {
            scene = FXMLLoader.load(getClass().getResource(I18nStringsUtil.getNewCustomerViewFxml()),
                    I18nUtil.getResourceBundleComponents());
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
        }

        modalWindow.setTitle(I18nComponentsUtil.getStageTitle());
        // Apply styling described in the FXML document
        modalWindow.setScene(new Scene(scene));
        modalWindow.setMinHeight(400);
        modalWindow.setMaxHeight(400);
        modalWindow.setMinWidth(750);
        modalWindow.setMaxWidth(750);
        // set the Owner of the modal window and the Modality of the new Stage (Modal Window)
        modalWindow.initOwner(parentStage);
        modalWindow.initModality(Modality.WINDOW_MODAL);
        modalWindow.show();
    }

    /**
     * Closes the modal window.
     */
    public static void closeModalWindow() {
        modalWindow.close();
    }
}
