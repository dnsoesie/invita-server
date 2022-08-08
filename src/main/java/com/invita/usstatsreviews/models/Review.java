package com.invita.usstatsreviews.models;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Random;

public class Review {
   @Field("id")
    private int id = new Random().nextInt(1000);
    @Field("notes")
    private String reviewNotes;

    public Review() {
    }

    public Review(int id, String reviewNotes) {
        this.id = id;
        this.reviewNotes = reviewNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReviewNotes() {
        return reviewNotes;
    }

    public void setReviewNotes(String reviewNotes) {
        this.reviewNotes = reviewNotes;
    }
}
