package com.example.application.data;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;

@Entity
@Table(name = "bookmarks")
public class SavedData extends  AbstractEntity{
    private String title;
    private String url;

    @ManyToOne
    private User user;

    // Constructors, getters, and setters...

    public SavedData() {
        // Default constructor
    }

    public SavedData(String title, String url) {
        this.title = title;
        this.url = url;
    }

    // Getters and setters for title and url

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
