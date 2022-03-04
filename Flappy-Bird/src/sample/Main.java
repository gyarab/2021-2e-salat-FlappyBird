package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.beans.EventHandler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Flappy bird");
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add(getClass().getResource("MenuStylesheet.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
                    Platform.exit();
                }
        );

    }

    public static void main(String[] args) {
        launch(args);
    }
}
