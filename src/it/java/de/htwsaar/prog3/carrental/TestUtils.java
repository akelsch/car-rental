package de.htwsaar.prog3.carrental;

import javafx.scene.input.KeyCode;

public class TestUtils {

    public static KeyCode[] toKeyCode(String str) {
        return str.codePoints()
                .mapToObj(c -> KeyCode.getKeyCode(String.valueOf((char) c)))
                .toArray(KeyCode[]::new);
    }
}
