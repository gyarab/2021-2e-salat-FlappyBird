package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SetUsername implements Initializable {

    @FXML
    TextField name;

    @FXML
    Button but;

    public void submit(ActionEvent actionEvent) throws IOException {
        if (!name.getText().equals("")) {
            if (name.getLength() < 20) {
                ReadWriteXML.write(name.getText(), ReadWriteXML.readHighscore());
                ((Stage) but.getScene().getWindow()).close();
            }else{

            }


        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
