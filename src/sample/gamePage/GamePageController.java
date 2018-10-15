package sample.gamePage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import sample.Controller;
import sample.gamePage.status.PhaseController;
import sample.gamePage.status.StatusPhase;
import sample.gamePage.status.phaseOne.PhaseOne;
import sample.gamePage.status.phaseThree.PhaseThree;
import sample.model.Continent;
import sample.model.Country;
import sample.model.GameMap;
import sample.model.Player;
import sample.utils.ColorOptions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class GamePageController {

    @FXML
    private ImageView gameMap;

    @FXML
    private AnchorPane gameMapPane;

    @FXML
    private AnchorPane statusPane;

    @FXML
    private AnchorPane controlPane;

    private PhaseController phaseController = new PhaseController();

    private static int currentPlayer = 0;

    private static int phase = 1;

    private Scene frontPage;

    private GameMap map = GameMap.getInstance();

    public GamePageController() throws IOException {
    }

    public void setFrontPage(Scene scene){

        this.frontPage = scene;
    }

    public void goToMainMenu(ActionEvent event){

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(frontPage);

    }

    public void gainArmy(){

        Controller.getCurrentPlayer().gainArmy();

    }

    public void renderMap() throws IOException {

        int width = map.getCoordinator().getX();

        int height = map.getCoordinator().getY();

        PhaseOne phaseOne = (PhaseOne) phaseController.getPhase(Controller.getPhase());

        phaseOne.update();

        AnchorPane status = phaseOne.getPane();

        statusPane.getChildren().addAll(status);

        for (Country country: map.getTerritories()){

            Button button = new Button();

            button.setText(country.getName());

            Background background = new Background(new BackgroundFill(ColorOptions.getColor(map.getContinents()
                    .indexOf(country.getContinent())), CornerRadii.EMPTY, Insets.EMPTY));

            button.setBackground( background);


            button.setOnAction(event -> {


            });

            double x = ((double) country.getCoordinator().getX()/width)*gameMap.getFitWidth()*0.68;

            double y = ((double) country.getCoordinator().getY()/height)*gameMap.getFitHeight()*0.8;

            AnchorPane.setLeftAnchor(button,x+125);

            AnchorPane.setTopAnchor(button,y+40);

            gameMapPane.getChildren().add(button);
        }
    }

}
