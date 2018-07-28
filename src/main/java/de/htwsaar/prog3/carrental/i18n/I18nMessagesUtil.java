package de.htwsaar.prog3.carrental.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for messages file
 * 
 * @author lukas
 * @version 28.07.2018
 */
public class I18nMessagesUtil {
    /**
     * Definition of all available Strings in strings_*.properties
     */
    
    private static ResourceBundle resourceBundle;
    
    /**
     * Initialization of the ResourceBundle
     */
    static {
        resourceBundle = I18Util.getMessagesResourceBundle();
    }
    
    
    /**
     * Access methods to get a internationalized String for the needed value
     */
    
}
