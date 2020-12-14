package de.htwsaar.prog3.carrental.model.generator;

import java.util.concurrent.ThreadLocalRandom;

public class GeneratorUtil {
    private static final String DIGITS = "0123456789";
    private static final String UPPER_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private GeneratorUtil(){
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
        if (symbols.length() < 2) throw new IllegalArgumentException();
        char[] charSeq = new char[length];
        final char[] symbolsArr = symbols.toCharArray();
        for (int i = 0; i < length; i++) {
            charSeq[i] = symbolsArr[RANDOM.nextInt(symbolsArr.length)];
        }
        return new String(charSeq);
    }
}
