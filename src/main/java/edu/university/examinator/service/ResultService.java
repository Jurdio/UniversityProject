package edu.university.examinator.service;

import edu.university.examinator.domain.Question;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class ResultService {
    private final Text correctCountText;
    private final Text incorrectCountText;
    private int correctCount;
    private int incorrectCount;

    public ResultService(Text correctCountText, Text incorrectCountText) {
        this.correctCountText = correctCountText;
        this.incorrectCountText = incorrectCountText;
    }

    private void updateCorrectCount() {
        correctCount++;
        correctCountText.setText(String.valueOf(correctCount));
    }

    private void updateIncorrectCount() {
        incorrectCount++;
        incorrectCountText.setText(String.valueOf(incorrectCount));
    }

    public void validateAnswer(ToggleGroup toggleGroup, Question question) {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

        if (selectedRadioButton != null) {
            String userAnswer = selectedRadioButton.getText();

            if (question.isCorrectAnswer(userAnswer)) {
                updateCorrectCount();
            } else {
                updateIncorrectCount();
            }

        } else {
            updateIncorrectCount();
        }
    }

}
