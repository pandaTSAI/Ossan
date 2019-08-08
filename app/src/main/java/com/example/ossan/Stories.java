package com.example.ossan;

import java.io.Serializable;

public class Stories implements Serializable {
    private int imageId;
    private String name,quote;

    public Stories(int imageId, String name, String quote) {
        this.imageId = imageId;
        this.name = name;
        this.quote = quote;
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
}
