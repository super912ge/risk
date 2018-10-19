package sample.gamePage.status;

import javafx.fxml.FXMLLoader;
import sample.GameStatus;
import sample.gamePage.status.phaseOne.PhaseOne;
import sample.gamePage.status.phaseThree.PhaseThree;
import sample.gamePage.status.phaseTwo.PhaseTwo;
import sample.gamePage.status.preGame.PreGame;

import java.io.IOException;

/*

 Switch between different game phase by loading different fxml file and return the controller.

 */
public class PhaseController {


    private PreGame preGame;

    private PhaseOne phaseOne;

    private PhaseTwo phaseTwo;

    private PhaseThree phaseThree;

/**
	 * This is a constructor of PhaseController
	 * 
	 * @throws IOException
	 */
    public PhaseController() throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("./preGame/PreGame.fxml"));

        loader.load();

        preGame = loader.getController();

        loader = new FXMLLoader(this.getClass().getResource("./phaseOne/PhaseOne.fxml"));

        loader.load();

        phaseOne = loader.getController();

        loader = new FXMLLoader(this.getClass().getResource("./phaseTwo/PhaseTwo.fxml"));

        loader.load();

        phaseTwo = loader.getController();

        loader = new FXMLLoader(this.getClass().getResource("./phaseThree/PhaseThree.fxml"));

        loader.load();

        phaseThree = loader.getController();
    }
/**
	 * This method is getPhase 
	 * 
	 * @return 
	 *            The PhaseStatus
	 */

    public PhaseStatus getPhase() {

        if (!GameStatus.getInstance().isStart()) {

            return preGame;
        }

        switch (GameStatus.getInstance().getPhase()) {

            case 1:
                return phaseOne;

            case 2:
                return phaseTwo;

            case 3:
                return phaseThree;
        }

        return null;
    }

}
