package de.htwsaar.prog3.carrental.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for components file.
 *
 * @author Lukas Raubuch, Jens Thewes
 */
public class I18nComponentsUtil {
    // Keys in components_*.properties
    private static final String STAGE_TITLE = "stage-title";
    private static final String DELETE_CONFIRMATION_DIALOG_TITLE = "dialog-delete-confirmation-title";
    private static final String DELETE_CONFIRMATION_DIALOG_HEADER = "dialog-delete-confirmation-header";
    private static final String CANCEL_CREATION_CONFIRMATION_DIALOG_TITLE = "dialog-cancel-creation-confirmation-title";
    private static final String CANCEL_CREATION_CONFIRMATION_DIALOG_HEADER = "dialog-cancel-creation-confirmation" +
            "-header";
    private static final String DIALOG_CONFIRMATION_TITLE = "dialog-confirmation-title";
    private static final String DIALOG_INFORMATION_TITLE = "dialog-information-title";
    private static final String DIALOG_CONFIRMATION_DELETE_CAR_HEADER = "dialog-confirmation-header-delete";
    private static final String DIALOG_INFORMATION_NO_CAR_SELECTED_HEADER = "dialog-information-header-no-car-selected";
    private static final String SEARCH_COMBOBOX_ID = "search-combobox-id";
    private static final String SEARCH_COMBOBOX_BRAND = "search-combobox-brand";
    private static final String SEARCH_COMBOBOX_HORSEPOWER = "search-combobox-horsepower";
    private static final String SEARCH_COMBOBOX_DAILYRATE = "search-combobox-dailyRate";
    private static final String SEARCH_COMBOBOX_DOORCOUNT = "search-combobox-doorCount";
    private static final String SEARCH_COMBOBOX_DRIVENDISTANCE = "search-combobox-drivenDistance";
    private static final String SEARCH_COMBOBOX_CATEGORY = "search-combobox-category";
    private static final String SEARCH_COMBOBOX_COLOR = "search-combobox-color";
    private static final String SEARCH_COMBOBOX_CONSTRUCTIONYEAR = "search-combobox-constructionYear";
    private static final String SEARCH_COMBOBOX_DEFECTS = "search-combobox-defects";
    private static final String SEARCH_COMBOBOX_FUEL = "search-combobox-fuel";
    private static final String SEARCH_COMBOBOX_GEARBOX = "search-combobox-gearbox";
    private static final String SEARCH_COMBOBOX_LICENSENUMBER = "search-combobox-licenseNumber";
    private static final String SEARCH_COMBOBOX_MODEL = "search-combobox-model";
    private static final String SEARCH_COMBOBOX_NEXTINSPECTION = "search-combobox-nextInspection";
    private static final String SEARCH_COMBOBOX_PARKINGLOT = "search-combobox-parkingLot";
    private static final String SEARCH_COMBOBOX_TIRES = "search-combobox-tires";
    private static final String SEARCH_COMBOBOX_VIN = "search-combobox-vin";

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getResourceBundleComponents();
    }

    // Internationalized values
    public static String getStageTitleString() {
        return resourceBundle.getString(STAGE_TITLE);
    }

    public static String getDeleteConfirmationDialogTitleString() {
        return resourceBundle.getString(DELETE_CONFIRMATION_DIALOG_TITLE);
    }

    public static String getDeleteConfirmationDialogHeaderString() {
        return resourceBundle.getString(DELETE_CONFIRMATION_DIALOG_HEADER);
    }

    public static String getConfirmationDialogHeaderDelete() {
        return resourceBundle.getString(DIALOG_CONFIRMATION_DELETE_CAR_HEADER);
    }

    public static String getInformationDialogTitle() {
        return resourceBundle.getString(DIALOG_INFORMATION_TITLE);
    }

    public static String getInformationDialogHeaderNoCarSelected() {
        return resourceBundle.getString(DIALOG_INFORMATION_NO_CAR_SELECTED_HEADER);
    }

    public static String getConfirmationDialogTitle() {
        return resourceBundle.getString(DIALOG_CONFIRMATION_TITLE);
    }

    public static String getSearchComboBoxId() {
        return resourceBundle.getString(SEARCH_COMBOBOX_ID);
    }

    public static String getSearchComboBoxBrand() {
        return resourceBundle.getString(SEARCH_COMBOBOX_BRAND);
    }

    public static String getSearchComboBoxHorsepower() {
        return resourceBundle.getString(SEARCH_COMBOBOX_HORSEPOWER);
    }

    public static String getSearchComboBoxDailyRate() {
        return resourceBundle.getString(SEARCH_COMBOBOX_DAILYRATE);
    }

    public static String getSearchComboBoxDoorCount() {
        return resourceBundle.getString(SEARCH_COMBOBOX_DOORCOUNT);
    }

    public static String getSearchComboBoxDrivenDistance() {
        return resourceBundle.getString(SEARCH_COMBOBOX_DRIVENDISTANCE);
    }

    public static String getSearchComboBoxCategory() {
        return resourceBundle.getString(SEARCH_COMBOBOX_CATEGORY);
    }

    public static String getSearchComboBoxColor() {
        return resourceBundle.getString(SEARCH_COMBOBOX_COLOR);
    }

    public static String getSearchComboBoxConstructionYear() {
        return resourceBundle.getString(SEARCH_COMBOBOX_CONSTRUCTIONYEAR);
    }

    public static String getSearchComboBoxDefects() {
        return resourceBundle.getString(SEARCH_COMBOBOX_DEFECTS);
    }

    public static String getSearchComboBoxFuel() {
        return resourceBundle.getString(SEARCH_COMBOBOX_FUEL);
    }

    public static String getSearchComboBoxGearbox() {
        return resourceBundle.getString(SEARCH_COMBOBOX_GEARBOX);
    }

    public static String getSearchComboBoxLicenseNumber() {
        return resourceBundle.getString(SEARCH_COMBOBOX_LICENSENUMBER);
    }

    public static String getSearchComboBoxModel() {
        return resourceBundle.getString(SEARCH_COMBOBOX_MODEL);
    }

    public static String getSearchComboBoxNextInspection() {
        return resourceBundle.getString(SEARCH_COMBOBOX_NEXTINSPECTION);
    }

    public static String getSearchComboBoxParkingLot() {
        return resourceBundle.getString(SEARCH_COMBOBOX_PARKINGLOT);
    }

    public static String getSearchComboBoxTires() {
        return resourceBundle.getString(SEARCH_COMBOBOX_TIRES);
    }

    public static String getSearchComboBoxVin() {
        return resourceBundle.getString(SEARCH_COMBOBOX_VIN);
    }

    public static String getCancelCreationConfirmationDialogTitleString() {
        return resourceBundle.getString(CANCEL_CREATION_CONFIRMATION_DIALOG_TITLE);
    }

    public static String getCancelCreationConfirmationDialogHeaderString() {
        return resourceBundle.getString(CANCEL_CREATION_CONFIRMATION_DIALOG_HEADER);
    }
}
