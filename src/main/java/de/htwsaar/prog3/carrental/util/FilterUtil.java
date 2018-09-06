package de.htwsaar.prog3.carrental.util;

import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;

/**
 * This class is used for filtering
 *
 * @author Julian Quint
 */
public final class FilterUtil {
    private static final String CAR_ID = I18nComponentsUtil.getCarIdLabel();
    private static final String CAR_BRAND = I18nComponentsUtil.getCarBrandLabel();
    private static final String CAR_HORSEPOWER = I18nComponentsUtil.getCarHorsepowerLabel();
    private static final String CAR_DAILY_RATE = I18nComponentsUtil.getCarDailyRateLabel();
    private static final String CAR_DOOR_COUNT = I18nComponentsUtil.getCarDoorCountLabel();
    private static final String CAR_DRIVEN_DISTANCE = I18nComponentsUtil.getCarDrivenDistanceLabel();
    private static final String CAR_CATEGORY = I18nComponentsUtil.getCarCategoryLabel();
    private static final String CAR_COLOR = I18nComponentsUtil.getCarColorLabel();
    private static final String CAR_CONSTRUCTION_YEAR = I18nComponentsUtil.getCarConstructionYearLabel();
    private static final String CAR_DEFECTS = I18nComponentsUtil.getCarDefectsLabel();
    private static final String CAR_FUEL = I18nComponentsUtil.getCarFuelLabel();
    private static final String CAR_GEARBOX = I18nComponentsUtil.getCarGearboxLabel();
    private static final String CAR_LICENCE_NUMBER = I18nComponentsUtil.getCarLicenceNumberLabel();
    private static final String CAR_MODEL = I18nComponentsUtil.getCarModelLabel();
    private static final String CAR_NEXT_INSPECTION = I18nComponentsUtil.getCarNextInspectionLabel();
    private static final String CAR_PARKING_LOT = I18nComponentsUtil.getCarParkingLotLabel();
    private static final String CAR_TIRES = I18nComponentsUtil.getCarTiresLabel();
    private static final String CAR_VIN = I18nComponentsUtil.getCarVinLabel();
    private static final String CUSTOMER_ID = I18nComponentsUtil.getCustomerIdLabel();
    private static final String CUSTOMER_FIRST_NAME = I18nComponentsUtil.getCustomerFirstNameLabel();
    private static final String CUSTOMER_LAST_NAME = I18nComponentsUtil.getCustomerLastNameLabel();
    private static final String CUSTOMER_EMAIL = I18nComponentsUtil.getCustomerEmailLabel();
    private static final String CUSTOMER_PHONE_NUMBER = I18nComponentsUtil.getCustomerPhoneNumberLabel();
    private static final String CUSTOMER_DATE_OF_BIRTH = I18nComponentsUtil.getCustomerDateOfBirthLabel();
    private static final String CUSTOMER_STREET = I18nComponentsUtil.getCustomerStreetLabel();
    private static final String CUSTOMER_HOUSE_NUMBER = I18nComponentsUtil.getCustomerHouseNumberLabel();
    private static final String CUSTOMER_CITY = I18nComponentsUtil.getCustomerCityLabel();
    private static final String CUSTOMER_ZIP_CODE = I18nComponentsUtil.getCustomerZipCodeLabel();
    private static final String CUSTOMER_ID_NUMBER = I18nComponentsUtil.getCustomerIdNumberLabel();
    private static final String CUSTOMER_DRIVER_LICENSE_ID = I18nComponentsUtil.getCustomerDriverLicenseIdLabel();
    private static final String EMPLOYEE_ID = I18nComponentsUtil.getEmployeeIdLabel();
    private static final String EMPLOYEE_FIRST_NAME = I18nComponentsUtil.getEmployeeFirstNameLabel();
    private static final String EMPLOYEE_LAST_NAME = I18nComponentsUtil.getEmployeeLastNameLabel();
    private static final String EMPLOYEE_POSITION = I18nComponentsUtil.getEmployeePositionLabel();
    private static final String RENTAL_ID = I18nComponentsUtil.getRentalIdLabel();
    private static final String RENTAL_BEGIN = I18nComponentsUtil.getRentalBeginLabel();
    private static final String RENTAL_CAR = I18nComponentsUtil.getRentalCarLabel();
    private static final String RENTAL_CUSTOMER = I18nComponentsUtil.getRentalCustomerLabel();
    private static final String RENTAL_EMPLOYEE = I18nComponentsUtil.getRentalEmployeeLabel();
    private static final String RENTAL_END = I18nComponentsUtil.getRentalEndLabel();
    private static final String RENTAL_EXTRA_COSTS = I18nComponentsUtil.getRentalExtraCostsLabel();
    private static final String RENTAL_NOTE = I18nComponentsUtil.getRentalNoteLabel();

    private FilterUtil(){
    }

    /**
     * Converts a frontend field for filtering with Hibernate
     *
     * @param field field from frontend
     * @return the converted field
     */
    public static String convertField(String field){
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
        } else {
            return field;
        }
    }

    /**
     * Converts a value for filtering with hibernate
     *
     * @param value value given by user
     * @param comparator determines whether % is added or not to the value
     * @return the converted value
     */
    public static String convertValue(String value, String comparator){
        if(comparator.equals("LIKE")){
            return "'%" + value + "%'";
        }
        return "'" + value + "'";
    }
}
