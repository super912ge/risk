package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import sample.model.Continent;
import sample.model.Country;
import sample.model.Player;


public class JUnitTest8 {


	private Continent testContinent = new Continent("Asia", 8);
	private Set<Continent> testContinentSet = new HashSet<Continent>();
	private Set<Country> testCountrySet = new HashSet<Country>();
	private static final int countryNum = 20;
	
	public static void printMsg(String msg) {
		System.out.println(msg);
	}
	
	@BeforeClass
	public static void beforeTest() {
		printMsg("Start to test calculation of reinforcement armies.");
	}
	
	@Test
	public void testReinforcement() {		
		testContinentSet.add(testContinent);
		for (int i = 0; i < countryNum; i++) {
			testCountrySet.add(new Country("test" + i));
		}
		
		Player player = new Player(0, 0, testCountrySet);
		player.setContinents(testContinentSet);
		player.gainArmy();
		
		assertEquals((int)Math.max(3, Math.floor(countryNum / 3)) + testContinent.getArmy(), (int)player.getArmy());
		
		printMsg("Successfully test the calculation of reinforcement armies.");
	}
	
	@AfterClass
	public static void afterTest() {
		printMsg("Finish testing calculation of reinforcement armies.");
		printMsg("");
	}
}
