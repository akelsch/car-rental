package de.htwsaar.prog3.carrental.view;

import de.htwsaar.prog3.carrental.controller.CustomerEditViewController;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Entry Point of the "Edit Customer" Dialog.
 *
 * @author Jens Thewes
 */
public class CustomerEditView {
    private static final Logger logger = LoggerFactory.getLogger(CustomerEditView.class);

    /**
     * Start the Edit Employee Dialog in a modal Window.
     *
     * @param parentStage
     * @param customer
     * @return
     */
    public boolean start(Stage parentStage, Customer customer) {
        // Load FXML document for the customer configuration view wit the needed resource bundle
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource(I18nStringsUtil.getCustomerEditViewFxml()),
                    I18nUtil.getResourceBundleComponents());
            Parent page = fxmlLoader.load();

            // create the modal Stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(parentStage);
            Scene scene = new Scene(page);
            modalStage.setScene(scene);
            modalStage.setMaxHeight(400);
            modalStage.setMaxWidth(750);
            modalStage.setResizable(false);

            // set the customer into the controller
            CustomerEditViewController controller = fxmlLoader.getController();
            controller.setModalStage(modalStage);
            controller.setCustomer(customer);

            // add handler to the modal Stage
            modalStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
                if (KeyCode.ENTER == event.getCode()) {
                    controller.handleApplyButtonClicked();
                }else if (KeyCode.ESCAPE == event.getCode()) {
                    controller.handleCancelButtonClicked();
                }
            });

            // show the dialog and wait until the user closes it
            modalStage.showAndWait();

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }
}
