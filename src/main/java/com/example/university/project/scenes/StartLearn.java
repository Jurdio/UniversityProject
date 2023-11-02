package com.example.university.project.scenes;

import javafx.stage.Stage;

public class StartLearn extends Scene{
    public StartLearn(){
        path = "/views/startLearn.fxml";
    }
    @Override
    public void start(Stage stage) throws Exception {
        loadScene(path,stage);
    }
}
