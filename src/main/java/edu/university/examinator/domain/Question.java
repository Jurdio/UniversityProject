package edu.university.examinator.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Question {
    private String text;
    private List<String> options;
    private String correctAnswer;
    private String pathToImage;

    public boolean isCorrectAnswer(String userAnswer) {
        return userAnswer != null && userAnswer.equals(correctAnswer);
    }
}
