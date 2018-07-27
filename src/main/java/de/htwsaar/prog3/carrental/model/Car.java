package de.htwsaar.prog3.carrental.model;

/**
 * This interface is used as a basis of a car object
 *
 * @author Lukas Raubuch
 */
public interface Car {
    String getBrand();
    void setBrand(String brand);
    String getModel();
    void setModel(String model);
    String getGear();
    void setGear(String gear);
    int getPower();
    void setPower(int power);
    double getPricePerDay();
    void setPricePerDay(double pricePerDay);
    String getColor();
    void setColor(String color);
    int getConstructionYear();
    void setConstructionYear(int constructionYear);
    int getDrivenKilometers();
    void setDrivenKilometers(int drivenKilometers);
    String getVehicleIdentificationNumber();
    void setVehicleIdentificationNumber(String vehicleIdentificationNumber);
    String getLicenseNumber();
    void setLicenseNumber(String licenseNumber);
    String getFuel();
    void setFuel(String fuel);
    int getNumberOfDoors();
    void setNumberOfDoors(int nuberOfDoors);
    String getDefectsDescription();
    void setDefectsDescription(String defectsDescription);
    String getDateOfNextExamination();
    void setDateOfNextExamination(String dateOfNextExamination);
    String getDriveType();
    void setDriveType();
    String getTires();
    void setTires();
    String getEquipment();
    void setEquipment();
    String getParkingLot();
    void setParkingLot();
}