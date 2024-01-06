package edu.university.examinator.content.fx.scene;

import javafx.stage.Stage;

public class AboutUs extends Scene {
    private static AboutUs instance;

    private AboutUs() {
        super("/views/aboutUs.fxml");
    }

    public static synchronized AboutUs getInstance() {
        if (instance == null) {
            instance = new AboutUs();
        }
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadScene(stage);
    }


}
