package com.example.university.project.controllers;

import com.example.university.project.builders.TreeBuilder;
import com.example.university.project.jsonObjects.Subtopic;
import com.example.university.project.jsonObjects.Topic;
import com.example.university.project.scenes.Menu;
import com.example.university.project.scenes.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

public class StartLearnController implements Initializable {
    @FXML
    private TreeView<String> treeView;
    @FXML
    private WebView webView;
      @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTree();
    }

    private void initializeTree() {
        treeView.setRoot(new TreeItem<>("Теми для вивчення"));
        TreeItem<String> treeItem = new TreeBuilder().buildTree();
        treeView.getRoot().getChildren().addAll(treeItem.getChildren());
        setupTreeViewSelectionHandler();
    }

    private void setupTreeViewSelectionHandler() {
        // Додаємо обробник подій для елементів TreeView
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleTreeViewSelection(newValue);
            }
        });
    }

    private void handleTreeViewSelection(TreeItem<String> selectedTreeItem) {
        // Код обробки вибору елементу TreeView
        String selectedText = selectedTreeItem.getValue();
        System.out.println("Selected: " + selectedText);

        for (Topic topic : TreeBuilder.topics) {
            for (Subtopic subtopic : topic.getSubtopics()) {
                if (subtopic.getName().equals(selectedText)) {
                    System.out.println(subtopic.getContent());

                    // Використовуйте try-with-resources для автоматичного закриття потоків
                    try (InputStream inputStream = getClass().getResourceAsStream(subtopic.getContent());
                         InputStreamReader reader = new InputStreamReader(inputStream);
                         BufferedReader bufferedReader = new BufferedReader(reader)) {

                        // Читайте вміст файлу рядок за рядком
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            content.append(line).append(System.lineSeparator());
                        }

                        // Встановіть базовий URL для завантаження зображень та інших ресурсів
                        String baseUrl = getClass().getResource("/pages/").toExternalForm();

                        // Завантажте вміст до WebView з використанням базового URL
                        webView.getEngine().loadContent("<base href='" + baseUrl + "'>" + content.toString());


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @FXML
    private void switchToMenu(ActionEvent event) throws Exception{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Menu.getInstance().start(stage);
    }
    @FXML
    public void switchToTest(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Test.getInstance().start(stage);
    }
}
