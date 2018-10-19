package sample.gamePage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import sample.GameStatus;
import sample.gamePage.status.PhaseController;
import sample.gamePage.status.PhaseStatus;
import sample.gamePage.status.countryStatus.CountryStatus;
import sample.model.Country;
import sample.model.GameMap;
import sample.model.Player;
import sample.utils.ColorUtil;
import sample.utils.LoadUtil;

import java.io.IOException;


public class GamePage {

    @FXML
    private ImageView gameMap;

    @FXML
    private AnchorPane gameMapPane;

    private PhaseStatus phaseStatus;

    @FXML
    private Label playerInfo;

    @FXML
    private AnchorPane statusPane;
    //
    // @FXML
    // private AnchorPane controlPane;

    private CountryStatus countryStatus;

    private PhaseController phaseController = new PhaseController();

    private Scene frontPage;

    private GameMap map = GameMap.getInstance();

    public GamePage() throws IOException {
    }

    public void setFrontPage(Scene scene) {

        this.frontPage = scene;
    }

    public void goToMainMenu() {

        Stage stage = (Stage) this.gameMapPane.getScene().getWindow();

        stage.setScene(frontPage);

    }

    public void save() throws IOException {

	    LoadUtil.saveFile ("save/");
    }

    public void updatePlayer() {

        Player player = GameStatus.getInstance().getCurrentPlayer();

        playerInfo.setText(player.playerInfo());
    }

    public void renderMap() throws IOException {

        int width = map.getCoordinator().getX();

        int height = map.getCoordinator().getY();

        phaseStatus = phaseController.getPhase();

        phaseStatus.setGamePage(this);

        phaseStatus.init();

        updatePlayer();

        statusPane.getChildren().add(phaseStatus.getPane());

        for (Country country : map.getTerritories()) {

            Button button = new Button();

            button.setText(country.getName());

            Background background = new Background(new BackgroundFill(ColorUtil.getContinentColor(map.getContinents()
                    .indexOf(country.getContinent())), CornerRadii.EMPTY, Insets.EMPTY));

            button.setBackground(background);

            button.setOnAction((ActionEvent event) -> {

                if (countryStatus == null) {

                    FXMLLoader loaderCountry = new FXMLLoader(this.getClass()
                            .getResource("./status/countryStatus/CountryStatus.fxml"));

                    try {

                        loaderCountry.load();

                        countryStatus = loaderCountry.getController();

                        countryStatus.setGamePage(this);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {

                    countryStatus.setSelectedCountry(country);

                } catch (IOException e) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setContentText(e.getMessage());

                    alert.show();

                }

                if (!GameStatus.getInstance().isCountryClicked()) {

                    GameStatus.getInstance().setCountryClicked(true);

                    statusPane.getChildren().set(1, countryStatus.getCountryStatusPane());
                }
            });

            double x = ((double) country.getCoordinator().getX() / width) * gameMap.getFitWidth() * 0.68;

            double y = ((double) country.getCoordinator().getY() / height) * gameMap.getFitHeight() * 0.8;

            AnchorPane.setLeftAnchor(button, x + 125);

            AnchorPane.setTopAnchor(button, y + 40);

            gameMapPane.getChildren().add(button);
        }
    }

    public void updatePhaseStatus() {

        phaseStatus = phaseController.getPhase();

        phaseStatus.init();

        statusPane.getChildren().clear();

        statusPane.getChildren().addAll(playerInfo, phaseStatus.getPane());

    }

    public void playerPhase() {

        phaseStatus.update();

        statusPane.getChildren().set(1, phaseStatus.getPane());
    }
}
