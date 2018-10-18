package sample.frontPage.gameSetup;

import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

    @FXML
    private Button confirm;


    private NumberTextField numberTextField;


    private static final int [] initialArmy = {35,30,25,20};

    @FXML
    public void initialize(){

        try {
            numberTextField = new NumberTextField();

            numberTextField.setRange(3,6);

        } catch (IllegalArgumentException e) {

            this.confirm.setDisable(true);
        }

        Label label = new Label("Number of players: ");

        AnchorPane.setLeftAnchor(label,10.0);

        AnchorPane.setTopAnchor(label, 40.0);

        AnchorPane.setTopAnchor(numberTextField,60.0);

        AnchorPane.setLeftAnchor(numberTextField,60.0);

        setupPane.getChildren().addAll(label,numberTextField);
    }

    @FXML
    public void confirm(ActionEvent event){


        if (numberTextField.isOutRange()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setContentText( numberTextField.getNumber() +" is invalid, please enter number between "+numberTextField.getRange());

            alert.show();
        }

        else {

            GameStatus.getInstance().reset();

            for (int i = 0; i < numberTextField.getNumber(); i++) {

                int n = i;

                Player player = new Player(i + 1, initialArmy[numberTextField.getNumber() - 3]);

                player.setTerritory(new HashSet<>());

                while (n < GameMap.getInstance().getTerritories().size()) {

                    player.addTerritory(GameMap.getInstance().getTerritories().get(n), 1);

                    n += numberTextField.getNumber();
                }

                GameStatus.getInstance().addPlayer(player);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getParent().getScene().getWindow();

            stage.close();
        }
    }
    @FXML
    public void cancel(ActionEvent event){

        Stage stage = (Stage) ((Node)event.getSource()).getParent().getScene().getWindow();

        stage.close();
    }

}
