package sample;

import sample.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {

	private static GameStatus instance = new GameStatus ();

	public static GameStatus getInstance () {

		return instance;
	}

	private List<Player> players;

	private int phase = 1;

	private int currentPlayerIndex = 0;

	private boolean isStart = false;

	public List<Player> getPlayers () { return players; }

	public int getPhase () {

		return phase;
	}

	public void addPlayer (Player player) {

		players.add (player);
	}

	public Player getCurrentPlayer () {

		return players.get (currentPlayerIndex);
	}

	public void nextPlayer () {

		currentPlayerIndex = (currentPlayerIndex + 1) % players.size ();

		if (! isStart && currentPlayerIndex == 0) isStart = true;
	}

	public void nextPhase () {

		phase = (phase + 1) % 3;

		if (phase == 1) nextPlayer ();
	}

	public void reset () {

		phase = 1;

		currentPlayerIndex = 0;

		players = new ArrayList<> ();
	}

	public boolean isStart () {

		return isStart;
	}

}
