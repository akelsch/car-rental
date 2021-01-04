package de.htwsaar.prog3.carrental.util.fx;

import de.htwsaar.prog3.carrental.util.MessageUtils;
import javafx.util.StringConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.NoSuchMessageException;

@RequiredArgsConstructor
public class LabelableStringConverter<T extends Labelable> extends StringConverter<T> {

    private final MessageUtils messageUtils;

    @Override
    public String toString(T object) {
        if (object == null) {
            return null;
        }

        try {
            return messageUtils.getMessage(object.getLabel());
        } catch (NoSuchMessageException e) {
            return object.toString();
        }
    }

    @Override
    public T fromString(String string) {
        return null;
    }
}
