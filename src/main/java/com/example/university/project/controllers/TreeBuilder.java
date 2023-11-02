package com.example.university.project.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

public class TreeBuilder {
    public static TreeItem<String> buildTree() {
        File jsonFile = new File("src/main/resources/data.json");
        Gson gson = new Gson();
        TreeItem<String> rootItem = new TreeItem<>();

        try (FileReader fileReader = new FileReader(jsonFile)) {
            Type type = new TypeToken<List<Topic>>() {}.getType();

            List<Topic> topics = gson.fromJson(fileReader, type);

            for (Topic topic : topics) {
                TreeItem<String> treeItem = new TreeItem<>(topic.getName().toString());
                System.out.println("Topic: " + topic.getName());
                for (Subtopic subtopic : topic.getSubtopics()) {
                    treeItem.getChildren().add(new TreeItem<>(subtopic.getName().toString()));
                    System.out.println("  Subtopic: " + subtopic.getName());
                }
                rootItem.getChildren().add(treeItem);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rootItem;
    }
}
