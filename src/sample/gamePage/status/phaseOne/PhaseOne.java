package sample.gamePage.status.phaseOne;

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
 * This class represents the first phase of the game
 * It updates current status
 */
public class PhaseOne extends PhaseStatus {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextFlow territory;

    @FXML
    private TextFlow continent;

    @FXML
    private Text stage;

    @FXML
    private Text log;

  
    private Player currentPlayer;

   /**
	  * @override function
    * This method is to get current pane
    * @return pane
	  */
    @Override
    public AnchorPane getPane() {
        return pane;
    }



    @Override
    public void init() {

        currentPlayer = GameStatus.getInstance().getCurrentPlayer();

        continent.getChildren().add(new Text(currentPlayer.getContinentString()));

        stage.setText("" + GameStatus.getInstance().getPhase());

        log.setText("Received " + currentPlayer.getArmy() + " new army, please select a country to place your army.");

        setContent();

        super.getGamePage().updatePlayer();
    }


    /**
	   * To update phrase one game which includes current player, territory,continent
     * stages,and gained army number 
	   */
    @Override
    public void update() {

        log.setText("");

        setContent();

        if (currentPlayer.getArmy() == currentPlayer.getSpentArmy()) {

            currentPlayer.getTerritory().clear();

            currentPlayer.getTerritory().addAll(GameUtil.getFinalCountry());

            GameStatus.getInstance().nextPhase();

            //Second phase hasn't implemented yet, jump to third phase;

            GameStatus.getInstance().nextPhase();

            super.getGamePage().updatePhaseStatus();
        }
    }

    private void setContent() {

        List<Text> textList = new ArrayList<>();

        for (Country country : currentPlayer.getTerritory()) {

            Text text = new Text(country.getName() + ": " + GameUtil.tempArmyDistributeMap.get(country) + "   ");

            textList.add(text);
        }

        territory.getChildren().clear();

        territory.getChildren().addAll(textList);
    }
}
