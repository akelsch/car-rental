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
}
