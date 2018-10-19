package sample.frontPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.GameStatus;
import sample.Main;
import sample.utils.LoadUtil;

import java.io.File;
import java.io.IOException;
/**
 * This class is used to contral the front page.
 */
public class FrontPageController {

    private Scene game;

    private Main main;

    public void setGame(Scene scene) {

        this.game = scene;
    }

	/**
	 * This method is used to load the game map
	 * 
	 * @param in
	 *            The event ActionEvent.
	 * @throws IOException
	 */
    public void loadMap(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Select GameMap File To Load");

        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("map file", "*.map"));

        File selected = fileChooser.showOpenDialog(null);

        if (selected != null) {

            try {

                LoadUtil.readFile(selected);


            } catch (Exception e) {

                e.printStackTrace();

            }

            DialogPane setup = new DialogPane();

            setup.setHeaderText("Please select how many players from 3 to 6. ");

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("./gameSetup/gameSetup.fxml"));

            AnchorPane content = loader.load();

            setup.setContent(content);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setDialogPane(setup);

            alert.showAndWait();

            if (GameStatus.getInstance ().getPlayers()!=null&&!GameStatus.getInstance ().getPlayers ().isEmpty ()) {

	            openGameScene (event);
            }
        }
    }
	/**
	 * This method is used to open the Game Scene
	 * 
	 * @param in
	 *            The actionEvent ActionEvent.
	 * @throws IOException
	 */
    private void openGameScene(ActionEvent actionEvent) throws IOException {

        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        main.getGamePageController().renderMap();

        primaryStage.setScene(game);

    }
	/**
	 * This method is used to load the progress of the game
	 */

    public void loadProgress() {


    }
	/**
	 * This method is used to exit the Game Scene
	 * 
	 * @param in
	 *            The actionEvent ActionEvent.
	 */
    public void exit(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.close();

    }
	/**
	 * This method is to set the Main
	 * 
	 * @param in
	 *            Main main
	 */
    public void setMain(Main main) {
        this.main = main;
    }
}
