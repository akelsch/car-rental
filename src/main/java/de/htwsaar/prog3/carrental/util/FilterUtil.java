package de.htwsaar.prog3.carrental.util;

/**
 * This class is used to convert variables for
 * {@link de.htwsaar.prog3.carrental.service.GenericService#filter(String, String, String)}.
 *
 * @author Julian Quint
 */
public final class FilterUtil {
    private static final String CAR_ID = I18nUtils.getCarIdLabel();
    private static final String CAR_BRAND = I18nUtils.getCarBrandLabel();
    private static final String CAR_HORSEPOWER = I18nUtils.getCarHorsepowerLabel();
    private static final String CAR_DAILY_RATE = I18nUtils.getCarDailyRateLabel();
    private static final String CAR_DOOR_COUNT = I18nUtils.getCarDoorCountLabel();
    private static final String CAR_DRIVEN_DISTANCE = I18nUtils.getCarDrivenDistanceLabel();
    private static final String CAR_CATEGORY = I18nUtils.getCarCategoryLabel();
    private static final String CAR_COLOR = I18nUtils.getCarColorLabel();
    private static final String CAR_CONSTRUCTION_YEAR = I18nUtils.getCarConstructionYearLabel();
    private static final String CAR_DEFECTS = I18nUtils.getCarDefectsLabel();
    private static final String CAR_FUEL = I18nUtils.getCarFuelLabel();
    private static final String CAR_GEARBOX = I18nUtils.getCarGearboxLabel();
    private static final String CAR_LICENCE_NUMBER = I18nUtils.getCarLicenceNumberLabel();
    private static final String CAR_MODEL = I18nUtils.getCarModelLabel();
    private static final String CAR_NEXT_INSPECTION = I18nUtils.getCarNextInspectionLabel();
    private static final String CAR_PARKING_LOT = I18nUtils.getCarParkingLotLabel();
    private static final String CAR_TIRES = I18nUtils.getCarTiresLabel();
    private static final String CAR_VIN = I18nUtils.getCarVinLabel();
    private static final String CUSTOMER_ID = I18nUtils.getCustomerIdLabel();
    private static final String CUSTOMER_FIRST_NAME = I18nUtils.getCustomerFirstNameLabel();
    private static final String CUSTOMER_LAST_NAME = I18nUtils.getCustomerLastNameLabel();
    private static final String CUSTOMER_EMAIL = I18nUtils.getCustomerEmailLabel();
    private static final String CUSTOMER_PHONE_NUMBER = I18nUtils.getCustomerPhoneNumberLabel();
    private static final String CUSTOMER_DATE_OF_BIRTH = I18nUtils.getCustomerDateOfBirthLabel();
    private static final String CUSTOMER_STREET = I18nUtils.getCustomerStreetLabel();
    private static final String CUSTOMER_HOUSE_NUMBER = I18nUtils.getCustomerHouseNumberLabel();
    private static final String CUSTOMER_CITY = I18nUtils.getCustomerCityLabel();
    private static final String CUSTOMER_ZIP_CODE = I18nUtils.getCustomerZipCodeLabel();
    private static final String CUSTOMER_ID_NUMBER = I18nUtils.getCustomerIdNumberLabel();
    private static final String CUSTOMER_DRIVER_LICENSE_ID = I18nUtils.getCustomerDriverLicenseIdLabel();
    private static final String EMPLOYEE_ID = I18nUtils.getEmployeeIdLabel();
    private static final String EMPLOYEE_FIRST_NAME = I18nUtils.getEmployeeFirstNameLabel();
    private static final String EMPLOYEE_LAST_NAME = I18nUtils.getEmployeeLastNameLabel();
    private static final String EMPLOYEE_POSITION = I18nUtils.getEmployeePositionLabel();
    private static final String RENTAL_ID = I18nUtils.getRentalIdLabel();
    private static final String RENTAL_BEGIN = I18nUtils.getRentalBeginLabel();
    private static final String RENTAL_CAR = I18nUtils.getRentalCarLabel();
    private static final String RENTAL_CUSTOMER = I18nUtils.getRentalCustomerLabel();
    private static final String RENTAL_EMPLOYEE = I18nUtils.getRentalEmployeeLabel();
    private static final String RENTAL_END = I18nUtils.getRentalEndLabel();
    private static final String RENTAL_EXTRA_COSTS = I18nUtils.getRentalExtraCostsLabel();
    private static final String RENTAL_NOTE = I18nUtils.getRentalNoteLabel();
    private static final String LIKE = I18nUtils.getSearchComboboxLike();

