package de.htwsaar.prog3.carrental.util;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;

import java.util.List;

public class FilterUtil {
    private static CarService carService = new CarService();

    public static List<Car> filter(String field, String comparator, String value){
       return carService.filter(convertField(field), convertComparator(comparator), convertValue(value));
    }

    private static String convertField(String field){
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

    private static String convertComparator(String comparator){
        if(comparator == "like"){
            return "LIKE";
        }
        return comparator;
    }

    private static String convertValue(String value){
        return "'" + value + "'";
    }
}
