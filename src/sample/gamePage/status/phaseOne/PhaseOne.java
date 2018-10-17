package sample.gamePage.status.phaseOne;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.Controller;
import sample.gamePage.status.StatusPhase;
import sample.model.Player;
/**
 * This class represents the first phase of the game
 * It updates current status
 */

public class PhaseOne implements StatusPhase{

    @FXML
    private AnchorPane pane;

    @FXML
    private Label player;

    @FXML
    private Label army;

    @FXML
    private Label territory;

    @FXML
    private Label continent;

    @FXML
    private Label stage;

    @FXML
    private Text log;

    /**
	 * To update phrase one game which includes current player, territory,continent
     * stages,and gained army number 
	 */
    public void update(){

        Player currentPlayerPlayer = Controller.getCurrentPlayer();

        player.setText(player.getText()+currentPlayerPlayer.getId());

        territory.setText(territory.getText()+currentPlayerPlayer.getTerritoryString());

        continent.setText(continent.getText()+currentPlayerPlayer.getContinentString());

        stage.setText(stage.getText()+Controller.getPhase());

        log.setText("Received "+currentPlayerPlayer.gainArmy()+" new army, please select a country to place your army.");

        army.setText(army.getText()+ currentPlayerPlayer.getArmy());

    }
    /**
	 * @override function
     * This method is to get current pane
     * @return pane
	 */

    @Override
    public AnchorPane getPane() {
        return this.pane;
    }
}
