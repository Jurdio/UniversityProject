package com.example.university.project.scenes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

public class WebViewExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Отримати URL ресурсу
            URL resourceUrl = getClass().getResource("/file.html");


            // Перевірити, чи ресурс існує
            if (resourceUrl != null) {
                System.out.println("Resource URL: " + resourceUrl.toExternalForm());

                // Завантажити HTML-файл за допомогою WebView
                WebView webView = new WebView();
                webView.getEngine().load(resourceUrl.toExternalForm());

                // Додатковий код для відображення WebView в primaryStage
                StackPane root = new StackPane();
                root.getChildren().add(webView);
                Scene scene = new Scene(root, 800, 600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } else {
                System.err.println("Resource not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
