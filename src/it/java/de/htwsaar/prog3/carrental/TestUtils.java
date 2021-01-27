package de.htwsaar.prog3.carrental;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class TestUtils {

    private static final Map<Integer, KeyCode> keyCodes = new HashMap<>(KeyCode.values().length) {{
        for (KeyCode value : KeyCode.values()) {
            put(value.getCode(), value);
        }
    }};

    public static KeyCode[] toKeyCode(String str) {
        return str.codePoints()
                .mapToObj(keyCodes::get)
                .toArray(KeyCode[]::new);
    }
}
