package com.example.tpvendredi01_09.entity;

import java.io.Serializable;
import java.nio.channels.FileLock;

public class Product implements Serializable {
    private int id;
    private String title;
    private String description;
    private Integer quantity;
    private Float price;

    public Product(int id, String title, String description, Integer quantity, Float price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
