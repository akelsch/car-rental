package de.htwsaar.prog3.carrental.util;

import de.htwsaar.prog3.carrental.i18n.I18nComponentsUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class is used to create standard dialogs via static access methods
 * 
 * @author Lukas Raubuch
 */
public class GUIDialogUtil {

	/**
	 * Creates an informational dialog that can be displayed to the user
	 * 
	 * @param headerText
	 *            the text that will be displayed in the information dialog
	 * @return an Alert object that represents the dialog that can be displayed
	 */
	public static final Alert createInformationDialog(String headerText) {
		Alert informationDialog = new Alert(AlertType.INFORMATION);
		informationDialog.setTitle(I18nComponentsUtil.getInformationDialogTitle());
		informationDialog.setHeaderText(headerText);
		return informationDialog;
	}

	public static final Alert createConfirmationDialog(String headerText) {
		Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
		confirmationDialog.setTitle(I18nComponentsUtil.getConfirmaionDialogTitle());
		confirmationDialog.setHeaderText(headerText);
		return confirmationDialog;
	}
}
