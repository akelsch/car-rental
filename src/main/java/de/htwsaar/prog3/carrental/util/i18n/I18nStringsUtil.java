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
    // Keys
    private static final String CAR_TABLE_VIEW_FXML = "car-table-view-fxml";
    private static final String CAR_EDIT_VIEW_FXML = "car-edit-view-fxml";
    private static final String CUSTOMER_TABLE_VIEW_FXML = "customer-table-view-fxml";
    private static final String CUSTOMER_EDIT_VIEW_FXML = "customer-edit-view-fxml";
    private static final String EMPLOYEE_TABLE_VIEW_FXML = "employee-table-view-fxml";
    private static final String EMPLOYEE_EDIT_VIEW_FXML = "employee-edit-view-fxml";
    private static final String RENTAL_TABLE_VIEW_FXML = "rental-table-view-fxml";
    private static final String RENTAL_EDIT_VIEW_FXML = "rental-edit-view-fxml";

    // Resource bundle
    private static ResourceBundle resourceBundle = I18nUtil.getResourceBundleStrings();

    private I18nStringsUtil() {
    }

    // Values
    public static String getCarTableViewFxml() {
        return resourceBundle.getString(CAR_TABLE_VIEW_FXML);
    }

    public static String getCarEditViewFxml() {
        return resourceBundle.getString(CAR_EDIT_VIEW_FXML);
    }

    public static String getCustomerTableViewFxml() {
        return resourceBundle.getString(CUSTOMER_TABLE_VIEW_FXML);
    }

    public static String getCustomerEditViewFxml() {
        return resourceBundle.getString(CUSTOMER_EDIT_VIEW_FXML);
    }

    public static String getEmployeeTableViewFxml() {
        return resourceBundle.getString(EMPLOYEE_TABLE_VIEW_FXML);
    }

    public static String getEmployeeEditViewFxml() {
        return resourceBundle.getString(EMPLOYEE_EDIT_VIEW_FXML);
    }

    public static String getRentalTableViewFxml() {
        return resourceBundle.getString(RENTAL_TABLE_VIEW_FXML);
    }

    public static String getRentalEditViewFxml() {
        return resourceBundle.getString(RENTAL_EDIT_VIEW_FXML);
    }
}
