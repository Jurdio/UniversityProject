package edu.university.examinator.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class Topic {
    @SerializedName("name")
    public String name;
    private List<Subtopic> subtopics;

}

