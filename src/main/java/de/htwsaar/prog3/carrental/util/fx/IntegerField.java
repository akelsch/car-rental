package de.htwsaar.prog3.carrental.util.fx;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import java.util.Objects;
import java.util.function.UnaryOperator;

public class IntegerField extends TextField {

    private static final UnaryOperator<TextFormatter.Change> INTEGER_FILTER =
            change -> change.getControlNewText().matches("\\d*") ? change : null;

    public IntegerField() {
        setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, INTEGER_FILTER));
    }

    public int getValue() {
        return Objects.requireNonNullElse(getIntegerTextFormatter().getValue(), 0);
    }

    public void setValue(int value) {
        getIntegerTextFormatter().setValue(value);
    }

    public ObjectProperty<Integer> valueProperty() {
        return getIntegerTextFormatter().valueProperty();
    }

    @SuppressWarnings("unchecked")
    private TextFormatter<Integer> getIntegerTextFormatter() {
        return (TextFormatter<Integer>) getTextFormatter();
    }
}
