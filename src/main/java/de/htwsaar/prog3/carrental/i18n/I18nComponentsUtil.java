package de.htwsaar.prog3.carrental.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for components file.
 *
 * @author Lukas Raubuch
 */
public class I18nComponentsUtil {
    /**
     * Definition of all available Strings in components_*.properties.
     */
    private static final String STAGE_TITLE = "stage-title";
    private static final String DIALOG_CONFIRMATION_TITLE = "dialog-confirmation-title";
	private static final String DIALOG_INFORMATION_TITLE = "dialog-information-title";
	private static final String DIALOG_CONFIRMATION_DELETE_CAR_HEADER = "dialog-confirmation-header-delete";
	private static final String DIALOG_INFORMATION_NO_CAR_SELECTED_HEADER = "dialog-information-header-no-car-selected";

    		
    private static ResourceBundle resourceBundle;

    /**
     * Initialization of the ResourceBundle.
     */
    static {
        resourceBundle = I18nUtil.getResourceBundleComponents();
    }

    /**
     * Returns the internationalized String of the key defined in STAGE_TITLE.
     * @return the internationalized String
     */
    public static String getStageTitleString() {
        return resourceBundle.getString(STAGE_TITLE);
    }

	public static String getConfirmationDialogHeaderDelete() {
		return resourceBundle.getString(DIALOG_CONFIRMATION_DELETE_CAR_HEADER);
	}

	public static String getInformationDialogTitle() {
		return resourceBundle.getString(DIALOG_INFORMATION_TITLE);
	}

	public static String getInformationDialogHeaderNoCarSelected() {
		return resourceBundle.getString(DIALOG_INFORMATION_NO_CAR_SELECTED_HEADER);
	}

	public static String getConfirmaionDialogTitle() {
		return resourceBundle.getString(DIALOG_CONFIRMATION_TITLE);
	}
}
