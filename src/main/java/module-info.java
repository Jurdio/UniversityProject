module com.example.univesity_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens com.example.university.project to javafx.fxml;
    exports com.example.university.project.scenes;
    exports com.example.university.project.controllers;
    opens com.example.university.project.controllers to javafx.fxml, com.google.gson;
    opens com.example.university.project.scenes to javafx.fxml;
    exports com.example.university.project.jsonObjects;
    opens com.example.university.project.jsonObjects to com.google.gson, javafx.fxml;
    exports com.example.university.project.builders;
    opens com.example.university.project.builders to com.google.gson, javafx.fxml;
}