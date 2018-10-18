package sample.gamePage.status;
import javafx.scene.layout.AnchorPane;
import sample.gamePage.GamePage;


public abstract class PhaseStatus {

    private static GamePage gamePage;

    public abstract AnchorPane getPane();

    public abstract void update();

    public abstract void init();

    public void setGamePage(GamePage gp){

        gamePage = gp;
    }

    protected GamePage getGamePage(){

        return gamePage;
    }

}
