package com.example.university.project.scenes;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountdownTimerWithProgressBarApp extends Application {

    private int seconds = 100;
    private Timeline timeline;
    private ProgressBar progressBar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label timerLabel = new Label("Time: " + seconds + " seconds");
        progressBar = new ProgressBar(1.0);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                seconds--;
                if (seconds >= 0) {
                    timerLabel.setText("Time: " + seconds + " seconds");
                    double progress = (double) seconds / 100.0; // обчислюємо прогрес відносно початкового часу
                    progressBar.setProgress(progress);
                } else {
                    timeline.stop();
                    timerLabel.setText("Time's up!");
                }
            }
        }));
        timeline.setCycleCount(seconds + 1);

        timeline.play();

        VBox root = new VBox(10);
        root.getChildren().addAll(timerLabel, progressBar);

        Scene scene = new Scene(root, 200, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Countdown Timer with ProgressBar");
        primaryStage.show();
    }

    @Override
    public void stop() {
        timeline.stop();
    }
}
