package de.htwsaar.prog3.carrental.view;

import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Entry Point of the "Rent out car" Dialog.
 *
 * @author Michael Bös
 *
 */
public class RentOutCarView {

    private static Stage modalWindow;

    /**
     * Start the rent out car Dialog in a modal Window.
     *
     * @param parentStage
     * @throws Exception
     */
    public void start(Stage parentStage) throws Exception {
        modalWindow = new Stage();
        // Load FXML document for the rent out configuration view with the needed resource bundle
        Parent scene = FXMLLoader.load(getClass().getResource(I18nStringsUtil.getRentOutCarViewURL()),
                I18nUtil.getResourceBundleComponents());
        modalWindow.setTitle(I18nComponentsUtil.getStageTitleString());
        // Apply styling described in the FXML document
        modalWindow.setScene(new Scene(scene));
        modalWindow.setMaxHeight(715);
        modalWindow.setMaxWidth(600);
        // set the Owner of the modal window and the Modality of the new Stage (Modal Window)
        modalWindow.initOwner(parentStage);
        modalWindow.initModality(Modality.WINDOW_MODAL);
        modalWindow.show();
    }

    /**
     * close the modal window
     *
     */
    public static void closeModalWindow() {
        modalWindow.close();
    }
}