    private FilterUtil() {
    }

    /**
     * Converts an entity field label for use in a JPQL query.
     *
     * @param field field label from frontend
     * @return the converted field
     */
    public static String convertField(String field) {
        if (CAR_ID.equals(field) || CUSTOMER_ID.equals(field) || EMPLOYEE_ID.equals(field) || RENTAL_ID.equals(field)) {
            return "id";
        } else if (CAR_BRAND.equals(field)) {
            return "brand";
        } else if (CAR_HORSEPOWER.equals(field)) {
            return "horsepower";
        } else if (CAR_DAILY_RATE.equals(field)) {
            return "dailyRate";
        } else if (CAR_DOOR_COUNT.equals(field)) {
            return "doorCount";
        } else if (CAR_DRIVEN_DISTANCE.equals(field)) {
            return "drivenDistance";
        } else if (CAR_CATEGORY.equals(field)) {
            return "category";
        } else if (CAR_COLOR.equals(field)) {
            return "color";
        } else if (CAR_CONSTRUCTION_YEAR.equals(field)) {
            return "constructionYear";
        } else if (CAR_DEFECTS.equals(field)) {
            return "defects";
        } else if (CAR_FUEL.equals(field)) {
            return "fuel";
        } else if (CAR_GEARBOX.equals(field)) {
            return "gearbox";
        } else if (CAR_LICENCE_NUMBER.equals(field)) {
            return "licenseNumber";
        } else if (CAR_MODEL.equals(field)) {
            return "model";
        } else if (CAR_NEXT_INSPECTION.equals(field)) {
            return "nextInspection";
        } else if (CAR_PARKING_LOT.equals(field)) {
            return "parkingLot";
        } else if (CAR_TIRES.equals(field)) {
            return "tires";
        } else if (CAR_VIN.equals(field)) {
            return "vin";
        } else if (CUSTOMER_DATE_OF_BIRTH.equals(field)) {
            return "dateOfBirth";
        } else if (CUSTOMER_DRIVER_LICENSE_ID.equals(field)) {
            return "driverLicenseId";
        } else if (CUSTOMER_EMAIL.equals(field)) {
            return "emailAddress";
        } else if (CUSTOMER_FIRST_NAME.equals(field) || EMPLOYEE_FIRST_NAME.equals(field)) {
            return "firstName";
        } else if (CUSTOMER_LAST_NAME.equals(field) || EMPLOYEE_LAST_NAME.equals(field)) {
            return "lastName";
        } else if (CUSTOMER_PHONE_NUMBER.equals(field)) {
            return "phoneNumber";
        } else if (CUSTOMER_HOUSE_NUMBER.equals(field)) {
            return "houseNumber";
        } else if (CUSTOMER_STREET.equals(field)) {
            return "street";
        } else if (CUSTOMER_CITY.equals(field)) {
            return "city";
        } else if (CUSTOMER_ID_NUMBER.equals(field)) {
            return "idNumber";
        } else if (CUSTOMER_ZIP_CODE.equals(field)) {
            return "zipCode";
        } else if (EMPLOYEE_POSITION.equals(field)) {
            return "position";
        } else if (RENTAL_BEGIN.equals(field)) {
            return "begin";
        } else if (RENTAL_CAR.equals(field)) {
            return "car";
        } else if (RENTAL_CUSTOMER.equals(field)) {
            return "customer";
        } else if (RENTAL_EMPLOYEE.equals(field)) {
            return "employee";
        } else if (RENTAL_END.equals(field)) {
            return "end";
        } else if (RENTAL_EXTRA_COSTS.equals(field)) {
            return "extraCosts";
        } else if (RENTAL_NOTE.equals(field)) {
            return "note";
        }

        return field;
    }

    /**
     * Converts a text field value for use in a JPQL query.
     *
     * @param value      text field value from frontend
     * @param comparator determines whether or not % is added to the value
     * @return the converted value
     */
    public static String convertValue(String value, String comparator) {
        if (LIKE.equals(comparator)) {
            return "'%" + value + "%'";
        }

        return "'" + value + "'";
    }
}
