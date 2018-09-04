package de.htwsaar.prog3.carrental.util;

public final class FilterUtil {
    private FilterUtil(){
    }

    public static String convertField(String field){
        switch (field){
            case "ID":
                return "id";
            case "Brand":
                return "brand";
            case "Horsepower [PS]":
                return "horsepower";
            case "Daily Rate [€]":
                return "dailyRate";
            case "Door Count":
                return "doorCount";
            case "Driven Distance [km]":
                return "drivenDistance";
            case "Category":
                return "category";
            case "Color":
                return "color";
            case "Construction Year":
                return "constructionYear";
            case "Defects":
                return "defects";
            case "Fuel":
                return "fuel";
            case "Gearbox":
                return "gearbox";
            case "License Number":
                return "licenseNumber";
            case "Model":
                return "model";
            case "Next Inspection":
                return "nextInspection";
            case "Parking Lot":
                return "parkingLot";
            case "Tires":
                return "tires";
            case "Vehicle Ident Number":
                return "vin";
            default:
                return field;
        }
    }

    public static String convertValue(String value, String comparator){
        if(comparator.equals("LIKE")){
            return "'%" + value + "%'";
        }
        return "'" + value + "'";
    }
}
