package sample.frontPage.gameSetup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controller;
import sample.model.Player;
import sample.utils.NumberTextField;


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

        Controller.reset();

        for(int i = 0; i< numberTextField.getNumber(); i++) {

            Controller.addPlayer(new Player(i+1,initialArmy[numberTextField.getNumber()-3]));
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
