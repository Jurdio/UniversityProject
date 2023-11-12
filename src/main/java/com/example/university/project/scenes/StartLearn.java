package com.example.university.project.scenes;

import javafx.application.Application;
import javafx.stage.Stage;

public class StartLearn extends Scene {
    private static StartLearn instance;
    private StartLearn(){
        super("/views/startLearn.fxml");
    }
    @Override
    public void start(Stage stage) throws Exception {
        loadScene(stage);
    }
    public static synchronized StartLearn getInstance() {
        if (instance == null) {
            instance = new StartLearn();
        }
        return instance;
    }
}
