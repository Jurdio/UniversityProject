package edu.university.examinator.loader;

import edu.university.examinator.domain.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class QuestionsLoader {
    private final List<Question> questionList;
    public QuestionsLoader(){
        questionList = loadQuestionsFromJson();
    }
    private List<Question> loadQuestionsFromJson(){
        try (InputStream inputStream = QuestionsLoader.class.getResourceAsStream("/data/questions.json");
             InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(inputStream))) {

            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<Question>>() {}.getType());

        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
    public List<Question> getQuestionList(){
        Collections.shuffle(questionList);

        for (Question question : questionList){
            Collections.shuffle(question.getOptions());
        }

        return questionList;
    }
}
