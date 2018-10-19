package sample.gamePage.status;

import javafx.scene.layout.AnchorPane;
import sample.gamePage.GamePage;

    /**
	 * This is an abstract class of describing Phase Status
	 * @return gamePage
	 */
public abstract class PhaseStatus {

    private static GamePage gamePage;

    public abstract AnchorPane getPane();

    public abstract void update();

    public abstract void init();

    protected GamePage getGamePage() {

        return gamePage;
    }
    /**
	 * This method is set Game Page
	 * 
	 * @param in
	 *            GamePage pg
	 */
    public void setGamePage(GamePage gp) {

        gamePage = gp;
    }

}
