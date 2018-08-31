package de.htwsaar.prog3.carrental.gui;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.util.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Entry Point of the Car Configuration Dialog.
 *
 * @author Jens Thewes
 */
public class CarConfigurationView {
    
    private static Stage modalWindow;
    private static Car car;

    /**
     * Start the Car Configuration View Dialog in a modal Window in order to edit a existing car.
     *
     * @param parentStage
     * @throws Exception
     */
    public void start(Stage parentStage, Car car) throws Exception {
        modalWindow  = new Stage();
        CarConfigurationView.car = car;
        // Load FXML document for the car configuration view wit the needed resource bundle
        Parent scene =
                FXMLLoader.load(getClass().getResource(I18nStringsUtil.getCarConfigurationViewURL()),
                        I18nUtil.getResourceBundleComponents());
        modalWindow.setTitle(I18nComponentsUtil.getStageTitleString());
        // Apply styling described in the FXML document
        modalWindow.setScene(new Scene(scene));
        modalWindow.setMaxHeight(600);
        modalWindow.setMaxWidth(900);
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
    
    /**
     * get the car to edit
     * 
     * @return
     */
    public static Car getCar() {
        return car;
    }
}
