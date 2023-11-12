package com.example.university.project.controllers;

import com.example.university.project.scenes.Menu;
import com.example.university.project.scenes.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import com.google.gson.Gson;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.FileReader;
import java.net.URL;
import java.util.List;
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
                    URL resourceUrl = getClass().getResource(subtopic.getContent());
                    webView.getEngine().load(resourceUrl.toExternalForm());
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
