package sample.gamePage.status.phaseThree;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import sample.GameStatus;
import sample.gamePage.status.PhaseStatus;
import sample.model.Country;
import sample.model.Player;
import sample.utils.GameUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *It is a class to describe all functions related to PhaseThree
 */
public class PhaseThree extends PhaseStatus {

    private Player player;

    @FXML
    private TextFlow territory;

    @FXML
    private AnchorPane pane;

    @FXML
    private Text stage;
    /**
	 * This method is to getPane and return it
	 * 
	 * @return pane
	 */
    @Override
    public AnchorPane getPane() {
        return pane;
    }
    /**
	 * This method is update 
	 * 
	 * @Override
	 */
    @Override
    public void update() {

    }
    /**
	 * This method is for init.
	 * @override
	 */
    @Override
    public void init() {

        player = GameStatus.getInstance().getCurrentPlayer();

        stage.setText(GameStatus.getInstance().getPhase() + "");

        setContent();
    }
    /**
	 * This method is set content of phase three
	 * 
	 */
    private void setContent() {

        List<Text> textList = new ArrayList<>();

        for (Country country : player.getTerritory()) {

            Text text = new Text(country.getName() + ": " + GameUtil.tempArmyDistributeMap.get(country) + "   ");

            textList.add(text);
        }

        territory.getChildren().clear();

        territory.getChildren().addAll(textList);
    }
    /**
	 * This method is skip
	 *
	 */
    public void skip(){

    	GameStatus.getInstance ().nextPhase ();

    	super.getGamePage ().updatePlayer ();

    	super.getGamePage().updatePhaseStatus ();
    }
}
