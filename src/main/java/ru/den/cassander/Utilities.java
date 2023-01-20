package ru.den.cassander;

/**
 * Класс для утилитных методов
 */

public final class Utilities {
    private Utilities() {

    }

    public static String convertToMultiline(String orig) {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}