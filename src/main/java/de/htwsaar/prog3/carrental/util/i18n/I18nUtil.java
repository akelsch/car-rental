package de.htwsaar.prog3.carrental.util.i18n;

import lombok.Getter;

import java.util.ResourceBundle;

/**
 * Internationalization utility class providing different resource bundles.
 *
 * @author Lukas Raubuch
 */
public final class I18nUtil {
    private I18nUtil() {
    }

    // Bundle base names
    private static final String I18N_BASENAME_STRINGS = "bundles/strings";
    private static final String I18N_BASENAME_COMPONENTS = "bundles/components";

    @Getter
    private static ResourceBundle resourceBundleStrings;

    @Getter
    private static ResourceBundle resourceBundleComponents;

    static {
        resourceBundleStrings = ResourceBundle.getBundle(I18N_BASENAME_STRINGS);
        resourceBundleComponents = ResourceBundle.getBundle(I18N_BASENAME_COMPONENTS);
    }
}
