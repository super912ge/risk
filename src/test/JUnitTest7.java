package test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import sample.model.Player;
import sample.model.Country;

public class JUnitTest7 {

	private int testId = 10;
	private int testArmy = 8;
	private Player player = new Player(testId, testArmy);
	private String testName = "Sumail";	
	
	public static void printMsg(String msg) {
		System.out.println(msg);
	}
	
	@BeforeClass
	public static void beforeTest() {
		printMsg("Start to test Player.");
	}
	
	@Test
	public void testName() {
		player.setUsername(testName);
		assertEquals(testName, player.getUsername());
		printMsg("Successfully set the username to the player.");
	}
	
	@Test
	public void testId() {
		assertEquals(testId, player.getId());
		printMsg("Successfully set the ID to the player");
	}
	
	@Test
	public void testArmy() {
		assertEquals(testArmy, player.getArmy());
		printMsg("Successfully set the army to the player");
	}
	
	@AfterClass
	public static void afterTest() {
		printMsg("Finish testing Player.");
		printMsg("");
	}
}
