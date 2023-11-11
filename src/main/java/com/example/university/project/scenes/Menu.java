package com.example.university.project.scenes;

import com.example.university.project.controllers.MenuController;
import com.example.university.project.controllers.TestController;
import javafx.fxml.FXMLLoader;
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
