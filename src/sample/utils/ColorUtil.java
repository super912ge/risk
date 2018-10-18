package sample.utils;

import javafx.scene.paint.Color;

public class ColorUtil {


    private static final Color[] continentColor = {

            Color.ALICEBLUE,

            Color.BLANCHEDALMOND,

            Color.BURLYWOOD,

            Color.CORAL,

            Color.VIOLET,

            Color.SEAGREEN,

            Color.TOMATO,

            Color.ANTIQUEWHITE
    };



	private static final Color[] playerColor = {

			Color.ORANGE,

			Color.RED,

			Color.YELLOW,

			Color.GREEN,

			Color.BLUE,

			Color.PURPLE
	};

    public static Color getContinentColor(int a){

        if (a>=0 && a<continentColor.length) return continentColor[a];

        else return null;
    }


	public static Color getPlayerColor(int a){

		if  ( a >=0 && a < playerColor.length) return continentColor[a];

		else return null;
	}
}
