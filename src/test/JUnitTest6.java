package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.scene.paint.Color;
import sample.model.Country;
import sample.model.Player;


public class JUnitTest6 {

	private Country country = new Country("China");
	private Color testColor = Color.BLACK;
	private Player testPlayer = new Player(0, 0);
	private int testArmy = 10;
	
	
	
	public static void printMsg(String msg) {
		System.out.println(msg);
	}
	
	@BeforeClass
	public static void beforeTest() {
		printMsg("Start to test Country.");
	}
	
	@Test
	public void testColor() {
		country.setColor(testColor);
		assertEquals(testColor, country.getColor());
		printMsg("Successfully set the color to the country.");
	}
	
	@Test
	public void testPlayer() {
		country.setPlayer(testPlayer);
		assertEquals(testPlayer, country.getPlayer());
		printMsg("Successfully set the player to the country");
	}
	
	@Test
	public void testArmy() {
		country.setArmy(testArmy);
		assertEquals(testArmy, country.getArmy());
		printMsg("Successfully set armies to the country");
	}
	
	@AfterClass
	public static void afterTest() {
		printMsg("Finish testing Country.");
		printMsg("");
	}
}
