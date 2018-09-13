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
    private static final String CAR_TABLE_VIEW_FXML = "car-table-view-fxml";
    private static final String NEW_CAR_VIEW_FXML = "new-car-view-fxml";
    private static final String EDIT_CAR_VIEW_FXML = "edit-car-view-fxml";
    private static final String CUSTOMER_TABLE_VIEW_FXML = "customer-table-view-fxml";
    private static final String NEW_CUSTOMER_VIEW_FXML = "new-customer-view-fxml";
    private static final String EDIT_CUSTOMER_VIEW_FXML = "edit-customer-view-fxml";
    private static final String EMPLOYEE_TABLE_VIEW_FXML = "employee-table-view-fxml";
    private static final String NEW_EMPLOYEE_VIEW_FXML = "new-employee-view-fxml";
    private static final String EDIT_EMPLOYEE_VIEW_FXML = "edit-employee-view-fxml";
    private static final String RENTAL_TABLE_VIEW_FXML = "rental-table-view-fxml";
	private static final String ROOT_VIEW_FXML = "root-view-fxml";

    // Resource bundle
    private static ResourceBundle resourceBundle = I18nUtil.getResourceBundleStrings();

    // Values
    public static String getCarTableViewFxml() {
        return resourceBundle.getString(CAR_TABLE_VIEW_FXML);
    }

    public static String getNewCarViewFxml() {
        return resourceBundle.getString(NEW_CAR_VIEW_FXML);
    }

    public static String getEditCarViewFxml() {
        return resourceBundle.getString(EDIT_CAR_VIEW_FXML);
    }

    public static String getCustomerTableViewFxml() {
        return resourceBundle.getString(CUSTOMER_TABLE_VIEW_FXML);
    }

    public static String getNewCustomerViewFxml() {
        return resourceBundle.getString(NEW_CUSTOMER_VIEW_FXML);
    }

    public static String getEditCustomerViewFxml() {
        return resourceBundle.getString(EDIT_CUSTOMER_VIEW_FXML);
    }

    public static String getEmployeeTableViewFxml() {
        return resourceBundle.getString(EMPLOYEE_TABLE_VIEW_FXML);
    }

    public static String getNewEmployeeViewFxml() {
        return resourceBundle.getString(NEW_EMPLOYEE_VIEW_FXML);
    }

    public static String getEditEmployeeViewFxml() {
        return resourceBundle.getString(EDIT_EMPLOYEE_VIEW_FXML);
    }

    public static String getRentalTableViewFxml() {
        return resourceBundle.getString(RENTAL_TABLE_VIEW_FXML);
    }

	public static String getRootViewFxml() {
		return resourceBundle.getString(ROOT_VIEW_FXML);
	}
}
