package de.htwsaar.prog3.carrental.util;

import com.sun.javafx.scene.control.DatePickerContent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class DateUtilsTest {

    @Test
    void createNextInspectionDates() {
        List<YearMonth> nextInspectionDates = DateUtils.createNextInspectionDates();
        YearMonth first = nextInspectionDates.get(0);
        YearMonth last = nextInspectionDates.get(23);

        assertEquals(first, YearMonth.now());
        assertEquals(last, YearMonth.now().plusMonths(23));
    }

    @Test
    void createDayCellFactory() throws Exception {
        DatePicker datePicker = new DatePicker();
        datePicker.setDayCellFactory(DateUtils.createDayCellFactory(date -> date.isBefore(LocalDate.now())));

        FxToolkit.setupStage(s -> s.setScene(new Scene(new StackPane(datePicker), 200, 100)));
        FxToolkit.showStage();

        DatePickerSkin skin = (DatePickerSkin) datePicker.getSkin();
        DatePickerContent content = (DatePickerContent) skin.getPopupContent();
        // DatePickerContent does not expose any DateCells, hence reflection
        List<DateCell> dayCells = (List<DateCell>) ReflectionTestUtils.getField(content, "dayCells");

        int todayIndex = IntStream.range(0, dayCells.size())
                .filter(i -> dayCells.get(i).getStyleClass().contains("today"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not find today's date in DatePicker"));

        DateCell beforeToday = dayCells.get(todayIndex - 1);
        assertTrue(beforeToday.isDisabled());
        assertEquals("-fx-background-color: #ffc0cb;", beforeToday.getStyle());

        DateCell afterToday = dayCells.get(todayIndex + 1);
        assertFalse(afterToday.isDisabled());
        assertNotEquals("-fx-background-color: #ffc0cb;", afterToday.getStyle());
    }
}
