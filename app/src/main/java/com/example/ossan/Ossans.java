package com.example.ossan;

import java.io.Serializable;
import java.sql.Blob;

public class Ossans implements Serializable {
    private int imageId;
    private String name,quote,price,story;

    public Ossans(int imageId, String name, String quote, String price,String story) {
        this.imageId = imageId;
        this.name = name;
        this.quote = quote;
        this.price = price;
        this.story = story;
    }
    public Ossans(){

    }

    public Ossans(int imageId, String name,String price){
        this.imageId = imageId;
        this.name = name;
        this.price = price;

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
