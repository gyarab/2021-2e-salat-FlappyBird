package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class StageController implements Initializable {
    Thread gameThread = null;
    boolean gamestat = true;

    @FXML
    ImageView bird;
    @FXML
    ImageView rTop;
    @FXML
    ImageView rDown;
    @FXML
    ImageView rTop2;
    @FXML
    ImageView rDown2;
    @FXML
    Pane pane;
    @FXML
    Label score;


    public void onClick() {
        Jump.jump();
        score.setText("Score: " + GlobalVar.score);
    }


    public void initialize(URL location, ResourceBundle resource) {
        try {
            Run i = new Run(bird, gamestat, rTop, rDown, rTop2, rDown2, pane);
            gameThread = i.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
