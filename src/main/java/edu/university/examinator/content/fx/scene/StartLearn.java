package edu.university.examinator.content.fx.scene;


import javafx.stage.Stage;

public class StartLearn extends Scene {
    private static StartLearn instance;

    private StartLearn() {
        super("/views/startLearn.fxml");
    }

    public static synchronized StartLearn getInstance() {
        if (instance == null) {
            instance = new StartLearn();
        }
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadScene(stage);
    }
}
