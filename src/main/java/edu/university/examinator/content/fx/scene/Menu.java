package edu.university.examinator.content.fx.scene;

import javafx.stage.Stage;

public class Menu extends Scene {
    private static Menu instance;

    private Menu() {
        super("/views/menu.fxml");
    }

    public static synchronized Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadScene(stage);
    }
}
