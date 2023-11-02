package com.example.university.project.scenes;

import javafx.stage.Stage;

public class Test extends Scene{
    public Test(){
        path = "/views/test.fxml";
    }
    @Override
    public void start(Stage stage) throws Exception {
        loadScene(path,stage);
    }
}
