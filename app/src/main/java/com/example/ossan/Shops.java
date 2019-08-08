package com.example.ossan;

import java.io.Serializable;

public class Shops implements Serializable {
    private int imageId;
    private String name,price;

    public Shops(int imageId,String name,String price){
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getid(String id) {
        return id;
    }
}
