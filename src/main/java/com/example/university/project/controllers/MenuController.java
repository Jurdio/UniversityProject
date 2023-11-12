package com.example.university.project.controllers;

import com.example.university.project.scenes.AboutUs;
import com.example.university.project.scenes.StartLearn;
import com.example.university.project.scenes.Test;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class MenuController {
    public Button testButton;
    public Button exitButton;

    @FXML
    public void switchToAboutUs(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AboutUs.getInstance().start(stage);
    }
    @FXML
    public void switchToTest(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Test.getInstance().start(stage);
    }
    @FXML
    public void switchToStartLearn(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        StartLearn.getInstance().start(stage);
    }
    @FXML
    private void exit() {
        Platform.exit();
    }
}