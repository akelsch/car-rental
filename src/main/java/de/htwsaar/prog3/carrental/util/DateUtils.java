package de.htwsaar.prog3.carrental.util;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DateUtils {

    private DateUtils() {
    }

    public static List<YearMonth> createNextInspectionDates() {
        List<YearMonth> dates = new ArrayList<>();
        YearMonth start = YearMonth.now();
        for (int i = 0; i < 24; i++) {
            dates.add(start);
            start = start.plusMonths(1);
        }
        return dates;
    }

    public static Callback<DatePicker, DateCell> createDayCellFactory(Predicate<LocalDate> predicate) {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (predicate.test(item)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
    }
}
