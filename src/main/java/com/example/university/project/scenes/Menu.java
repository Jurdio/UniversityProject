package com.example.university.project.scenes;

import javafx.stage.Stage;

public class Menu extends Scene {
    private static Menu instance;
    private Menu() {
        super("/views/menu.fxml");
    }
    @Override
    public void start(Stage stage) throws Exception {
        loadScene(stage);
    }
    public static synchronized Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }
}
