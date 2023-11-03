package com.example.university.project.scenes;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class PaginationExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Приклад тексту, який ви хочете вивести
        String fullText = "У цьому курсі ви вивчете основи комп'ютерів, суперкомп'ютерів і т.д. Текст трошки довший, але давайте представимо, що це ваш повний текст.";

        // Створюємо TextFlow
        TextFlow textFlow = new TextFlow();
        textFlow.setPrefWidth(400);

        // Розділяємо текст на сторінки
        String[] pages = paginateText(fullText, 1000); // 100 - максимальна кількість символів на сторінці

        // Створюємо Pagination та додаємо обробник подій
        Pagination pagination = new Pagination(pages.length);
        pagination.setPageFactory(pageIndex -> {
            textFlow.getChildren().clear();
            textFlow.getChildren().add(new Text(pages[pageIndex]));
            return new StackPane(textFlow);
        });

        VBox root = new VBox(10, pagination, textFlow);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Pagination Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Функція для розбиття тексту на сторінки
    private String[] paginateText(String text, int maxCharsPerPage) {
        String[] words = text.split("\\s+");
        StringBuilder currentPage = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (currentPage.length() + word.length() <= maxCharsPerPage) {
                currentPage.append(word).append(" ");
            } else {
                result.append(currentPage.toString().trim()).append("\n");
                currentPage = new StringBuilder(word + " ");
            }
        }

        if (currentPage.length() > 0) {
            result.append(currentPage.toString().trim());
        }

        return result.toString().split("\n");
    }
}
