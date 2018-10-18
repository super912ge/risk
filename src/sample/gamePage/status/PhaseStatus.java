package sample.gamePage.status;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.gamePage.GamePage;


public abstract class PhaseStatus {

    private GamePage gamePage;

    public abstract AnchorPane getPane();

    public void addPane(Node node){

        getPane().getChildren().add(node);

    }

    public abstract void update();

    public abstract void init();

    public void setGamePage(GamePage gamePage){

        this.gamePage = gamePage;
    }

    protected GamePage getGamePage(){

        return this.gamePage;
    }

}
