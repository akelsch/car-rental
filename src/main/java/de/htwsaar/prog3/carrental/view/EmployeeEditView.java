package de.htwsaar.prog3.carrental.view;

import de.htwsaar.prog3.carrental.controller.EmployeeEditViewController;
import de.htwsaar.prog3.carrental.model.Employee;
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
 * Entry point of the "Edit Employee" dialog.
 *
 * @author Jens Thewes
 */
public class EmployeeEditView {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeEditView.class);

    /**
     * Starts the "Edit Employee" dialog in a modal window.
     *
     * @param parentStage given stage from EmployeeTableView in order to guarantee window modality
     * @param employee    given employee to be edit
     * @return true, if all edited changes are applied to given employee; false it at least one error occurs
     */
    public boolean start(Stage parentStage, Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(I18nStringsUtil.getEmployeeEditViewFxml()));
            loader.setResources(I18nUtil.getResourceBundleComponents());

            Parent page = loader.load();
            EmployeeEditViewController controller = loader.getController();

            // Create the modal stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(parentStage);
            Scene scene = new Scene(page);
            modalStage.setScene(scene);
            modalStage.setMaxHeight(200);
            modalStage.setMaxWidth(600);
            modalStage.setResizable(false);

            // Put the modal stage and employee into the controller
            controller.setModalStage(modalStage);
            controller.initialize(employee);

            // Show the dialog and wait until the user closes it
            modalStage.showAndWait();

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }
}
