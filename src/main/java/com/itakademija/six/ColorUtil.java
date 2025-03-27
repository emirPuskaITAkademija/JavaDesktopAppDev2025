package com.itakademija.six;

import java.awt.*;

public class ColorUtil {

    //java.awt.Color -> String kojeg ćemo snimiti u bazi
    public static String colorToString(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }

    //String color -> vratiti nazad u Color
    public static Color stringToColor(String colorStr) throws IllegalArgumentException {
        if(colorStr == null || colorStr.length() == 0){
            return null;
        }
        if (colorStr.startsWith("#")) {
            colorStr = colorStr.substring(1);
        }
        if (colorStr.length() != 6) {
            throw new IllegalArgumentException("Invalid color format");
        }
        int red = Integer.parseInt(colorStr.substring(0, 2), 16); // Crvena komponenta
        int green = Integer.parseInt(colorStr.substring(2, 4), 16); // Zelena komponenta
        int blue = Integer.parseInt(colorStr.substring(4, 6), 16); // Plava komponenta
        return new Color(red, green, blue);
    }
}
