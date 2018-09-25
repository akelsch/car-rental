package de.htwsaar.prog3.carrental.util.i18n;

import java.util.ResourceBundle;

/**
 * Internationalization utility class for {@code components.properties}.
 * <p>
 * Provides strings that are visible to the end user.
 *
 * @author Lukas Raubuch, Jens Thewes, Julian Quint, Arthur Kelsch
 */
public final class I18nComponentsUtil {
    // Keys
    private static final String CAR_BRAND_LABEL = "car-brand-label";
    private static final String CAR_CATEGORY_LABEL = "car-category-label";
    private static final String CAR_COLOR_LABEL = "car-color-label";
    private static final String CAR_CONSTRUCTION_YEAR_LABEL = "car-construction-year-label";
    private static final String CAR_DAILY_RATE_LABEL = "car-daily-rate-label";
    private static final String CAR_DEFECTS_LABEL = "car-defects-label";
    private static final String CAR_DOOR_COUNT_LABEL = "car-door-count-label";
    private static final String CAR_DRIVEN_DISTANCE_LABEL = "car-driven-distance-label";
    private static final String CAR_FUEL_LABEL = "car-fuel-label";
    private static final String CAR_GEARBOX_LABEL = "car-gearbox-label";
    private static final String CAR_HORSEPOWER_LABEL = "car-horsepower-label";
    private static final String CAR_ID_LABEL = "car-id-label";
    private static final String CAR_LICENCE_NUMBER_LABEL = "car-licence-number-label";
    private static final String CAR_MODEL_LABEL = "car-model-label";
    private static final String CAR_NEXT_INSPECTION_LABEL = "car-next-inspection-label";
    private static final String CAR_NO_VALID_BRAND = "car-no-valid-brand";
    private static final String CAR_NO_VALID_CATEGORY = "car-no-valid-category";
    private static final String CAR_NO_VALID_COLOR = "car-no-valid-color";
    private static final String CAR_NO_VALID_CONSTRUCTION_YEAR = "car-no-valid-construction-year";
    private static final String CAR_NO_VALID_DAILY_RATE = "car-no-valid-daily-rate";
    private static final String CAR_NO_VALID_DOOR_COUNT = "car-no-valid-door-count";
    private static final String CAR_NO_VALID_DRIVEN_DISTANCE = "car-no-valid-driven-distance";
    private static final String CAR_NO_VALID_FUEL = "car-no-valid-fuel";
    private static final String CAR_NO_VALID_GEARBOX = "car-no-valid-gearbox";
    private static final String CAR_NO_VALID_HORSEPOWER = "car-no-valid-horsepower";
    private static final String CAR_NO_VALID_LICENCE_NUMBER = "car-no-valid-licence-number";
    private static final String CAR_NO_VALID_LICENCE_NUMBER_DUPLICATE = "car-no-valid-licence-number-duplicate";
    private static final String CAR_NO_VALID_MODEL = "car-no-valid-model";
    private static final String CAR_NO_VALID_NEXT_INSPECTION = "car-no-valid-next-inspection";
    private static final String CAR_NO_VALID_PARKING_LOT = "car-no-valid-parking-lot";
    private static final String CAR_NO_VALID_PARKING_LOT_DUPLICATE = "car-no-valid-parking-lot-duplicate";
    private static final String CAR_NO_VALID_TIRES = "car-no-valid-tires";
    private static final String CAR_NO_VALID_VIN = "car-no-valid-vin";
    private static final String CAR_NO_VALID_VIN_DUPLICATE = "car-no-valid-vin-duplicate";
    private static final String CAR_PARKING_LOT_LABEL = "car-parking-lot-label";
    private static final String CAR_TIRES_LABEL = "car-tires-label";
    private static final String CAR_VIN_LABEL = "car-vin-label";
    private static final String CUSTOMER_CITY_LABEL = "customer-city-label";
    private static final String CUSTOMER_DATE_OF_BIRTH_LABEL = "customer-date-of-birth-label";
    private static final String CUSTOMER_DRIVER_LICENSE_ID_LABEL = "customer-driver-license-id-label";
    private static final String CUSTOMER_EMAIL_LABEL = "customer-email-label";
    private static final String CUSTOMER_FIRST_NAME_LABEL = "customer-first-name-label";
    private static final String CUSTOMER_HOUSE_NUMBER_LABEL = "customer-house-number-label";
    private static final String CUSTOMER_ID_LABEL = "customer-id-label";
    private static final String CUSTOMER_ID_NUMBER_LABEL = "customer-id-number-label";
    private static final String CUSTOMER_LAST_NAME_LABEL = "customer-last-name-label";
    private static final String CUSTOMER_NO_VALID_CITY_NAME = "customer-no-valid-city-name";
    private static final String CUSTOMER_NO_VALID_DATE_OF_BIRTH = "customer-no-valid-date-of-birth";
    private static final String CUSTOMER_NO_VALID_DRIVER_LICENCE = "customer-no-valid-driver-license";
    private static final String CUSTOMER_NO_VALID_DRIVER_LICENCE_DUPLICATE = "customer-no-valid-driver-license-duplicate";
    private static final String CUSTOMER_NO_VALID_EMAIL_ADDRESS = "customer-no-valid-email-address";
    private static final String CUSTOMER_NO_VALID_FIRST_NAME = "customer-no-valid-first-name";
    private static final String CUSTOMER_NO_VALID_HOUSE_NUMBER = "customer-no-valid-house-number";
    private static final String CUSTOMER_NO_VALID_ID_NUMBER = "customer-no-valid-id-number";
    private static final String CUSTOMER_NO_VALID_ID_NUMBER_DUPLICATE = "customer-no-valid-id-number-duplicate";
    private static final String CUSTOMER_NO_VALID_LAST_NAME = "customer-no-valid-last-name";
    private static final String CUSTOMER_NO_VALID_PHONE_NUMBER = "customer-no-valid-phone-number";
    private static final String CUSTOMER_NO_VALID_STREET_NAME = "customer-no-valid-street-name";
    private static final String CUSTOMER_NO_VALID_ZIP_CODE = "customer-no-valid-zip-code";
    private static final String CUSTOMER_PHONE_NUMBER_LABEL = "customer-phone-number-label";
    private static final String CUSTOMER_STREET_LABEL = "customer-street-label";
    private static final String CUSTOMER_ZIP_CODE_LABEL = "customer-zip-code-label";
    private static final String DIALOG_ABOUT_TEXT = "dialog-about-text";
    private static final String DIALOG_CANCEL_CONFIRMATION_TEXT = "dialog-cancel-confirmation-text";
    private static final String DIALOG_CONFIRMATION_TITLE = "dialog-confirmation-title";
    private static final String DIALOG_DELETE_CONFIRMATION_TEXT = "dialog-delete-confirmation-text";
    private static final String DIALOG_DELETE_NO_SELECTION_TEXT = "dialog-delete-no-selection-text";
    private static final String DIALOG_ERROR_INVALID_FIELDS_TEXT = "dialog-invalid-fields-text";
    private static final String DIALOG_ERROR_INVALID_FIELDS_TITLE = "dialog-invalid-fields-title";
    private static final String DIALOG_INFORMATION_TITLE = "dialog-information-title";
    private static final String DIALOG_INVALID_NUMBER_TEXT = "dialog-invalid-number-text";
    private static final String EMPLOYEE_FIRST_NAME_LABEL = "employee-first-name-label";
    private static final String EMPLOYEE_ID_LABEL = "employee-id-label";
    private static final String EMPLOYEE_LAST_NAME_LABEL = "employee-last-name-label";
    private static final String EMPLOYEE_NO_VALID_FIRST_NAME = "employee-no-valid-first-name";
    private static final String EMPLOYEE_NO_VALID_LAST_NAME = "employee-no-valid-last-name";
    private static final String EMPLOYEE_NO_VALID_POSITION = "employee-no-valid-position";
    private static final String EMPLOYEE_POSITION_LABEL = "employee-position-label";
    private static final String RENTAL_BEGIN_LABEL = "rental-begin-label";
    private static final String RENTAL_CAR_LABEL = "rental-car-label";
    private static final String RENTAL_CURRENCY_LABEL = "rental-currency-label";
    private static final String RENTAL_CUSTOMER_LABEL = "rental-customer-label";
    private static final String RENTAL_DURATION_LABEL = "rental-duration-label";
    private static final String RENTAL_EMPLOYEE_LABEL = "rental-employee-label";
    private static final String RENTAL_END_LABEL = "rental-end-label";
    private static final String RENTAL_EXTRA_COSTS_LABEL = "rental-extra-costs-label";
    private static final String RENTAL_ID_LABEL = "rental-id-label";
    private static final String RENTAL_NOTE_LABEL = "rental-note-label";
    private static final String RENTAL_NO_VALID_DURATION = "rental-no-valid-duration";
    private static final String RENTAL_NO_VALID_EMPLOYEE = "rental-no-valid-employee";
    private static final String RENTAL_NO_VALID_EXTRA_COSTS = "rental-no-valid-extra-costs";
    private static final String SEARCH_COMBOBOX_LIKE = "search-combobox-like";
    private static final String STAGE_TITLE = "stage-title";

