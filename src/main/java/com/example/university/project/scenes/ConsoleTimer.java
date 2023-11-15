package com.example.university.project.scenes;

import java.util.Timer;
import java.util.TimerTask;

public class ConsoleTimer {
    private int totalSeconds = 120;
    private Timer timer;

    public int getTotalSeconds() {
        return totalSeconds;
    }

    public void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (totalSeconds > 0) {
                    totalSeconds--;
                    updateTimerText();
                } else {
                    handleTimerFinish();
                }
            }
        }, 0, 1000);  // Update every second
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void updateTimerText() {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        System.out.printf(":%02d:%02d%n", minutes, seconds);
    }

    private void handleTimerFinish() {
        System.out.println("Time's up!");
        stopTimer();
    }
}
