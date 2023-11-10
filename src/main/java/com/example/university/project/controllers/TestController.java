package com.example.university.project.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

public class TestController implements Initializable {

    @FXML
    private RadioButton answer1;
    @FXML
    private RadioButton answer2;
    @FXML
    private RadioButton answer3;
    @FXML
    private RadioButton answer4;
    private List<RadioButton> radioButtonList;
    @FXML
    private Text questionText;
    @FXML
    private Text correctCountText;  // Для відображення лічильника правильних відповідей
    @FXML
    private Text incorrectCountText;  // Для відображення лічильника неправильних відповідей
    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup();
    private List<Question> questions;
    private int currentQuestionIndex;
    private int correctCount;
    private int incorrectCount;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        radioButtonList = List.of(answer1,answer2,answer3,answer4);
        // Assign the toggle group to each radio button
        answer1.setToggleGroup(toggleGroup);
        answer2.setToggleGroup(toggleGroup);
        answer3.setToggleGroup(toggleGroup);
        answer4.setToggleGroup(toggleGroup);
        answer1.setSelected(true);

        // If needed, you can also set the default selected radio button
        // For example, answer1.setSelected(true);
        // Зчитування питань з JSON-файлу
        // Зчитування питань з JSON-файлу
        questions = loadQuestionsFromJson("src/main/resources/data/questions.json");


        // Встановлення тексту першого питання
        if (!questions.isEmpty()) {
            showQuestion(0);
        }
    }

    // Оновлює інтерфейс згідно індексу питання
    private void showQuestion(int questionIndex) {
        Question currentQuestion = questions.get(questionIndex);
        questionText.setText(currentQuestion.getText());

        List<String> options = currentQuestion.getOptions();
        for (int i = 0; i < radioButtonList.size() && i < options.size(); i++) {
            radioButtonList.get(i).setText(options.get(i));
        }

        // Позначаємо, що жоден RadioButton не вибрано
        toggleGroup.selectToggle(null);

        // Зберігаємо індекс поточного питання
        currentQuestionIndex = questionIndex;
    }

    // You can add an event handler for the button click
    @FXML
    private void handleButtonClick() {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

        if (selectedRadioButton != null) {
            String userAnswer = selectedRadioButton.getText();
            // Перевіряємо правильність відповіді
            Question currentQuestion = questions.get(currentQuestionIndex);
            if (currentQuestion.isCorrectAnswer(userAnswer)) {
                // Відповідь правильна
                correctCount++;
            } else {
                // Відповідь неправильна
                incorrectCount++;
            }

            // Оновлюємо лічильники на інтерфейсі
            updateCounters();

            // Перехід до наступного питання або завершення тесту
            if (currentQuestionIndex < questions.size() - 1) {
                showQuestion(currentQuestionIndex + 1);
            } else {
                // Тут ви можете вставити код для завершення тесту
                System.out.println("Test completed");
            }
        } else {
            // No answer selected
            System.out.println("No answer selected");
        }
    }
    private void updateCounters() {
        // Оновлює лічильники на інтерфейсі
        correctCountText.setText(String.valueOf(correctCount));
        incorrectCountText.setText(String.valueOf(incorrectCount));
    }
    private List<Question> loadQuestionsFromJson(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            // Використовуйте Gson для розбору JSON-файлу в об'єкт
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<Question>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
