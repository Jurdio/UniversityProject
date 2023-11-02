package com.example.university.project.controllers;

import com.google.gson.annotations.SerializedName;

public class Subtopic {
    @SerializedName("name")
    public String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
// Геттери, сеттери та інші методи
}
