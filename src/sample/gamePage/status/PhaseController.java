package sample.gamePage.status;
import javafx.fxml.FXMLLoader;
import sample.gamePage.status.phaseOne.PhaseOne;
import sample.gamePage.status.phaseThree.PhaseThree;
import sample.gamePage.status.phaseTwo.PhaseTwo;
import java.io.IOException;
import java.security.InvalidParameterException;

public class PhaseController {

    private  PhaseOne phaseOne;

    private  PhaseTwo phaseTwo;

    private  PhaseThree phaseThree;


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


    public StatusPhase getPhase(int i){

        if (i==1) return phaseOne;

        if (i==2) return phaseTwo;

        if (i==3) return phaseThree;

        else throw new InvalidParameterException();
    }

}
