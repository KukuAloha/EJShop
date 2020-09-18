package com.example.ejshop.model;

public class Product {
    private String name;
    private String img;
    private String description;

    public Product() {
    }

    public Product(String name, String img, String description) {
        this.name = name;
        this.img = img;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
