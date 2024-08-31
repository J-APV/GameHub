package com.example.application.data;

import com.example.application.data.User;
import com.example.application.views.discounts.Deal;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bookmark extends AbstractEntity {

    private String title;
    private String url;

    private String thumb;

    private String salePrice;

    private String normalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors, getters, setters...

    public Bookmark() {
        // Default constructor
    }

    public Bookmark(Deal deal) {
        this.title = deal.getTitle();
        this.url = deal.getLink();
        this.thumb = deal.getThumb();
        this.salePrice = deal.getSalePrice();
        this.normalPrice = deal.getNormalPrice();
    }

    // Getters and setters for title and url

    public String getTitle(){
        return title;
    }

    public String getUrl(){
        return this.url;
    }

    public String getThumb(){
        return thumb;
    }

    public String getSalePrice(){
        return this.salePrice;
    }

    public String getNormalPrice(){
        return this.normalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}