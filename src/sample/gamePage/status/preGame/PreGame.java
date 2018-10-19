package sample.gamePage.status.preGame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
 * This class defines the scenes and information showed prior to a game.
 *
 */

public class PreGame extends PhaseStatus {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextFlow status;

    @FXML
    private TextField input;

    private Player player;

    @FXML
    private Button confirm;


    /**
     * initialize all class variables
     */
    public void init() {

        player = GameStatus.getInstance().getCurrentPlayer();

        GameUtil.initTempMap(player);

        confirm.setDisable(player.getArmy() != player.getSpentArmy());

        setContent();
    }

    /**
     * Get the panel
     */
    public AnchorPane getPane() {

        return pane;
    }

   /**
    * Set information of a player and update.
    */
    public void confirm() {

        player.setUsername(input.getText().trim());

        input.setText("");

        player.getTerritory().clear();

        player.getTerritory().addAll(GameUtil.getFinalCountry());

        player.setArmy(0);

        player.setSpentArmy(0);

        GameStatus.getInstance().nextPlayer();

        if (!GameStatus.getInstance().isStart()) {

            update();

            super.getGamePage().updatePlayer();
        } else super.getGamePage().updatePhaseStatus();

    }

    /**
     * Reset the information of current page.
     */
    public void reset() {

        GameUtil.initTempMap(player);

        input.setText("");

        player.setSpentArmy(player.getTerritory().size());

        update();
    }

    /**
     * update the current information.
     */
    public void update() {

        player = GameStatus.getInstance().getCurrentPlayer();

        this.confirm.setDisable(player.getArmy() != player.getSpentArmy());

        setContent();
    }

    /**
     * Set content for the current page.
     */
    private void setContent() {

        List<Text> textList = new ArrayList<>();

        for (Country country : player.getTerritory()) {

            Text text = new Text(country.getName() + ": " + GameUtil.tempArmyDistributeMap.get(country) + "   ");

            textList.add(text);
        }

        status.getChildren().clear();

        status.getChildren().addAll(textList);
    }
}
