package edu.university.examinator.scene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Abstract class representing a scene in the application.
 */
public abstract class Scene extends Application {
    protected String path;
    public Scene(String path) {
        this.path = path;
    }

    public Scene(StackPane root, int i, int i1) {
    }

    public void loadScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        Parent root = fxmlLoader.load();

        javafx.scene.Scene scene = new javafx.scene.Scene(root, 1000, 600);
        Font.loadFont(getClass().getResourceAsStream("/fonts/NoSignal.otf"), 16);
        stage.setResizable(false);
        stage.setTitle("Computer program");
        stage.setScene(scene);
        stage.show();
    }

}
