package com.istad.util;

import java.awt.*;

public class ColorUtil {
    public static final String RESET  = "\u001B[0m";
    public static final String RED    = "\u001B[31m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE   = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN   = "\u001B[36m";
    public static final String WHITE  = "\u001B[37m";

    public static String colorText(String text, String colorCode) {
        return colorCode + text + RESET;
    }

    public static String randomColorText(String text) {
        String[] colors = {RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE};
        int index = (int)(Math.random() * colors.length);
        return colors[index] + text + RESET;
    }
}
