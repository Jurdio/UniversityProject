package com.example.university.project.jsonObjects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Topic {
    @SerializedName("name")
    public String name;
    private List<Subtopic> subtopics;

    // Геттери, сеттери та інші методи

    public String getName() {
        return name;
    }

    public List<Subtopic> getSubtopics() {
        return subtopics;
    }
}

