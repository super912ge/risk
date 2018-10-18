package sample.model;

import java.util.Set;
import java.util.HashSet;


/**
 * This class represents all of the data and funcionality that a player would
 * have. 
 */
public class Player {

    private int id;

    private String username;

    private int army;

    private int spentArmy;


    private Set<Card> cards;

    private Set<Country> territory = new HashSet<>();

    private Set<Continent> continents = new HashSet<> ();

    
	/**
	 * To get the player ID number
	 * 
	 * @return the player ID number with int type
	 */
    public int getId() {
        return id;
    }
    
    /**
	 * To set the player ID number
	 * 
	 * @param id
	 *            the desired the player ID number that want to be set with int
	 *            type
	 */
    public void setId(int id) {
        this.id = id;
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
	 * @param username
	 *            the desired the player name that want to be set with String
	 *            type
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
	 * @param army
	 *            the Army number with type int
	 *            type
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
	 * @param territory
	 *            the countries list desired to be set to belong to the player
	 *            with HashSet type
	 */

    public void setTerritory(Set<Country> territory) {
        this.territory = territory;
    }




    public void setContinents(Set<Continent> continents) {

    	this.continents = continents;
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
	 * To set the countinents list to belong to the player
	 * 
	 * @param continents
	 *            the continents list desired to be set to belong to the player
	 *            with ArrayList type
	 */

    /**
	 * Constructor method
	 * 
	 * @param id
	 *            the player id with int type
	 * @param army
	 *            the numer of army with int type
     *
     * @Param territory
     *
     *          the set of countries set to player in the begin
	 */

    public Player(int id, int army, Set<Country> territory){

        this.id = id;

        this.army = army;

        this.territory = territory;
    }
    /**
	 * Method to gain the number of Army
	 * 
	 * @return the number of Army gained with type int
	 */
    public int gainArmy(){

        int gainedArmy = Math.max(getTerritory().size()/3,3);

        if (getContinents()!=null&& getContinents().isEmpty()){

            for (Continent continent : getContinents()) {

                gainedArmy += continent.getArmy();
            }
        }

        setArmy(getArmy()+gainedArmy);

        return gainedArmy;
    }
    /**
	 * Method to get territory with type String
	 * 
	 * @return the territoy string with String type
	 */   

    public String getTerritoryString(){

        if (territory.isEmpty()) return "";

        StringBuilder stringBuilder = new StringBuilder();

        territory.forEach(i->{stringBuilder.append(i); stringBuilder.append (" ");});

        return stringBuilder.toString();
    }
     /**
	 * Method to get Continent String
	 * 
	 * @return the get Continent String with type String
	 */
    public String getContinentString(){

        if (continents.isEmpty()) return "";

        StringBuilder stringBuilder = new StringBuilder();

        territory.forEach(i-> {stringBuilder.append(i); stringBuilder.append (" ");});

        return stringBuilder.toString();
    }

	public Set<Card> getCards () {

		return cards;
	}

	public void setCards (Set<Card> cards) {

		this.cards = cards;
	}

	public int getSpentArmy () {

		return spentArmy;
	}

	public void setSpentArmy (int spentArmy) {

		this.spentArmy = spentArmy;
	}


}
