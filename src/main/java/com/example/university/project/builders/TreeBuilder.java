package com.example.university.project.builders;

import com.example.university.project.jsonObjects.Subtopic;
import com.example.university.project.jsonObjects.Topic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class TreeBuilder {
    public static List<Topic> topics = parseJson();
    private static List<Topic> parseJson(){
        File jsonFile = new File("src/main/resources/data/data.json");
        Gson gson = new Gson();
        List<Topic> newList;

        try (FileReader fileReader = new FileReader(jsonFile)) {
            Type type = new TypeToken<List<Topic>>() {}.getType();
            newList = gson.fromJson(fileReader, type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
