package de.htwsaar.prog3.carrental.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for messages file.
 *
 * @author Lukas Raubuch
 */
public class I18nMessagesUtil {
    /**
     * Definition of all available Strings in messages_*.properties.
     */
    // private static final String...

    private static ResourceBundle resourceBundle;

    /**
     * Initialization of the ResourceBundle.
     */
    static {
        resourceBundle = I18Util.getResourceBundleMessages();
    }
}
