package com.invita.usstatsreviews.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "location")
public class Location {
    @Id
    private String id;
    @Field("zipcode")
    private String zipcode;
    @Field("reviews")
    private List<Review> reviews;

    public Location() {
    }

    public Location(String zipcode, List<Review> reviews) {
        this.zipcode = zipcode;
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
