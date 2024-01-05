package edu.university.examinator.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Topic {
    @SerializedName("name")
    public String name;
    private List<Subtopic> subtopics;

    public String getName() {
        return name;
    }

    public List<Subtopic> getSubtopics() {
        return subtopics;
    }
}

