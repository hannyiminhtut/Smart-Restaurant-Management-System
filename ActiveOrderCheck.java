package com.example.firstproject;

public class ActiveOrderCheck {
    private int id;
    private String name;
    private int price,count,total;

    public ActiveOrderCheck(int id, String name, int price, int count, int total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.total = total;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ActiveOrderCheck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", total=" + total +
                '}';
    }
}
