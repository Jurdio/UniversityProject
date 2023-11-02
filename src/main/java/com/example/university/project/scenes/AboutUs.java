package com.example.university.project.scenes;

import javafx.stage.Stage;

public class AboutUs extends Scene {
    public AboutUs(){
        path = "/views/aboutUs.fxml";
    }
    @Override
    public void start(Stage stage) throws Exception {
        loadScene(path,stage);
    }

}
