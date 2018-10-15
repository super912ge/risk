package sample.utils;

import javafx.scene.paint.Color;

public class ColorOptions {


    private static final Color[] color = {
            Color.ALICEBLUE,
            Color.BLANCHEDALMOND,
            Color.BURLYWOOD,
            Color.CORAL,
            Color.VIOLET,
            Color.SEAGREEN,
            Color.TOMATO,
            Color.ANTIQUEWHITE
    };


    public static Color getColor(int a){

        if (a<color.length) return color[a];

        else return null;
    }
}
