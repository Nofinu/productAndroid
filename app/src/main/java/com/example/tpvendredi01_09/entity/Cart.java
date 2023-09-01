package com.example.tpvendredi01_09.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProducts(Product products) {
        this.products.add(products);
    }
}
