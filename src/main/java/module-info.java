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

    exports edu.university.examinator;

    exports edu.university.examinator.scene;
    exports edu.university.examinator.controller;

    opens edu.university.examinator to javafx.graphics, javafx.fxml;

    opens edu.university.examinator.controller to javafx.fxml, com.google.gson;
    opens edu.university.examinator.scene to javafx.fxml;
    exports edu.university.examinator.domain;
    opens edu.university.examinator.domain to com.google.gson, javafx.fxml;

}
