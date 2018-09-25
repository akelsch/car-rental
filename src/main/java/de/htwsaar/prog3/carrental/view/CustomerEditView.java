package de.htwsaar.prog3.carrental.view;

import de.htwsaar.prog3.carrental.controller.CustomerEditViewController;
import de.htwsaar.prog3.carrental.model.Customer;
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
 * Entry point of the "Edit Customer" dialog.
 *
 * @author Jens Thewes
 */
public class CustomerEditView {
    private static final Logger logger = LoggerFactory.getLogger(CustomerEditView.class);

    /**
     * Starts the "Edit Customer" dialog in a modal window.
     *
     * @param parentStage given stage from CustomerTableView in order to guarantee window modality
     * @param customer    given customer to be edit
     * @return true, if all edited changes are applied to given customer; false it at least one error occurs
     */
    public boolean start(Stage parentStage, Customer customer) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getCustomerEditViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            Parent page = loader.load();
            CustomerEditViewController controller = loader.getController();

            // Create the modal stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(parentStage);
            Scene scene = new Scene(page);
            modalStage.setScene(scene);
            modalStage.setMaxHeight(400);
            modalStage.setMaxWidth(750);
            modalStage.setResizable(false);

            // Put the modal stage and customer into the controller
            controller.setModalStage(modalStage);
            controller.initialize(customer);

            // Show the dialog and wait until the user closes it
            modalStage.showAndWait();

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }
}
