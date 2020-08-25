package com.example.foodgalaxy.Model;

public class OrderItems {
    private int Id;
    private String MenuName;
    private double price;
    private int quantity;

    public int getId() {
        return Id;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItems(int id, String menuName, int quantity, double price) {
        Id = id;
        MenuName = menuName;
        this.quantity = quantity;
        this.price = price;
    }
}
