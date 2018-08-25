package de.htwsaar.prog3.carrental.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for strings file.
 *
 * @author Lukas Raubuch, Jens Thewes
 */
public class I18nStringsUtil {
    /**
     * Definition of all available Strings in strings_*.properties.
     */
    private static final String CAR_TABLE_VIEW_URL = "car-tableview-fxml-file";
    private static final String NEW_CAR_CREATION_VIEW_URL = "new-car-creationview-fxml-file";
    // private static final String...

    private static ResourceBundle resourceBundle;

    /**
     * Initialization of the ResourceBundle.
     */
    static {
        resourceBundle = I18nUtil.getResourceBundleStrings();
    }

    /**
     * Returns the internationalized String of the key defined in STAGE_TITLE.
     * 
     * @return the internationalized String
     */
    public static String getCarTableViewURL() {
        return resourceBundle.getString(CAR_TABLE_VIEW_URL);
    }

    /**
     * Returns the internationalized String of the key defined in
     * 
     * @return the internationalized String
     */
    public static String getNewCarCreationViewURL() {
        return resourceBundle.getString(NEW_CAR_CREATION_VIEW_URL);
    }
}
