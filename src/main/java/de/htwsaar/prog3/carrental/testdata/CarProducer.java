package de.htwsaar.prog3.carrental.testdata;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;

import java.util.List;

/**
 * Produces some test data
 *
 * @author Julian Quint
 */
public class CarProducer {
    public static void main(String[] args){
        CarService carService = new CarService();
        carService.persist(createCar(250,3,6000,570,"Nissan","Coupe","Blue","2017","Gasoline","Automatic","N ISM 0",
                "GT-R","10-2019","2C","Summer Tires","1G2ZF57B584174326"));
        carService.persist(createCar(120,5,25000,303,"BMW","Sedan","Black","2016","Gasoline","Manual","HD GG 1234",
                "335i","04-2020","2A","Summer Tires","1FTJX35G4RKA95915"));
        carService.persist(createCar(150,3,10000,333,"Mercedes","Coupe","Grey","2017","Gasoline","Automatic","SB AB 2018",
                "C 400","08-2021","3E","Summer Tires","JKAEXEA128A066488"));
        carService.persist(createCar(260,3,12500,510,"Mercedes","Coupe","Black","2018","Gasoline","Automatic","M OK 1337",
                "C 63 S AMG","08-2021","1B","Summer Tires","1M2P264C1WM025115"));
        carService.persist(createCar(200,3,16000,466,"Ford","Coupe","Red","2017","Gasoline","Manual","K ARL 322",
                "Mustang GT","06-2021","4A","Summer Tires","5NPEC4AC5BH130519"));
        carService.persist(createCar(130,5,50000,215,"BMW","Sedan","Dark Grey","1990","Gasoline","Manual","SB E 30",
                "M3","10-2019","2D","Summer Tires","3C6JR6AG1DG512299"));
        carService.persist(createCar(30,3,5000,90,"VW","Hatchback","White","2016","Gasoline","Manual","HD UP 314",
                "UP","08-2022","5A","Summer Tires","19XFB2F5XEE218674"));
        carService.persist(createCar(125,3,30000,200,"Toyota","Coupe","Purple","2014","Gasoline","Manual","SB GT 86",
                "GT86","02-2019","1F","Summer Tires","3TMLU42N99M032229"));
        carService.persist(createCar(300,3,9000,610,"Audi","Coupe","Black","2018","Gasoline","Automatic","ST WR 52",
                "R8 V10 Plus","01-2023","2E","Summer Tires","WMWMF735X8TT87454"));
        carService.persist(createCar(250,3,9000,280,"Nissan","Coupe","Blue","2000","Gasoline","Manual","SB GOD 42",
                "Skyline GT-R","11-2020","6B","Summer Tires","1N6ND02S1FC301390"));
        carService.persist(createCar(210,3,21000,328,"Nissan","Roadster","Silver","2012","Gasoline","Manual","SB NS 370",
                "370Z","06-2019","1A","Summer Tires","2HGFB2F5XDH599074"));
        carService.persist(createCar(180,5,32000,220,"Skoda","Estate","Red","2016","Gasoline","Manual","SB TSI 20",
                "Octavia RS","12-2021","7E","Summer Tires","KNDJD733265646563"));
        carService.persist(createCar(170,5,5000,225,"Peugot","Estate","Yellow","2018","Gasoline","Automatic","SB PG 308",
                "508","11-2022","6C","Summer Tires","1FTNF21586EA23225"));
        carService.persist(createCar(205,5,8000,370,"Kia","Sedan","Red","2017","Gasoline","Automatic","SB ST 33",
                "Stinger","06-2021","3B","Summer Tires","1FAHP2EW0CG105260"));
        carService.persist(createCar(250,3,20000,431,"BMW","Coupe","Blue","2015","Gasoline","Manual","SB JP 317",
                "M4","02-2020","2F","Summer Tires","JTEGP21A050066614"));
        List<Car> list = carService.findAll();
        for (Car c: list){
            System.out.println(c);
        }
    }

    private static Car createCar(int dailyRate, int doorCount, int drivenDistance, int horsepower, String brand,
                                 String category, String color, String constructionYear, String fuel, String gearbox,
                                 String licenseNumber, String model, String  nextInspection, String parkingSlot,
                                 String tires, String vin){
        Car car = new Car();
        car.setDailyRate(dailyRate);
        car.setDoorCount(doorCount);
        car.setDrivenDistance(drivenDistance);
        car.setHorsepower(horsepower);
        car.setBrand(brand);
        car.setCategory(category);
        car.setColor(color);
        car.setConstructionYear(constructionYear);
        car.setFuel(fuel);
        car.setGearbox(gearbox);
        car.setLicenseNumber(licenseNumber);
        car.setModel(model);
        car.setNextInspection(nextInspection);
        car.setParkingLot(parkingSlot);
        car.setTires(tires);
        car.setVin(vin);

        return car;
    }
}
