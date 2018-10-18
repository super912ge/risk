package sample.gamePage.status;
import javafx.fxml.FXMLLoader;
import sample.gamePage.status.phaseOne.PhaseOne;
import sample.gamePage.status.phaseThree.PhaseStatusThree;
import sample.gamePage.status.phaseTwo.PhaseStatusTwo;
import java.io.IOException;
import java.security.InvalidParameterException;

/*

 Switch between different game phase by loading different fxml file and return the controller.

 */
public class PhaseController {

    private PhaseOne phaseOne;

    private PhaseStatusTwo phaseTwo;

    private PhaseStatusThree phaseThree;


    public PhaseController () throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("./phaseOne/PhaseOne.fxml"));

        loader.load();

        phaseOne = loader.getController();

        loader = new FXMLLoader(this.getClass().getResource("./phaseTwo/PhaseTwo.fxml"));

        loader.load();

        phaseTwo = loader.getController();

        loader = new FXMLLoader(this.getClass().getResource("./phaseThree/PhaseThree.fxml"));

        loader.load();

        phaseThree = loader.getController();
    }


    public PhaseStatus getPhase(int i){

        if (i==1) return phaseOne;

        if (i==2) return phaseTwo;

        if (i==3) return phaseThree;

        else throw new InvalidParameterException();
    }

}
