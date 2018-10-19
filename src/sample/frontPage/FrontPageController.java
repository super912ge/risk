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
import sample.Main;
import sample.utils.LoadUtil;

import java.io.File;
import java.io.IOException;

public class FrontPageController {

    private Scene game;

    private Main main;

    public void setGame(Scene scene) {

        this.game = scene;
    }

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

            openGameScene(event);
        }
    }

    private void openGameScene(ActionEvent actionEvent) throws IOException {

        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        main.getGamePageController().renderMap();

        primaryStage.setScene(game);

    }

    public void loadProgress() {


    }

    public void exit(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.close();

    }

    public void setMain(Main main) {
        this.main = main;
    }
}
