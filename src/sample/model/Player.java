package sample.model;

import java.util.HashSet;
import java.util.Set;


/**
 * This class represents all of the data and funcionality that a player would
 * have.
 */
public class Player {

    private int id;

    private String username = "";

    private int army;

    private int spentArmy = 0;

    private Set<Card> cards;

    private Set<Country> territory;

    private Set<Continent> continents = new HashSet<>();


    /**
     * Constructor method
     *
     * @param id   the player id with int type
     * @param army the numer of army with int type
     * @Param territory
     * <p>
     * the set of countries set to player in the begin
     */

    public Player(int id, int army, Set<Country> territory) {

        this.id = id;

        this.army = army;

        this.territory = territory;
    }


    public Player(int id, int army) {

        this.id = id;

        this.army = army;
    }

    /**
     * To get the player ID number
     *
     * @return the player ID number with int type
     */
    public int getId() {
        return id;
    }

    /**
     * To get the player name
     *
     * @return the player name with String type
     */
    public String getUsername() {
        return username;
    }

    /**
     * To set the player name
     *
     * @param username the desired the player name that want to be set with String
     *                 type
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * To get the Army number
     *
     * @return army with type int
     */

    public int getArmy() {
        return army;
    }

    /**
     * To set the Army number
     *
     * @param army the Army number with type int
     *             type
     */
    public void setArmy(int army) {
        this.army = army;
    }

    /**
     * To get the player's countries list
     *
     * @return the player's countries list with ArrayList type
     */

    public Set<Country> getTerritory() {
        return territory;
    }

    /**
     * To set the countries list to belong to the player
     *
     * @param territory the countries list desired to be set to belong to the player
     *                  with HashSet type
     */

    public void setTerritory(Set<Country> territory) {

        this.territory = territory;

        updateContinent();
    }

    /**
     * To get the player's continents list
     *
     * @return the player's continents list with ArrayList type
     */
    private Set<Continent> getContinents() {

        return continents;
    }

    /**
     * To set the continents set to belong to the player
     *
     * @param continents the continents set desired to be set to belong to the player
     *                   with HashSet type
     */

    public void setContinents(Set<Continent> continents) {

        this.continents = continents;
    }

    /**
     * Method to gain the number of Army
     *
     * @return the number of Army gained with type int
     */
    public void gainArmy() {

    	this.spentArmy = 0;
        int gainedArmy = Math.max(getTerritory().size() / 3, 3);

        if (getContinents() != null && !getContinents().isEmpty()) {

            for (Continent continent : getContinents()) {

                gainedArmy += continent.getArmy();
            }
        }
        setArmy(gainedArmy);
    }

    /**
     * Method to get Continent String
     *
     * @return the get Continent String with type String
     */
    public String getContinentString() {

        if (continents.isEmpty()) return "";

        StringBuilder stringBuilder = new StringBuilder();

        territory.forEach(i -> {
            stringBuilder.append(i);
            stringBuilder.append(" ");
        });

        return stringBuilder.toString();
    }

    public Set<Card> getCards() {

        return cards;
    }

    public void setCards(Set<Card> cards) {

        this.cards = cards;
    }

    public int getSpentArmy() {

        return spentArmy;
    }

    public void setSpentArmy(int spentArmy) {

        this.spentArmy = spentArmy;
    }


    public void addTerritory(Country country, int armyAssigned) {

        country.setPlayer(this);

        country.setArmy(armyAssigned);

        this.army -= armyAssigned;

        this.territory.add(country);

        updateContinent();

    }

    private void updateContinent() {

        for (Continent c : GameMap.getInstance().getContinents()) {

            if (!this.continents.contains(c) && this.territory.containsAll(c.getCountries())) {

                this.continents.add(c);
            }

        }
    }

    public String playerInfo() {

        return "Player: " + id + " " + username + " Available Army : " + (army - spentArmy);
    }

}
