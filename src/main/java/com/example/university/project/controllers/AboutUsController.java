package com.example.university.project.controllers;

import com.example.university.project.scenes.Menu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
/**
 * Controller class for the "About Us" scene.
 */
public class AboutUsController {
    /**
     * Navigates back to the main menu.
     *
     * @param event The event triggered by the user.
     * @throws Exception If an error occurs while switching scenes.
     */
    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    }
    /**
     * Exits the application.
     */
    @FXML
    private void exit() {
        Platform.exit();
    }
}
