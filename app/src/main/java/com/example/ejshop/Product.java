package com.example.ejshop;

public class Product {
    private String name;
    private Long price;
    private String description;
    private String imgUrl;

    public Product() {
    }

    public Product(String name, Long price, String description, String imgUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
