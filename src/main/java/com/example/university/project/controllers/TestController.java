package com.example.university.project.controllers;

import com.example.university.project.scenes.Menu;
import com.example.university.project.scenes.Test;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


public class TestController implements Initializable, BaseController { @FXML
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
    private Text correctCountText;
    @FXML
    private Text incorrectCountText;
    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    private ProgressBar timerProgressBar;
    @FXML
    private Button startButton;  // Додаємо кнопку "Старт"
    @FXML
    private Button answerButton;

    private List<Question> questions;
    private int currentQuestionIndex;
    private int correctCount;
    private int incorrectCount;
    private double totalTimeInSeconds = 3.0;
    private Timeline timerTimeline;
    private Stage endtest;  // Додаємо поле для доступу до вікна
    private Stage stage;
    private boolean isTestRunning = false;  // Додаємо флаг, щоб слідкувати за станом тесту

    @FXML
    private Button backToMenuButton;
    @FXML
    private void switchToMenu(ActionEvent event) throws Exception {
        // Скасувати таймер тесту
        timerTimeline.stop();

        // Очистити лічильники правильних та неправильних відповідей
        correctCount = 0;
        incorrectCount = 0;

        // Очистити тексти на екрані
        questionText.setText("");
        correctCountText.setText("0");
        incorrectCountText.setText("0");

        // Перехід до меню
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Menu.getInstance().start(stage);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radioButtonList = List.of(answer1, answer2, answer3, answer4);
        for (RadioButton radioButton : radioButtonList){
            radioButton.setToggleGroup(toggleGroup);
        }

        questions = loadQuestionsFromJson("src/main/resources/data/questions.json");
        startButton.setDisable(false);

        if (!questions.isEmpty()) {
            resetTimer();
            disableQuestions();
            // Змінюємо обробник для кнопки "Старт"
            startButton.setOnAction(event -> {
                if (!isTestRunning) {
                    startTest();
                }
            });
        }
    }

    private void startTest() {
        enableQuestions();
        startButton.setDisable(true);  // Вимикаємо кнопку "Старт" після початку тесту
        isTestRunning = true;  // Встановлюємо прапорець, що тест розпочався
        showQuestionAndStartTimer(0);
        correctCount = 0;
        incorrectCount = 0;
        correctCountText.setText(String.valueOf(correctCount));
        incorrectCountText.setText(String.valueOf(incorrectCount));
    }

    private void disableQuestions() {
        for (RadioButton radioButton : radioButtonList) {
            radioButton.setDisable(true);
        }
        answerButton.setDisable(true);


    }

    private void enableQuestions() {
        for (RadioButton radioButton : radioButtonList) {
            radioButton.setDisable(false);
        }
        answerButton.setDisable(false);
    }

    private void showQuestionAndStartTimer(int questionIndex) {
        if (questionIndex < questions.size()) {
            resetTimer();
            showQuestion(questionIndex);
            startTimer();
        } else {
            System.out.println("Test completed");
            stopTest();
        }
    }
    private void stopTest() {
        timerTimeline.stop();
        enableQuestions();
        startButton.setDisable(false);  // Активуємо кнопку "Старт" після завершення тесту
        answerButton.setDisable(true);
        isTestRunning = false;  // Скидаємо прапорець, що тест закінчився

        // Виводимо результат тесту у вигляді алерту
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(endtest);
            alert.setTitle("Результат тесту");
            alert.setHeaderText(null);

            String resultMessage = String.format("Правильних відповідей: %d\nНеправильних відповідей: %d", correctCount, incorrectCount);
            alert.setContentText(resultMessage);


            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Можна додаткові дії після закриття алерту (наприклад, перезавантаження тесту або закриття вікна)
            }
        });
    }

    private void resetTimer() {
        if (timerTimeline != null) {
            timerTimeline.stop();
        }

        timerProgressBar.setProgress(1.0);
        initializeTimer();
    }

    private void initializeTimer() {
        timerTimeline = new Timeline(
                new KeyFrame(Duration.seconds(totalTimeInSeconds), new KeyValue(timerProgressBar.progressProperty(), 0))
        );

        timerTimeline.setOnFinished(event -> handleTimerFinish());
        timerTimeline.setCycleCount(1);
    }

    private void startTimer() {
        if (timerTimeline.getStatus() != Animation.Status.RUNNING) {
            timerTimeline.playFromStart();
        }
    }

    private void handleTimerFinish() {
        System.out.println("Time's up!");
        showQuestionAndStartTimer(currentQuestionIndex + 1);
    }

    private void showQuestion(int questionIndex) {
        Question currentQuestion = questions.get(questionIndex);
        questionText.setText(currentQuestion.getText());

        List<String> options = currentQuestion.getOptions();
        for (int i = 0; i < radioButtonList.size() && i < options.size(); i++) {
            radioButtonList.get(i).setText(options.get(i));
        }

        toggleGroup.selectToggle(null);
        currentQuestionIndex = questionIndex;
    }

    @FXML
    private void handleButtonClick() {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

        if (isTestRunning) {
        if (selectedRadioButton != null) {
            String userAnswer = selectedRadioButton.getText();

            Question currentQuestion = questions.get(currentQuestionIndex);
            if (currentQuestion.isCorrectAnswer(userAnswer)) {
                correctCount++;
            } else {
                incorrectCount++;
            }

            updateCounters();
            showQuestionAndStartTimer(currentQuestionIndex + 1);
        } else {
            System.out.println("No answer selected");
        }
        } else {
            System.out.println("Тест ще не розпочатий");
        }
    }

    private void updateCounters() {
        correctCountText.setText(String.valueOf(correctCount));
        incorrectCountText.setText(String.valueOf(incorrectCount));
    }

    private List<Question> loadQuestionsFromJson(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<Question>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

