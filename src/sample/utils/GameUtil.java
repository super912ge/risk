package sample.utils;

import sample.model.Country;
import sample.model.Player;

import java.util.*;

/**
 * GameUtil class Distribute Army
 */
public class GameUtil {

    public static Map<Country, Integer> tempArmyDistributeMap = new HashMap<>();
   /**
     * method to find All ConnectedCountry
     * @param player is the player in the list
     *      country is the connectedCountry
     * @return sortedArrayList is the list of valid
     */
    public static List<Country> findAllConnectedCountry(Player player, Country country) {

        Set<Country> validOptions = new HashSet<>();

        connectedCountryUtil(country, validOptions, player.getTerritory());

        List<Country> sortedArrayList = new ArrayList<>(validOptions);

        sortedArrayList.sort((i, j) -> {

            if (!i.getContinent().equals(j.getContinent())) {

                return i.getContinent().getName().compareTo(j.getContinent().getName());
            } else return i.getName().compareTo(j.getName());

        });

        return sortedArrayList;
    }
   /**
     * getFinalCountry
     * @return the set of Country
     */
    public static Set<Country> getFinalCountry() {

        for (Map.Entry<Country, Integer> entry : tempArmyDistributeMap.entrySet()) {

            entry.getKey().setArmy(entry.getValue());
        }

        return tempArmyDistributeMap.keySet();
    }
   /**
     * connectedCountry
     * @param c is the temp variable 
     *         visited Country that have been visited in the Country set
     *         visited Country that are available in the Country set
     */
    private static void connectedCountryUtil(Country c, Set<Country> visited, Set<Country> available) {

        for (Country country : c.getAdjacentCountry()) {

            if (!visited.contains(country) && available.contains(country)) {

                visited.add(country);

                connectedCountryUtil(country, visited, available);
            }
        }
    }
   /**
     * method to Distribute Army
     * @param country the country that will get army 
     *        army    the number of army that country can add
     */
    public static void tempDistributeArmy(Country country, int army) {

        tempArmyDistributeMap.computeIfPresent(country, (k, v) -> v += army);

    }
   /**
     * method to reset the TempMap
     * 
     */
    public static void resetTempMap() {

        tempArmyDistributeMap.clear();
    }
   /**
     * method to initial the TempMap
     * 
     */
    public static void initTempMap(Player player) {

        resetTempMap();

        player.getTerritory().forEach(c -> tempArmyDistributeMap.put(c, c.getArmy()));
    }
   /**
     * method to get the number of Army
     * 
     */
    public static int getDistributedArmy() {

        return tempArmyDistributeMap.values().stream().mapToInt(i -> i).sum();
    }
}
