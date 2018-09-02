package de.htwsaar.prog3.carrental.util.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for {@code strings.properties}.
 * <p>
 * Provides strings that are NOT visible to the end user.
 *
 * @author Lukas Raubuch, Jens Thewes
 */
public final class I18nStringsUtil {
    private I18nStringsUtil() {
    }

    // Keys
    private static final String CAR_TABLE_VIEW_URL = "car-table-view-fxml";
    private static final String NEW_CAR_VIEW_URL = "new-car-view-fxml";
    private static final String EDIT_CAR_VIEW_URL = "edit-car-view-fxml";
    private static final String CUSTOMER_TABLE_VIEW_URL = "customer-table-view-fxml";
    private static final String NEW_CUSTOMER_VIEW_URL = "new-customer-view-fxml";
    private static final String EDIT_CUSTOMER_VIEW_URL = "edit-customer-view-fxml";
    private static final String EMPLOYEE_TABLE_VIEW_URL = "employee-table-view-fxml";
    private static final String NEW_EMPLOYEE_VIEW_URL = "new-employee-view-fxml";
    private static final String EDIT_EMPLOYEE_VIEW_URL = "edit-employee-view-fxml";
    private static final String RENTAL_TABLE_VIEW_URL = "rental-table-view-fxml";

    // Resource bundle
    private static ResourceBundle resourceBundle = I18nUtil.getResourceBundleStrings();

    // Values
    public static String getCarTableViewURL() {
        return resourceBundle.getString(CAR_TABLE_VIEW_URL);
    }

    public static String getNewCarViewURL() {
        return resourceBundle.getString(NEW_CAR_VIEW_URL);
    }

    public static String getEditCarViewURL() {
        return resourceBundle.getString(EDIT_CAR_VIEW_URL);
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

    public static String getEmployeeTableViewURL() {
        return resourceBundle.getString(EMPLOYEE_TABLE_VIEW_URL);
    }

    public static String getNewEmployeeViewURL() {
        return resourceBundle.getString(NEW_EMPLOYEE_VIEW_URL);
    }

    public static String getEditEmployeeViewURL() {
        return resourceBundle.getString(EDIT_EMPLOYEE_VIEW_URL);
    }

    public static String getRentalTableViewURL() {
        return resourceBundle.getString(RENTAL_TABLE_VIEW_URL);
    }
}
