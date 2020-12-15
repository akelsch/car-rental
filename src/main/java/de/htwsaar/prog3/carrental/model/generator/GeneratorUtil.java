package de.htwsaar.prog3.carrental.model.generator;

import java.security.SecureRandom;

public class GeneratorUtil {
    private static final String DIGITS = "0123456789";
    private static final String UPPER_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();

    private GeneratorUtil() {
    }

    public static String generateUpperSequence(int length) {
        return generateSequence(UPPER_LETTERS, length);
    }

    public static String generateUpperNumericSequence(int length) {
        return generateSequence((UPPER_LETTERS + DIGITS), length);
    }

    public static String generateNumericSequence(int length) {
        return generateSequence(DIGITS, length);
    }

    private static String generateSequence(String symbols, int length) {
        int symbolsLength = symbols.length();
        if (symbolsLength < 2) throw new IllegalArgumentException();

        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sequence.append(symbols.charAt(getRandomInt(symbolsLength)));
        }

        return sequence.toString();
    }

    public static int getRandomInt(int max) {
        return random.nextInt(max);
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
