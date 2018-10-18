package sample.model;

import java.io.File;
import java.util.List;

/**
 * This is class GameMap to achieve the function of edit map
 */
public class GameMap {

    private static final GameMap instance = new GameMap();
    /**
	 * The method of class GameMap to  get instance
	 * @return instance
	 */
    public static GameMap getInstance() {
        return instance;
    }

    private File image;

    private Coordinator coordinator;

    private Boolean wrap;

    private String author;

    private String scroll;

    private Boolean warn;

    private List<Continent> continents;

    private List<Country> territories;
    
    /**
	 * The method of class GameMap to  get image
	 * @return image
	 */
    public File getImage() {
        return image;
    }
    /**
	 * The method of class GameMap to  set image
	 * @param image, the image of the map
	 */
    public void setImage(File image) {
        this.image = image;
    }
    
    /**
	 * The method of class GameMap to  get wrap
	 * @return wrap with the type Boolean
	 */
    public Boolean getWrap() {
        return wrap;
    }
    /**
	 * The method of class GameMap to  set wrap
	 * @param wrap with the type boolean
	 */
    public void setWrap(Boolean wrap) {
        this.wrap = wrap;
    }
    /**
	 * The method of class GameMap to  get author
	 * @return the author by the type of String
	 */
    public String getAuthor() {
        return author;
    }
    /**
	 * The method of class GameMap to  set wrap
	 * @param author
	 */

    public void setAuthor(String author) {
        this.author = author;
    }
    /**
	 * The method of class GameMap to  get scroll
	 * @return the scroll by the type of String
	 */
    public String getScroll() {
        return scroll;
    }
    /**
	 * The method of class GameMap to  set scroll
	 * @param scroll with type Scroll
	 */
    public void setScroll(String scroll) {
        this.scroll = scroll;
    }
    /**
	 * The method of class GameMap to  get the list of continents
	 * @return continents,the list of continents by type List
	 */
    public List<Continent> getContinents() {
        return continents;
    }
     /**
	 * The method of class GameMap to  set continents
	 * @param continents, with a list of Continents
	 */
    public void setContinents(List<Continent> continents) {
        this.continents = continents;
    }
    /**
	 * The method of class GameMap to  get the list of Territories
	 * @return Territories,the list of Territories by type List
	 */
    public List<Country> getTerritories() {
        return territories;
    }
     /**
	 * The method of class GameMap to  set Territories
	 * @param Territories, with a list of Territories
	 */
    public void setTerritories(List<Country> territories) {
        this.territories = territories;
    }
    /**
	 * The method of class GameMap to  get warn
	 * @return warn by the type of Boolean
	 */
    public Boolean getWarn() {
        return warn;
    }
     /**
	 * The method of class GameMap to  set Warn
	 * @param warn, with boolean of warn
	 */
    public void setWarn(Boolean warn) {
        this.warn = warn;
    }
    /**
	 * The method of class GameMap to  get coodinator
	 * @return the coodinator by the type of Coodinator
	 */
    public Coordinator getCoordinator() {
        return coordinator;
    }
     /**
	 * The method of class GameMap to  set coordinator
	 * @param coordinator
	 */
    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }
}
