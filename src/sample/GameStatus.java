package sample;

import sample.model.Player;
import sample.utils.GameUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * This class maintains all information and status of the game.
 */
public class GameStatus {

    private static GameStatus instance = new GameStatus();
    private boolean countryClicked = false;
    private List<Player> players;
    private int phase = 1;
    private int currentPlayerIndex = 0;
    private boolean isStart = false;

    /**
     * Get an instance of the GameStatus.
     * @return an instance of the GameStatus.
     */
    public static GameStatus getInstance() {

        return instance;
    }

    /**
     * Get the phase.
     * @return the phase.
     */
    public int getPhase() {

        return phase;
    }

    /**
     * Add a player in the game.
     * @param player is the player to be added in the game.
     */
    public void addPlayer(Player player) {

        players.add(player);
    }

    /**
     * Get the current playing player.
     * @return the current playing player.
     */
    public Player getCurrentPlayer() {

        return players.get(currentPlayerIndex);
    }

    /**
     * Turns to next player.
     */
    public void nextPlayer() {

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

        if (!isStart && currentPlayerIndex == 0) {

            isStart = true;

            getCurrentPlayer().gainArmy();
        }

        GameUtil.initTempMap(getCurrentPlayer());
    }

    /**
     * Turns to next phase.
     */
    public void nextPhase() {

        phase = (phase + 1) % 4;

        if (phase == 0) phase = 1;

        if (isStart && phase == 1) {

            nextPlayer();

            getCurrentPlayer().gainArmy();
        }
    }

    /**
     * Reset information of the status of the game.
     */
    public void reset() {

        phase = 1;

        currentPlayerIndex = 0;

        players = new ArrayList<>();
    }

    /**
     * check whether it is the start of a cycle.
     * @return true if it is the start of a cycle.
     */
    public boolean isStart() {

        return isStart;
    }

    /**
     * Check whether a country is clicked.
     * @return true is a country is clicked.
     */
    public boolean isCountryClicked() {
        return countryClicked;
    }

    /**
     * Set the status of the country.
     * @param countryClicked is the status of the country indicating whether it is clicked.
     */
    public void setCountryClicked(boolean countryClicked) {
        this.countryClicked = countryClicked;
    }

    /**
     * Get a list of players.
     * @return a list of players.
     */
	public List<Player> getPlayers () {

		return players;
	}
}
