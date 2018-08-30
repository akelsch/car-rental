package de.htwsaar.prog3.carrental.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for messages file.
 *
 * @author Lukas Raubuch
 */
public class I18nMessagesUtil {
    // Keys in messages_*.properties
    // private static final String...

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getResourceBundleMessages();
    }

    // Internationalized values
    // public static String get...
}
