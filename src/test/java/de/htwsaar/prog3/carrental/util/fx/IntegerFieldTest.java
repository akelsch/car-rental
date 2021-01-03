package de.htwsaar.prog3.carrental.util.fx;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.Stop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(ApplicationExtension.class)
class IntegerFieldTest {

    private IntegerField integerField;

    @Start
    void start(Stage stage) {
        integerField = new IntegerField();
        stage.setScene(new Scene(new StackPane(integerField), 200, 100));
        stage.show();
    }

    @Stop
    void stop() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void getValueValid(FxRobot robot) {
        // a final enter is required as the Property usually would only change after un-focusing
        robot.type(KeyCode.DIGIT4, KeyCode.DIGIT2, KeyCode.ENTER);
        assertEquals(42, integerField.getValue());
    }

    @Test
    void getValueInvalid(FxRobot robot) {
        robot.type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.ENTER);
        assertEquals(0, integerField.getValue());
    }

    @Test
    void setValue() {
        integerField.setValue(4711);
        assertEquals(4711, integerField.getValue());
    }

    @Test
    void valueProperty() {
        ObjectProperty<Integer> valueProperty = integerField.valueProperty();
        valueProperty.addListener((observable, oldValue, newValue) -> integerField.setId("changed"));
        assertNotEquals("changed", integerField.getId());
        integerField.setValue(360);
        assertEquals("changed", integerField.getId());
    }
}
