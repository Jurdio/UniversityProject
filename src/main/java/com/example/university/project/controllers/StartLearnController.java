package com.example.university.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import com.google.gson.Gson;

import java.io.FileReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StartLearnController implements Initializable {
    @FXML
    private TreeView<String> treeView;
    @FXML
    private TextFlow textFlow;
    @FXML
    private Pagination pagination;
    @FXML
    private StackPane stackPane;
    private static final int MAX_CHARS_PER_PAGE = 2500; // Максимальна кількість символів на сторінці
    private String currentContent = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTree();
        setupPagination();
    }

    private void initializeTree() {
        treeView.setRoot(new TreeItem<>("Теми для вивчення"));
        TreeItem<String> treeItem = new TreeBuilder().buildTree();
        treeView.getRoot().getChildren().addAll(treeItem.getChildren());
        setupTreeViewSelectionHandler();
    }
    private void setupPagination() {
        pagination.setPageFactory(pageIndex -> {
            createPage(pageIndex);
            return textFlow;
        });
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
                    currentContent = subtopic.getContent();
                    updatePagination();
                }
            }
        }
    }
    private void setTextToTextFlow(String newText) {
        if (textFlow == null) {
            textFlow = new TextFlow();
        }
        textFlow.getChildren().clear();
        textFlow.getChildren().add(new Text(newText));
    }
    private void createPage(int pageIndex) {
        int maxCharsPerPage = MAX_CHARS_PER_PAGE;
        int startIndex = pageIndex * maxCharsPerPage;
        int endIndex = Math.min(startIndex + maxCharsPerPage, currentContent.length());

        String pageText = currentContent.substring(startIndex, endIndex);

        textFlow.getChildren().clear();
        Text textNode = new Text(pageText);
        textFlow.getChildren().add(textNode);
    }
    private void updatePagination() {
        int numPages = (int) Math.ceil((double) currentContent.length() / MAX_CHARS_PER_PAGE);
        pagination.setPageCount(numPages);

        pagination.setPageFactory(pageIndex -> {
            createPage(pageIndex);
            return textFlow;
        });
    }
}