    // Resource bundle
    private static ResourceBundle resourceBundle = I18nUtil.getResourceBundleComponents();

    private I18nComponentsUtil() {
    }

    // Values
    public static String getCarBrandLabel() {
        return resourceBundle.getString(CAR_BRAND_LABEL);
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

    public static String getCarDailyRateLabel() {
        return resourceBundle.getString(CAR_DAILY_RATE_LABEL);
    }

    public static String getCarDefectsLabel() {
        return resourceBundle.getString(CAR_DEFECTS_LABEL);
    }

    public static String getCarDoorCountLabel() {
        return resourceBundle.getString(CAR_DOOR_COUNT_LABEL);
    }

    public static String getCarDrivenDistanceLabel() {
        return resourceBundle.getString(CAR_DRIVEN_DISTANCE_LABEL);
    }

    public static String getCarFuelLabel() {
        return resourceBundle.getString(CAR_FUEL_LABEL);
    }

    public static String getCarGearboxLabel() {
        return resourceBundle.getString(CAR_GEARBOX_LABEL);
    }

    public static String getCarHorsepowerLabel() {
        return resourceBundle.getString(CAR_HORSEPOWER_LABEL);
    }

    public static String getCarIdLabel() {
        return resourceBundle.getString(CAR_ID_LABEL);
    }

    public static String getCarLicenceNumberLabel() {
        return resourceBundle.getString(CAR_LICENCE_NUMBER_LABEL);
    }

    public static String getCarModelLabel() {
        return resourceBundle.getString(CAR_MODEL_LABEL);
    }

    public static String getCarNextInspectionLabel() {
        return resourceBundle.getString(CAR_NEXT_INSPECTION_LABEL);
    }

    public static String getCarNoValidBrand() {
        return resourceBundle.getString(CAR_NO_VALID_BRAND);
    }

    public static String getCarNoValidCategory() {
        return resourceBundle.getString(CAR_NO_VALID_CATEGORY);
    }

    public static String getCarNoValidColor() {
        return resourceBundle.getString(CAR_NO_VALID_COLOR);
    }

    public static String getCarNoValidConstructionYear() {
        return resourceBundle.getString(CAR_NO_VALID_CONSTRUCTION_YEAR);
    }

    public static String getCarNoValidDailyRate() {
        return resourceBundle.getString(CAR_NO_VALID_DAILY_RATE);
    }

    public static String getCarNoValidDoorCount() {
        return resourceBundle.getString(CAR_NO_VALID_DOOR_COUNT);
    }

    public static String getCarNoValidDrivenDistance() {
        return resourceBundle.getString(CAR_NO_VALID_DRIVEN_DISTANCE);
    }

    public static String getCarNoValidFuel() {
        return resourceBundle.getString(CAR_NO_VALID_FUEL);
    }

    public static String getCarNoValidGearbox() {
        return resourceBundle.getString(CAR_NO_VALID_GEARBOX);
    }

    public static String getCarNoValidHorsepower() {
        return resourceBundle.getString(CAR_NO_VALID_HORSEPOWER);
    }

    public static String getCarNoValidLicenceNumber() {
        return resourceBundle.getString(CAR_NO_VALID_LICENCE_NUMBER);
    }

    public static String getCarNoValidLicenceNumberDuplicate() {
        return resourceBundle.getString(CAR_NO_VALID_LICENCE_NUMBER_DUPLICATE);
    }

    public static String getCarNoValidModel() {
        return resourceBundle.getString(CAR_NO_VALID_MODEL);
    }

    public static String getCarNoValidNextInspection() {
        return resourceBundle.getString(CAR_NO_VALID_NEXT_INSPECTION);
    }

    public static String getCarNoValidParkingLot() {
        return resourceBundle.getString(CAR_NO_VALID_PARKING_LOT);
    }

    public static String getCarNoValidParkingLotDuplicate() {
        return resourceBundle.getString(CAR_NO_VALID_PARKING_LOT_DUPLICATE);
    }

    public static String getCarNoValidTires() {
        return resourceBundle.getString(CAR_NO_VALID_TIRES);
    }

    public static String getCarNoValidVin() {
        return resourceBundle.getString(CAR_NO_VALID_VIN);
    }

    public static String getCarNoValidVinDuplicate() {
        return resourceBundle.getString(CAR_NO_VALID_VIN_DUPLICATE);
    }

    public static String getCarParkingLotLabel() {
        return resourceBundle.getString(CAR_PARKING_LOT_LABEL);
    }

    public static String getCarTiresLabel() {
        return resourceBundle.getString(CAR_TIRES_LABEL);
    }

    public static String getCarVinLabel() {
        return resourceBundle.getString(CAR_VIN_LABEL);
    }

    public static String getCustomerCityLabel() {
        return resourceBundle.getString(CUSTOMER_CITY_LABEL);
    }

    public static String getCustomerDateOfBirthLabel() {
        return resourceBundle.getString(CUSTOMER_DATE_OF_BIRTH_LABEL);
    }

    public static String getCustomerDriverLicenseIdLabel() {
        return resourceBundle.getString(CUSTOMER_DRIVER_LICENSE_ID_LABEL);
    }

    public static String getCustomerEmailLabel() {
        return resourceBundle.getString(CUSTOMER_EMAIL_LABEL);
    }

    public static String getCustomerFirstNameLabel() {
        return resourceBundle.getString(CUSTOMER_FIRST_NAME_LABEL);
    }

    public static String getCustomerHouseNumberLabel() {
        return resourceBundle.getString(CUSTOMER_HOUSE_NUMBER_LABEL);
    }

    public static String getCustomerIdLabel() {
        return resourceBundle.getString(CUSTOMER_ID_LABEL);
    }

    public static String getCustomerIdNumberLabel() {
        return resourceBundle.getString(CUSTOMER_ID_NUMBER_LABEL);
    }

    public static String getCustomerLastNameLabel() {
        return resourceBundle.getString(CUSTOMER_LAST_NAME_LABEL);
    }

    public static String getCustomerNoValidCityName() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_CITY_NAME);
    }

    public static String getCustomerNoValidDateOfBirth() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_DATE_OF_BIRTH);
    }

    public static String getCustomerNoValidDriverLicence() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_DRIVER_LICENCE);
    }

    public static String getCustomerNoValidDriverLicenceDuplicate() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_DRIVER_LICENCE_DUPLICATE);
    }

    public static String getCustomerNoValidEmailAddress() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_EMAIL_ADDRESS);
    }

    public static String getCustomerNoValidFirstName() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_FIRST_NAME);
    }

    public static String getCustomerNoValidHouseNumber() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_HOUSE_NUMBER);
    }

    public static String getCustomerNoValidIdNumber() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_ID_NUMBER);
    }

    public static String getCustomerNoValidIdNumberDuplicate() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_ID_NUMBER_DUPLICATE);
    }

    public static String getCustomerNoValidLastName() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_LAST_NAME);
    }

    public static String getCustomerNoValidPhoneNumber() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_PHONE_NUMBER);
    }

    public static String getCustomerNoValidStreetName() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_STREET_NAME);
    }

    public static String getCustomerNoValidZipCode() {
        return resourceBundle.getString(CUSTOMER_NO_VALID_ZIP_CODE);
    }

    public static String getCustomerPhoneNumberLabel() {
        return resourceBundle.getString(CUSTOMER_PHONE_NUMBER_LABEL);
    }

    public static String getCustomerStreetLabel() {
        return resourceBundle.getString(CUSTOMER_STREET_LABEL);
    }

    public static String getCustomerZipCodeLabel() {
        return resourceBundle.getString(CUSTOMER_ZIP_CODE_LABEL);
    }

    public static String getDialogAboutText() {
        return resourceBundle.getString(DIALOG_ABOUT_TEXT);
    }

    public static String getDialogCancelConfirmationText() {
        return resourceBundle.getString(DIALOG_CANCEL_CONFIRMATION_TEXT);
    }

    public static String getDialogConfirmationTitle() {
        return resourceBundle.getString(DIALOG_CONFIRMATION_TITLE);
    }

    public static String getDialogDeleteConfirmationText() {
        return resourceBundle.getString(DIALOG_DELETE_CONFIRMATION_TEXT);
    }

    public static String getDialogDeleteNoSelectionText() {
        return resourceBundle.getString(DIALOG_DELETE_NO_SELECTION_TEXT);
    }

    public static String getDialogErrorInvalidFieldsText() {
        return resourceBundle.getString(DIALOG_ERROR_INVALID_FIELDS_TEXT);
    }

    public static String getDialogErrorInvalidFieldsTitle() {
        return resourceBundle.getString(DIALOG_ERROR_INVALID_FIELDS_TITLE);
    }

    public static String getDialogInformationTitle() {
        return resourceBundle.getString(DIALOG_INFORMATION_TITLE);
    }

    public static String getDialogInvalidNumberText() {
        return resourceBundle.getString(DIALOG_INVALID_NUMBER_TEXT);
    }

    public static String getEmployeeFirstNameLabel() {
        return resourceBundle.getString(EMPLOYEE_FIRST_NAME_LABEL);
    }

    public static String getEmployeeIdLabel() {
        return resourceBundle.getString(EMPLOYEE_ID_LABEL);
    }

    public static String getEmployeeLastNameLabel() {
        return resourceBundle.getString(EMPLOYEE_LAST_NAME_LABEL);
    }

    public static String getEmployeeNoValidFirstName() {
        return resourceBundle.getString(EMPLOYEE_NO_VALID_FIRST_NAME);
    }

    public static String getEmployeeNoValidLastName() {
        return resourceBundle.getString(EMPLOYEE_NO_VALID_LAST_NAME);
    }

    public static String getEmployeeNoValidPosition() {
        return resourceBundle.getString(EMPLOYEE_NO_VALID_POSITION);
    }

    public static String getEmployeePositionLabel() {
        return resourceBundle.getString(EMPLOYEE_POSITION_LABEL);
    }

    public static String getRentalBeginLabel() {
        return resourceBundle.getString(RENTAL_BEGIN_LABEL);
    }

    public static String getRentalCarLabel() {
        return resourceBundle.getString(RENTAL_CAR_LABEL);
    }

    public static String getRentalCurrencyLabel() {
        return resourceBundle.getString(RENTAL_CURRENCY_LABEL);
    }

    public static String getRentalCustomerLabel() {
        return resourceBundle.getString(RENTAL_CUSTOMER_LABEL);
    }

    public static String getRentalDurationLabel() {
        return resourceBundle.getString(RENTAL_DURATION_LABEL);
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

    public static String getRentalIdLabel() {
        return resourceBundle.getString(RENTAL_ID_LABEL);
    }

    public static String getRentalNoValidDuration() {
        return resourceBundle.getString(RENTAL_NO_VALID_DURATION);
    }

    public static String getRentalNoValidEmployee() {
        return resourceBundle.getString(RENTAL_NO_VALID_EMPLOYEE);
    }

    public static String getRentalNoValidExtraCosts() {
        return resourceBundle.getString(RENTAL_NO_VALID_EXTRA_COSTS);
    }

    public static String getRentalNoteLabel() {
        return resourceBundle.getString(RENTAL_NOTE_LABEL);
    }

    public static String getSearchComboboxLike() {
        return resourceBundle.getString(SEARCH_COMBOBOX_LIKE);
    }

    public static String getStageTitle() {
        return resourceBundle.getString(STAGE_TITLE);
    }
}
