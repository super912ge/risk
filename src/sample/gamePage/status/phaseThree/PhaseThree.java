package sample.gamePage.status.phaseThree;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sample.gamePage.status.StatusPhase;

public class PhaseThree implements StatusPhase {

    @FXML
    private AnchorPane pane;


    @Override
    public AnchorPane getPane() {
        return this.pane;
    }
}
