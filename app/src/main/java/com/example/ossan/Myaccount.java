package com.example.ossan;

import java.io.Serializable;

public class Myaccount implements Serializable {
    private int imageId;
    private String name;

    public Myaccount(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;

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
}
