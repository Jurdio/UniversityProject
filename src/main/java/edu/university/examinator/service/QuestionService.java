package edu.university.examinator.service;

import edu.university.examinator.domain.Question;
import edu.university.examinator.loader.QuestionsLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

public class QuestionService {
    private final List<Question> questionList;
    private final List<RadioButton> radioButtonList;
    private final Text questionText;
    private final ImageView testImage;
    @Getter
    private final ToggleGroup toggleGroup;
    private int index;

    public QuestionService(Text questionText, List<RadioButton> radioButtonList, ImageView testImage, ToggleGroup toggleGroup) {
        questionList = new QuestionsLoader().getQuestionList();
        this.questionText = questionText;
        this.radioButtonList = radioButtonList;
        this.testImage = testImage;
        this.toggleGroup = toggleGroup;
        index = 0;
    }

    public void showQuestion() {
        testImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(questionList.get(index).getPathToImage()))));
        questionText.setText(questionList.get(index).getText());

        toggleGroup.selectToggle(null);

        for (int i = 0; i < radioButtonList.size(); i++) {
            radioButtonList.get(i).setText(questionList.get(index).getOptions().get(i));
        }
    }

    public boolean hasQuestions() {
        return index < questionList.size();
    }

    public Question getQuestion() {
        return questionList.get(index);
    }

    public void resetIndex() {
        index = 0;
    }

    public void incrementIndex() {
        index++;
    }

}
