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
 *
 */
public class PhaseThree extends PhaseStatus {

    private Player player;

    @FXML
    private TextFlow territory;

    @FXML
    private AnchorPane pane;

    @FXML
    private Text stage;

    @Override
    public AnchorPane getPane() {
        return pane;
    }

    @Override
    public void update() {

    }

    @Override
    public void init() {

        player = GameStatus.getInstance().getCurrentPlayer();

        stage.setText(GameStatus.getInstance().getPhase() + "");

        setContent();
    }

    private void setContent() {

        List<Text> textList = new ArrayList<>();

        for (Country country : player.getTerritory()) {

            Text text = new Text(country.getName() + ": " + GameUtil.tempArmyDistributeMap.get(country) + "   ");

            textList.add(text);
        }

        territory.getChildren().clear();

        territory.getChildren().addAll(textList);
    }

    public void skip(){

    	GameStatus.getInstance ().nextPhase ();

    	super.getGamePage ().updatePlayer ();

    	super.getGamePage().updatePhaseStatus ();
    }
}
