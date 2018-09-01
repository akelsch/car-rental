package de.htwsaar.prog3.carrental.gui;

import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Dialog to create a new Employee
 * 
 * @author Jens Thewes
 *
 */
public class NewEmployeeView {

    private static Stage modalWindow;

    /**
     * Start the New Employee Dialog in a modal Window
     * 
     * @param parentStage
     * @throws Exception
     */
    public void start(Stage parentStage) throws Exception {
        modalWindow = new Stage();
        // Load FXML document for the new employee view wit the needed resource bundle
        Parent scene =
                FXMLLoader.load(getClass().getResource(I18nStringsUtil.getNewEmployeeViewURL()),
                        I18nUtil.getResourceBundleComponents());
        modalWindow.setTitle(I18nComponentsUtil.getStageTitleString());
        // Apply styling described in the FXML document
        modalWindow.setScene(new Scene(scene));
        modalWindow.setMaxHeight(200);
        modalWindow.setMaxWidth(600);
        modalWindow.setHeight(200);
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
