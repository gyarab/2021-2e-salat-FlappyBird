package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Label highscore;


    public void initialize(URL location, ResourceBundle resource) {
        setHighscore();
    }


    /**
     * this method creates window of the actual game
     *
     * @param actionEvent
     * @throws IOException
     */
    public void play(ActionEvent actionEvent) throws IOException {
        if (ReadWriteXML.readInt() == -1) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("setUsername.fxml").openStream());
            stage.setScene(new Scene(root));
            stage.show();
            stage.setResizable(false);
        } else {
            setHighscore();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("StagePlay.fxml").openStream());
            stage.setScene(new Scene(root));
            stage.show();
            stage.setResizable(false);
            stage.setOnCloseRequest(event -> {
                        Run.game = false;
                        System.out.println(GlobalVar.score);
                    }
            );
        }
    }


    /**
     * this method creates window of the game which is being played by computer
     *
     * @param actionEvent
     * @throws IOException
     */
    public void bot(ActionEvent actionEvent) throws IOException {
        setHighscore();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("StageBot.fxml").openStream());
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
                    BotRun.game = false;
                    System.out.println(GlobalVar.score);
                }
        );
    }

    public void setHighscore() {
        try {
            highscore.setText("Highest Score : " + ReadWriteXML.readHighscore());
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
