package de.htwsaar.prog3.carrental.view;

import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditCustomerView {
    
    private static Stage modalWindow;
    private static Customer customer;

    /**
     * Start the Edit Employee Dialog in a modal Window
     * 
     * @param parentStage
     * @throws Exception
     */
    public void start(Stage parentStage, Customer customer) throws Exception {
        modalWindow = new Stage();
        EditCustomerView.customer = customer;
        // Load FXML document for the edit customer view wit the needed resource bundle
        Parent scene =
                FXMLLoader.load(getClass().getResource(I18nStringsUtil.getEditCustomerViewURL()),
                        I18nUtil.getResourceBundleComponents());
        modalWindow.setTitle(I18nComponentsUtil.getStageTitleString());
        // Apply styling described in the FXML document
        modalWindow.setScene(new Scene(scene));
        modalWindow.setMaxHeight(400);
        modalWindow.setMaxWidth(750);
        modalWindow.setHeight(400);
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
     * get the customer to edit
     * 
     * @return
     */
    public static Customer getCustomer() {
        return customer;
    }
}
