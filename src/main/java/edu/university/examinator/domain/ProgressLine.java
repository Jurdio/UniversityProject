package edu.university.examinator.domain;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class ProgressLine {
    private final ProgressBar progressBar;
    private Timeline timerTimeline;
    private Runnable timelineFinishedHandler;

    public ProgressLine(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void initializeTimeline(int seconds) {
        timerTimeline = new Timeline(
                new KeyFrame(Duration.seconds(seconds), new KeyValue(progressBar.progressProperty(), 0))
        );

        timerTimeline.setOnFinished(event -> timelineFinishHandler());
        timerTimeline.setCycleCount(1);
    }

    public void startTimeline(int milliseconds) {
        if (timerTimeline.getStatus() != Animation.Status.RUNNING) {
            timerTimeline.playFromStart();
        }

        timerTimeline.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            double elapsedTime = newValue.toMillis();
            double progressPercentage = elapsedTime / milliseconds;

            // Застосувати стиль "urgent" коли залишилось 30% часу
            if (progressPercentage >= 0.7) {
                progressBar.getStyleClass().add("urgent");
            }
        });
    }

    public void stopTimeline() {
        timerTimeline.stop();
    }

    public void resetTimeline() {
        if (timerTimeline != null) {
            stopTimeline();
            progressBar.setProgress(1);
            progressBar.getStyleClass().clear();
            progressBar.getStyleClass().add("progress-bar");
        }
    }

    public void setTimelineFinishedHandler(Runnable handler) {
        this.timelineFinishedHandler = handler;
    }

    public void timelineFinishHandler() {
        if (timelineFinishedHandler != null) {
            timelineFinishedHandler.run();
        }
    }
}
