package sample.gamePage.status.preGame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import sample.GameStatus;
import sample.gamePage.GamePageController;
import sample.gamePage.status.PhaseStatus;
import sample.model.Country;
import sample.model.Player;
import sample.utils.GameUtil;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class PreGame extends PhaseStatus {

	@FXML
	private Label title;

	@FXML
	private TextFlow status;

	@FXML
	private TextField input;

	private Player player;

	private GamePageController gamePageController;

	@FXML
	private Button confirm;

	@PostConstruct
	public void init(){

		confirm.setDisable (true);

		player = GameStatus.getInstance ().getCurrentPlayer ();

		setContent ();

	}

	public void confirm(){

		player.setUsername (input.getText().trim ());

		player.setSpentArmy (0);

		player.setArmy (0);

		GameStatus.getInstance ().nextPlayer ();

		player = GameStatus.getInstance ().getCurrentPlayer ();

		if (!GameStatus.getInstance ().isStart ()) update ();

		else gamePageController.start();

	}

	public void reset() {

		GameUtil.initTempMap (player);

		input.setText ("");

		player.setSpentArmy (player.getTerritory ().size ());

		update ();

	}

	private void update(){

		setContent ();


	}

	private String generateTitle(Player player) {

		return "Player " + player.getId () + ", available army: " + (player.getArmy () - player.getSpentArmy ());
	}

	private void setContent(){

		player.setSpentArmy (player.getTerritory ().size ());

		title.setText (generateTitle (player)) ;

		List<Text> textList = new ArrayList<> ();

		for (Country country : player.getTerritory ()){

			Text text = new Text (country.getName ()+ " "+ GameUtil.tempArmyDistributeMap.get (country));

			textList.add (text);
		}

		status.getChildren ().addAll (textList);
	}

	public void setGamePageController (GamePageController gamePageController) {

		this.gamePageController = gamePageController;
	}
}
