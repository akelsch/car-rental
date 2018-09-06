package de.htwsaar.prog3.carrental.util.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for {@code components.properties}.
 * <p>
 * Provides strings that are visible to the end user.
 *
 * @author Lukas Raubuch, Jens Thewes
 */
public final class I18nComponentsUtil {
    private I18nComponentsUtil() {
    }

    // Keys
    private static final String STAGE_TITLE = "stage-title";
    private static final String DELETE_CONFIRMATION_DIALOG_HEADER = "dialog-delete-confirmation-header";
    private static final String CANCEL_CREATION_CONFIRMATION_DIALOG_TITLE = "dialog-confirmation-title";
    private static final String CANCEL_CREATION_CONFIRMATION_DIALOG_HEADER = "dialog-cancel-creation-confirmation" +
            "-header";
    private static final String APPLY_CREATION_CONFIRMATION_DIALOG_TITLE = "dialog-confirmation-title";
    private static final String APPLY_CREATION_CONFIRMATION_DIALOG_HEADER = "dialog-apply-creation-confirmation-header";
    private static final String DIALOG_CONFIRMATION_TITLE = "dialog-confirmation-title";
    private static final String DIALOG_INFORMATION_TITLE = "dialog-information-title";
    private static final String DIALOG_CONFIRMATION_DELETE_OBJECT_HEADER = "dialog-confirmation-header-delete";
    private static final String DIALOG_INFORMATION_NO_OBJECT_SELECTED_HEADER = "dialog-information-header-no-object" +
            "-selected";
    private static final String DIALOG_INFORMATION_ABOUT_HEADER = "dialog-information-header-about";
    private static final String CAR_ID_LABEL = "car-id-label";
    private static final String CAR_BRAND_LABEL = "car-brand-label";
    private static final String CAR_MODEL_LABEL = "car-model-label";
    private static final String CAR_CATEGORY_LABEL = "car-category-label";
    private static final String CAR_COLOR_LABEL = "car-color-label";
    private static final String CAR_CONSTRUCTION_YEAR_LABEL = "car-constructionYear-label";
    private static final String CAR_DRIVEN_DISTANCE_LABEL = "car-drivenDistance-label";
    private static final String CAR_GEARBOX_LABEL = "car-gearBox-label";
    private static final String CAR_HORSEPOWER_LABEL = "car-horsePower-label";
    private static final String CAR_FUEL_LABEL = "car-fuel-label";
    private static final String CAR_DOOR_COUNT_LABEL = "car-doorCount-label";
    private static final String CAR_TIRES_LABEL = "car-tires-label";
    private static final String CAR_NEXT_INSPECTION_LABEL = "car-nextInspection-label";
    private static final String CAR_VIN_LABEL = "car-vin-label";
    private static final String CAR_EQUIPMENT_LABEL = "car-equipment-label";
    private static final String CAR_DEFECTS_LABEL = "car-defects-label";
    private static final String CAR_LICENCE_NUMBER_LABEL = "car-licenceNumber-label";
    private static final String CAR_DAILY_RATE_LABEL = "car-dailyRate-label";
    private static final String CAR_PARKING_LOT_LABEL = "car-parkingLot-label";
    private static final String CUSTOMER_ID_LABEL = "customer-id-label";
    private static final String CUSTOMER_FIRST_NAME_LABEL = "customer-firstName-label";
    private static final String CUSTOMER_LAST_NAME_LABEL = "customer-lastName-label";
    private static final String CUSTOMER_EMAIL_LABEL = "customer-email-label";
    private static final String CUSTOMER_PHONE_NUMBER_LABEL = "customer-phoneNumber-label";
    private static final String CUSTOMER_DATE_OF_BIRTH_LABEL = "customer-dateOfBirth-label";
    private static final String CUSTOMER_STREET_LABEL = "customer-street-label";
    private static final String CUSTOMER_HOUSE_NUMBER_LABEL = "customer-houseNumber-label";
    private static final String CUSTOMER_CITY_LABEL = "customer-city-label";
    private static final String CUSTOMER_ZIP_CODE_LABEL = "customer-zipCode-label";
    private static final String CUSTOMER_ID_NUMBER_LABEL = "customer-idNumber-label";
    private static final String CUSTOMER_DRIVER_LICENSE_ID_LABEL = "customer-driverLicenseId-label";
    private static final String EMPLOYEE_ID_LABEL = "employee-id-label";
    private static final String EMPLOYEE_FIRST_NAME_LABEL = "employee-firstName-label";
    private static final String EMPLOYEE_LAST_NAME_LABEL = "employee-lastName-label";
    private static final String EMPLOYEE_POSITION_LABEL = "employee-position-label";
    private static final String RENTAL_ID_LABEL = "rental-id-label";
    private static final String RENTAL_BEGIN_LABEL = "rental-begin-label";
    private static final String RENTAL_CAR_LABEL = "rental-car-label";
    private static final String RENTAL_CUSTOMER_LABEL = "rental-customer-label";
    private static final String RENTAL_EMPLOYEE_LABEL = "rental-employee-label";
    private static final String RENTAL_END_LABEL = "rental-end-label";
    private static final String RENTAL_EXTRA_COSTS_LABEL = "rental-extraCosts-label";
    private static final String RENTAL_NOTE_LABEL = "rental-note-label";

