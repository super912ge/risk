package sample.gamePage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
import sample.model.Coordinator;
import sample.model.Country;
import sample.model.GameMap;
import sample.model.Player;
import sample.utils.ColorUtil;
import sample.utils.LoadUtil;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

	    BufferedImage readImage ;

	    try {

	    	readImage = ImageIO.read(GameMap.getInstance ().getImage ());

		     int h = readImage.getHeight();

		     int w = readImage.getWidth();

		     map.setCoordinator (new Coordinator (w,h));

	    } catch (Exception e) {

	    	Alert alert = new Alert (Alert.AlertType.ERROR);

	    	alert.setContentText (e.getMessage ());

		    alert.show ();
	    }

        int width = map.getCoordinator ().getX();

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

                    	Alert alert = new Alert (Alert.AlertType.ERROR);

                    	alert.setTitle (e.getMessage ());

                    	alert.show ();
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

	        Image image = new Image (map.getImage ().toURI ().toURL ().toExternalForm ());

	        gameMap.setImage (image);

	        gameMap.setFitWidth ((map.getCoordinator ().getX ()*gameMap.getFitHeight ()/map.getCoordinator ().getY ()));

            double x = ((double) country.getCoordinator().getX() / width) * gameMap.getFitWidth() ;

            double y = ((double) country.getCoordinator().getY() / height) * gameMap.getFitHeight();

            AnchorPane.setLeftAnchor(button, x );

            AnchorPane.setTopAnchor(button, y );

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
