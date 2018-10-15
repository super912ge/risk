package sample.gamePage.status.phaseTwo;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sample.gamePage.status.StatusPhase;

public class PhaseTwo implements StatusPhase {

    @FXML
    AnchorPane pane;


    @Override
    public AnchorPane getPane() {
        return this.pane;
    }
}
