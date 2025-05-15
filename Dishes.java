package com.example.firstproject;

public class Dishes {
    private int id;
    private String name;
    private int price;
    private boolean enable;

    public Dishes(int id, String name, int price, boolean enable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", enable=" + enable +
                '}';
    }
}
