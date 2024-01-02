package com.example.university.project.controllers.controllersServices.testControllerServices;

import javafx.application.Platform;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class ConsoleTimer {
    private int totalMilliseconds = 5000;  // Загальний час в мілісекундах
    private Timer timer;

    public void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (totalMilliseconds > 0) {
                    totalMilliseconds -= 10;
                    updateTimerText();
                } else {
                    handleTimerFinish();
                }
            }
        }, 0, 10);  // Оновлення кожну мілісекунду (1 мілісекунда)
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }
    private String timeFormatter(){
        int minutes = totalMilliseconds / (1000 * 60);
        int seconds = (totalMilliseconds / 1000) % 60;
        int milliseconds = totalMilliseconds % 1000 / 10;

        // Форматування часу у вигляді "00:01:28"

        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
    }

    private void updateTimerText() {
        timeFormatter();
//        Platform.runLater(() -> {
//            // Оновлення інтерфейсу з потока JavaFX Application Thread
//            timerText.setText(formattedTime);
//        });
    }

    private void handleTimerFinish() {
        try {
            stopTimer();
            System.out.println("Таймер завершено!");
            // Деякі ваші інші дії після завершення таймера
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ConsoleTimer().startTimer();
    }

}
