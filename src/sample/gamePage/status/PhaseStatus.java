package sample.gamePage.status;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public abstract class PhaseStatus {

    @FXML
    private AnchorPane pane;

    public AnchorPane getPane(){

        return pane;
    }
}
