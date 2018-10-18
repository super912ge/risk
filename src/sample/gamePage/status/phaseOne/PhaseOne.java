package sample.gamePage.status.phaseOne;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.GameStatus;
import sample.gamePage.status.PhaseStatus;
import sample.model.Player;

public class PhaseOne extends PhaseStatus {

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

    @Override
    public AnchorPane getPane() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void init() {

    }

//
//    public void update(){
//
//        Player currentPlayerPlayer = GameStatus.getCurrentPlayer();
//
//        player.setText(player.getText()+currentPlayerPlayer.getId());
//
//        territory.setText(territory.getText()+currentPlayerPlayer.getTerritoryString());
//
//        continent.setText(continent.getText()+currentPlayerPlayer.getContinentString());
//
//        stage.setText(stage.getText()+ GameStatus.getPhase());
//
//        log.setText("Received "+currentPlayerPlayer.gainArmy()+" new army, please select a country to place your army.");
//
//        army.setText(army.getText()+ currentPlayerPlayer.getArmy());
//
//    }
}
