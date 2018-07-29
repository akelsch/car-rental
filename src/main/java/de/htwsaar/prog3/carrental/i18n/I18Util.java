package de.htwsaar.prog3.carrental.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Internationalization utility class used in this project mainly to keep code clean of String
 * objects.
 *
 * @author Lukas Raubuch
 */
public class I18Util {

    /**
     * Basenames of the different Strings Use strings if it is something you need but don't want the
     * user to see as a Message Use messages if it is message that you want to show to the user on
     * directly e.g. a dialog message Use components if it is something you want to display inside
     * of your components.
     */
    private static final String I18N_BASENAME_STRINGS = "de.htwsaar.prog3.carrental.i18n.strings";
    private static final String I18N_BASENAME_MESSAGES = "de.htwsaar.prog3.carrental.i18n.messages";
    private static final String I18N_BASENAME_COMPONENTS =
            "de.htwsaar.prog3.carrental.i18n.components";

    /**
     * Definition of ResourceBundles for each file.
     */
    private static ResourceBundle resourceBundleStrings;
    private static ResourceBundle resourceBundleMessages;
    private static ResourceBundle resourceBundleComponents;

    /**
     * Initialization of the ResourceBundles.
     */
    static {
        resourceBundleStrings = ResourceBundle.getBundle(I18N_BASENAME_STRINGS, Locale.getDefault());
        resourceBundleMessages = ResourceBundle.getBundle(I18N_BASENAME_MESSAGES, Locale.getDefault());
        resourceBundleComponents = ResourceBundle.getBundle(I18N_BASENAME_COMPONENTS, Locale.getDefault());
    }

    public static ResourceBundle getStringsResourceBundle() {
        return resourceBundleStrings;
    }

    public static ResourceBundle getMessagesResourceBundle() {
        return resourceBundleMessages;
    }

    public static ResourceBundle getComponentsResourceBundle() {
        return resourceBundleComponents;
    }
}
