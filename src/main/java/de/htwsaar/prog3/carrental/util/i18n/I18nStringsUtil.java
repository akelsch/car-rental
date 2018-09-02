package de.htwsaar.prog3.carrental.util.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for strings file.
 *
 * @author Lukas Raubuch, Jens Thewes
 */
public class I18nStringsUtil {
    // Keys in strings_*.properties
    private static final String CAR_TABLE_VIEW_URL = "car-tableview-fxml-file";
    private static final String NEW_CAR_VIEW_URL = "new-carview-fxml-file";
    private static final String EDIT_CAR_VIEW_URL = "edit-carview-fxml-file";
    private static final String EMPLOYEE_TABLE_VIEW_URL = "employee-tableview-fxml-file";
    private static final String RENTAL_TABLE_VIEW_URL = "rental-tableview-fxml-file";
    private static final String CUSTOMER_TABLE_VIEW_URL = "customer-tableview-fxml-file";
    private static final String NEW_CUSTOMER_VIEW_URL = "new-customerview-fxml-file";
    private static final String EDIT_CUSTOMER_VIEW_URL = "edit-customerview-fxml-file";
    private static final String NEW_EMPLOYEE_VIEW_URL ="new-employeeview-fxml-file";
    private static final String EDIT_EMPLOYEE_VIEW_URL ="edit-employeeview-fxml-file";

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getResourceBundleStrings();
    }

    // Internationalized values
    public static String getCarTableViewURL() {
        return resourceBundle.getString(CAR_TABLE_VIEW_URL);
    }

    public static String getNewCarViewURL() {
        return resourceBundle.getString(NEW_CAR_VIEW_URL);
    }

    public static String getEditCarViewURL() {
        return resourceBundle.getString(EDIT_CAR_VIEW_URL);
    }

    public static String getEmployeeTableViewURL() {
        return resourceBundle.getString(EMPLOYEE_TABLE_VIEW_URL);
    }

    public static String getRentalTableViewURL() {
        return resourceBundle.getString(RENTAL_TABLE_VIEW_URL);
    }

    public static String getCustomerTableViewURL() {
        return resourceBundle.getString(CUSTOMER_TABLE_VIEW_URL);
    }

    public static String getNewCustomerViewURL() {
        return resourceBundle.getString(NEW_CUSTOMER_VIEW_URL);
    }

    public static String getEditCustomerViewURL() {
        return resourceBundle.getString(EDIT_CUSTOMER_VIEW_URL);
    }
   
    public static String getNewEmployeeViewURL() {
	    return resourceBundle.getString(NEW_EMPLOYEE_VIEW_URL);
	  }

    public static String getEditEmployeeViewURL() {
        return resourceBundle.getString(EDIT_EMPLOYEE_VIEW_URL);
    }
}
