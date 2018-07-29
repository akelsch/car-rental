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

    private static ResourceBundle resourceBundle;

    /**
     * Initialization of the ResourceBundle.
     */
    static {
        resourceBundle = I18Util.getStringsResourceBundle();
    }
}
