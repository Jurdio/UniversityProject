package com.example.university.project.controllers;

import com.example.university.project.jsonObjects.Question;
import com.example.university.project.scenes.Menu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.animation.KeyValue;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
public class TestController implements Initializable {
    @FXML
    private RadioButton answer1;
    @FXML
    private RadioButton answer2;
    @FXML
    private RadioButton answer3;
    @FXML
    private RadioButton answer4;
    @FXML
    private ImageView testImage;
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
    @FXML
    private Text timerText;
    private Timeline timerTimeline;
    private Stage endtest;  // Додаємо поле для доступу до вікна
    private Stage stage;
    private int nextQuestionIndex; // Додайте це поле класу для збереження індексу наступного питання
    private boolean isTestRunning = false;  // Додаємо флаг, щоб слідкувати за станом тесту
    private ConsoleTimer consoleTimer = new ConsoleTimer();
    @FXML
    private void switchToMenu(ActionEvent event) throws Exception {
        // Скасувати таймер тесту
        timerTimeline.stop();
        consoleTimer.stopTimer();

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
        questionText.setWrappingWidth(600);
        radioButtonList = List.of(answer1, answer2, answer3, answer4);
        for (RadioButton radioButton : radioButtonList){
            radioButton.setToggleGroup(toggleGroup);
        }

        questions = loadQuestionsFromJson("/data/questions.json");
        startButton.setDisable(false);

        if (!questions.isEmpty()) {
            resetTimer(0);
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
            resetTimer(questionIndex + 1);
            showQuestion(questionIndex);
            startTimer();
        } else {
            System.out.println("Test completed");
            stopTest();
        }
    }
    private void stopTest() {
        consoleTimer.stopTimer();
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
    private void resetTimer(int nextQuestionIndex) {
        if (timerTimeline != null) {
            timerTimeline.stop();
            consoleTimer.stopTimer();
        }
        timerProgressBar.setProgress(1.0);
        this.nextQuestionIndex = nextQuestionIndex; // Зберегти індекс наступного питання
        initializeTimer();
    }
    private void initializeTimer() {
        consoleTimer = new ConsoleTimer();
        timerTimeline = new Timeline(
                new KeyFrame(Duration.seconds(consoleTimer.getTotalSeconds()), new KeyValue(timerProgressBar.progressProperty(), 0))
        );
        timerTimeline.setOnFinished(event -> handleTimerFinish());
        timerTimeline.setCycleCount(1);
    }
    private void startTimer() {
        timerProgressBar.getStyleClass().clear(); // Очистити всі класи стилів
        timerProgressBar.getStyleClass().add("progress-bar"); // Додати клас за замовчуванням
        if (timerTimeline.getStatus() != Animation.Status.RUNNING) {
            timerTimeline.playFromStart();
        }
        consoleTimer.startTimer();

        // Встановлення псевдокласу "urgent" для прогрес-бара, коли залишається 10% часу або менше
        timerTimeline.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            double remainingTime = (consoleTimer.getTotalMilliseconds() - newValue.toMillis()) / consoleTimer.getTotalMilliseconds();
            if (remainingTime <= 0.1) {
                timerProgressBar.getStyleClass().add("urgent");
            }
        });
    }
    // Оновлений метод handleTimerFinish, який використовує nextQuestionIndex
    private void handleTimerFinish() {
        System.out.println("Час на питання вийшов!");
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
            } else {
                System.out.println("Відповідь не вибрана, зараховане як не правильне!");
                incorrectCount++;
            }
        }
        updateCounters();
        showQuestionAndStartTimer(nextQuestionIndex);
        timerProgressBar.getStyleClass().clear(); // Очистити всі класи стилів
        timerProgressBar.getStyleClass().add("progress-bar"); // Додати клас за замовчуванням
    }
    private void showQuestion(int questionIndex) {
        Question currentQuestion = questions.get(questionIndex);
        questionText.setText(currentQuestion.getText());
        Image image = new Image(getClass().getResourceAsStream(currentQuestion.getPathToImage()));
        Platform.runLater(() -> {
            testImage.setImage(image);
        });
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
        try (InputStream inputStream = getClass().getResourceAsStream(filePath);
             InputStreamReader reader = new InputStreamReader(inputStream)) {

            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<Question>>() {}.getType());

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    public class ConsoleTimer {
        private int totalMilliseconds = 5000;  // Загальний час в мілісекундах
        private Timer timer;

        public int getTotalMilliseconds() {
            return totalMilliseconds;
        }
        public int getTotalSeconds(){
            return (totalMilliseconds / 1000) % 60;
        }

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

        private void updateTimerText() {
            int minutes = totalMilliseconds / (1000 * 60);
            int seconds = (totalMilliseconds / 1000) % 60;
            int milliseconds = totalMilliseconds % 1000;

            // Форматування часу у вигляді "00:01:28"
            String formattedTime = String.format("%02d:%02d:%02d", minutes, seconds, milliseconds / 10);
            System.out.println(formattedTime);

            Platform.runLater(() -> {
                // Оновлення інтерфейсу з потока JavaFX Application Thread
                timerText.setText(formattedTime);
            });
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
    }
}

