package de.htwsaar.prog3.carrental.util;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for strings file.
 *
 * @author Lukas Raubuch, Jens Thewes
 */
public class I18nStringsUtil {
    // Keys in strings_*.properties
    private static final String CAR_TABLE_VIEW_URL = "car-tableview-fxml-file";
    private static final String NEW_CAR_CREATION_VIEW_URL = "new-car-creationview-fxml-file";
    private static final String CAR_CONFIGURATION_VIEW_IRL = "car-configurationview-fxml-file";

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getResourceBundleStrings();
    }

    // Internationalized values
    public static String getCarTableViewURL() {
        return resourceBundle.getString(CAR_TABLE_VIEW_URL);
    }

    public static String getNewCarCreationViewURL() {
        return resourceBundle.getString(NEW_CAR_CREATION_VIEW_URL);
    }

    public static String getCarConfigurationViewURL() {
        return resourceBundle.getString(CAR_CONFIGURATION_VIEW_IRL);
    }
}
