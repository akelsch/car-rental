module carrental {
    requires com.fasterxml.classmate;
    requires java.persistence;
    requires java.sql;
    requires java.xml.bind;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires lombok;
    requires net.bytebuddy;
    requires slf4j.api;

    exports de.htwsaar.prog3.carrental.controller;
    opens de.htwsaar.prog3.carrental.controller;
    opens de.htwsaar.prog3.carrental.model;
    opens de.htwsaar.prog3.carrental;
}
