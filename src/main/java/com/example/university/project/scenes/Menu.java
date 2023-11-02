package com.example.university.project.scenes;

import javafx.stage.Stage;

public class Menu extends Scene {
    public Menu(){
        path = "/views/menu.fxml";
    }
    @Override
    public void start(Stage stage) throws Exception {
        loadScene(path, stage);
    }
    public static void main(String[] args) {
        launch();
    }
}
