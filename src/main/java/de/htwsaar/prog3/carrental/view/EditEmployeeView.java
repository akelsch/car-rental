package de.htwsaar.prog3.carrental.view;

import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Entry Point of the "Edit Employee" Dialog.
 *
 * @author Jens Thewes
 * 
 */
public class EditEmployeeView {

    private static Stage modalWindow;
    private static Employee employee;

    /**
     * Start the Edit Employee Dialog in a modal Window
     * 
     * @param parentStage
     * @throws Exception
     */
    public void start(Stage parentStage, Employee employee) throws Exception {
        modalWindow = new Stage();
        EditEmployeeView.employee = employee;
        // Load FXML document for the new employee view wit the needed resource bundle
        Parent scene =
                FXMLLoader.load(getClass().getResource(I18nStringsUtil.getEditEmployeeViewURL()),
                        I18nUtil.getResourceBundleComponents());
        modalWindow.setTitle(I18nComponentsUtil.getStageTitleString());
        // Apply styling described in the FXML document
        modalWindow.setScene(new Scene(scene));
        modalWindow.setMinHeight(200);
        modalWindow.setMaxHeight(200);
        modalWindow.setMinWidth(600);
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

    /**
     * get the employee to edit
     * 
     * @return
     */
    public static Employee getEmployee() {
        return employee;
    }
}
