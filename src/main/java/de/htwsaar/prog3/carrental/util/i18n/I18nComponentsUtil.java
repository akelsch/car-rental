package de.htwsaar.prog3.carrental.util.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for components file.
 *
 * @author Lukas Raubuch, Jens Thewes
 */
public class I18nComponentsUtil {
    // Keys in components_*.properties
    private static final String STAGE_TITLE = "stage-title";
    private static final String DELETE_CONFIRMATION_DIALOG_HEADER = "dialog-delete-confirmation-header";
    private static final String CANCEL_CREATION_CONFIRMATION_DIALOG_TITLE = "dialog-cancel-creation-confirmation-title";
    private static final String CANCEL_CREATION_CONFIRMATION_DIALOG_HEADER = "dialog-cancel-creation-confirmation-header";
    private static final String DIALOG_CONFIRMATION_TITLE = "dialog-confirmation-title";
    private static final String DIALOG_INFORMATION_TITLE = "dialog-information-title";
    private static final String DIALOG_CONFIRMATION_DELETE_OBJECT_HEADER = "dialog-confirmation-header-delete";
    private static final String DIALOG_INFORMATION_NO_OBJECT_SELECTED_HEADER = "dialog-information-header-no-object-selected";
    private static final String DIALOG_INFORMATION_ABOUT_HEADER = "dialog-information-header-about";

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getResourceBundleComponents();
    }

    // Internationalized values
    public static String getStageTitleString() {
        return resourceBundle.getString(STAGE_TITLE);
    }


    public static String getDeleteConfirmationDialogHeaderString() {
        return resourceBundle.getString(DELETE_CONFIRMATION_DIALOG_HEADER);
    }

    public static String getConfirmationDialogHeaderDelete() {
        return resourceBundle.getString(DIALOG_CONFIRMATION_DELETE_OBJECT_HEADER);
    }

    public static String getInformationDialogTitle() {
        return resourceBundle.getString(DIALOG_INFORMATION_TITLE);
    }

    public static String getInformationDialogHeaderNoObjectSelected() {
        return resourceBundle.getString(DIALOG_INFORMATION_NO_OBJECT_SELECTED_HEADER);
    }

    public static String getConfirmationDialogTitle() {
        return resourceBundle.getString(DIALOG_CONFIRMATION_TITLE);
    }

    public static String getCancelCreationConfirmationDialogTitleString() {
        return resourceBundle.getString(CANCEL_CREATION_CONFIRMATION_DIALOG_TITLE);
    }

    public static String getCancelCreationConfirmationDialogHeaderString() {
        return resourceBundle.getString(CANCEL_CREATION_CONFIRMATION_DIALOG_HEADER);
    }
    
    public static String getDialogInformationHeaderAbout() {
    	return resourceBundle.getString(DIALOG_INFORMATION_ABOUT_HEADER);
    }
}