    // Resource bundle
    private static ResourceBundle resourceBundle = I18nUtil.getResourceBundleComponents();

    // Values
    public static String getStageTitleString() {
        return resourceBundle.getString(STAGE_TITLE);
    }

    public static String getDeleteConfirmationDialogHeaderString() {
        return resourceBundle.getString(DELETE_CONFIRMATION_DIALOG_HEADER);
    }

    public static String getConfirmationDialogHeaderDelete() {
        return resourceBundle.getString(DIALOG_CONFIRMATION_DELETE_OBJECT_HEADER);
    }

    public static String getInformationDialogTitle() {
        return resourceBundle.getString(DIALOG_INFORMATION_TITLE);
    }

    public static String getInformationDialogHeaderNoObjectSelected() {
        return resourceBundle.getString(DIALOG_INFORMATION_NO_OBJECT_SELECTED_HEADER);
    }

    public static String getConfirmationDialogTitle() {
        return resourceBundle.getString(DIALOG_CONFIRMATION_TITLE);
    }

    public static String getCancelCreationConfirmationDialogTitleString() {
        return resourceBundle.getString(CANCEL_CREATION_CONFIRMATION_DIALOG_TITLE);
    }

    public static String getCancelCreationConfirmationDialogHeaderString() {
        return resourceBundle.getString(CANCEL_CREATION_CONFIRMATION_DIALOG_HEADER);
    }

    public static String getDialogInformationHeaderAbout() {
        return resourceBundle.getString(DIALOG_INFORMATION_ABOUT_HEADER);
    }

    public static String getApplyCreationConfirmationDialogTitleString() {
        return resourceBundle.getString(APPLY_CREATION_CONFIRMATION_DIALOG_TITLE);
    }

    public static String getApplyCreationConfirmationDialogHeaderString() {
        return resourceBundle.getString(APPLY_CREATION_CONFIRMATION_DIALOG_HEADER);
    }

    public static String getCarIdLabel() {
        return resourceBundle.getString(CAR_ID_LABEL);
    }

    public static String getCarBrandLabel() {
        return resourceBundle.getString(CAR_BRAND_LABEL);
    }

    public static String getCarModelLabel() {
        return resourceBundle.getString(CAR_MODEL_LABEL);
    }

    public static String getCarCategoryLabel() {
        return resourceBundle.getString(CAR_CATEGORY_LABEL);
    }

    public static String getCarColorLabel() {
        return resourceBundle.getString(CAR_COLOR_LABEL);
    }

    public static String getCarConstructionYearLabel() {
        return resourceBundle.getString(CAR_CONSTRUCTION_YEAR_LABEL);
    }

    public static String getCarDrivenDistanceLabel() {
        return resourceBundle.getString(CAR_DRIVEN_DISTANCE_LABEL);
    }

    public static String getCarGearboxLabel() {
        return resourceBundle.getString(CAR_GEARBOX_LABEL);
    }

    public static String getCarHorsepowerLabel() {
        return resourceBundle.getString(CAR_HORSEPOWER_LABEL);
    }

    public static String getCarFuelLabel() {
        return resourceBundle.getString(CAR_FUEL_LABEL);
    }

    public static String getCarDoorCountLabel() {
        return resourceBundle.getString(CAR_DOOR_COUNT_LABEL);
    }

