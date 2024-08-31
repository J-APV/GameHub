package com.example.application.data;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "Indie_Games")
public class IndieGame extends AbstractEntity {

    private String title;

    private String thumbnail;
    private String description;
    private String username;

    private double price;
    private String filePath;


    private ArrayList<String> tags;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getFile() {
        return filePath;
    }


    public void setFile(String file) {
        filePath = file;
    }


}

