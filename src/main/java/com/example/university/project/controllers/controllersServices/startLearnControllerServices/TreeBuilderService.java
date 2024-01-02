package com.example.university.project.controllers.controllersServices.startLearnControllerServices;


import com.example.university.project.jsonObjects.Subtopic;
import com.example.university.project.jsonObjects.Topic;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.scene.control.TreeItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TreeBuilderService {
    public static List<Topic> topics = parseJson();

    private static List<Topic> parseJson() {
        List<Topic> newList = new ArrayList<>();
        Gson gson = new Gson();

        // Використовуємо getResourceAsStream() для завантаження ресурсів з JAR-файлу
        try (InputStream inputStream = TreeBuilderService.class.getResourceAsStream("/data/data.json");
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }

            JsonArray jsonArray = JsonParser.parseString(content.toString()).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                Topic topic = gson.fromJson(jsonElement, Topic.class);
                newList.add(topic);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newList;
    }

    public TreeItem<String> buildTree() {
        TreeItem<String> rootItem = new TreeItem<>();
        for (Topic topic : topics) {
            TreeItem<String> treeItem = new TreeItem<>(topic.getName().toString());
            System.out.println("Topic: " + topic.getName());
            for (Subtopic subtopic : topic.getSubtopics()) {
                treeItem.getChildren().add(new TreeItem<>(subtopic.getName().toString()));
                System.out.println("  Subtopic: " + subtopic.getName());
                System.out.println(" Desription :" + subtopic.getContent());
            }
            rootItem.getChildren().add(treeItem);
        }
        return rootItem;
    }
}
