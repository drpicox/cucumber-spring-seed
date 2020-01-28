package com.drpicox.ddd;

public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }
}
