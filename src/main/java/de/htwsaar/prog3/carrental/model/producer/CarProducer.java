package de.htwsaar.prog3.carrental.model.producer;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.service.CarService;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Produces some test data for the {@link Car} model.
 *
 * @author Julian Quint
 */
public class CarProducer {
    private static CarService carService = new CarService();

    public static void produce() {
        List<Car> list = new ArrayList<>();

        list.add(new Car("Nissan", "Coupe", "Blue", "2017", 250, null, 3, 6000, null, "Gasoline",
                "Automatic", 570, "N ISM 0",
                "GT-R", "10-2019", "2C", "Summer Tires", "1G2ZF57B584174326"));
        list.add(new Car("BMW", "Sedan", "Black", "2016", 120, null, 5, 25000, null, "Gasoline", "Manual",
                303, "HD GG 1234",
                "335i", "04-2020", "2A", "Summer Tires", "1FTJX35G4RKA95915"));
        list.add(new Car("Mercedes", "Coupe", "Grey", "2017", 150, null, 3, 10000, null, "Gasoline",
                "Automatic", 333, "SB AB 2018",
                "C 400", "08-2021", "3E", "Winter Tires", "JKAEXEA128A066488"));
        list.add(new Car("Mercedes", "Coupe", "Black", "2018", 260, null, 3, 12500, null, "Gasoline",
                "Automatic", 510, "M OK 1337",
                "C 63 S AMG", "08-2021", "1B", "Summer Tires", "1M2P264C1WM025115"));
        list.add(new Car("Ford", "Coupe", "Red", "2017", 200, null, 3, 16000, null, "Gasoline", "Manual",
                466, "K ARL 322",
                "Mustang GT", "06-2021", "4A", "Summer Tires", "5NPEC4AC5BH130519"));
        list.add(new Car("BMW", "Sedan", "Dark Grey", "1990", 130, null, 5, 50000, null, "Gasoline",
                "Manual", 215, "SB E 30",
                "M3", "10-2019", "2D", "Summer Tires", "3C6JR6AG1DG512299"));
        list.add(new Car("VW", "Hatchback", "White", "2016", 30, null, 3, 5000, null, "Diesel", "Manual",
                90, "HD UP 314",
                "UP", "08-2022", "5A", "Mixed Tires", "19XFB2F5XEE218674"));
        list.add(new Car("Toyota", "Coupe", "Purple", "2014", 125, null, 3, 30000, null, "Hybrid",
                "Manual", 200, "SB GT 86",
                "GT86", "02-2019", "1F", "Winter Tires", "3TMLU42N99M032229"));
        list.add(new Car("Audi", "Coupe", "Black", "2018", 300, null, 3, 9000, null, "Gasoline", "Automatic"
                , 610, "ST WR 52",
                "R8 V10 Plus", "01-2023", "2E", "Summer Tires", "WMWMF735X8TT87454"));
        list.add(new Car("Nissan", "Coupe", "Blue", "2000", 250, null, 3, 9000, null, "Gasoline", "Manual",
                280, "SB GOD 42",
                "Skyline GT-R", "11-2020", "6B", "Summer Tires", "1N6ND02S1FC301390"));
        list.add(new Car("Nissan", "Roadster", "Silver", "2012", 210, null, 3, 21000, null, "Gasoline",
                "Manual", 328, "SB NS 370",
                "370Z", "06-2019", "1A", "Summer Tires", "2HGFB2F5XDH599074"));
        list.add(new Car("Skoda", "Estate", "Red", "2016", 180, null, 5, 32000, null, "Gasoline", "Manual",
                220, "SB TSI 20",
                "Octavia RS", "12-2021", "7E", "Summer Tires", "KNDJD733265646563"));
        list.add(new Car("Peugeot", "Estate", "Yellow", "2018", 170, null, 5, 5000, null, "Gasoline",
                "Automatic", 225, "SB PG 308",
                "508", "11-2022", "6C", "Summer Tires", "1FTNF21586EA23225"));
        list.add(new Car("Kia", "Sedan", "Red", "2017", 205, null, 5, 8000, null, "Gasoline", "Automatic",
                370, "SB ST 33",
                "Stinger", "06-2021", "3B", "Summer Tires", "1FAHP2EW0CG105260"));
        list.add(new Car("BMW", "Coupe", "Blue", "2015", 250, null, 3, 20000, null, "Gasoline", "Manual",
                431, "SB JP 317",
                "M4", "02-2020", "2F", "Summer Tires", "JTEGP21A050066614"));

        for (Car car : list) {
            carService.persist(car);
        }
    }

    public static void main(String[] args) {
        produce();
        EntityManagerUtil.closeEntityManagerFactory();
    }
}
