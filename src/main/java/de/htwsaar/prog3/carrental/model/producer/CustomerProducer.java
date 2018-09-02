package de.htwsaar.prog3.carrental.model.producer;

import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.util.EntityManagerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Produces some test data for the {@link Customer} model.
 *
 * @author Julian Quint
 */
public class CustomerProducer {
    private static CustomerService customerService = new CustomerService();

    private static void produce() {
        List<Customer> list = new ArrayList<>();

        list.add(new Customer("Saarbrücken", "01.01.1970", "L52C006JE96", "wbraun@htwsaar.de", "Wolfgang", "141",
                "4077722104D580209241090826", "Braun", "+492718281828", "Otto-Hahn Straße", 66111));
        list.add(new Customer("Saarbrücken", "24.02.1955", "E12G064UE75", "sjobs@mac.os", "Steve", "42",
                "5082135064D730803739021052", "Jobs", "+4918686302002", "Geisberg", 66132));
        list.add(new Customer("Saarbrücken", "27.11.1940", "J34G744PO63", "blee@gmail.com", "Bruce", "20",
                "3965673348D721226240040178", "Lee", "+491590767803", "Schulstraße", 66130));
        list.add(new Customer("Dortmund", "03.08.1984", "L43Q643MM47", "kolle@empire.de", "Felix", "73",
                "7329048896D880623138011838", "Blume", "+491726894235", "Wieselweg", 44142));
        list.add(new Customer("Dortmund", "12.09.1980", "W64C743DI34", "kraemer@performance.de", "Jean Pierrre", "89",
                "3778296792D771211938112098", "Kraemer", "+491558600873", "Klönnestraße", 44143));
        list.add(new Customer("Berlin", "31.06.1995", "K47U371PP73", "max@muster.de", "Max", "1",
                "6237763868D680121040102494", "Mustermann", "+491601283606", "Musterstraße", 10115));
        list.add(new Customer("Stuttgart", "26.06.1967", "I15Y458AJ18", "jason@transporter.de", "Jason", "4",
                "6171758595D660330040061254", "Statham", "+491780069260", "Kaiserstraße", 70173));
        list.add(new Customer("München", "02.05.1972", "V72H674GX63", "djohnson@rock.de", "Dwayne", "13",
                "7146115786D741202839122652", "Johnson", "+491790764271", "Dorfstraße", 81247));
        list.add(new Customer("Kiel", "04.06.1975", "G46K471FR49", "ajolie@gmx.com", "Angelina", "27",
                "1693557288D751014239033080", "Jolie", "+491525360169", "Parkstraße", 24105));
        list.add(new Customer("Berlin", "15.04.1990", "L73S162SA15", "ewatson@web.de", "Emma", "51",
                "8471690711D900624539112024", "Watson", "+491637275929", "Ilburger Ufer", 10587));

        for (Customer customer : list) {
            customerService.persist(customer);
        }
    }

    public static void main(String[] args) {
        produce();
        EntityManagerUtil.closeEntityManagerFactory();
    }
}
