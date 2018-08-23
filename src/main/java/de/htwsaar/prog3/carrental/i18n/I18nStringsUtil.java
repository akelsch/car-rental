package de.htwsaar.prog3.carrental.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for strings file.
 *
 * @author Lukas Raubuch
 */
public class I18nStringsUtil {
    /**
     * Definition of all available Strings in strings_*.properties.
     */
	private static final String CAR_TABLE_VIEW_URL = "car-tableview-fxml-file";
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
     * @return the internationalized String
     */
    public static String getCarTableViewURL() {
        return resourceBundle.getString(CAR_TABLE_VIEW_URL);
    }
}
