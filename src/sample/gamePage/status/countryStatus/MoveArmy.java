package sample.gamePage.status.countryStatus;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import sample.GameStatus;
import sample.model.Country;
import sample.utils.GameUtil;

public class MoveArmy {

    @FXML
    private AnchorPane moveArmy;

    @FXML
    private ChoiceBox<Country> options;

    void setFrom(Country from) {

        options.setItems(FXCollections.observableArrayList(
                GameUtil.findAllConnectedCountry(GameStatus.getInstance().getCurrentPlayer(), from)));
    }

    AnchorPane getMoveArmy() {

        return moveArmy;
    }

    Country getSelected() {

        return options.getValue();
    }
}
