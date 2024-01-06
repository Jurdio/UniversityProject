package edu.university.examinator.content.fx.scene;

import javafx.stage.Stage;

public class Test extends Scene {
    private static Test instance;

    private Test() {
        super("/views/test.fxml");
    }

    // Повернути єдиний екземпляр класу
    public static synchronized Test getInstance() {
        if (instance == null) {
            instance = new Test();
        }
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadScene(stage);
    }
}
