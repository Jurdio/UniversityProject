package com.example.university.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTree();
    }
    private void initializeTree(){
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
        for (Topic topic : TreeBuilder.topics){
            for (Subtopic subtopic : topic.getSubtopics()){
                if (subtopic.getName().equals(selectedText)){
                    System.out.println(subtopic.getContent());
                }
            }
        }
        // Додайте код для виведення тексту відповіgetDescriptionдно до вашого інтерфейсу
    }




}
