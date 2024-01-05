package edu.university.examinator.scene;

import javafx.stage.Stage;

public class AboutUs extends Scene {
    private static AboutUs instance;
    private AboutUs() {
        super("/views/aboutUs.fxml");
    }
    @Override
    public void start(Stage stage) throws Exception {
        loadScene(stage);
    }
    public static synchronized AboutUs getInstance() {
        if (instance == null) {
            instance = new AboutUs();
        }
        return instance;
    }


}
