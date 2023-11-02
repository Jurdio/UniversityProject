package com.example.university.project.controllers;

import com.example.university.project.scenes.AboutUs;
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
    /**
     * Switches to the "About Us" scene.
     *
     * @param event The event triggered by the user.
     * @throws Exception If an error occurs while switching scenes.
     */
    @FXML
    public void switchToAboutUs(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new AboutUs().start(stage);
    }
    /**
     * Switches to the "Test" scene. (Not implemented)
     *
     * @param event The event triggered by the user.
     * @throws Exception If an error occurs while switching scenes.
     */
    @FXML
    public void switchToTest(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new Test().start(stage);
    }
    /**
     * Switches to the "Start Learn" scene.
     *
     * @param event The event triggered by the user.
     * @throws Exception If an error occurs while switching scenes.
     */
    @FXML
    public void switchToStartLearn(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new StartLearn().start(stage);
    }
    /**
     * Exits the application.
     */
    @FXML
    private void exit() {
        Platform.exit();
    }
}