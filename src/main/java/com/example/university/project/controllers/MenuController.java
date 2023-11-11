package com.example.university.project.controllers;

import com.example.university.project.scenes.AboutUs;
import com.example.university.project.scenes.Menu;
import com.example.university.project.scenes.StartLearn;
import com.example.university.project.scenes.Test;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
/**
 * Controller class for the main menu.
 */
public class MenuController {
    @FXML
    public void switchToAboutUs(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    }

    @FXML
    public void switchToTest(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Test.getInstance().start(stage);
    }
    /**
     * Exits the application.
     */
    @FXML
    private void exit() {
        Platform.exit();
    }

}