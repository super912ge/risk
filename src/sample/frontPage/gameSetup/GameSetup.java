package sample.frontPage.gameSetup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.GameStatus;
import sample.model.Country;
import sample.model.GameMap;
import sample.model.Player;
import sample.utils.NumberTextField;

import java.util.HashSet;
import java.util.Set;

public class GameSetup {

    @FXML
    private AnchorPane setupPane;


    private NumberTextField numberTextField;


    private static final int [] initialArmy = {35,30,25,20};

    @FXML
    public void initialize(){

        numberTextField = new NumberTextField();

        Label label = new Label("Number of players: ");

        AnchorPane.setLeftAnchor(label,10.0);

        AnchorPane.setTopAnchor(label, 40.0);

        AnchorPane.setTopAnchor(numberTextField,60.0);

        AnchorPane.setLeftAnchor(numberTextField,60.0);

        setupPane.getChildren().addAll(label,numberTextField);
    }

    @FXML
    public void confirm(ActionEvent event){

        GameStatus.getInstance ().reset();

        for(int i =0; i< numberTextField.getNumber(); i++) {

            int n = i;

            Player player = new Player(i+1,initialArmy[numberTextField.getNumber()-3]);

        	while ( n < GameMap.getInstance ().getTerritories ().size ()){

        		player.addTerritory(GameMap.getInstance().getTerritories().get(n), 1);

                n += numberTextField.getNumber();
	        }

            GameStatus.getInstance ().addPlayer(player);
        }

        Stage stage = (Stage) ((Node)event.getSource()).getParent().getScene().getWindow();

        stage.close();

    }
    @FXML
    public void cancel(ActionEvent event){

        Stage stage = (Stage) ((Node)event.getSource()).getParent().getScene().getWindow();

        stage.close();
    }

}
