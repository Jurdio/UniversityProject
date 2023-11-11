package com.example.university.project.scenes;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * The main class responsible for launching the application.
 */
public class StartApp extends Application {
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        Menu.getInstance().start(stage);
    }
}
