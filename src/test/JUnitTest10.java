package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import sample.model.GameMap;
import sample.utils.LoadUtil;


public class JUnitTest10 {

	private GameMap map = GameMap.getInstance();
	private LoadUtil loader = new LoadUtil();
	
	public static void printMsg(String msg) {
		System.out.println(msg);
	}
	
	@BeforeClass
	public static void beforeTest() {
		printMsg("Start to test reading a .map file.");
		try {
			LoadUtil.readFile(new File("C:\\Users\\Admin\\Desktop\\Game\\risk\\src\\test\\Asia.map"));
		}
		catch (Exception e) {
			printMsg(e.getMessage());
		}
	}
	
	@Test
	public void testAuthor() {
		assertEquals("Rustin Terry", map.getAuthor());
		assertEquals(map.getImage().getName(), "Asia.bmp");
		assertEquals(map.getScroll(), "horizontal");
		assertTrue(map.getWarn());
		assertTrue(map.getWrap());
		printMsg("Successfully read the map \"Asia.map\" and store information");
	}
	
	
	@AfterClass
	public static void afterTest() {
		printMsg("Finish testing reading a .map file.");
		printMsg("");
	}
}
