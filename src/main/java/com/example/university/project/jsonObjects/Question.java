package com.example.university.project.jsonObjects;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Question {
    private String text;
    private List<String> options;
    private String correctAnswer;
    private String pathToImage;

    public String getPathToImage() {
        return pathToImage;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        Collections.shuffle(options);
        return options;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public boolean isCorrectAnswer(String userAnswer) {
        // Перевірте, чи відповідь коректна
        return userAnswer != null && userAnswer.equals(correctAnswer);
    }

}
