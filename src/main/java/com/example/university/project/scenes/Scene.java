package com.example.university.project.scenes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Abstract class representing a scene in the application.
 */
public abstract class Scene extends Application {
    protected static String path;
    /**
     * Loads the specified FXML scene into the given stage.
     *
     * @param fxmlPath The path to the FXML file.
     * @param stage    The stage in which the scene should be loaded.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public void loadScene(String fxmlPath, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        javafx.scene.Scene scene = new javafx.scene.Scene(root, 1000 , 600);
        Font.loadFont(getClass().getResourceAsStream("/fonts/NoSignal.otf"),16);
        stage.setResizable(false);
        stage.setTitle("Computer program");
        stage.setScene(scene);
        stage.show();
    }
}
