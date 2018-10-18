package sample.gamePage.status.countryStatus;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.GameStatus;
import sample.gamePage.GamePage;
import sample.model.Country;
import sample.model.Player;
import sample.utils.GameUtil;
import sample.utils.NumberTextField;

import java.util.stream.Collectors;

public class CountryStatus {

    @FXML
    private SplitPane countryStatusPane;

    @FXML
    private Text country;

    @FXML
    private Text owner;

    @FXML
    private Text continent;

    @FXML
    private Text adjacent;

    @FXML
    private Button place;

    @FXML
    private Text army;

    @FXML
    private AnchorPane playerPane;

    private GamePage gamePage;

    private Country selectedCountry;

    private NumberTextField numberTextField;


    public void placeArmy(){

        if (numberTextField.isOutRange()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setContentText( numberTextField.getNumber() +" is invalid, please enter number between " + numberTextField.getRange());

            alert.show();
        }

        else {

            GameUtil.tempDistributeArmy(selectedCountry, numberTextField.getNumber());

            GameStatus.getInstance().getCurrentPlayer().setSpentArmy(GameStatus.getInstance().getCurrentPlayer().getSpentArmy()
                    + numberTextField.getNumber());

            updateArmy();

            numberTextField.setRange(0, GameStatus.getInstance().getCurrentPlayer().getArmy() -
                    GameStatus.getInstance().getCurrentPlayer().getSpentArmy());

            GameStatus.getInstance().setCountryClicked(false);

            gamePage.updatePlayer();

            gamePage.playerPhase();
        }
    }

    public void setGamePage(GamePage gamePage) {

        this.gamePage = gamePage;
    }

    private void update(){

        Player current = GameStatus.getInstance().getCurrentPlayer();

        if(numberTextField == null) {

            numberTextField = new NumberTextField();

            AnchorPane.setTopAnchor(numberTextField, 70.0);

            AnchorPane.setLeftAnchor(numberTextField, 106.0);

            playerPane.getChildren().add(numberTextField);
        }

        numberTextField.setRange(0,GameStatus.getInstance().getCurrentPlayer().getArmy()-
                GameStatus.getInstance().getCurrentPlayer().getSpentArmy());

        numberTextField.setDisable(!selectedCountry.getPlayer().equals(current)||current.getArmy()==current.getSpentArmy());

        this.country.setText(selectedCountry.getName());

        this.continent.setText(selectedCountry.getContinent().getName());

        this.owner.setText(selectedCountry.getPlayer().getId()+" "+ selectedCountry.getPlayer().getUsername());

        this.adjacent.setText(selectedCountry.getAdjacentCountry().stream().map(Country::getName).collect(Collectors.joining(" ")));

        int army = GameUtil.tempArmyDistributeMap.containsKey(selectedCountry)?
                GameUtil.tempArmyDistributeMap.get(selectedCountry):selectedCountry.getArmy();

        this.army.setText(army +"");

        this.place.setDisable(!current.equals(selectedCountry.getPlayer()));
    }

    private void updateArmy(){

        this.army.setText(GameUtil.tempArmyDistributeMap.get(selectedCountry)+"");

        try {

            this.numberTextField.setNumber(0);

        } catch (IllegalArgumentException e) {

            e.printStackTrace();
        }
    }

    public void setSelectedCountry(Country selectedCountry) {

        this.selectedCountry = selectedCountry;

        update();
    }

    public SplitPane getCountryStatusPane() {

        return countryStatusPane;
    }

    public void cancel(){

        GameStatus.getInstance().setCountryClicked(false);

        gamePage.updatePhaseStatus();
    }

}
