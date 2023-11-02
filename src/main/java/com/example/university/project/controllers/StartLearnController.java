package com.example.university.project.controllers;

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
        TreeItem<String> treeItem = TreeBuilder.buildTree();
        treeView.getRoot().getChildren().addAll(treeItem.getChildren());
    }



}