    public static String getCarTiresLabel() {
        return resourceBundle.getString(CAR_TIRES_LABEL);
    }

    public static String getCarNextInspectionLabel() {
        return resourceBundle.getString(CAR_NEXT_INSPECTION_LABEL);
    }

    public static String getCarVinLabel() {
        return resourceBundle.getString(CAR_VIN_LABEL);
    }

    public static String getCarEquipmentLabel() {
        return resourceBundle.getString(CAR_EQUIPMENT_LABEL);
    }

    public static String getCarDefectsLabel() {
        return resourceBundle.getString(CAR_DEFECTS_LABEL);
    }

    public static String getCarLicenceNumberLabel() {
        return resourceBundle.getString(CAR_LICENCE_NUMBER_LABEL);
    }

    public static String getCarDailyRateLabel() {
        return resourceBundle.getString(CAR_DAILY_RATE_LABEL);
    }

    public static String getCarParkingLotLabel() {
        return resourceBundle.getString(CAR_PARKING_LOT_LABEL);
    }

    public static String getCustomerIdLabel() {
        return resourceBundle.getString(CUSTOMER_ID_LABEL);
    }

    public static String getCustomerFirstNameLabel() {
        return resourceBundle.getString(CUSTOMER_FIRST_NAME_LABEL);
    }

    public static String getCustomerLastNameLabel() {
        return resourceBundle.getString(CUSTOMER_LAST_NAME_LABEL);
    }

    public static String getCustomerEmailLabel() {
        return resourceBundle.getString(CUSTOMER_EMAIL_LABEL);
    }

    public static String getCustomerPhoneNumberLabel() {
        return resourceBundle.getString(CUSTOMER_PHONE_NUMBER_LABEL);
    }

    public static String getCustomerDateOfBirthLabel() {
        return resourceBundle.getString(CUSTOMER_DATE_OF_BIRTH_LABEL);
    }

    public static String getCustomerStreetLabel() {
        return resourceBundle.getString(CUSTOMER_STREET_LABEL);
    }

    public static String getCustomerHouseNumberLabel() {
        return resourceBundle.getString(CUSTOMER_HOUSE_NUMBER_LABEL);
    }

    public static String getCustomerCityLabel() {
        return resourceBundle.getString(CUSTOMER_CITY_LABEL);
    }

    public static String getCustomerZipCodeLabel() {
        return resourceBundle.getString(CUSTOMER_ZIP_CODE_LABEL);
    }

    public static String getCustomerIdNumberLabel() {
        return resourceBundle.getString(CUSTOMER_ID_NUMBER_LABEL);
    }

    public static String getCustomerDriverLicenseIdLabel() {
        return resourceBundle.getString(CUSTOMER_DRIVER_LICENSE_ID_LABEL);
    }

    public static String getEmployeeIdLabel() {
        return resourceBundle.getString(EMPLOYEE_ID_LABEL);
    }

    public static String getEmployeeFirstNameLabel() {
        return resourceBundle.getString(EMPLOYEE_FIRST_NAME_LABEL);
    }

    public static String getEmployeeLastNameLabel() {
        return resourceBundle.getString(EMPLOYEE_LAST_NAME_LABEL);
    }

    public static String getEmployeePositionLabel() {
        return resourceBundle.getString(EMPLOYEE_POSITION_LABEL);
    }

    public static String getRentalIdLabel() {
        return resourceBundle.getString(RENTAL_ID_LABEL);
    }

    public static String getRentalBeginLabel() {
        return resourceBundle.getString(RENTAL_BEGIN_LABEL);
    }

    public static String getRentalCarLabel() {
        return resourceBundle.getString(RENTAL_CAR_LABEL);
    }

    public static String getRentalCustomerLabel() {
        return resourceBundle.getString(RENTAL_CUSTOMER_LABEL);
    }

    public static String getRentalEmployeeLabel() {
        return resourceBundle.getString(RENTAL_EMPLOYEE_LABEL);
    }

    public static String getRentalEndLabel() {
        return resourceBundle.getString(RENTAL_END_LABEL);
    }

    public static String getRentalExtraCostsLabel() {
        return resourceBundle.getString(RENTAL_EXTRA_COSTS_LABEL);
    }

    public static String getRentalNoteLabel() {
        return resourceBundle.getString(RENTAL_NOTE_LABEL);
    }
}
