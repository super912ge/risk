package sample.model;

import java.util.HashSet;
import java.util.Set;

public class Player {

    private int id;

    private String username;

    private int army;

    private int spentArmy;

    private Set<Card> cards;

    private Set<Country> territory = new HashSet<>();

    private Set<Continent> continents = new HashSet<> ();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getArmy() {
        return army;
    }

    public void setArmy(int army) {
        this.army = army;
    }

    public Set<Country> getTerritory() {
        return territory;
    }

    public void setTerritory(Set<Country> territory) {
        this.territory = territory;
    }

    private Set<Continent> getContinents() {

        return continents;
    }

    public void setContinents(Set<Continent> continents) {
        this.continents = continents;
    }

    public Player(int id, int army, Set<Country> countries){

        this.id = id;

        this.army = army;

        this.territory = countries;
    }

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

    public String getTerritoryString(){

        if (territory.isEmpty()) return "";

        StringBuilder stringBuilder = new StringBuilder();

        territory.forEach(i->{stringBuilder.append(i); stringBuilder.append (" ");});

        return stringBuilder.toString();
    }

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
