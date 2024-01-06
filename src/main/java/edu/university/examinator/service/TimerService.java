package edu.university.examinator.service;

import edu.university.examinator.domain.ConsoleTimer;
import edu.university.examinator.domain.ProgressLine;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class TimerService {
    private final ConsoleTimer consoleTimer;
    private final ProgressLine progressLine;
    private final Text timerText;
    public TimerService(Text timerText, ProgressBar progressBar){
        consoleTimer = new ConsoleTimer(this);
        progressLine = new ProgressLine(progressBar);
        this.timerText = timerText;
        progressLine.initializeTimeline(consoleTimer.getTotalSeconds());
    }
    public void startTestTime(){
        consoleTimer.startTimer();
        progressLine.startTimeline(consoleTimer.getTotalMilliseconds());
    }
    public void stopTestTime(){
        consoleTimer.stopTimer();
        progressLine.stopTimeline();
    }
    public void resetTestTime(){
        consoleTimer.stopTimer();
        consoleTimer.resetTimer();
        progressLine.resetTimeline();
    }
    private String timeFormatter(int milliseconds){
        int minutes = milliseconds / (1000 * 60);
        int seconds = (milliseconds / 1000) % 60;
        milliseconds = milliseconds % 1000 / 10;

        // Форматування часу у вигляді "00:01:28"
        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
    }
    public void setTimelineFinishedHandler(Runnable handler) {
        progressLine.setTimelineFinishedHandler(handler);
    }
    public void onTimeChanged(int milliseconds) {
        Platform.runLater(()-> timerText.setText(timeFormatter(milliseconds)));
    }
}
