package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.scene.paint.Color;
import sample.utils.ColorUtil;


public class JUnitTest9 {
	
	public static void printMsg(String msg) {
		System.out.println(msg);
	}
	
	@BeforeClass
	public static void beforeTest() {
		printMsg("Start to test ColorUtil.");
	}
	
	@Test
	public void testContinentColor() {
		assertEquals(Color.CORAL, ColorUtil.getContinentColor(3));
		assertNull(ColorUtil.getContinentColor(20));
		printMsg("Successfully test the continent color.");
	}
	
	@Test
	public void testPlayerColor() {
		assertEquals(Color.BLANCHEDALMOND, ColorUtil.getPlayerColor(1));
		assertNull(ColorUtil.getPlayerColor(10));
		printMsg("Successfully test the player color.");
	}
	
	@AfterClass
	public static void afterTest() {
		printMsg("Finish testing ColorUtil.");
		printMsg("");
	}
}
