package com.example.a20521566seminar10;

public class Product {
    public Product(int id, double price, String title, String image) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    private int id;
    private double price;
    private String title, image;
}
